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
import model.Slider;

/**
 *
 * @author Yankee
 */
public class SliderDAO extends DBContext {

    public ArrayList<Slider> getAllSliders(int pageSize, int pageIndex, String status, String title) {
        ArrayList<Slider> list = new ArrayList<>();
        try {
            String sql = "select * from (select row_number()over (order by slider_id asc) as stt, \n"
                    + "s.slider_id, s.slider_title,s.slider_img,s.slider_backlink, s.slider_status,s.slider_note \n"
                    + "FROM quiz_practice_db.slider as s \n";
            if (status != null) {
                if (!status.equalsIgnoreCase("all")) {
                    sql += " where s.slider_status = '" + status + "' ";
                }
            }
            if (title != null) {
                sql += " where s.slider_title like '%" + title + "%'";
            }
            sql += " ) as t where  t.stt >= (? - 1)*? + 1 AND t.stt <= ? * ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pageSize);
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setId(rs.getInt("slider_id"));
                s.setTitle(rs.getString("slider_title"));
                s.setImage(rs.getString("slider_img"));
                s.setBacklink(rs.getString("slider_backlink"));
                s.setStatus(rs.getString("slider_status"));
                s.setNote(rs.getString("slider_note"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getRowcount(String status, String title) {
        try {
            String sql = "select count(*) as total From  quiz_practice_db.slider as s ";
            if (status != null) {
                if (!status.equalsIgnoreCase("all")) {
                    sql += " where s.slider_status = '" + status + "' ";
                }
            }
            if (title != null) {
                sql += " where s.slider_title like '%" + title + "%'";
            }
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
