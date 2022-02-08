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
import model.Role;

/**
 *
 * @author Vu Duc Tien
 */
public class AccountDBContext extends DBContext {

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "select a.account_id, ap.account_fullname, ap.account_email, ap.account_phone, ap.address, ap.`status`,\n"
                    + "		ap.gender, r.role_id, r.role_name\n"
                    + "from quiz_practice_db.`account` as a\n"
                    + "join quiz_practice_db.account_profile as ap on a.account_id = ap.account_id\n"
                    + "left join quiz_practice_db.account_role as ar on ar.account_id = ap.account_id\n"
                    + "left join quiz_practice_db.`role` as r on ar.role_id = r.role_id;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(1));
                if (!checkAccountIsExist(a, accounts)) {
                    a.setFullname(rs.getString(2));
                    a.setEmail(rs.getString(3));
                    a.setPhone(rs.getString(4));
                    a.setAddress(rs.getString(5));
                    a.setStatus(rs.getBoolean(6));
                    a.setGender(rs.getBoolean(7));
                    a.getRole().add(new Role(rs.getInt(8), rs.getString(9)));
                    accounts.add(a);
                } else {
                    for (Account account : accounts) {
                        if (account.getId() == a.getId()) {
                            account.getRole().add(new Role(rs.getInt(8), rs.getString(9)));
                            break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
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

    public Account getAccount(String username, String password) {
        try {
            String sql = "SELECT a.account_id, a.username, a.password,\n"
                    + "ap.account_email, ap.account_phone,\n"
                    + "ap.account_fullname, ap.address\n"
                    + "FROM account as a\n"
                    + "JOIN account_profile as ap on a.account_id = ap.account_id\n"
                    + "WHERE (a.username = ? OR ap.account_email = ?) and a.password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, username);
            stm.setString(3, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setUsername(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setEmail(rs.getString(4));
                account.setPhone(rs.getString(5));
                account.setFullname(rs.getString(6));
                account.setAddress(rs.getString(7));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isExistAccount(String phone, String email, String username) {
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
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
                    + "`address`)\n"
                    + "VALUES(?,?,?,?,?);";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, account.getUsername());
            stm1.setString(2, account.getPassword());
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
            stm2.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateProfile(Account account) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `quiz_practice_db`.`account_profile`\n"
                    + "SET\n"
                    + "`account_email` = ?,\n"
                    + "`account_phone` = ?,\n"
                    + "`account_fullname` = ?,\n"
                    + "`address` = ?\n"
                    + "WHERE `account_id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getPassword());
            stm.setInt(2, account.getId());
            stm.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Account getAccountProfileById(int id) {
        Account resAccount = new Account();

        return resAccount;
    }

    public static void main(String[] args) {
        AccountDBContext adbc = new AccountDBContext();
//        adbc.insertAccount(new Account("tienvd", "he153313", "tienvdhe153313@fpt.edu.vn", "0983563147", "Vu Duc Tien", new Date(2001, 7, 2), "Ha Noi"));
//        Account a = adbc.getAccount("admin", "admin");
//        System.out.println(a.toString());
//        Account a = new Account();
//        a.setId(1);
//        a.setUsername("admin");
//        a.setPassword("admin");
//        adbc.changePassword(a);
//        for (Account allAccount : adbc.getAllAccounts()) {
//            allAccount.display();
//        }
    }
}
