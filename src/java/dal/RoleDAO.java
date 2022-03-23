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
import model.Role;

/**
 *
 * @author luongnthhe151453
 */
public class RoleDAO extends DBContext{
    
    public ArrayList<Role> getRoles(){
        Connection connection = getConnection();
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "select setting_id, setting_value from setting where setting_type = 'role';";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                roles.add(new Role(rs.getInt(1), rs.getString(2)));
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }
}
