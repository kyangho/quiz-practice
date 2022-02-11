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
import model.Subject;

/**
 *
 * @author Yankee
 */
public class SubjectDAO extends DBContext {

    public ArrayList<Subject> getAllSetting_1(int pageIndex) {
        String sql = "select * from (select row_number() over (order by subject_id ) as stt,\n"
                + "subject_id, subject_title, subject_author, subject_status\n"
                + "from quiz_practice_db.`subject` ) as t\n"
                + "where stt between ?*3-2 and ?*3";
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageIndex);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setSubject_title(rs.getString("subject_title"));
                subject.setSubject_author(rs.getString("subject_author"));
                subject.setSubject_status(rs.getString("subject_status"));

                subjects.add(subject);

            }

        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return subjects;
    }

    public ArrayList<Subject> getAllSetting_2(int pageSize, int pageIndex) {
        String sql = "select * from (select row_number() over (order by subject_id ) as stt,\n"
                + "subject_id, subject_title, subject_author, subject_status\n"
                + "from quiz_practice_db.`subject` ) as t\n"
                + "where  t.stt >= (? - 1)*? + 1 AND t.stt <= ? * ?";
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageIndex);
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setSubject_title(rs.getString("subject_title"));
                subject.setSubject_author(rs.getString("subject_author"));
                subject.setSubject_status(rs.getString("subject_status"));

                subjects.add(subject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return subjects;
    }

    public int getRowcount() {
        try {
            String sql = " select count(*) as total From  quiz_practice_db.subject";
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
        }
        return -1;
    }

    public Subject getSubjectDetail(String subject_id) {
        try {
            String sql = "select s.subject_id,s.subject_title, s.subject_author, s.subject_status\n"
                    + " from quiz_practice_db.subject s where s.subject_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject_id);
            ResultSet rs = ps.executeQuery();
            Subject sub = null;
            while (rs.next()) {
                if (sub == null) {
                    sub = new Subject();
                    sub.setSubject_id(rs.getInt("subject_id"));
                    sub.setSubject_title(rs.getString("subject_title"));
                    sub.setSubject_author(rs.getString("subject_author"));
                    sub.setSubject_status(rs.getString("subject_status"));

                }
            }
            return sub;

        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void inserSubject(Subject subject) {
        try {
            String sql = "INSERT INTO `quiz_practice_db`.`subject` (`subject_title`, `subject_author`, `subject_status`) VALUES (?,?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject.getSubject_title());
            ps.setString(2, subject.getSubject_author());
            ps.setString(3, subject.getSubject_status());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void main(String[] args) {
//        SubjectDAO s = new SubjectDAO();
//        Subject sub = new Subject("Thi", "Ham", "Unpublished");
//        s.inserSubject(sub);
//    }
}
