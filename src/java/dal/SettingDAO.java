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
public class SettingDAO extends DBContext {

    public Setting getSettingById(int id) {
        String sql_select = "SELECT \n"
                + "    `setting`.`setting_name`,\n"
                + "    `setting`.`setting_type`,\n"
                + "    `setting`.`setting_description`,\n"
                + "    `setting`.`setting_value`,\n"
                + "    `setting`.`setting_status`\n"
                + "FROM `quiz_practice_db`.`setting`\n"
                + "WHERE `setting`.`setting_id` = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql_select);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Setting setting = new Setting();
                setting.setId(id);
                setting.setName(rs.getString("setting_name"));
                setting.setDescription(rs.getString("setting_description"));
                setting.setType(rs.getString("setting_type"));
                setting.setValue(rs.getString("setting_value"));
                setting.setStatus(rs.getString("setting_status"));
                return setting;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int totalRowsInSetting(String status, String type, String setting_name) {
        try {
            String sql = "select count(*) as toltalRows from quiz_practice_db.setting\n";
            if (status != null && type != null) {
                if (!type.equals("all") && !status.equals("all")) {
                    sql += "where setting_type = '" + type + "'" + " AND setting_status = '" + status + "'";
                }
                if (type.equals("all") && !status.equals("all")) {
                    sql += "where setting_status = '" + status + "'";
                }
                if (!type.equals("all") && status.equals("all")) {
                    sql += "where setting_type = '" + type + "'";
                }
            }

            if (setting_name != null) {
                sql += "where setting_name like '%" + setting_name + "%'";
            }
//            System.out.println(sql);

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("toltalRows");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public ArrayList<Setting> getALLSetting(int pageSize, int pageIndex, String type, String status, String setting_name) {
        String sql_get = "select * from\n"
                + "	(select row_number() over (order by setting_id ) as stt,\n"
                + "		setting_id, setting_name, setting_status, setting_type, setting_description"
                + "         from quiz_practice_db.setting\n";
        if (status != null && type != null) {
                if (!type.equals("all") && !status.equals("all")) {
                    sql_get += "where setting_type = '" + type + "'" + " AND setting_status = '" + status + "'";
                }
                if (type.equals("all") && !status.equals("all")) {
                    sql_get += "where setting_status = '" + status + "'";
                }
                if (!type.equals("all") && status.equals("all")) {
                    sql_get += "where setting_type = '" + type + "'";
                }
            }

        if (setting_name != null) {
            sql_get += "where setting_name like '%" + setting_name + "%'";
        }

        sql_get += "        ) as t\n"
                + " where  t.stt >= (? - 1)*? + 1 AND t.stt <= ? * ?;";
        ArrayList<Setting> settings = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql_get);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageIndex);
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Setting setting = new Setting();
                setting.setId(rs.getInt("setting_id"));
                setting.setName(rs.getString("setting_name"));
                setting.setType(rs.getString("setting_type"));
                setting.setDescription(rs.getString("setting_description"));
                setting.setStatus(rs.getString("setting_status"));

                settings.add(setting);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return settings;

    }

//    public static void main(String[] args) {
//        SettingDAO sdb = new SettingDAO();
//        System.out.println(sdb.totalRowsInSetting(null, null, "a"));
//        for (Setting setting :sdb.GetALLSetting(2, 1, "role", "Active", null)) {
//            System.out.println(setting.toString());
//            System.out.println("");
//        }
//    }
}
