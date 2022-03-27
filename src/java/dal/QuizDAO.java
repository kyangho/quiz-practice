/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import controller.TypeConfigController;
import java.sql.Connection;
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
import model.Setting;
import model.Subject;
import model.Type;
import model.Ques_Ans;

/**
 *
 * @author Yankee
 */
public class QuizDAO extends DBContext {

    PreparedStatement ps = null;

    public ArrayList<Quiz> getQuiz(int pageSize, int pageIndex, String subject, String category, String quiz_type, String search_quiz_title) {
        Connection connection = getConnection();
        ArrayList<Quiz> quizs = new ArrayList<>();
        String sql = "select * from (select row_number()over (order by quiz_id asc) as stt,\n"
                + "q.quiz_id, q.quiz_title, s.subject_title, c.setting_name, q.quiz_level\n"
                + "                , q.quiz_type, ap.account_fullname as Author\n"
                + "                FROM quiz as q \n"
                + "                left join subject as s on q.quiz_subject = s.subject_id \n"
                + "                left join setting as c on q.quiz_category = c.setting_id\n"
                + "                left join quiz_account as qa on qa.quiz_id = q.quiz_id"
                + "                left join account_profile as ap on qa.account_id = ap.account_id";
        if (subject != null && category != null && quiz_type != null) {
            if (!subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "' and c.setting_name = '" + category + "' and q.quiz_type = '" + quiz_type + "'";
            }
            if (subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where  c.setting_name = '" + category + "' and q.quiz_type = '" + quiz_type + "'";
            }
            if (subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where q.quiz_type = '" + quiz_type + "' ";
            }
            if (subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                sql += " where c.setting_name = '" + category + "'";
            }
            if (!subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "'  and q.quiz_type = '" + quiz_type + "'";
            }
            if (!subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "'";
            }
            if (!subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                sql += " where s.subject_title = '" + subject + "' and c.setting_name = '" + category + "'";
            }
        }
        if (search_quiz_title != null) {
            sql += " where q.quiz_title like '%" + search_quiz_title + "%'";
        }
        sql += " ) as t where  t.stt >= (? - 1)*? + 1 AND t.stt <= ? * ?      ";
//        System.out.println(sql);
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

                Subject sub = new Subject();
                sub.setSubject_title(rs.getString("subject_title"));
                q.setSubject(sub);

                Category cate = new Category();
                cate.setCategory_name(rs.getString("setting_name"));
                q.setCategory(cate);
                Account acc = new Account();
                acc.setFullname(rs.getString("Author"));
                q.setAuthor(acc);
                quizs.add(q);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return quizs;
    }

    public static void main(String[] args) {
        QuizDAO q = new QuizDAO();
        q.getQuiz(1, 3, null, null, null, null);
        System.out.println();

    }

    public int getRowcount(String subject, String category, String quiz_type, String search_quiz_title) {
        Connection connection = getConnection();
        try {
            String sql = "select count(*) as total From  quiz\n"
                    + "left join subject as s on quiz.quiz_subject = s.subject_id\n"
                    + "left join setting as c on quiz.quiz_category = c.setting_id\n"
                    + "left join quiz_account as qa on qa.quiz_id = quiz.quiz_id\n"
                    + "join account_profile as ap on qa.account_id = ap.account_id";
            if (subject != null && category != null && quiz_type != null) {
                if (!subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                    sql += " where subject_title = '" + subject + "' and setting_name = '" + category + "' and quiz_type = '" + quiz_type + "'; ";
                }
                if (subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                    sql += " where  setting_name = '" + category + "' and quiz_type = '" + quiz_type + "'; ";
                }
                if (subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                    sql += " where quiz_type = '" + quiz_type + "'; ";
                }
                if (subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                    sql += " where setting_name = '" + category + "'; ";
                }
                if (!subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && !quiz_type.equalsIgnoreCase("all")) {
                    sql += " where subject_title = '" + subject + "'  and quiz_type = '" + quiz_type + "'; ";
                }
                if (!subject.equalsIgnoreCase("all") && category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                    sql += " where subject_title = '" + subject + "'; ";
                }
                if (!subject.equalsIgnoreCase("all") && !category.equalsIgnoreCase("all") && quiz_type.equalsIgnoreCase("all")) {
                    sql += " where subject_title = '" + subject + "' and setting_name = '" + category + "'; ";
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
            ps.close();;
        } catch (SQLException e) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public ArrayList<Subject> getSubs() {
        Connection connection = getConnection();
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
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return s;
    }

    public ArrayList<Category> getCates() {
        Connection connection = getConnection();
        ArrayList<Category> c = new ArrayList<>();
        String sql = "SELECT * FROM setting where setting_type = 'Category'";
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category cate = new Category();
                cate.setCategory_id(rs.getInt("setting_id"));
                cate.setCategory_name(rs.getString("setting_name"));
                cate.setCategory_value(rs.getString("setting_value"));
                c.add(cate);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }

    public Quiz getQuizDetail(int id) {
        Connection connection = getConnection();
        try {
            String sql = " select quiz.quiz_id, quiz_title, quiz_level\n"
                    + "                           , quiz_rate, quiz_duration, quiz_type\n"
                    + "                           , account_fullname as author, subject_title, c.setting_value, c.setting_name, quiz_account.rate,\n"
                    + "                           question.question_content\n"
                    + "                           from quiz \n"
                    + "                           left join quiz_account on quiz_account.quiz_id = quiz.quiz_id\n"
                    + "                           left join account_profile as ap on quiz_account.account_id = ap.account_id\n"
                    + "                           left join setting as c on quiz.quiz_category = c.setting_id \n"
                    + "                           left join `subject` on quiz.quiz_subject = `subject`.subject_id\n"
                    + "                           left join quiz_question as qq on quiz.quiz_id = qq.quiz_id\n"
                    + "                           left join `question` on qq.question_id = question.question_id\n"
                    + "                           where quiz.quiz_id = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
//            System.out.println(sql);
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getInt("quiz_id"));
                q.setTitle(rs.getString("quiz_title"));
                q.setLevel(rs.getString("quiz_level"));
                q.setRate(rs.getDouble("quiz_rate"));
                q.setType(rs.getString("quiz_type"));
                q.setRate(rs.getInt("quiz_rate"));
                q.setDuration(rs.getInt("quiz_duration"));
                Account a = new Account();
                a.setFullname(rs.getString("author"));
                q.setAuthor(a);

                Category c = new Category();
                c.setCategory_name(rs.getString("setting_name"));
                c.setCategory_value(rs.getString("setting_value"));
                q.setCategory(c);

                Subject s = new Subject();
                s.setSubject_title(rs.getString("subject_title"));
                q.setSubject(s);

                q.setQuestions(getQuestionOfQuiz(q.getId()));
                return q;
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int insertQuiz(Quiz q, String type) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String insert_quiz = "INSERT INTO `quiz`\n"
                    + "(`quiz_name`,`quiz_title`,`quiz_subject`,`quiz_category`,\n"
                    + "`quiz_author`,`quiz_description`,`quiz_duration`,`quiz_level`,\n"
                    + "`quiz_rate`,`quiz_type`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps_insert_quiz = connection.prepareStatement(insert_quiz);
            ps_insert_quiz.setString(1, q.getName());
            ps_insert_quiz.setString(2, q.getTitle());
            if (q.getSubject().getSubject_id() != 0) {
                ps_insert_quiz.setInt(3, q.getSubject().getSubject_id());
            } else {
                ps_insert_quiz.setString(3, null);
            }
            if (q.getCategory().getCategory_id() != 0) {
                ps_insert_quiz.setInt(4, q.getCategory().getCategory_id());
            } else {
                ps_insert_quiz.setString(4, null);
            }
            if (q.getAuthor() != null) {
                ps_insert_quiz.setInt(5, q.getAuthor().getId());
            } else {
                ps_insert_quiz.setString(5, null);
            }
            ps_insert_quiz.setString(6, q.getDescription());
            if (q.getDuration() != 0) {
                ps_insert_quiz.setDouble(7, q.getDuration());
            } else {
                ps_insert_quiz.setString(7, null);
            }
            ps_insert_quiz.setString(8, q.getLevel());
            if (q.getRate() != 0) {
                ps_insert_quiz.setDouble(9, q.getRate());
            } else {
                ps_insert_quiz.setString(9, null);
            }
            ps_insert_quiz.setString(10, q.getType());
//            System.out.println(insert_quiz);
            ps_insert_quiz.executeUpdate();

            String sqlGetLastId = "select LAST_INSERT_ID() as id";
            PreparedStatement stm2 = connection.prepareStatement(sqlGetLastId);
            ResultSet rs = stm2.executeQuery();
            if (rs.next()) {
                q.setId(rs.getInt(1));
            }
            if (type.equals("practice")) {
                for (Question question : q.getQuestions()) {
                    inset_quiz_ques(q.getId(), question.getId());
                }

            } else {
                for (Question question : q.getQuestions()) {
                    insertQues(question.getContent());
                }
                for (Question question : q.getQuestions()) {
                    String s = question.getContent();
                    Question qu = new Question();
                    qu.setContent(s);
                    getQuestion(qu.getContent(), q.getId());
                }
            }
            ps_insert_quiz.close();
            stm2.close();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return q.getId();
    }

    public int insertPractice(Quiz q) {
        Connection connection = getConnection();
        q.setId(-1);
        try {
            connection.setAutoCommit(false);
            String insert_quiz = "INSERT INTO `quiz`\n"
                    + "(`quiz_name`,`quiz_title`,`quiz_subject`,`quiz_category`,\n"
                    + "`quiz_author`,`quiz_description`,`quiz_duration`,`quiz_level`,\n"
                    + "`quiz_rate`,`quiz_type`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps_insert_quiz = connection.prepareStatement(insert_quiz);
            ps_insert_quiz.setString(1, q.getName());
            ps_insert_quiz.setString(2, q.getTitle());
            if (q.getSubject().getSubject_id() != 0) {
                ps_insert_quiz.setInt(3, q.getSubject().getSubject_id());
            } else {
                ps_insert_quiz.setString(3, null);
            }
            if (q.getCategory().getCategory_id() != 0) {
                ps_insert_quiz.setInt(4, q.getCategory().getCategory_id());
            } else {
                ps_insert_quiz.setString(4, null);
            }
            if (q.getAuthor() != null) {
                ps_insert_quiz.setInt(5, q.getAuthor().getId());
            } else {
                ps_insert_quiz.setString(5, null);
            }
            ps_insert_quiz.setString(6, q.getDescription());
            if (q.getDuration() != 0) {
                ps_insert_quiz.setDouble(7, q.getDuration());
            } else {
                ps_insert_quiz.setDouble(7, 15);
            }
            ps_insert_quiz.setString(8, q.getLevel());
            if (q.getRate() != 0) {
                ps_insert_quiz.setDouble(9, q.getRate());
            } else {
                ps_insert_quiz.setDouble(9, 50);
            }
            ps_insert_quiz.setString(10, q.getType());
            ps_insert_quiz.executeUpdate();

            String sqlGetLastId = "select LAST_INSERT_ID() as id";
            PreparedStatement stm2 = connection.prepareStatement(sqlGetLastId);
            ResultSet rs = stm2.executeQuery();
            if (rs.next()) {
                q.setId(rs.getInt(1));
            }
            for (Question question : q.getQuestions()) {
                String insert_quiz_ques = "INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (?, ?);";
                PreparedStatement ps_insert_quiz_ques = connection.prepareStatement(insert_quiz_ques);
                ps_insert_quiz_ques.setInt(1, q.getId());
                ps_insert_quiz_ques.setInt(2, question.getId());
                ps_insert_quiz_ques.executeUpdate();
                ps_insert_quiz_ques.close();
            }
            ps_insert_quiz.close();
            stm2.close();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return q.getId();
    }

    public ArrayList<Quiz_Account> getQuizzesPractice(int accountID, int pageindex, int pagesize) {
        Connection connection = getConnection();
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return qa;
    }

    public int totalRowsForQuizPractice(int accountID) {
        Connection connection = getConnection();
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    private ArrayList<Answer> getAnswerOfQues(int questionID) {
        Connection connection = getConnection();
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            String sql = "select * from answer where question_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, questionID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                answers.add(new Answer(rs.getInt(1), rs.getString(2)));
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return answers;
    }

    public void editQuiz(Quiz q) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `quiz` SET `quiz_title` = ?, `subject_id` = ?, `setting_id` = ?, `quiz_level` = ?,\n"
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
                getQuestion(qu.getContent(), q.getId());
            }
            ps.close();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void inset_quiz_ques(int quiz_id, int ques_id) {
        Connection connection = getConnection();
        try {
            String insert_quiz_ques = "INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (?, ?);";
            PreparedStatement ps_insert_quiz_ques = connection.prepareStatement(insert_quiz_ques);
            ps_insert_quiz_ques.setInt(1, quiz_id);
            ps_insert_quiz_ques.setInt(2, ques_id);
            ps_insert_quiz_ques.executeUpdate();
            ps_insert_quiz_ques.close();
//            System.out.println(insert_quiz_ques);
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteQuiz(int q) {
        Connection connection = getConnection();
        try {
            String delete_quiz_acc = "DELETE FROM `quiz_account` WHERE (`quiz_id` = ?);";
            PreparedStatement ps_dqa = connection.prepareStatement(delete_quiz_acc);
            ps_dqa.setInt(1, q);
            ps_dqa.executeUpdate();
            ps_dqa.close();
            String delete_quiz_ques = "DELETE FROM `quiz_question` WHERE (`quiz_id` = ?);";
            PreparedStatement ps_dqq = connection.prepareStatement(delete_quiz_ques);
            ps_dqq.setInt(1, q);
            ps_dqq.executeUpdate();
            ps_dqq.close();
            String delete_quiz = "DELETE FROM `quiz` WHERE (`quiz_id` = ?);";
            PreparedStatement ps_dq = connection.prepareStatement(delete_quiz);
            ps_dq.setInt(1, q);
            ps_dq.executeUpdate();
            ps_dq.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void deleteQuizQues(int q, int[] qid) {
        Connection connection = getConnection();
        try {
            String delete_question = "DELETE FROM `quiz_question` WHERE (`quiz_id` = ?);";
            PreparedStatement stm = connection.prepareStatement(delete_question);
            stm.setInt(1, q);
            stm.executeUpdate();
            stm.close();
            for (int i : qid) {
                String sql = "DELETE FROM `question` WHERE (`question_id` = ?);";
                PreparedStatement psd = connection.prepareStatement(sql);
                psd.setInt(1, i);
                psd.executeUpdate();
                System.out.println(sql + " " + delete_question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertQues(String q) {
        Connection connection = getConnection();
        try {
            String insert_ques = "INSERT INTO `question` (`question_content`) VALUES (?);";
            PreparedStatement ps_insert = connection.prepareStatement(insert_ques);
            ps_insert.setString(1, q);
            ps_insert.executeUpdate();
            ps_insert.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Question> getQuestion(String s, int id) {
        Connection connection = getConnection();
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
//            System.out.println(sql);
            ps_qq.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return qs;
    }

    private ArrayList<Question> getQuestionOfQuiz(int quizID) {
        Connection connection = getConnection();
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
                q.setMedia(rs.getBlob("question_media"));
                q.setAnswers(getAnswerOfQues(q.getId()));
                questions.add(q);

            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return questions;
    }

    public Quiz getQuizById(int id) {
        Connection connection = getConnection();
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Quiz_Account getPracticeByQuizID(int id, int accountid) {
        Connection connection = getConnection();
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<Setting> getQuizTypes() {
        SettingDAO sd = new SettingDAO();
        TypeConfigController tcc = new TypeConfigController();
        ArrayList<Type> types = tcc.getTypesList();
        ArrayList<Setting> settings = sd.getALLSetting(10000, 1, types.get(5).getName(), "all", "all");
        return settings;
    }

    public ArrayList<Ques_Ans> getQuestion_AnswerList(String search, int accountID) {
        QuestionDAO qdao = new QuestionDAO();
        Connection connection = getConnection();
        ArrayList<Ques_Ans> ques_Anses = new ArrayList<>();
        try {
            String sql = "SELECT q.question_id, answer_id, qu.quiz_id FROM user_answer ua\n"
                    + "left join  question q on ua.question_id = q.question_id\n"
                    + "left join quiz qu on qu.quiz_id = ua.quiz_id\n"
                    + "where account_id = ?\n";
            if (search.equals("wrong")) {
                sql += "and answer_id <> question_correct_answer and answer_id is not null";
            } else if (search.equals("correct")) {
                sql += "and answer_id = question_correct_answer";
            } else if (search.equals("none")) {
                sql += "and answer_id is null";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Ques_Ans qa = new Ques_Ans();
                qa.setQuestion(qdao.getQuestionById(rs.getInt(1), 1));
                qa.setAnswer(rs.getString(2));
                qa.setQuiz(getQuizById(rs.getInt(3)));
                ques_Anses.add(qa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ques_Anses;
    }

    public Answer getAnswerByID(int id) {
        Connection connection = getConnection();
        try {
            String sql = "select answer_id, answer_content from answer where answer_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Answer a = new Answer(rs.getInt(1), rs.getString(2));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
