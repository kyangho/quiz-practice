/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Tebellum
 */
public class HomeDAO extends DBContext {

    public ArrayList<Account> getAccounts() {
        Connection connection = getConnection();
        ArrayList<Account> acc = new ArrayList<>();
        String sql = "SELECT * FROM `account` a join `account_profile` inf on inf.account_id = a.account_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("account_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("account_email"));
                a.setPhone(rs.getString("account_phone"));
                a.setFullname(rs.getString("account_fullname"));
                a.setAvatar(rs.getBlob("account_avatar"));
                acc.add(a);
            }
//            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return acc;
    }
    public static void main(String[] args) {
        HomeDAO h = new HomeDAO();
        for (Account a : h.getAccounts()) {
            System.out.println(a.getFullname());
        }
        
    }
    
    
    public Account getInfomation(String user) {
        Connection connection = getConnection();
        try {
            String sql = "select * from account a join account_profile ap \n"
                    + "on a.account_id = ap.account_id\n"
                    + "where a.account_id = ?\n"
                    + ";";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(1));
                a.setUsername(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setEmail(rs.getString(5));
                a.setPhone(rs.getString(6));
                a.setFullname(rs.getString(7));
                return a;
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        HomeDAO h = new HomeDAO();
//        for (Account b : h.getAccounts()) {
//            System.out.println(b.toString());
//        }
//    }
}
