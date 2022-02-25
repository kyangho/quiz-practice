/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Answer;
import model.Question;
import model.Quiz;
import model.Subject;

/**
 *
 * @author conmu
 */
public class QuizDAO extends DBContext {

    public ArrayList<Quiz> getQuizzesPractice(int accountID, int pageindex, int pagesize) {
        ArrayList<Quiz> quizs = new ArrayList<>();
        try {
            String sql = "select * from (\n"
                    + "	select row_number() over (order by quiz.quiz_id ) as stt, quiz.quiz_id, quiz_title, quiz_img, quiz_level \n"
                    + "		   , quiz_rate, quiz_time_end, quiz_time_start, quiz_type\n"
                    + "		   , account_fullname as author, subject_title\n"
                    + "	from quiz \n"
                    + "	join quiz_account on quiz_account.quiz_id = quiz.quiz_id\n"
                    + "	join `account` on `account`.account_id = quiz.account_id\n"
                    + "	join account_profile on account_profile.account_id = `account`.account_id\n"
                    + "	join `subject` on quiz.subject_id = `subject`.subject_id\n"
                    + "	where quiz_account.account_id = ?\n"
                    + "	order by quiz_time_start desc\n"
                    + ") as quizz\n";
            int start = (pageindex - 1) * pagesize;
            sql += "limit " + start + "," + pagesize + ";";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getInt(2));
                q.setTitle(rs.getString(3));
                q.setImg(rs.getString(4));
                q.setLevel(rs.getString(5));
                q.setRate(rs.getDouble(6));
                q.setEndTime(rs.getDate(7));
                q.setStartTime(rs.getDate(8));
                q.setType(rs.getString(9));
                Account a = new Account();
                a.setFullname(rs.getString(10));
                q.setAuthor(a);
                Subject s = new Subject();
                s.setSubject_title(rs.getString(11));
                q.setSubject(s);
                q.setQuestions(getQuestionOfQuiz(q.getId()));
                quizs.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quizs;
    }

    private ArrayList<Answer> getAnswerOfQues(int questionID) {
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            String sql = "select * from answer where question_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, questionID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                answers.add(new Answer(rs.getInt(1), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }

    private ArrayList<Question> getQuestionOfQuiz(int quizID) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = "select * from question\n"
                    + "join quiz_question on quiz_question.question_id = question.question_id\n"
                    + "where quiz_id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quizID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question q = new Question(rs.getInt(1), rs.getString(2), rs.getString(3));
                q.setAnswers(getAnswerOfQues(q.getId()));
                questions.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return questions;
    }

    public void getAllQuiz() {
        String sql = "	select quiz.quiz_id, quiz_title, quiz_img, quiz_level \n"
                + "		   , quiz_rate, quiz_time_end, quiz_time_start, quiz_type\n"
                + "		   , account_fullname as author, subject_title\n"
                + "	from quiz \n"
                + "	join quiz_account on quiz_account.quiz_id = quiz.quiz_id\n"
                + "	join `account` on `account`.account_id = quiz.account_id\n"
                + "	join account_profile on account_profile.account_id = `account`.account_id\n"
                + "	join `subject` on quiz.subject_id = `subject`.subject_id\n"
                + "	order by quiz_time_start desc\n";
    }

    public static void main(String[] args) {
        QuizDAO qdb = new QuizDAO();

        for (Quiz q : qdb.getQuizzesPractice(3, 1, 4)) {
            q.display();
        }
//        for (Answer ans : qdb.getAnswerOfQues(1)) {
//            System.out.println(ans.toString());
//        }
//        for (Question question : qdb.getQuestionOfQuiz(1)) {
//            question.display();
//        }
    }
}
