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
 * @author Vu Duc Tien
 */
public class DBContext {
    protected Connection connection;
    
    public DBContext(){
        try {
            String user = "root";
            String pass = "admin123";
            String url = "jdbc:mysql://localhost:3306/quiz_practice_db?useSSL=false";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println(connection.getCatalog());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
           DBContext db = new DBContext();
    }
}
