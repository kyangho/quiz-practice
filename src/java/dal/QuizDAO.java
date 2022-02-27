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
import model.Answer;

/**
 *
 * @author Yankee
 */
public class QuizDAO extends DBContext {

    PreparedStatement ps = null;

    public ArrayList<Quiz> getQuiz(String subject, String category, String quiz_type, String search_quiz_title) {
        ArrayList<Quiz> quizs = new ArrayList<>();
        String sql = "SELECT q.quiz_id, q.quiz_title, s.subject_title, c.category_name, q.quiz_level\n"
                + ", q.quiz_type, ap.account_fullname as Author, q.quiz_status\n"
                + "FROM quiz_practice_db.quiz as q \n"
                + "join quiz_practice_db.subject as s on q.subject_id = s.subject_id \n"
                + "join quiz_practice_db.category as c on q.category_id = c.category_id\n"
                + "join quiz_practice_db.account_profile as ap on q.account_id = ap.account_id\n";
        if (subject != null && category != null && quiz_type != null) {
            if (!subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "' and c.category_name = '" + category + "' and q.quiz_type = '" + quiz_type + "'; ";
            }
            if (subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where  c.category_name = '" + category + "' and q.quiz_type = '" + quiz_type + "'; ";
            }
            if (subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where q.quiz_type = '" + quiz_type + "'; ";
            }
            if (subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                sql += " where c.category_name = '" + category + "'; ";
            }
            if (!subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "'  and q.quiz_type = '" + quiz_type + "'; ";
            }
            if (!subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "'; ";
            }
            if (!subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "' and c.category_name = '" + category + "'; ";
            }
        }
        if (search_quiz_title != null) {
            sql += "where q.quiz_title like '%" + search_quiz_title + "%'";
        }
        System.out.println(sql);
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

//    public static void main(String[] args) {
//        QuizDAO q = new QuizDAO();
//        for (Quiz quiz : q.getQuiz("all", "all", "all",null)) {
//            System.out.println(quiz.getTitle());
//        }
//    }
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

    public ArrayList<Category> getCates() {
        ArrayList<Category> c = new ArrayList<>();
        String sql = "SELECT * FROM quiz_practice_db.category;";
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category cate = new Category();
                cate.setCategory_id(rs.getInt("category_id"));
                cate.setCategory_name(rs.getString("category_name"));
                cate.setCategory_value(rs.getString("category_value"));
                c.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public Quiz getQuizDetail(int id) {
        try {
            String sql = "    select quiz.quiz_id, quiz_title, quiz_img, quiz_level \n"
                    + "		   , quiz_rate, quiz_time_end, quiz_time_start, quiz_type\n"
                    + "		   , account_fullname as author, subject_title, quiz.quiz_status\n"
                    + "	from quiz_practice_db.quiz \n"
                    + "	join quiz_practice_db.quiz_account on quiz_account.quiz_id = quiz.quiz_id\n"
                    + "	join quiz_practice_db.account_profile as ap on quiz.account_id = ap.account_id\n"
                    + "	join quiz_practice_db.`subject` on quiz.subject_id = `subject`.subject_id\n"
                    + "	where quiz.quiz_id = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getInt("quiz_id"));
                q.setTitle(rs.getString("quiz_title"));
                q.setImg(rs.getString("quiz_img"));
                q.setLevel(rs.getString("quiz_level"));
                q.setRate(rs.getDouble("quiz_rate"));
                q.setEndTime(rs.getDate("quiz_time_end"));
                q.setStartTime(rs.getDate("quiz_time_start"));
                q.setType(rs.getString("quiz_type"));

                Account a = new Account();
                a.setFullname(rs.getString("author"));
                q.setAuthor(a);

                Subject s = new Subject();
                s.setSubject_title(rs.getString("subject_title"));
                q.setSubject(s);
                q.setQuestions(getQuestionOfQuiz(q.getId()));
                q.setStatus(rs.getString("quiz_status"));
                return q;

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//    public static void main(String[] args) {
//        QuizDAO q = new QuizDAO();
//        int id = 2;
//        Quiz qu = q.getQuizDetail(id);
//        System.out.println(qu.getTitle());
//    }
    public ArrayList<Quiz> getQuizzesPractice(int accountID, int pageindex, int pagesize) {
        ArrayList<Quiz> quizs = new ArrayList<>();
        try {
            String sql = "select * from (\n"
                    + "	select row_number() over (order by quiz.quiz_id ) as stt, quiz.quiz_id, quiz_title, quiz_img, quiz_level \n"
                    + "		   , quiz_rate, quiz_time_end, quiz_time_start, quiz_type\n"
                    + "		   , account_fullname as author, subject_title, quiz.quiz_status\n"
                    + "	from quiz \n"
                    + "	join quiz_account on quiz_account.quiz_id = quiz.quiz_id\n"
                    + "	join `account` on `account`.account_id = quiz.account_id\n"
                    + "	join account_profile on account_profile.account_id = `account`.account_id\n"
                    + "	join `subject` on quiz.subject_id = `subject`.subject_id\n"
                    + "	where quiz_account.account_id = ?\n"
                    + "	order by quiz_time_start desc\n"
                    + ") as quizz\n";
            if (pageindex != 0 || pagesize != 0) {
                int start = (pageindex - 1) * pagesize;
                sql += "limit " + start + "," + pagesize + ";";
            }
//            System.out.println(sql);
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
                q.setStatus(rs.getString(12));
                quizs.add(q);

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return quizs;
    }

    public int totalRowsForQuizPractice(int accountID) {
        String sql = "select count(*) from (\n"
                + "	select row_number() over (order by quiz.quiz_id ) as stt, quiz.quiz_id, quiz_title, quiz_img, quiz_level \n"
                + "		   , quiz_rate, quiz_time_end, quiz_time_start, quiz_type\n"
                + "		   , account_fullname as author, subject_title, quiz.quiz_status\n"
                + "	from quiz \n"
                + "	join quiz_account on quiz_account.quiz_id = quiz.quiz_id\n"
                + "	join `account` on `account`.account_id = quiz.account_id\n"
                + "	join account_profile on account_profile.account_id = `account`.account_id\n"
                + "	join `subject` on quiz.subject_id = `subject`.subject_id\n"
                + "	where quiz_account.account_id = ?\n"
                + "	order by quiz_time_start desc\n"
                + ") as quizz\n";
//            System.out.println(sql);
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
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
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return questions;
    }

    public ArrayList<Quiz> getAllQuiz(String key, int pageindex, int pagesize) {
        ArrayList<Quiz> quizs = new ArrayList<>();
        try {
            String sql = "select * from (\n"
                    + "	select row_number() over (order by quiz.quiz_id ) as stt, quiz.quiz_id, quiz_title, quiz_img, quiz_level \n"
                    + "		   , quiz_rate, quiz_time_end, quiz_time_start, quiz_type\n"
                    + "		   , account_fullname as author, subject_title, quiz.quiz_status\n"
                    + "	from quiz \n"
                    + "	join quiz_account on quiz_account.quiz_id = quiz.quiz_id\n"
                    + "	join `account` on `account`.account_id = quiz.account_id\n"
                    + "	join account_profile on account_profile.account_id = `account`.account_id\n"
                    + "	join `subject` on quiz.subject_id = `subject`.subject_id\n"
                    + "	where (1=1)\n";

            if (key != null) {
                sql += "        and quiz_title like '%" + key + "%'\n";
            }
            sql += "	order by quiz_time_start desc\n"
                    + ") as quizz limit ";
            if (key == null) {
                sql += "0,12;";
            } else {
                int start = (pageindex - 1) * pagesize;
                sql += start + "," + pagesize + ";";
            }
//            System.out.println(sql);
            PreparedStatement stm = connection.prepareStatement(sql);
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
                q.setStatus(rs.getString(12));
                quizs.add(q);

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return quizs;
    }

    public Quiz getQuizById(int id) {
        try {
            String sql = "select quiz.quiz_id, quiz_title, quiz_img, quiz_level \n"
                    + "		   , quiz_rate, quiz_time_end, quiz_time_start, quiz_type\n"
                    + "		   , account_fullname as author, subject_title, quiz.quiz_status\n"
                    + "	from quiz \n"
                    + "	join quiz_account on quiz_account.quiz_id = quiz.quiz_id\n"
                    + "	join `account` on `account`.account_id = quiz.account_id\n"
                    + "	join account_profile on account_profile.account_id = `account`.account_id\n"
                    + "	join `subject` on quiz.subject_id = `subject`.subject_id\n"
                    + "	where quiz.quiz_id = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getInt(1));
                q.setTitle(rs.getString(2));
                q.setImg(rs.getString(3));
                q.setLevel(rs.getString(4));
                q.setRate(rs.getDouble(5));
                q.setEndTime(rs.getDate(6));
                q.setStartTime(rs.getDate(7));
                q.setType(rs.getString(8));
                Account a = new Account();
                a.setFullname(rs.getString(9));
                q.setAuthor(a);
                Subject s = new Subject();
                s.setSubject_title(rs.getString(10));
                q.setSubject(s);
                q.setQuestions(getQuestionOfQuiz(q.getId()));
                q.setStatus(rs.getString(11));
                return q;

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkHasJoin(int accountID, int quizID) {
        try {
            String sql = "select * from quiz_account\n"
                    + "where account_id = ? and quiz_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, quizID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
