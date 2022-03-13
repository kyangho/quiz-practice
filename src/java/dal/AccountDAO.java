/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Role;

/**
 *
 * @author Vu Duc Tien
 */
public class AccountDAO extends DBContext {

    private boolean checkAccountIsExist(Account a, ArrayList<Account> accounts) {
        if (accounts.isEmpty()) {
            return false;
        }
        boolean flag = false;
        for (Account acc : accounts) {
            if (acc.getId() == a.getId()) {
                flag = true;
            }
        }
        return flag;
    }

    public Account getAccount(String username, String password) {
        try {
            String sql1 = "SELECT a.account_id, a.username, a.password, a.account_status,\n"
                    + "ap.account_email, ap.account_phone, ap.account_fullname, ap.account_address, ap.account_gender, ap.account_avatar\n"
                    + "FROM account as a\n"
                    + "JOIN account_profile as ap on a.account_id = ap.account_id\n"
                    + "JOIN account_role as ar on a.account_id = ar.account_id\n"
                    + "JOIN role as r on r.role_id = ar.role_id\n"
                    + "WHERE a.username = ?";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, username);
            ResultSet rs1 = stm1.executeQuery();
            String sql2 = "SELECT r.role_id, r.role_name\n"
                    + "FROM account as a\n"
                    + "JOIN account_role as ar on a.account_id = ar.account_id\n"
                    + "JOIN role as r on r.role_id = ar.role_id\n"
                    + "WHERE username = ?";

            if (rs1.next()) {
                Account account = new Account();
                account.setId(rs1.getInt(1));
                account.setUsername(rs1.getString(2));
                account.setPassword(rs1.getString(3));
                account.setStatus(rs1.getString(4));
                account.setEmail(rs1.getString(5));
                account.setPhone(rs1.getString(6));
                account.setFullname(rs1.getString(7));
                account.setAddress(rs1.getString(8));
                account.setGender(rs1.getBoolean(9));
                account.setAvatar(rs1.getString(10));

                ArrayList<Role> roles = new ArrayList<>();
                PreparedStatement stm2 = connection.prepareStatement(sql2);
                stm2.setString(1, username);
                ResultSet rs2 = stm2.executeQuery();
                while (rs2.next()) {
                    Role r = new Role();
                    r.setId(rs2.getInt(1));
                    r.setRoleName(rs2.getString(2));
                    roles.add(r);
                }
                account.setRole(roles);
                if (password.equals(account.getPassword())) {
                    return account;
                } else if (BCrypt.verifyer().verify(password.toCharArray(), account.getPassword()).verified == true) {
                    return account;
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Account isExistAccount(String phone, String email, String username, String condition) {
        try {
            String sql = "SELECT a.account_id, a.username, ap.account_email, ap.account_phone\n"
                    + "FROM account as a\n"
                    + "JOIN account_profile as ap on a.account_id = ap.account_id\n"
                    + "WHERE ap.account_phone = ? " + condition + " ap.account_email = ? " + condition + " a.username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, phone);
            stm.setString(2, email);
            stm.setString(3, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setUsername(rs.getString(2));
                account.setEmail(rs.getString(3));
                account.setPhone(rs.getString(4));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertAccount(Account account) {
        try {
            connection.setAutoCommit(false);
            String sql1 = "INSERT INTO `quiz_practice_db`.`account`\n"
                    + "(`username`,\n"
                    + "`password`,\n"
                    + "`account_status`)\n"
                    + "VALUES\n"
                    + "(?,?,?);\n";
            String sql2 = "INSERT INTO `quiz_practice_db`.`account_profile`\n"
                    + "(`account_id`,\n"
                    + "`account_email`,\n"
                    + "`account_phone`,\n"
                    + "`account_fullname`,\n"
                    + "`account_address`,\n"
                    + "`account_gender`,\n"
                    + "`account_avatar`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?,?);";

            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, account.getUsername());
            if (account.getRole().size() > 0) {
                stm1.setString(2, account.getEmail());
            } else {
                stm1.setString(2, account.getPassword());
            }
            stm1.setString(3, "ACTIVE");
            stm1.executeUpdate();

            String sql3 = "SELECT LAST_INSERT_ID();";
            PreparedStatement stm3 = connection.prepareStatement(sql3);
            ResultSet rs = stm3.executeQuery();
            rs.next();
            int id = rs.getInt("LAST_INSERT_ID()");
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setInt(1, id);
            stm2.setString(2, account.getEmail());
            stm2.setString(3, account.getPhone());
            stm2.setString(4, account.getFullname());
            stm2.setString(5, account.getAddress());
            stm2.setBoolean(6, account.isGender());
            stm2.setString(7, account.getAvatar());
            stm2.executeUpdate();

            if (account.getRole().size() > 0) {
                String sql4 = "INSERT INTO `quiz_practice_db`.`account_role`\n"
                        + "(`account_id`,\n"
                        + "`role_id`)\n"
                        + "VALUES\n"
                        + "(?,\n"
                        + "?);";
                PreparedStatement stm4 = connection.prepareStatement(sql4);
                for (Role role : account.getRole()) {
                    stm4.setInt(1, id);
                    stm4.setInt(2, role.getId());
                    stm4.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void changePassword(Account account) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `quiz_practice_db`.`account`\n"
                    + "SET\n"
                    + "`password` = ?\n"
                    + "WHERE `account_id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getPassword());
            stm.setInt(2, account.getId());
            stm.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateAccount(Account account) {
        try {
            connection.setAutoCommit(false);
            String sql1 = "UPDATE `quiz_practice_db`.`account`\n"
                    + "SET\n"
                    + "`account_status` = ?\n"
                    + "WHERE `account_id` = ?;";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, account.getStatus().toUpperCase());
            stm1.setInt(2, account.getId());
            stm1.executeUpdate();

            String sql2 = "UPDATE `quiz_practice_db`.`account_profile`\n"
                    + "SET\n"
                    + "`account_email` = ?,\n"
                    + "`account_phone` = ?,\n"
                    + "`account_fullname` = ?,\n"
                    + "`account_address` = ?,\n"
                    + "`account_gender` = ?,\n"
                    + "`account_avatar` = ?\n"
                    + "WHERE `account_id` = ?;";
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setString(1, account.getEmail());
            stm2.setString(2, account.getPhone());
            stm2.setString(3, account.getFullname());
            stm2.setString(4, account.getAddress());
            stm2.setBoolean(5, account.isGender());
            stm2.setString(6, account.getAvatar());
            stm2.setInt(7, account.getId());
            stm2.executeUpdate();
            String sql3 = "DELETE FROM `quiz_practice_db`.`account_role`\n"
                    + "WHERE account_id = ?;";
            PreparedStatement stm3 = connection.prepareStatement(sql3);
            stm3.setInt(1, account.getId());
            stm3.executeUpdate();

            String sql4 = "INSERT INTO `quiz_practice_db`.`account_role`\n"
                    + "(`account_id`,\n"
                    + "`role_id`)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?);";
            PreparedStatement stm4 = connection.prepareStatement(sql4);
            for (Role role : account.getRole()) {
                stm4.setInt(1, account.getId());
                stm4.setInt(2, role.getId());
                stm4.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateAccountProfile(Account account) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `quiz_practice_db`.`account_profile`\n"
                    + "SET\n"
                    + "`account_email` = ?,\n"
                    + "`account_phone` = ?,\n"
                    + "`account_fullname` = ?,\n"
                    + "`account_address` = ?,\n"
                    + "`account_gender` = ?,\n"
                    + "`account_avatar` = ?\n"
                    + "WHERE `account_id` = ?;";
            PreparedStatement stm2 = connection.prepareStatement(sql);
            stm2.setString(1, account.getEmail());
            stm2.setString(2, account.getPhone());
            stm2.setString(3, account.getFullname());
            stm2.setString(4, account.getAddress());
            stm2.setBoolean(5, account.isGender());
            stm2.setString(6, account.getAvatar());
            stm2.setInt(7, account.getId());
            stm2.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Account getAccountById(int id) {
        Account account = null;
        try {
            String sql = "select  a.account_id, a.username ,ap.account_fullname, \n"
                    + "		ap.account_email, ap.account_phone, ap.account_address, a.account_status,\n"
                    + "		ap.account_gender, r.role_id, r.role_name, ap.account_avatar  \n"
                    + "	from quiz_practice_db.`account` as a\n"
                    + "	join quiz_practice_db.account_profile as ap on a.account_id = ap.account_id\n"
                    + "	left join quiz_practice_db.account_role as ar on ar.account_id = ap.account_id\n"
                    + "	left join quiz_practice_db.`role` as r on ar.role_id = r.role_id\n"
                    + "	where a.account_id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (account == null) {
                    account = new Account();
                    account.setId(rs.getInt(1));
                    account.setUsername(rs.getString(2));
                    account.setFullname(rs.getString(3));
                    account.setEmail(rs.getString(4));
                    account.setPhone(rs.getString(5));
                    account.setAddress(rs.getString(6));
                    account.setStatus(rs.getString(7));
                    account.setGender(rs.getBoolean(8));
                    account.getRole().add(new Role(rs.getInt(9), rs.getString(10)));
                    account.setAvatar(rs.getString(11));
                } else {
                    account.getRole().add(new Role(rs.getInt(9), rs.getString(10)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    public ArrayList<Account> getAllAccountsByFilter(int pageindex, int pageSize, String id, String fullname, String email, String phone, String roleID, String status, String keySearch) {
        ArrayList<Account> accounts = new ArrayList<>();

        String sql = "select * \n"
                + "from (\n"
                + "	select row_number() over (order by a.account_id ) as stt, a.account_id, a.username ,ap.account_fullname, \n"
                + "		ap.account_email, ap.account_phone, ap.account_address, a.account_status,\n"
                + "		ap.account_gender, r.role_id, r.role_name, ap.account_avatar  \n"
                + "	from quiz_practice_db.`account` as a\n"
                + "	join quiz_practice_db.account_profile as ap on a.account_id = ap.account_id\n"
                + "	left join quiz_practice_db.account_role as ar on ar.account_id = ap.account_id\n"
                + "	left join quiz_practice_db.`role` as r on ar.role_id = r.role_id\n"
                + "	where (1=1)\n";
        if (id != null && fullname != null && email != null && roleID != null && status != null && phone != null) {
            int flag = 0;
            if (status.equalsIgnoreCase("active")) {
                flag = 1;
            }
            if (!roleID.equalsIgnoreCase("all") && !status.equalsIgnoreCase("all")) {
                sql += "        AND a.account_status = " + flag + " AND r.role_id = " + roleID + "\n";
            }
            if (!roleID.equalsIgnoreCase("all") && status.equalsIgnoreCase("all")) {
                sql += "        AND r.role_id = " + roleID + "\n";
            }
            if (roleID.equalsIgnoreCase("all") && !status.equalsIgnoreCase("all")) {
                sql += "        AND a.account_status = " + flag + "\n";
            }
            if (!id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && !phone.equalsIgnoreCase("all")) {
                sql += "        order by a.account_id " + id + ", ap.account_fullname " + fullname + " ,ap.account_email " + email + ",ap.account_phone " + phone + "\n";
            }
            if (!id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by a.account_id " + id + ", ap.account_fullname " + fullname + " ,ap.account_email " + email + "\n";
            }
            if (!id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by a.account_id " + id + ", ap.account_fullname " + fullname + "\n";
            }
            if (!id.equalsIgnoreCase("all") && fullname.equalsIgnoreCase("all") && email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by a.account_id " + id + "\n";
            }
            if (id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && !phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_fullname " + fullname + " ,ap.account_email " + email + " ,ap.account_phone " + phone + "\n";
            }
            if (id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_fullname " + fullname + " ,ap.account_email " + email + "\n";
            }
            if (id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && email.equalsIgnoreCase("all") && !phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_fullname " + fullname + " ,ap.account_phone " + phone + "\n";
            }
            if (id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_fullname " + fullname + "\n";
            }
            if (id.equalsIgnoreCase("all") && fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && !phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_email " + email + " ,ap.account_phone " + phone + "\n";
            }
            if (id.equalsIgnoreCase("all") && fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_email " + email + "\n";
            }
            if (id.equalsIgnoreCase("all") && fullname.equalsIgnoreCase("all") && email.equalsIgnoreCase("all") && !phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_phone " + phone + "\n";
            }
        }
        if (keySearch != null) {
            sql += "    and (ap.account_email like '%" + keySearch + "%' or ap.account_fullname like '%" + keySearch + "%'"
                    + " or ap.account_phone like '%" + keySearch + "%')";
        }
        int start = (pageindex - 1) * pageSize;
        sql += "\n) as acc limit " + start + "," + pageSize + ";";
//                + "where acc.stt >= (? - 1)*? + 1 AND acc.stt <= ? * ?;";
//        System.out.println(sql);
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(1, pageindex);
//            stm.setInt(2, pageSize);
//            stm.setInt(3, pageindex);
//            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(2));
                if (!checkAccountIsExist(a, accounts)) {
                    a.setUsername(rs.getString(3));
                    a.setFullname(rs.getString(4));
                    a.setEmail(rs.getString(5));
                    a.setPhone(rs.getString(6));
                    a.setAddress(rs.getString(7));
                    a.setStatus(rs.getString(8));
                    a.setGender(rs.getBoolean(9));
                    a.getRole().add(new Role(rs.getInt(10), rs.getString(11)));
                    a.setAvatar(rs.getString(12));
                    accounts.add(a);
                } else {
                    for (Account account : accounts) {
                        if (account.getId() == a.getId()) {
                            account.getRole().add(new Role(rs.getInt(10), rs.getString(11)));
                            break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public int totalRowsByAccountInfor(String id, String fullname, String email, String phone, String roleID, String status, String keySearch) {
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "select count(*) as total \n"
                + "	from quiz_practice_db.`account` as a\n"
                + "	join quiz_practice_db.account_profile as ap on a.account_id = ap.account_id\n"
                + "	left join quiz_practice_db.account_role as ar on ar.account_id = ap.account_id\n"
                + "	left join quiz_practice_db.`role` as r on ar.role_id = r.role_id\n"
                + "	where (1=1)\n";

        if (id != null && fullname != null && email != null && roleID != null && status != null && phone != null) {
            int flag = 0;
            if (status.equalsIgnoreCase("active")) {
                flag = 1;
            }
            if (!roleID.equalsIgnoreCase("all") && !status.equalsIgnoreCase("all")) {
                sql += "        AND a.account_status = " + flag + " AND r.role_id = " + roleID + "\n";
            }
            if (!roleID.equalsIgnoreCase("all") && status.equalsIgnoreCase("all")) {
                sql += "        AND r.role_id = " + roleID + "\n";
            }
            if (roleID.equalsIgnoreCase("all") && !status.equalsIgnoreCase("all")) {
                sql += "        AND a.account_status = " + flag + "\n";
            }
            if (!id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && !phone.equalsIgnoreCase("all")) {
                sql += "        order by a.account_id " + id + ", ap.account_fullname " + fullname + " ,ap.account_email " + email + ",ap.account_phone " + phone + "\n";
            }
            if (!id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by a.account_id " + id + ", ap.account_fullname " + fullname + " ,ap.account_email " + email + "\n";
            }
            if (!id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by a.account_id " + id + ", ap.account_fullname " + fullname + "\n";
            }
            if (!id.equalsIgnoreCase("all") && fullname.equalsIgnoreCase("all") && email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by a.account_id " + id + "\n";
            }
            if (id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && !phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_fullname " + fullname + " ,ap.account_email " + email + " ,ap.account_phone " + phone + "\n";
            }
            if (id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_fullname " + fullname + " ,ap.account_email " + email + "\n";
            }
            if (id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && email.equalsIgnoreCase("all") && !phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_fullname " + fullname + " ,ap.account_phone " + phone + "\n";
            }
            if (id.equalsIgnoreCase("all") && !fullname.equalsIgnoreCase("all") && email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_fullname " + fullname + "\n";
            }
            if (id.equalsIgnoreCase("all") && fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && !phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_email " + email + " ,ap.account_phone " + phone + "\n";
            }
            if (id.equalsIgnoreCase("all") && fullname.equalsIgnoreCase("all") && !email.equalsIgnoreCase("all") && phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_email " + email + "\n";
            }
            if (id.equalsIgnoreCase("all") && fullname.equalsIgnoreCase("all") && email.equalsIgnoreCase("all") && !phone.equalsIgnoreCase("all")) {
                sql += "        order by ap.account_phone " + phone + "\n";
            }
        }
        if (keySearch != null) {
            sql += "    and (ap.account_email like '%" + keySearch + "%' or ap.account_fullname like '%" + keySearch + "%' "
                    + " or ap.account_phone like '%" + keySearch + "%')";
        }
        System.out.println(sql);
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
//            while (rs.next()) {
//                Account a = new Account();
//                a.setId(rs.getInt(1));
//                if (!checkAccountIsExist(a, accounts)) {
//                    a.setFullname(rs.getString(2));
//                    a.setEmail(rs.getString(3));
//                    a.setPhone(rs.getString(4));
//                    a.setAddress(rs.getString(5));
//                    a.setStatus(rs.getString(6));
//                    a.setGender(rs.getBoolean(7));
//                    a.getRole().add(new Role(rs.getInt(8), rs.getString(9)));
//                    accounts.add(a);
//                } else {
//                    for (Account account : accounts) {
//                        if (account.getId() == a.getId()) {
//                            account.getRole().add(new Role(rs.getInt(8), rs.getString(9)));
//                            break;
//                        }
//                    }
//                }
//            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return accounts.size();
        return 0;
    }

    public Account isExistAccountForAdd(String phone, String email, String username) {
        try {
            String sql = "SELECT a.username, ap.account_phone, ap.account_email FROM account_profile as ap\n"
                    + "JOIN account as a on ap.account_id = a.account_id\n"
                    + "WHERE ap.account_phone = ? OR ap.account_email = ? OR a.username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, phone);
            stm.setString(2, email);
            stm.setString(3, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setUsername(rs.getString(1));
                a.setPhone(rs.getString(2));
                a.setEmail(rs.getString(3));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void ActiveStatus(int id) {
        try {
            String sql = "UPDATE `quiz_practice_db`.`account`\n"
                    + "SET\n"
                    + "`account_status` =?\n"
                    + "WHERE `account_id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "ACTIVE");
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DeActiveStatus(int id) {
        try {
            String sql = "UPDATE `quiz_practice_db`.`account`\n"
                    + "SET\n"
                    + "`account_status` =?\n"
                    + "WHERE `account_id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "DEACTIVE");
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Account> getTeacherOrStudent(String role) {
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id FROM quiz_practice_db.account_role ar\n"
                + "join quiz_practice_db.role r on ar.role_id = r.role_id\n"
                + "where r.role_name = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, role);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = getAccountById(rs.getInt(1));
                accounts.add(a);
            }
            return accounts;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        AccountDAO adbc = new AccountDAO();
//        adbc.isExistAccountForAdd(null, "user@user.com", null).display();
        boolean empty = adbc.getAccountById(2).getRole().isEmpty();
        System.out.println(empty);
//        System.out.println(adbc.totalRowsByAccountInfor(null, null, null, null, null, null, null));
//        Account a = adbc.getAccount("admin", "admin@admin.com");
        String newPassword = "user123";
        String hashPass = "$2a$12$OS.8wHYDW4UvK1vLv3Qsvu46XUgQK4u/r5zVpan6VIflwm3Y4TojO";
        System.out.println(BCrypt.withDefaults().hashToString(12, newPassword.toCharArray()));
        System.out.println(BCrypt.verifyer().verify(newPassword.toCharArray(), hashPass).verified == true);
    }
}
