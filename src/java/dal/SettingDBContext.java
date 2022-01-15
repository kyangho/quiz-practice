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
import model.Setting;

/**
 *
 * @author ducky
 */
public class SettingDBContext extends DBContext{
    public ArrayList<Setting> getSettings(){
        String sql_get = "SELECT `setting`.`setting_id`,\n" +
                            "    `setting`.`setting_name`,\n" +
                            "    `setting`.`setting_type`,\n" +
                            "    `setting`.`setting_description`,\n" +
                            "    `setting`.`setting_value`,\n" +
                            "    `setting`.`setting_status`\n" +
                            "FROM `quiz_practice_db`.`setting`;";
        ArrayList<Setting> settings = new ArrayList<>();
        
        try{
            PreparedStatement stm = connection.prepareStatement(sql_get);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Setting setting = new Setting();
                setting.setId(rs.getInt("setting_id"));
                setting.setName(rs.getString("setting_name"));
                setting.setType(rs.getString("setting_type"));
                setting.setDescription(rs.getString("setting_description"));
                setting.setValue(rs.getString("setting_value"));
                setting.setStatus(rs.getString("setting_status"));
                
                settings.add(setting);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return settings;
    }
    
    public boolean insertSetting(Setting setting){
        String sql_insert = "INSERT INTO `quiz_practice_db`.`setting`\n" +
                                "	(`setting_name`,`setting_type`,\n" +
                                "    `setting_description`,`setting_value`,\n" +
                                "    `setting_status`)\n" +
                                "VALUES\n" +
                                "(?,?,?,?,?);";
        try{
            PreparedStatement stm = connection.prepareStatement(sql_insert);
            stm.setString(1, setting.getName());
            stm.setString(2, setting.getType());
            stm.setString(3, setting.getDescription());
            stm.setString(4, setting.getValue());
            stm.setString(5, setting.getStatus());
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean updateSetting(Setting setting){
        String sql_update = "UPDATE `quiz_practice_db`.`setting`\n" +
                                "SET\n" +
                                "	`setting_name` = ?,`setting_type` = ?,\n" +
                                "	`setting_description` = ?,`setting_value` = ?,\n" +
                                "	`setting_status` = ?\n" +
                                "WHERE `setting_id` = ?;";
        try{
            PreparedStatement stm = connection.prepareStatement(sql_update);
            stm.setString(1, setting.getName());
            stm.setString(2, setting.getType());
            stm.setString(3, setting.getDescription());
            stm.setString(4, setting.getValue());
            stm.setString(5, setting.getStatus());
            stm.setInt(6, setting.getId());
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean deactiveSetting(int settingId){
        String sql = "UPDATE `quiz_practice_db`.`setting`\n" +
                        "SET`setting_status` = ?\n" +
                        "WHERE `setting_id` = ?;";
        
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "DEACTIVE");
            stm.setInt(2, settingId);
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean activeSetting(int settingId){
                String sql = "UPDATE `quiz_practice_db`.`setting`\n" +
                        "SET`setting_status` = ?\n" +
                        "WHERE `setting_id` = ?;";
        
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "ACTIVE");
            stm.setInt(2, settingId);
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        SettingDBContext sdb = new SettingDBContext();
        
        for (Setting s : sdb.getSettings()) {
            System.out.println(s.getId() + " " + s.getName());
        }
    }
}
