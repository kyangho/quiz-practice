/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author ducky
 */
public class AccountDBContext extends DBContext{
    public Account getAccount(String username, String password){
        String sql_get = "SELECT \n" +
                            "    `account`.`username`,\n" +
                            "    `account`.`password`\n" +
                            "FROM `quiz_practice_db`.`account`\n" +
                            "WHERE username = ? AND password = ? AND account_status = 'ACTIVE'";
        Account account = new Account();
        try{
            PreparedStatement stm = connection.prepareStatement(sql_get);
            stm.setString(1, username);
            stm.setString(2, password);
            
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                account.setUsername(username);
                account.setPassword(password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return account;
    }
    
    public boolean insertAccount(Account account){
        String sql_insert = "INSERT INTO `quiz_practice_db`.`account`\n" +
                                "(`username`,\n" +
                                "`password`,\n" +
                                "`account_status`)\n" +
                                "VALUES\n" +
                                "(?,?,?);";
        try{
            PreparedStatement stm = connection.prepareStatement(sql_insert);
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
            stm.setString(3, account.getStatus());
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
