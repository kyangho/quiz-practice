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
import model.Answer;
import model.Category;
import model.Question;
import model.Quiz;
import model.Quiz_Account;
import model.Subject;

/**
 *
 * @author Yankee
 */
public class QuizDAO extends DBContext {

    PreparedStatement ps = null;

    public ArrayList<Quiz> getQuiz(int pageSize, int pageIndex, String subject, String category, String quiz_type, String search_quiz_title) {
        ArrayList<Quiz> quizs = new ArrayList<>();
//        String sql = "SELECT q.quiz_id, q.quiz_title, s.subject_title, c.category_name, q.quiz_level\n"
//                + ", q.quiz_type, ap.account_fullname as Author, q.quiz_status\n"
//                + "FROM quiz as q \n"
//                + "join subject as s on q.subject_id = s.subject_id \n"
//                + "join category as c on q.category_id = c.category_id\n"
//                + "join account_profile as ap on q.account_id = ap.account_id\n";
        String sql = "select * from (select row_number()over (order by quiz_id asc) as stt,\n"
                + "q.quiz_id, q.quiz_title, s.subject_title, c.category_name, q.quiz_level\n"
                + "                , q.quiz_type, ap.account_fullname as Author, q.quiz_status\n"
                + "                FROM quiz as q \n"
                + "                join subject as s on q.subject_id = s.subject_id \n"
                + "                join category as c on q.category_id = c.category_id\n"
                + "                join account_profile as ap on q.account_id = ap.account_id";
        if (subject != null && category != null && quiz_type != null) {
            if (!subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "' and c.category_name = '" + category + "' and q.quiz_type = '" + quiz_type + "'";
            }
            if (subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where  c.category_name = '" + category + "' and q.quiz_type = '" + quiz_type + "'";
            }
            if (subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where q.quiz_type = '" + quiz_type + "' ";
            }
            if (subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                sql += " where c.category_name = '" + category + "'";
            }
            if (!subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "'  and q.quiz_type = '" + quiz_type + "'";
            }
            if (!subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "'";
            }
            if (!subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "' and c.category_name = '" + category + "'";
            }
        }
        if (search_quiz_title != null) {
            sql += " where q.quiz_title like '%" + search_quiz_title + "%'";
        }
        sql += " ) as t where  t.stt >= (? - 1)*? + 1 AND t.stt <= ? * ?      ";
        System.out.println(sql);
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pageSize);
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
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
                quizs.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quizs;
    }

    public int getRowcount(String subject, String category, String quiz_type, String search_quiz_title) {
        try {
            String sql = "select count(*) as total From  quiz\n"
                    + "join subject as s on quiz.subject_id = s.subject_id\n"
                    + "join category as c on quiz.category_id = c.category_id\n"
                    + "join account_profile as ap on quiz.account_id = ap.account_id";
            if (subject != null && category != null && quiz_type != null) {
                if (!subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                    sql += " where subject_title = '" + subject + "' and category_name = '" + category + "' and quiz_type = '" + quiz_type + "'; ";
                }
                if (subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                    sql += " where  category_name = '" + category + "' and quiz_type = '" + quiz_type + "'; ";
                }
                if (subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                    sql += " where quiz_type = '" + quiz_type + "'; ";
                }
                if (subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                    sql += " where category_name = '" + category + "'; ";
                }
                if (!subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                    sql += " where subject_title = '" + subject + "'  and quiz_type = '" + quiz_type + "'; ";
                }
                if (!subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                    sql += " where subject_title = '" + subject + "'; ";
                }
                if (!subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                    sql += " where subject_title = '" + subject + "' and category_name = '" + category + "'; ";
                }
            }
            if (search_quiz_title != null) {
                sql += "where quiz_title like '%" + search_quiz_title + "%'";
            }
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return -1;
    }

    public ArrayList<Subject> getsubs() {
        ArrayList<Subject> s = new ArrayList<>();
        String sql = "SELECT * FROM subject;";
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
        String sql = "SELECT * FROM category;";
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
            String sql = " select quiz.quiz_id, quiz_title, quiz_img, quiz_level\n"
                    + "                           , quiz_rate, quiz_time_end, quiz_time_start, quiz_type\n"
                    + "                           , account_fullname as author, subject_title, quiz.quiz_status, c.category_value, quiz_account.quiz_rate,\n"
                    + "                           question.question_content\n"
                    + "                           from quiz \n"
                    + "                           join quiz_account on quiz_account.quiz_id = quiz.quiz_id\n"
                    + "                           join account_profile as ap on quiz.account_id = ap.account_id\n"
                    + "                           join category as c on quiz.category_id = c.category_id \n"
                    + "                           join `subject` on quiz.subject_id = `subject`.subject_id\n"
                    + "                           join quiz_question as qq on quiz.quiz_id = qq.quiz_id\n"
                    + "                           join `question` on qq.question_id = question.question_id\n"
                    + "                           where quiz.quiz_id = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            System.out.println(sql);
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getInt("quiz_id"));
                q.setTitle(rs.getString("quiz_title"));
                q.setImg(rs.getString("quiz_img"));
                q.setLevel(rs.getString("quiz_level"));
                q.setRate(rs.getDouble("quiz_rate"));
                q.setType(rs.getString("quiz_type"));
                q.setRate(rs.getInt("quiz_rate"));

                Account a = new Account();
                a.setFullname(rs.getString("author"));
                q.setAuthor(a);

                Category c = new Category();
                c.setCategory_value(rs.getString("category_value"));
                q.setCategory(c);

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

    public void insertQuiz(Quiz q) {
        try {
            String insert_quiz = "INSERT INTO `quiz` (`quiz_title`, `subject_id`, "
                    + "`category_id`, `quiz_level`, `account_id`, `quiz_type`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps_insert_quiz = connection.prepareStatement(insert_quiz);
            ps_insert_quiz.setString(1, q.getTitle());
            ps_insert_quiz.setInt(2, q.getSubject().getSubject_id());
            ps_insert_quiz.setInt(3, q.getCategory().getCategory_id());
            ps_insert_quiz.setString(4, q.getLevel());
            ps_insert_quiz.setInt(5, q.getAuthor().getId());
            ps_insert_quiz.setString(6, q.getType());
            System.out.println(insert_quiz);
            ps_insert_quiz.executeUpdate();

            for (Question question : q.getQuestions()) {
                insertQues(question.getContent());
            }
            for (Question question : q.getQuestions()) {
                String s = question.getContent();
                Question qu = new Question();
                qu.setContent(s);
                getquestion(qu.getContent(), q.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Quiz_Account> getQuizzesPractice(int accountID, int pageindex, int pagesize) {
        ArrayList<Quiz_Account> qa = new ArrayList<>();
        try {
            String sql = "select * from (        \n"
                    + "select row_number() over (order by quiz_id ) as stt, qa.*\n"
                    + "from quiz_account qa\n"
                    + "where account_id = ?\n"
                    + "order by time_join desc\n"
                    + ") as quizz\n";
            if (pageindex != 0 && pagesize != 0) {
                int start = (pageindex - 1) * pagesize;
                sql += "limit " + start + "," + pagesize + ";";
            }
//            System.out.println(sql);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            ResultSet rs = stm.executeQuery();
            AccountDAO adb = new AccountDAO();
            while (rs.next()) {
                Quiz_Account q = new Quiz_Account();
                q.setAccount(adb.getAccountById(accountID));
                q.setQuiz(getQuizById(rs.getInt(2)));
                q.setRate(rs.getDouble(5));
                q.setTimeJoin(rs.getDate(4));
                q.setDuration(rs.getDouble(6));
                qa.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return qa;
    }

    public int totalRowsForQuizPractice(int accountID) {
        String sql = "select count(*)"
                + "from quiz_account qa\n"
                + "where account_id = ?\n"
                + "order by time_join desc\n";
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

    public void editQuiz(Quiz q) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `quiz` SET `quiz_title` = ?, `subject_id` = ?, `category_id` = ?, `quiz_level` = ?,\n"
                    + "`quiz_type` = ?\n"
                    + "WHERE (`quiz_id` = ?) ;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, q.getTitle());
            ps.setInt(2, q.getSubject().getSubject_id());
            ps.setInt(3, q.getCategory().getCategory_id());
            ps.setString(4, q.getLevel());
            ps.setString(5, q.getType());
            ps.setInt(6, q.getId());
            ps.executeUpdate();

            int qid = q.getId();
            for (Question question : q.getQuestions()) {
                int[] qqid = {question.getId()};
                deleteQuizQues(qid, qqid);
            }

            for (Question question : q.getQuestions()) {
                insertQues(question.getContent());
            }
            for (Question question : q.getQuestions()) {
                String s = question.getContent();
                Question qu = new Question();
                qu.setContent(s);
                getquestion(qu.getContent(), q.getId());
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void inset_quiz_ques(int quiz_id, int ques_id) {
        try {
            String insert_quiz_ques = "INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (?, ?);";
            PreparedStatement ps_insert_quiz_ques = connection.prepareStatement(insert_quiz_ques);
            ps_insert_quiz_ques.setInt(1, quiz_id);
            ps_insert_quiz_ques.setInt(2, ques_id);
            ps_insert_quiz_ques.executeUpdate();
            System.out.println(insert_quiz_ques);
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteQuiz(int q) {
        try {
            String delete_quiz_acc = "DELETE FROM `quiz_account` WHERE (`quiz_id` = ?);";
            PreparedStatement ps_dqa = connection.prepareStatement(delete_quiz_acc);
            ps_dqa.setInt(1, q);
            ps_dqa.executeUpdate();
            String delete_quiz_ques = "DELETE FROM `quiz_question` WHERE (`quiz_id` = ?);";
            PreparedStatement ps_dqq = connection.prepareStatement(delete_quiz_ques);
            ps_dqq.setInt(1, q);
            ps_dqq.executeUpdate();
            String delete_quiz = "DELETE FROM `quiz` WHERE (`quiz_id` = ?);";
            PreparedStatement ps_dq = connection.prepareStatement(delete_quiz);
            ps_dq.setInt(1, q);
            ps_dq.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteQuizQues(int q, int[] qid) {
        try {
            String delete_question = "DELETE FROM `quiz_question` WHERE (`quiz_id` = ?);";
            PreparedStatement stm = connection.prepareStatement(delete_question);
            stm.setInt(1, q);
            stm.executeUpdate();

            for (int i : qid) {
                String sql = "DELETE FROM `question` WHERE (`question_id` = ?);";
                PreparedStatement psd = connection.prepareStatement(sql);
                psd.setInt(1, i);
                psd.executeUpdate();
                System.out.println(sql + " " + delete_question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertQues(String q) {
        try {
            String insert_ques = "INSERT INTO `question` (`question_content`) VALUES (?);";
            PreparedStatement ps_insert = connection.prepareStatement(insert_ques);
            ps_insert.setString(1, q);
            ps_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Question> getquestion(String s, int id) {
        ArrayList<Question> qs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM question where question.question_content = ?;";
            PreparedStatement ps_qq = connection.prepareStatement(sql);
            ps_qq.setString(1, s);
            ResultSet rs = ps_qq.executeQuery();
            while (rs.next()) {
                Question ques = new Question();
                ques.setId(rs.getInt("question_id"));
                ques.setContent(rs.getString("question_content"));
                qs.add(ques);
            }
            for (Question q : qs) {
                inset_quiz_ques(id, q.getId());
            }
            System.out.println(sql);
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qs;
    }

    private ArrayList<Question> getQuestionOfQuiz(int quizID) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = "select * from question\n"
                    + "   join quiz_question on question.question_id = quiz_question.question_id\n"
                    + "   where quiz_id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quizID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question q = new Question(rs.getInt(1), rs.getString(2), rs.getString(3));
                q.setMedia(rs.getBlob(9));
                q.setAnswers(getAnswerOfQues(q.getId()));
                questions.add(q);

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return questions;
    }

    public Quiz getQuizById(int id) {
        try {
            String sql = "select q.quiz_id, quiz_name, ap.account_fullname as author, quiz_title, quiz_type,\n"
                    + "		subject_title, st.setting_value as category, st1.setting_value as `level`,\n"
                    + "        q.quiz_rate\n"
                    + "from quiz q\n"
                    + "join `subject` s on s.subject_id = q.quiz_subject\n"
                    + "join account_profile ap on ap.account_id = q.quiz_author\n"
                    + "join setting as st on st.setting_id = q.quiz_category\n"
                    + "join setting as st1 on st1.setting_id = q.quiz_level\n"
                    + "where q.quiz_id = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getInt(1));
                q.setName(rs.getString(2));
                Account a = new Account();
                a.setFullname(rs.getString(3));
                q.setAuthor(a);
                q.setTitle(rs.getString(4));
                q.setType(rs.getString(5));
                Subject s = new Subject();
                s.setSubject_title(rs.getString(6));
                q.setSubject(s);
                Category cate = new Category();
                cate.setCategory_value(rs.getString(7));
                q.setCategory(cate);
                q.setLevel(rs.getString(8));
                q.setRate(rs.getDouble(9));
                q.setQuestions(getQuestionOfQuiz(q.getId()));
                return q;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Quiz_Account getPracticeByQuizID(int id, int accountid) {
        try {
            String sql = "select *  from quiz_account where account_id = ? and quiz_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountid);
            stm.setInt(2, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Quiz_Account q = new Quiz_Account();
                q.setQuiz(getQuizById(1));
                q.setDuration(rs.getDouble(5));
                q.setRate(rs.getDouble(4));
                q.setTimeJoin(rs.getDate(3));
                return q;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        QuizDAO qdb = new QuizDAO();
//        for (Question q : qdb.getQuizById(1).getQuestions()) {
//            System.out.println(q.toString());
//        }
        System.out.println(qdb.getPracticeByQuizID(1, 3).getQuiz().getName());
//        System.out.println(qdb.totalRowsForQuizPractice(3));
    }

}
