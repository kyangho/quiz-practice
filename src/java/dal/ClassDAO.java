/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.classes.Classes;

/**
 *
 * @author Vu Duc Tien
 */
public class ClassDAO extends DBContext {

    public ArrayList<Classes> getClassList() {
        ArrayList<Classes> classes = new ArrayList<>();
        String sql = "SELECT * FROM quiz_practice_db.class";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Classes c = new Classes();
                c.setClassID(rs.getInt(1));
                c.setClassName(rs.getString(2));
                c.setStatus(rs.getString(3));
                c.setNote(rs.getString(4));
                classes.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classes;
    }

    public ArrayList<Classes> getClassList(String key, int pageSize, int pageIndex) {
        ArrayList<Classes> classes = new ArrayList<>();
        String sql = "select class_id from \n"
                + "(select row_number() over (order by class_id asc) as stt, c.class_id, c.class_name, c.class_status, c.class_note, ap.account_id\n"
                + "from quiz_practice_db.class c\n"
                + "join quiz_practice_db.account_profile ap on ap.account_id = c.author\n"
                + "where (1 = 1) \n";
        if (key != null) {
            sql += "and (ap.account_fullname like '%" + key + "%' or c.class_name like '%" + key + "%')\n";
        }
        sql += ") as t\n"
                + "where t.stt >= (? - 1) * ? + 1 and t.stt <= ? * ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageIndex);
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Classes c = getClassByID(rs.getInt("class_id"));
                classes.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classes;
    }

    public int getTotalRows(String key) {
        String where = "";
        String sql = "select count(*) as total from \n"
                + "(select row_number() over (order by class_id asc) as stt, c.class_id, c.class_name, c.class_status, c.class_note, ap.account_id\n"
                + "from quiz_practice_db.class c\n"
                + "join quiz_practice_db.account_profile ap on ap.account_id = c.author\n"
                + "where (1 = 1) \n"
                + where;
        if (key != null) {
            where = "and (ap.account_fullname like '%" + key + "%' or c.class_name like '%" + key + "%'\n";
        }
        sql += ") as t\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Classes getClassByID(int classID) {
        AccountDAO adao = new AccountDAO();
        String sql = "SELECT * FROM quiz_practice_db.class c\n"
                + "where c.class_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, classID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Classes c = new Classes();
                c.setClassID(rs.getInt(1));
                c.setClassName(rs.getString(2));
                c.setStatus(rs.getString(3));
                c.setNote(rs.getString(4));
                c.setUsers(getUserListOfClass(rs.getInt(1)));
                c.setAuthor(adao.getAccountById(rs.getInt(5)));

                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Account> getUserListOfClass(int classID) {
        ArrayList<Account> accounts = new ArrayList<>();
        AccountDAO adao = new AccountDAO();
        String sql = "select a.account_id\n"
                + "from quiz_practice_db.class c\n"
                + "join quiz_practice_db.class_user cu on c.class_id = cu.class_id\n"
                + "join quiz_practice_db.account a on a.account_id = cu.account_id\n"
                + "where c.class_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, classID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = adao.getAccountById(rs.getInt(1));
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public void updateClass(int classID, String className, String status, String note, int authorID) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `quiz_practice_db`.`class`\n"
                    + "SET\n"
                    + "`class_name` = ?,\n"
                    + "`class_status` = ?,\n"
                    + "`class_note` = ?,\n"
                    + "`author` = ?\n"
                    + "WHERE `class_id` = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, className);
            stm.setString(2, status);
            if (note.equals("")) {
                stm.setString(3, null);
            } else {
                stm.setString(3, note);
            }
            stm.setInt(4, authorID);
            stm.setInt(5, classID);
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ChangeStatus(int id, String status) {
        try {
            String sql = "UPDATE `quiz_practice_db`.`class`\n"
                    + "SET\n"
                    + "`class_status` =?\n"
                    + "WHERE `class_id` = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (status.equalsIgnoreCase("DEACTIVE")) {
                stm.setString(1, "ACTIVE");
            } else {
                stm.setString(1, "DEACTIVE");
            }
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertClass(String className, String note, String status, int authorID) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO `quiz_practice_db`.`class`\n"
                    + "(`class_name`, `class_stutus`, `class_note`, `author`)\n"
                    + "VALUES (?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, className);
            stm.setString(2, status);
            if (note.equals("")) {
                stm.setString(3, null);
            } else {
                stm.setString(3, note);
            }
            stm.setInt(4, authorID);
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertAccountToClass(int classID, int accountID) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO `quiz_practice_db`.`class_user`\n"
                    + "(`class_id`, `account_id`)\n"
                    + "VALUES (?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, classID);
            stm.setInt(2, accountID);
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        ClassDAO cdao = new ClassDAO();
        ArrayList<Classes> classes = cdao.getClassList("math", 3, 1);
        if (classes.isEmpty()) {
            System.out.println("empty");
        }
        for (Classes c : classes) {
            System.out.println(c.getClassID() + " " + c.getClassName() + " " + c.getStatus() + " " + c.getAuthor().getId());
            for (Account a : c.getUsers()) {
                System.out.println(a.getId() + " " + a.getFullname());
            }
        }
    }
}
