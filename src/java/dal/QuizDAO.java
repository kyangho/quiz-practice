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
import model.Account;
import model.Category;
import model.Question;
import model.Quiz;
import model.Subject;

/**
 *
 * @author Yankee
 */
public class QuizDAO extends DBContext {

    PreparedStatement ps = null;

    public ArrayList<Quiz> getQuiz() {
        ArrayList<Quiz> quizs = new ArrayList<>();
        String sql = "SELECT q.quiz_id, q.quiz_title, s.subject_title, c.category_name, q.quiz_level\n"
                + ",q.quiz_type, ap.account_fullname as Author, q.quiz_status\n"
                + "-- , ques.question_content\n"
                + "FROM quiz_practice_db.quiz as q \n"
                + "join quiz_practice_db.subject as s on q.subject_id = s.subject_id \n"
                + "join quiz_practice_db.category as c on q.category_id = c.category_id\n"
                + "join quiz_practice_db.account_profile as ap on q.quiz_author = ap.account_id\n"
                + "-- join quiz_practice_db.quiz_question as qq on q.quiz_id = qq.quiz_id\n"
                + "-- join quiz_practice_db.question as ques on qq.question_id = ques.question_id;";
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getInt("quiz_id"));
                q.setTitle(rs.getString("quiz_title"));
                q.setLevel(rs.getString("quiz_level"));
                q.setType(rs.getString("quiz_type"));
                q.setStatus(rs.getString("quiz_status"));

                Subject sub = new Subject();
                sub.setSubject_title(rs.getString("subject_title"));
                q.setSubject(sub);

                Category cate = new Category();
                cate.setCategory_name(rs.getString("category_name"));
                q.setCategory(cate);
                Account acc = new Account();
                acc.setFullname(rs.getString("Author"));
                q.setAuthor(acc);

//                Question ques = new Question();
//                ques.setContent(rs.getString("question_content"));
//                q.setQues(ques);
                quizs.add(q);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quizs;
    }

    public ArrayList<Subject> getsubs() {
        ArrayList<Subject> s = new ArrayList<>();
        String sql = "SELECT * FROM quiz_practice_db.subject;";
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject sub = new Subject();
                sub.setSubject_id(rs.getInt("subject_id"));
                sub.setSubject_title(rs.getString("subject_title"));
                s.add(sub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

//    public static void main(String[] args) {
//        QuizDAO q = new QuizDAO();
//        for (Subject sub : q.getsubs()) {
//            System.out.println(sub.getSubject_title());
//        }
//    }
}
