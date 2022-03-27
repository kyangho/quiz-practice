/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Role;

/**
 *
 * @author Vu Duc Tien
 */
public class AccountDAO extends DBContext {

    public Account getAccount(String username, String password) {
        Connection connection = getConnection();
        try {
            String sql1 = "SELECT a.account_id, a.username, a.password, a.account_status,\n"
                    + "ap.account_email, ap.account_phone, ap.account_fullname, ap.account_gender, ap.account_avatar\n"
                    + "FROM account as a\n"
                    + "JOIN account_profile as ap on a.account_id = ap.account_id\n"
                    + "WHERE a.username = ?";

            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, username);
            ResultSet rs1 = stm1.executeQuery();

            if (rs1.next()) {
                Account account = new Account();
                account.setId(rs1.getInt(1));
                account.setUsername(rs1.getString(2));
                account.setPassword(rs1.getString(3));
                account.setStatus(rs1.getString(4));
                account.setEmail(rs1.getString(5));
                account.setPhone(rs1.getString(6));
                account.setFullname(rs1.getString(7));
                account.setGender(rs1.getBoolean(8));
                account.setAvatar(rs1.getBlob(9));
                account.setRole(getRolesByAccount(account.getId()));
                if (BCrypt.verifyer().verify(password.toCharArray(), account.getPassword()).verified == true) {
                    return account;
                } else {
                    return null;
                }
            }
            stm1.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Account isExistAccount(String phone, String email, String username, String condition) {
        Connection connection = getConnection();
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void insertAccount(Account account, String condition) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String sql1 = "INSERT INTO account\n"
                    + "(`username`,\n"
                    + "`password`,\n"
                    + "`account_status`)\n"
                    + "VALUES\n"
                    + "(?,?,?);\n";
            String sql3 = "SELECT LAST_INSERT_ID();";
            String sql2 = "INSERT INTO `account_profile`\n"
                    + "(`account_id`,\n"
                    + "`account_email`,\n"
                    + "`account_phone`,\n"
                    + "`account_fullname`,\n"
                    + "`account_gender`,\n"
                    + "`account_avatar`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?);";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            System.out.println(account.getPassword());
            if (account.getPassword() == null || account.getPassword().trim().length() == 0) {
                account.setPassword(BCrypt.withDefaults().hashToString(12, account.getEmail().toCharArray()));
            }
            stm1.setString(1, account.getUsername());
            stm1.setString(2, account.getPassword());
            stm1.setString(3, condition.toUpperCase());
            stm1.executeUpdate();

            PreparedStatement stm3 = connection.prepareStatement(sql3);
            ResultSet rs = stm3.executeQuery();
            if (rs.next()) {
                account.setId(rs.getInt("LAST_INSERT_ID()"));
            }

            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setInt(1, account.getId());
            stm2.setString(2, account.getEmail());
            stm2.setString(3, account.getPhone());
            stm2.setString(4, account.getFullname());
            stm2.setBoolean(5, account.isGender());
            stm2.setBlob(6, account.getAvatar());
            stm2.executeUpdate();

            if (account.getRole().size() > 0) {
                String sql4 = "INSERT INTO `account_role`\n"
                        + "(`account_id`,\n"
                        + "`setting_id`)\n"
                        + "VALUES\n"
                        + "(?,\n"
                        + "?);";
                PreparedStatement stm4 = connection.prepareStatement(sql4);
                for (Role role : account.getRole()) {
                    stm4.setInt(1, account.getId());
                    stm4.setInt(2, role.getId());
                    stm4.executeUpdate();
                }
            }
            stm1.close();
            stm2.close();
            stm3.close();
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
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void changePassword(Account account) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `account`\n"
                    + "SET\n"
                    + "`password` = ?\n"
                    + "WHERE `account_id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getPassword());
            stm.setInt(2, account.getId());
            stm.execute();
            connection.commit();
            stm.close();
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
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateAccount(Account account) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String sql1 = "UPDATE `account`\n"
                    + "SET\n"
                    + "`account_status` = ?\n"
                    + "WHERE `account_id` = ?;";
            String sql2 = "UPDATE `account_profile`\n"
                    + "SET\n"
                    + "`account_email` = ?,\n"
                    + "`account_phone` = ?,\n"
                    + "`account_fullname` = ?,\n"
                    + "`account_gender` = ?,\n"
                    + "`account_avatar` = ?\n"
                    + "WHERE `account_id` = ?;";
            String sql3 = "DELETE FROM `account_role`\n"
                    + "WHERE account_id = ?;";
            String sql4 = "INSERT INTO `account_role`\n"
                    + "(`account_id`,\n"
                    + "`setting_id`)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?);";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, account.getStatus().toUpperCase());
            stm1.setInt(2, account.getId());
            stm1.executeUpdate();

            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setString(1, account.getEmail());
            stm2.setString(2, account.getPhone());
            stm2.setString(3, account.getFullname());
            stm2.setBoolean(4, account.isGender());
            stm2.setBlob(5, account.getAvatar());
            stm2.setInt(6, account.getId());
            stm2.executeUpdate();

            PreparedStatement stm3 = connection.prepareStatement(sql3);
            stm3.setInt(1, account.getId());
            stm3.executeUpdate();

            PreparedStatement stm4 = connection.prepareStatement(sql4);
            for (Role role : account.getRole()) {
                stm4.setInt(1, account.getId());
                stm4.setInt(2, role.getId());
                stm4.executeUpdate();
            }
            stm1.close();
            stm2.close();
            stm3.close();
            stm4.close();
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
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateAccountProfile(Account account, InputStream avatar) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `account_profile`SET\n"
                    + "`account_phone` = ? ,`account_fullname` = ? ,`account_gender` = ? \n";
            if (avatar != null) {
                sql += ",`account_avatar` = ?\n";
            }
            sql += " WHERE (`account_id` = ?);";
            PreparedStatement stm2 = connection.prepareStatement(sql);
            stm2.setString(1, account.getPhone());
            stm2.setString(2, account.getFullname());
            stm2.setBoolean(3, account.isGender());
            if (avatar != null) {
                stm2.setBlob(4, avatar);
                stm2.setInt(5, account.getId());
            } else {
                stm2.setInt(4, account.getId());
            }
            stm2.executeUpdate();
            connection.commit();
            stm2.close();
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
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Account getAccountById(int id) {
        Connection connection = getConnection();
        Account account = null;
        try {
            String sql = "select ap.*, username,account_status from `account` a\n"
                    + "join account_profile ap on ap.account_id = a.account_id\n"
                    + "where ap.account_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setId(rs.getInt(1));
                account.setFullname(rs.getString(2));
                account.setEmail(rs.getString(3));
                account.setPhone(rs.getString(4));
                account.setGender(rs.getBoolean(5));
                account.setAvatar(rs.getBlob(6));
                account.setUsername(rs.getString(7));
                account.setStatus(rs.getString(8));
                account.setRole(getRolesByAccount(id));
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return account;
    }

    private ArrayList<Role> getRolesByAccount(int id) {
        Connection connection = getConnection();
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql2 = "select s.setting_id, setting_value from setting s\n"
                    + "join account_role a on a.setting_id = s.setting_id\n"
                    + "where setting_type = 'role' and account_id = ?";
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setInt(1, id);
            ResultSet rs2 = stm2.executeQuery();
            while (rs2.next()) {
                Role r = new Role();
                r.setId(rs2.getInt(1));
                r.setRoleName(rs2.getString(2));
                roles.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }

    public ArrayList<Account> getAllAccountsByFilter(int pageindex, int pageSize, String id,
            String fullname, String email, String phone, String roleID, String status, String keySearch) {
        Connection connection = getConnection();
        ArrayList<Account> accounts = new ArrayList<>();

        String sql = "select * \n"
                + "from (\n"
                + "	select row_number() over (order by a.account_id ) as stt, a.account_id, a.username ,ap.account_fullname, \n"
                + "                 		ap.account_email, ap.account_phone, a.account_status,\n"
                + "                 		ap.account_gender, s.setting_id, s.setting_value, ap.account_avatar  \n"
                + "                 	from `account` as a\n"
                + "                 	join account_profile as ap on a.account_id = ap.account_id\n"
                + "                 	left join account_role as ar on ar.account_id = ap.account_id\n"
                + "                 	left join setting as s on ar.setting_id = s.setting_id\n"
                + "                 	where (1=1)\n";
        if (id != null && fullname != null && email != null && roleID != null && status != null && phone != null) {
            int flag = 0;
            if (status.equalsIgnoreCase("active")) {
                flag = 1;
            }
            if (!roleID.equalsIgnoreCase("all") && !status.equalsIgnoreCase("all")) {
                sql += "        AND a.account_status = " + flag + " AND s.setting_id = " + roleID + "\n";
            }
            if (!roleID.equalsIgnoreCase("all") && status.equalsIgnoreCase("all")) {
                sql += "        AND s.setting_id = " + roleID + "\n";
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
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(2));
                if (!checkAccountIsExist(a, accounts)) {
                    a.setUsername(rs.getString(3));
                    a.setFullname(rs.getString(4));
                    a.setEmail(rs.getString(5));
                    a.setPhone(rs.getString(6));
                    a.setStatus(rs.getString(7));
                    a.setGender(rs.getBoolean(8));
                    a.getRole().add(new Role(rs.getInt(9), rs.getString(10)));
                    a.setAvatar(rs.getBlob(11));
                    accounts.add(a);
                } else {
                    for (Account account : accounts) {
                        if (account.getId() == a.getId()) {
                            account.getRole().add(new Role(rs.getInt(9), rs.getString(10)));
                            break;
                        }
                    }
                }
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return accounts;
    }

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

    public int totalRowsByAccountInfor(String id, String fullname, String email, String phone,
            String roleID, String status, String keySearch) {
        Connection connection = getConnection();
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "select count(*) as stt"
                + "                 	from `account` as a\n"
                + "                 	join account_profile as ap on a.account_id = ap.account_id\n"
                + "                 	left join account_role as ar on ar.account_id = ap.account_id\n"
                + "                 	left join setting as s on ar.setting_id = s.setting_id\n"
                + "                 	where (1=1)\n";

        if (id != null && fullname != null && email != null && roleID != null && status != null && phone != null) {
            int flag = 0;
            if (status.equalsIgnoreCase("active")) {
                flag = 1;
            }
            if (!roleID.equalsIgnoreCase("all") && !status.equalsIgnoreCase("all")) {
                sql += "        AND a.account_status = " + flag + " AND r.setting_id = " + roleID + "\n";
            }
            if (!roleID.equalsIgnoreCase("all") && status.equalsIgnoreCase("all")) {
                sql += "        AND s.setting_id = " + roleID + "\n";
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
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public Account isExistAccountForAdd(String phone, String email, String username) {
        Connection connection = getConnection();
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void ActiveStatus(int id) {
        Connection connection = getConnection();
        try {
            String sql = "UPDATE `account`\n"
                    + "SET\n"
                    + "`account_status` =?\n"
                    + "WHERE `account_id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "ACTIVE");
            stm.setInt(2, id);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void DeActiveStatus(int id) {
        Connection connection = getConnection();
        try {
            String sql = "UPDATE `account`\n"
                    + "SET\n"
                    + "`account_status` =?\n"
                    + "WHERE `account_id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "DEACTIVE");
            stm.setInt(2, id);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.commit();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Account> getTeacherOrStudent(String role) {
        Connection connection = getConnection();
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id FROM account_role ar\n"
                + "join setting r on ar.setting_id = r.setting_id\n"
                + "where r.setting_value = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, role);
            ResultSet rs = stm.executeQuery();
            stm.close();
            while (rs.next()) {
                Account a = getAccountById(rs.getInt(1));
                accounts.add(a);
            }
            return accounts;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
