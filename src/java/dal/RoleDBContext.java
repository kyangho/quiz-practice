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
import model.Role;

/**
 *
 * @author ducky
 */
public class RoleDBContext extends DBContext{
    public ArrayList<Role> getRoles(){
        ArrayList<Role> roles = new ArrayList<>();
        
        String sql_get = "SELECT r.role_id, r.role_type, r.role_value, " +
                            "r.role_active, rf.function_id, rf.function_active\n" +
                            "FROM quiz_practice_db.role as r\n" +
                            "inner join quiz_practice_db.role_function as rf " +
                            "on r.role_id = rf.role_id" +
                            "order by r.role_id";
        
        try{
            PreparedStatement stm = connection.prepareStatement(sql_get);
            ResultSet rs = stm.executeQuery();
            
            int lastId = 0;
            FunctionDBContext fdb = new FunctionDBContext();
            while(rs.next()){
                Role role = new Role();
                
                if(lastId != rs.getInt("role_id")){
                    role.setId(rs.getInt("role_id"));
                    role.setType(rs.getString("role_type"));
                    role.setValue(rs.getString("role_value"));
                    role.setActive(rs.getBoolean("role_active"));
                    
                    lastId = role.getId();
                }
                role.setFunctions(fdb.getFunctionsByRoleId(role.getId()));
                
                roles.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }
    
    public Role getRoleById(int id){
        Role role = new Role();
        
        String sql_get = "SELECT r.role_id, r.role_type, r.role_value, " +
                            "r.role_active, rf.function_id, rf.function_active\n" +
                            "FROM quiz_practice_db.role as r\n" +
                            "inner join quiz_practice_db.role_function as rf " +
                            "on r.role_id = rf.role_id" +
                            "order by r.role_id" +
                            "where r.role_id = ?";
        
        try{
            PreparedStatement stm = connection.prepareStatement(sql_get);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            FunctionDBContext fdb = new FunctionDBContext();
            if(rs.next()){
                role.setId(rs.getInt("role_id"));
                role.setType(rs.getString("role_type"));
                role.setValue(rs.getString("role_value"));
                role.setActive(rs.getBoolean("role_active"));
                role.setFunctions(fdb.getFunctionsByRoleId(role.getId()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return role;
    }
    
    public boolean updateRole(ArrayList<Role> roles){
        String sql_update_roletbl = "UPDATE `quiz_practice_db`.`role`\n" +
                                        "SET\n" +
                                        "`role_id` = ?," +
                                        "`role_type` = ?," +
                                        "`role_value` = ?," +
                                        "`role_active` = ?" +
                                        "WHERE `role_id` = ?;";
        try{
            for (Role r : roles){
                PreparedStatement stm_update_roletbl = connection.prepareStatement(sql_update_roletbl);
                stm_update_roletbl.setInt(1, r.getId());
                stm_update_roletbl.setString(2, r.getType());
                stm_update_roletbl.setString(3, r.getValue());
                stm_update_roletbl.setBoolean(4, r.isActive());
                
                stm_update_roletbl.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean insertRole(ArrayList<Role> roles){
        String sql_insert = "INSERT INTO `quiz_practice_db`.`role`\n" +
                                "(`role_id`," +
                                "`role_type`," +
                                "`role_value`," +
                                "`role_active`)" +
                                "VALUES\n" +
                                "(?," +
                                "?," +
                                "?," +
                                "?);";
        
        try{
            for (Role r :  roles){
                PreparedStatement stm = connection.prepareStatement(sql_insert);
                stm.setInt(1, r.getId());
                stm.setString(2, r.getType());
                stm.setString(3, r.getValue());
                stm.setBoolean(4, r.isActive());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
