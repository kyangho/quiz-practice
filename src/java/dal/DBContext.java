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

    public Connection connection;
    private String user = "ljgpnrdtbqnqze";
    private String pass = "f63ec82850964146927bd513c4ac3330d3bdb5346a13aebaf3b446ac028c3cb9";
    private String url = "jdbc:postgresql://ec2-44-194-92-192.compute-1.amazonaws.com:5432/denuvcv64biigd";

    public DBContext() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
