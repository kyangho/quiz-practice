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
public class SettingDBContext extends DBContext {

    public ArrayList<Setting> getSettings(String lastSettingID, int pageSize, String key, String value) {
        String sql_get = "SELECT * FROM quiz_practice_db.setting\n"
                + "where (1=1) ";
        if (key != null && !key.equalsIgnoreCase("setting_name")) {
            sql_get += "and " + key + " = '" + value + "'\n";
        }

        if (key != null && key.equalsIgnoreCase("setting_name")) {
            sql_get += "and " + key + " like '%" + value + "%'\n";
        }
        ArrayList<Setting> settings = new ArrayList<>();
        try {
            if (lastSettingID.equals("<1")) {
                lastSettingID = ">0";
            }
            if (lastSettingID.contains(">")) {
                sql_get += "and setting_id " + lastSettingID + " order by setting_id asc limit " + pageSize + ";";
            } else {
                sql_get += "and setting_id " + lastSettingID + " order by setting_id desc limit " + pageSize + ";";
            }
            PreparedStatement stm = connection.prepareStatement(sql_get);
//            stm.setInt(1, lastSettingID);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
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

    public int toltalRowsInSetting(String key, String value) {
        try {
            String sql = "select count(*) as toltalRows from quiz_practice_db.setting";
            if (key != null && !value.equals("all") && !key.equals("setting_status")) {
                sql += " where " + key + " like '%" + value + "%';";
            }

            if (key != null && !value.equals("all") && key.equals("setting_status")) {
                sql += " where " + key + "='" + value + "';";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt("toltalRows");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public boolean insertSetting(Setting setting) {
        String sql_insert = "INSERT INTO `quiz_practice_db`.`setting`\n"
                + "	(`setting_name`,`setting_type`,\n"
                + "    `setting_description`,`setting_value`,\n"
                + "    `setting_status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        try {
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

    public boolean updateSetting(Setting setting) {
        String sql_update = "UPDATE `quiz_practice_db`.`setting`\n"
                + "SET\n"
                + "	`setting_name` = ?,`setting_type` = ?,\n"
                + "	`setting_description` = ?,`setting_value` = ?,\n"
                + "	`setting_status` = ?\n"
                + "WHERE `setting_id` = ?;";
        try {
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

    public boolean deactiveSetting(int settingId) {
        String sql = "UPDATE `quiz_practice_db`.`setting`\n"
                + "SET`setting_status` = ?\n"
                + "WHERE `setting_id` = ?;";

        try {
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

    public boolean activeSetting(int settingId) {
        String sql = "UPDATE `quiz_practice_db`.`setting`\n"
                + "SET`setting_status` = ?\n"
                + "WHERE `setting_id` = ?;";

        try {
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

//    public static void main(String[] args) {
//        SettingDBContext sdb = new SettingDBContext();
////        System.out.println(sdb.toltalRowsInSetting("setting_status", "Active"));
//        for (Setting setting : sdb.getSettings(">0", 3, "setting_status", "Active")) {
//            System.out.println(setting.getId() + " " + setting.getName());
//        }
//    }
}
