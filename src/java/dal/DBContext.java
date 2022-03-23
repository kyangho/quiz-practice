/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ducky
 */
public class DBContext {
    private String user = "root"; 
    private String pass = "admin123";
    private String url = "jdbc:mysql://localhost:3306/quiz_practice_db_test_2?allowPublicKeyRetrieval=true&useSSL=false";
//    private String user = "giakinh0823"; 
//    private String pass = "Giakinh0823@";
//    private String url = "jdbc:mysql://168.138.101.134:3306/quiz_db?allowPublicKeyRetrieval=true&useSSL=false";
//    public Connection connection;
//
//    public DBContext() {
//        try {
//            String user = "root";
//            String pass = "admin123";
//            String url = "jdbc:mysql://localhost:3306/quiz_db?allowPublicKeyRetrieval=true&useSSL=false";
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(url, user, pass);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
