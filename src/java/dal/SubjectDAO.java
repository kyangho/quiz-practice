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
import model.Subject;

/**
 *
 * @author Yankee
 */
public class SubjectDAO extends DBContext {

    public ArrayList<Subject> getAllSubject_2(int pageSize, int pageIndex, String status, String subject_title) {
//        String s = "SELECT * FROM quiz_practice_db.`subject` as s join quiz_practice_db.`user` on s.user_id = `user`.user_id";
        String sql = "select * from (select row_number() over (order by subject_id ) as stt,\n"
                + "subject_id, subject_title, subject_status, subject_author, ap.account_fullname, ap.account_id FROM quiz_practice_db.`subject` \n"
                + "as s join quiz_practice_db.`account` on s.subject_author = `account`.account_id \n"
                + "join quiz_practice_db.account_profile as ap on `account`.account_id = ap.account_id";
        if (status != null) {
            if (!status.equals("all")) {
                sql += " where subject_status = '" + status + "'";
            }
        }
        if (subject_title != null) {
            sql += " where subject_title like '%" + subject_title + "%'";
        }
        sql += " ) as t where  t.stt >= (? - 1)*? + 1 AND t.stt <= ? * ?";
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageIndex);
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setSubject_title(rs.getString("subject_title"));
                subject.setSubject_status(rs.getString("subject_status"));
                Account u = new Account();
                u.setFullname(rs.getString("account_fullname"));
                subject.setSubject_Author(u);
                subjects.add(subject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return subjects;
    }

    public int getRowcount(String status, String subject_title) {
        try {
            String sql = "select count(*) as total From  quiz_practice_db.subject";
            if (status != null) {
                if (!status.equals("all")) {
                    sql += " where subject_status = '" + status + "'";
                }
            }
            if (subject_title != null) {
                sql += " where subject_title like '%" + subject_title + "%'";
            }
//            System.out.println(sql);
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return -1;
    }

    public Subject getSubjectDetail(String subject_id) {
        try {
            String sql = "select s.subject_id,s.subject_title, ap.account_fullname, s.subject_status\n"
                    + "FROM quiz_practice_db.`subject` \n"
                    + "as s join quiz_practice_db.`account` on s.subject_author = `account`.account_id \n"
                    + "join quiz_practice_db.account_profile as ap on `account`.account_id = ap.account_id\n"
                    + " where s.subject_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject_id);
            ResultSet rs = ps.executeQuery();
            Subject sub = null;
            while (rs.next()) {
                if (sub == null) {
                    sub = new Subject();
                    sub.setSubject_id(rs.getInt("subject_id"));
                    sub.setSubject_title(rs.getString("subject_title"));
                    sub.setSubject_status(rs.getString("subject_status"));

                    Account acc = new Account();
                    acc.setFullname(rs.getString("account_fullname"));
                    sub.setSubject_Author(acc);
                }
            }
            return sub;

        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void inserSubject(Subject subject) {
        try {
            String sql = "INSERT INTO `quiz_practice_db`.`subject` (`subject_title`, `subject_author`, `subject_status`) VALUES (?,?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject.getSubject_title());
            ps.setInt(2, subject.getSubject_Author().getId());
            ps.setString(3, subject.getSubject_status());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editSubject(Subject sub) {
        try {
            String sql = "UPDATE `quiz_practice_db`.`subject`\n"
                    + "SET\n"
                    + "`subject_title` =?,\n"
                    + "`subject_author` =?,\n"
                    + "`subject_status` = ?\n"
                    + "WHERE `subject_id` =?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, sub.getSubject_title());
            ps.setInt(2, sub.getSubject_Author().getId());
            ps.setString(3, sub.getSubject_status());
            ps.setInt(4, sub.getSubject_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean unpublishedSubject(int subjectId) {
        try {
            String sql = "UPDATE `quiz_practice_db`.`subject` SET `subject_status` = ?"
                    + " WHERE (`subject_id` = ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Unpublished");
            ps.setInt(2, subjectId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean publishedSubject(int subjectId) {
        try {
            String sql = "UPDATE `quiz_practice_db`.`subject` SET `subject_status` = ?"
                    + " WHERE (`subject_id` = ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Published");
            ps.setInt(2, subjectId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public void deleteSubject(int id) {
        try {
            String sql = "delete from quiz_practice_db.`subject` where `subject`.subject_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        SubjectDAO s = new SubjectDAO();
        for (Subject dSubject : s.getAllSubject_2(1, 2, "all", null)) {
            System.out.println(dSubject.getSubject_id());
        }
        
    }
}

//    public ArrayList<Subject> getAllSubject_1(int pageIndex) {
//        String sql = "select * from (select row_number() over (order by subject_id ) as stt,\n"
//                + "subject_id, subject_title, subject_author, subject_status\n"
//                + "from quiz_practice_db.`subject` ) as t\n"
//                + "where stt between ?*3-2 and ?*3";
//        ArrayList<Subject> subjects = new ArrayList<>();
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, pageIndex);
//            ps.setInt(2, pageIndex);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Subject subject = new Subject();
//                subject.setSubject_id(rs.getInt("subject_id"));
//                subject.setSubject_title(rs.getString("subject_title"));
//                subject.setSubject_author(rs.getString("subject_author"));
//                subject.setSubject_status(rs.getString("subject_status"));
//
//                subjects.add(subject);
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//        return subjects;
//    }
