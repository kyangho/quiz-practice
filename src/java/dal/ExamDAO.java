/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Exam;

/**
 *
 * @author kienct
 */
public class ExamDAO extends DBContext {

//lay exam list sort by updatedDate
    public ArrayList<Exam> getExams() {
        ArrayList<Exam> exams = new ArrayList<>();
        String sql = "SELECT `exam`.`id`,\n"
                + "    `exam`.`thumbnail`,\n"
                + "    `exam`.`title`,\n"
                + "    `exam`.`category`,\n"
                + "    `exam`.`isFree`,\n"
                + "    `exam`.`author`,\n"
                + "    `exam`.`update_date`,\n"
                + "    `exam`.`brief`,\n"
                + "    `exam`.`content`,\n"
                + "    `exam`.`class_id`\n"
                + "FROM `quiz_practice_db`.`exam`"
                + "ORDER BY `exam`.`update_date` DESC;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exam exam = new Exam();
                exam.setId(rs.getInt("id"));
                exam.setThumbnail(rs.getString("thumbnail"));
                exam.setTitle(rs.getString("title"));
                exam.setIsFree(rs.getBoolean("isFree"));
                exam.setAuthor(rs.getString("author"));
                exam.setUpdatedDate(rs.getDate("update_date"));
                exam.setBriefInfo(rs.getString("brief"));
                exam.setContent(rs.getString("content"));
                exam.setCategory(rs.getString("category"));
                int classId = rs.getInt("class_id");
                ClassDAO classDAO = new ClassDAO();
                exam.setClassName(classDAO.getClassByID(classId).getClassName());
                exams.add(exam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exams;
    }

//lay examlist theo search sort by updatedDate
    public ArrayList<Exam> getExams(String subject, String category, String type, String search) {
        ArrayList<Exam> exams = new ArrayList<>();
        String sql = "SELECT `exam`.`id`,\n"
                + "    `exam`.`thumbnail`,\n"
                + "    `exam`.`title`,\n"
                + "    `exam`.`category`,\n"
                + "    `exam`.`isFree`,\n"
                + "    `exam`.`author`,\n"
                + "    `exam`.`update_date`,\n"
                + "    `exam`.`brief`,\n"
                + "    `exam`.`content`,\n"
                + "    `exam`.`class_id`\n"
                + "FROM `quiz_practice_db`.`exam`\n"
                + "WHERE\n"
                + "`exam`.`category` LIKE ('%" + category + "%') AND `exam`.`title` LIKE ('%" + search + "%')\n"
                + "ORDER BY `exam`.`update_date` DESC;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exam exam = new Exam();
                exam.setId(rs.getInt("id"));
                exam.setThumbnail(rs.getString("thumbnail"));
                exam.setTitle(rs.getString("title"));
                exam.setIsFree(rs.getBoolean("isFree"));
                exam.setAuthor(rs.getString("author"));
                exam.setUpdatedDate(rs.getDate("update_date"));
                exam.setBriefInfo(rs.getString("brief"));
                exam.setContent(rs.getString("content"));
                exam.setCategory(rs.getString("category"));
                int classId = rs.getInt("class_id");
                ClassDAO classDAO = new ClassDAO();
                exam.setClassName(classDAO.getClassByID(classId).getClassName());
                exams.add(exam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exams;
    }

    public Exam getExamDetail(int id) {
        Exam exam = new Exam();
        String sql = "SELECT `exam`.`id`,\n"
                + "    `exam`.`thumbnail`,\n"
                + "    `exam`.`title`,\n"
                + "    `exam`.`category`,\n"
                + "    `exam`.`isFree`,\n"
                + "    `exam`.`author`,\n"
                + "    `exam`.`update_date`,\n"
                + "    `exam`.`brief`,\n"
                + "    `exam`.`content`,\n"
                + "    `exam`.`class_id`\n"
                + "FROM `quiz_practice_db`.`exam`"
                + "WHERE `exam`.`id` = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                exam.setId(rs.getInt("id"));
                exam.setThumbnail(rs.getString("thumbnail"));
                exam.setTitle(rs.getString("title"));
                exam.setIsFree(rs.getBoolean("isFree"));
                exam.setAuthor(rs.getString("author"));
                exam.setUpdatedDate(rs.getDate("update_date"));
                exam.setBriefInfo(rs.getString("brief"));
                exam.setContent(rs.getString("content"));
                exam.setCategory(rs.getString("category"));

                int classId = rs.getInt("class_id");
                ClassDAO classDAO = new ClassDAO();
                exam.setClassName(classDAO.getClassByID(classId).getClassName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exam;
    }

    public static void main(String[] args) {
        ExamDAO examDAO = new ExamDAO();
        for (Exam e : examDAO.getExams("", "", "t", "")){
            System.out.println(e.toString());
        }
    }
}
