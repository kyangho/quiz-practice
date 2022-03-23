package dal;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Category;
import model.Levels;
import model.Question;
import model.Quiz;
import model.Subcategory;
import model.Subject;

/**
 *
 * @author conmu
 */
public class QuestionDAO extends DBContext {

    public ArrayList<Question> getQuestions(int accountId, int pageindex, int pagesize, String key, String subject,
            String subcategory, String level, String status) {
        ArrayList<Question> questions = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "select * from (\n"
                    + "	  select  row_number()over (order by q.question_id asc) as stt,\n"
                    + "		q.*, s.subject_title, sb.subcategory_name, quiz.quiz_category, st.setting_value as category,\n"
                    + "        st1.setting_value as `level`\n"
                    + "from question q\n"
                    + "left join quiz_question qq on qq.question_id = q.question_id\n"
                    + "left join quiz on qq.quiz_id = quiz.quiz_id\n"
                    + "left join subcategory sb on sb.subcategory_id = q.subcategory_id\n"
                    + "left join `subject` s on s.subject_id = q.subject_id\n"
                    + "left join setting st on quiz.quiz_category = st.setting_id\n"
                    + "left join setting st1 on q.question_level = st1.setting_id\n"
                    + "where (1=1)\n";
            if (accountId != 1) {
                sql += "AND quiz_author = " + accountId + "\n";
            }
            int paramIndex = 0;
            HashMap<Integer, Object[]> params = new HashMap<>();
            if (key != null) {
                sql += " and question_content  like '%" + key + "%'\n";
            }
            if (subject != null && !subject.equals("all")) {
                sql += " AND s.subject_id = ?\n";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getName();
                param[1] = subject;
                params.put(paramIndex, param);
            }
            if (subcategory != null && !subcategory.equals("all")) {
                sql += " AND sb.subcategory_id = ?\n";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getName();
                param[1] = subcategory;
                params.put(paramIndex, param);
            }
            if (level != null && !level.equals("all")) {
                sql += " AND st1.setting_value = ?\n";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getName();
                param[1] = level;
                params.put(paramIndex, param);
            }
            if (status != null && !status.equals("all")) {
                sql += " AND question_status = ?\n";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getName();
                param[1] = status;
                params.put(paramIndex, param);
            }
            if (pageindex != 0 || pagesize != 0) {
                int start = (pageindex - 1) * pagesize;
                sql += "limit " + start + "," + pagesize + "\n";
            }

            sql += ") as ques";
//            System.out.println(sql);
            stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object[]> entry : params.entrySet()) {
                Integer index = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if (type.equals(String.class.getName())) {
                    stm.setString(index, value[1].toString());
                }
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("question_id"));
                q.setContent(rs.getString("question_content"));
                q.setCategory(new Category(rs.getInt("quiz_category"), rs.getString("category")));
                q.setSubject(new Subject(rs.getInt("subject_id"), rs.getString("subject_title")));
                q.setSubCategory(new Subcategory(rs.getInt("subcategory_id"), rs.getString("subcategory_name")));
                q.setLevel(rs.getString("level"));
                q.setStatus(rs.getString("question_status"));
                questions.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return questions;
    }

    public ArrayList<Subcategory> getSubcategorys() {
        ArrayList<Subcategory> subcategorys = new ArrayList<>();
        try {
            String sql = "select * from subcategory";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                subcategorys.add(new Subcategory(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return subcategorys;
    }

    public int getTotalRows(int accountId, String key, String subject,
            String subcategory, String level, String status) {

        String sql = "select count(*)\n"
                + "from question q\n"
                + "left join quiz_question qq on qq.question_id = q.question_id\n"
                + "left join quiz on qq.quiz_id = quiz.quiz_id\n"
                + "left join subcategory sb on sb.subcategory_id = q.subcategory_id\n"
                + "left join `subject` s on s.subject_id = q.subject_id\n"
                + "left join setting st on quiz.quiz_category = st.setting_id\n"
                + "left join setting st1 on q.question_level = st1.setting_id\n"
                + "	where (1=1)\n";
        if (accountId != 1) {
            sql += "AND quiz_author = " + accountId + "\n";
        }
        int paramIndex = 0;
        HashMap<Integer, Object[]> params = new HashMap<>();
        if (key != null) {
            sql += " and question_content  like '%" + key + "%'\n";
        }
        if (subject != null && !subject.equals("all")) {
            sql += " AND s.subject_id = ?\n";
            paramIndex++;
            Object[] param = new Object[2];
            param[0] = String.class.getName();
            param[1] = subject;
            params.put(paramIndex, param);
        }
        if (subcategory != null && !subcategory.equals("all")) {
            sql += " AND sb.subcategory_id = ?\n";
            paramIndex++;
            Object[] param = new Object[2];
            param[0] = String.class.getName();
            param[1] = subcategory;
            params.put(paramIndex, param);
        }
        if (level != null && !level.equals("all")) {
            sql += " AND st1.setting_value = ?\n";
            paramIndex++;
            Object[] param = new Object[2];
            param[0] = String.class.getName();
            param[1] = level;
            params.put(paramIndex, param);
        }
        if (status != null && !status.equals("all")) {
            sql += " AND question_status = ?\n";
            paramIndex++;
            Object[] param = new Object[2];
            param[0] = String.class.getName();
            param[1] = status;
            params.put(paramIndex, param);
        }
//            System.out.println(sql);
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object[]> entry : params.entrySet()) {
                Integer index = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if (type.equals(String.class.getName())) {
                    stm.setString(index, value[1].toString());
                }
            }
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return -1;
    }

    public Question getQuestionById(int id, int accountId) {
        try {
            String sql = "select question.* , quiz_category from question \n"
                    + "join quiz_question on quiz_question.question_id = question.question_id\n"
                    + "join quiz on quiz.quiz_id = quiz_question.quiz_id\n"
                    + "where question.question_id = ?\n ";
            if (accountId != 1) {
                sql += "and quiz_author = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            if (accountId != 1) {
                stm.setInt(2, accountId);
            }
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt(1));
                q.setContent(rs.getString(2));
                q.setSubCategory(new Subcategory(rs.getInt(3), null));
                q.setLevel(rs.getString(4));
                q.setStatus(rs.getString(5));
                q.setCorrectAnswer(rs.getString(6));
                q.setMedia(rs.getBlob(7));
                q.setSubject(new Subject(rs.getInt(8), null));
                q.setCategory(new Category(rs.getInt(9), null));
                q.setAnswers(getAnswerForQues(id));
                return q;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return null;
    }

    public Question getQuestionById(int id) {
        try {
            String sql = "select question.question_id, question_content, question_correct_answer, subject_id, category_id,"
                    + "question_level, question_status, subcategory_id from question\n"
                    + "join quiz_question on quiz_question.question_id = question.question_id\n"
                    + "join quiz on quiz.quiz_id = quiz_question.quiz_id\n"
                    + "where question.question_id = ? \n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt(1));
                q.setContent(rs.getString(2));
                q.setCorrectAnswer(rs.getString(3));
                q.setSubject(new Subject(rs.getInt(4), null));
                q.setCategory(new Category(rs.getInt(5), null));
                q.setLevel(rs.getString(6));
                q.setStatus(rs.getString(7));
                q.setSubCategory(new Subcategory(rs.getInt(8), null));
                q.setAnswers(getAnswerForQues(id));
                return q;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return null;
    }

    public ArrayList<Subcategory> getSubCategoryByCate(int cateId) {
        ArrayList<Subcategory> subcategorys = new ArrayList<>();
        try {
            String sql = "select * from subcategory\n"
                    + "where category_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cateId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                subcategorys.add(new Subcategory(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return subcategorys;
    }

    public ArrayList<Quiz> getQuizForQuestion(int accountId) {
        ArrayList<Quiz> quizs = new ArrayList<>();
        try {
            String sql = "select quiz_id, quiz_name from quiz\n";
            if (accountId != 1) {
                sql += "where quiz.account_id = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (accountId != 1) {
                stm.setInt(1, accountId);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getInt(1));
                q.setName(rs.getString(2));
                quizs.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return quizs;
    }

    public ArrayList<Levels> getLevel() {
        ArrayList<Levels> levels = new ArrayList<>();
        try {
            String sql = "select setting_id, setting_value from setting where setting_type = 'level'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                levels.add(new model.Levels(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return levels;
    }

    public ArrayList<Category> getCategory() {
        ArrayList<Category> categorys = new ArrayList<>();
        try {
            String sql = "select * from setting where setting_type = 'category';";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                categorys.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return categorys;
    }

    public void deleteAnswer(int answerID) {
        try {
            connection.setAutoCommit(false);
            String sql = "delete from answer where answer_id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, answerID);
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
//                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteQuestion(int quesid) {
        try {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM `question`\n"
                    + "WHERE question_id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quesid);
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
//                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private ArrayList<Answer> getAnswerForQues(int quesId) {
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            String sql = "select answer_id, answer_content from answer where question_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quesId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                answers.add(new Answer(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return answers;
    }

    public void changeStatusQues(int id, String status) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `question`\n"
                    + "SET\n"
                    + "`question_status` = ?\n"
                    + "WHERE `question_id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, id);
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
//                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean updateQuestion(String quizId, Question q, InputStream media) {
        try {
            connection.setAutoCommit(false);
            String deleteAnswer = "DELETE FROM `answer`\n"
                    + "WHERE question_id = ?;";
            PreparedStatement stm_2 = connection.prepareStatement(deleteAnswer);
            stm_2.setInt(1, q.getId());
            stm_2.executeUpdate();

            String insertAnswer = "INSERT INTO `answer`\n"
                    + "(`question_id`,\n"
                    + "`answer_content`)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?);";
            PreparedStatement stm_3 = connection.prepareStatement(insertAnswer);
            for (Answer answer : q.getAnswers()) {
                stm_3.setInt(1, q.getId());
                stm_3.setString(2, answer.getContent());
                stm_3.executeUpdate();
            }
            String sql_tmp = "select answer_id from answer where answer_content = ? and question_id = ?\n";
            PreparedStatement stm_tmp = connection.prepareStatement(sql_tmp);
            stm_tmp.setString(1, q.getCorrectAnswer());
            stm_tmp.setInt(2, q.getId());
            ResultSet rs = stm_tmp.executeQuery();
            String correctAns = null;
            if (rs.next()) {
                correctAns = new String(rs.getString(1));
            }
            String sql = "UPDATE `quiz_db`.`question`\n"
                    + "SET\n"
                    + "`question_content` = ?,\n"
                    + "`subcategory_id` = ?,\n"
                    + "`question_level` = ?,\n"
                    + "`question_status` = ?,\n"
                    + "`question_correct_answer` = ?,\n"
                    + "`subject_id` = ?\n";
            if (media != null) {
                sql += ", `question_media` = ?\n";
            }
            sql += "WHERE `question_id` = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, q.getContent());
            stm.setInt(2, q.getSubCategory().getId());
            stm.setString(3, q.getLevel());
            stm.setString(4, q.getStatus());
            stm.setString(5, correctAns);
            stm.setInt(6, q.getSubject().getSubject_id());
            if (media != null) {
                stm.setBlob(7, media);
                stm.setInt(8, q.getId());
            } else {
                stm.setInt(7, q.getId());
            }
            stm.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
//                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        QuestionDAO qdao = new QuestionDAO();
    }
}
