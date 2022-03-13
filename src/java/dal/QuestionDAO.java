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
        try {
            String sql = "select * from (\n"
                    + "	select row_number()over (order by question.question_id asc) as stt,\n"
                    + "			question.question_id, question_content, question_level,\n"
                    + "			question_status ,`subject`.subject_id\n"
                    + "			, `subject`.subject_title, category.category_id, category.category_value\n"
                    + "			, subcategory.subcategory_id, subcategory.subcategory_name\n"
                    + "	from question\n"
                    + "	join quiz_question on quiz_question.question_id = question.question_id\n"
                    + "	join quiz on quiz_question.quiz_id = quiz.quiz_id\n"
                    + "	join category on category.category_id = question.category_id\n"
                    + "	join subcategory on subcategory.subcategory_id = question.subcategory_id\n"
                    + "	join `subject` on `subject`.subject_id = question.subject_id\n"
                    + "	where (1=1)\n";
            if (accountId != 1) {
                sql += "AND account_id = " + accountId + "\n";
            }
            int paramIndex = 0;
            HashMap<Integer, Object[]> params = new HashMap<>();
            if (key != null) {
                sql += " and question_content  like '%" + key + "%'\n";
            }
            if (subject != null && !subject.equals("all")) {
                sql += " AND `subject`.subject_id = ?\n";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getName();
                param[1] = subject;
                params.put(paramIndex, param);
            }
            if (subcategory != null && !subcategory.equals("all")) {
                sql += " AND subcategory.subcategory_id = ?\n";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getName();
                param[1] = subcategory;
                params.put(paramIndex, param);
            }
            if (level != null && !level.equals("all")) {
                sql += " AND question_level = ?\n";
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
            PreparedStatement stm = connection.prepareStatement(sql);
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
                q.setCategory(new Category(rs.getInt("category_id"), rs.getString("category_value")));
                q.setSubject(new Subject(rs.getInt("subject_id"), rs.getString("subject_title")));
                q.setSubCategory(new Subcategory(rs.getInt("subcategory_id"), rs.getString("subcategory_name")));
                q.setLevel(rs.getString("question_level"));
                q.setStatus(rs.getString("question_status"));
                questions.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    public int getQuizIdOfQuestion(int quesId) {
        try {
            String sql = "select * from quiz_question\n"
                    + "where question_id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quesId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
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
        return subcategorys;
    }

    public int getTotalRows(int accountId, String key, String subject,
            String subcategory, String level, String status) {
        try {
            String sql = "select count(*)\n"
                    + "	from question\n"
                    + "	join quiz_question on quiz_question.question_id = question.question_id\n"
                    + "	join quiz on quiz_question.quiz_id = quiz.quiz_id\n"
                    + "	join category on category.category_id = question.category_id\n"
                    + "	join subcategory on subcategory.subcategory_id = question.subcategory_id\n"
                    + "	join `subject` on `subject`.subject_id = question.subject_id\n"
                    + "	where (1=1)\n";
            if (accountId != 1) {
                sql += "AND account_id = " + accountId + "\n";
            }
            int paramIndex = 0;
            HashMap<Integer, Object[]> params = new HashMap<>();
            if (key != null) {
                sql += " and question_content  like '%" + key + "%'\n";
            }
            if (subject != null && !subject.equals("all")) {
                sql += " AND `subject`.subject_id = ?\n";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getName();
                param[1] = subject;
                params.put(paramIndex, param);
            }
            if (subcategory != null && !subcategory.equals("all")) {
                sql += " AND subcategory.subcategory_id = ?\n";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getName();
                param[1] = subcategory;
                params.put(paramIndex, param);
            }
            if (level != null && !level.equals("all")) {
                sql += " AND question_level = ?\n";
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
            PreparedStatement stm = connection.prepareStatement(sql);
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
        return -1;
    }

    public Question getQuestionById(int id) {
        try {
            String sql = "select * from question where question_id = ?";
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
                q.setMedia(rs.getBlob("question_media"));
                q.setAnswers(getAnswerForQues(id));
                return q;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//    public static void main(String[] args) throws SQLException {
//        QuestionDAO q = new QuestionDAO();
//        int id = 2;
//        Question ques = q.getQuestionById(id);
//        System.out.println(ques.getMedia().getBinaryStream());
//        
//    }
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
        return quizs;
    }

    public ArrayList<Category> getCategory() {
        ArrayList<Category> categorys = new ArrayList<>();
        try {
            String sql = "select * from category";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                categorys.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorys;
    }

    public ArrayList<Answer> getAnswerForQues(int quesId) {
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
        return answers;
    }

    public boolean updateQuestion(String quizId, Question q, InputStream media) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `quiz_practice_db`.`question`\n"
                    + "SET\n"
                    + "`question_content` = ?,\n"
                    //                    + "`correct_answer` = ?,\n"
                    + "`subject_id` = ?,\n"
                    + "`category_id` = ?,\n"
                    + "`question_level` = ?,\n"
                    + "`question_status` = ?,\n"
                    + "`subcategory_id` = ?\n";
            if (media != null) {
                sql += ", `question_media` = ?\n";
            }
            sql += "WHERE `question_id` = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, q.getContent());
            stm.setInt(2, q.getSubject().getSubject_id());
            stm.setInt(3, q.getCategory().getCategory_id());
            stm.setString(4, q.getLevel());
            stm.setString(5, q.getStatus());
            stm.setInt(6, q.getSubCategory().getId());
            if (media != null) {
                stm.setBlob(7, media);
                stm.setInt(8, q.getId());
            } else {
                stm.setInt(7, q.getId());
            }
            stm.executeUpdate();

            String update_quizQues = "UPDATE `quiz_practice_db`.`quiz_question`\n"
                    + "SET\n"
                    + "`quiz_id` = ?\n"
                    + "WHERE `question_id` = ?;";
            PreparedStatement stm_1 = connection.prepareStatement(update_quizQues);
            stm_1.setInt(1, Integer.parseInt(quizId));
            stm_1.setInt(2, q.getId());
            stm_1.executeUpdate();
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
            } catch (SQLException ex) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        QuestionDAO qdao = new QuestionDAO();
////        System.out.println(qdao.getQuestions(1, 1, 4, null, "all", "1", "all", "all").size());
////        for (Question question : qdao.getQuestions(1, 1, 4, null, "2", "1", "easy", "all")) {
////            System.out.println(question.getContent() + " " + question.getId());
////        }
////        for (Subcategory subcategory : qdao.getSubCategoryByCate(1)) {
////            System.out.println(subcategory.getName());
////        }
////        System.out.println(qdao.getTotalRows(1, null, "all", "1", "all", "all"));
//        for (Answer answerForQue : qdao.getAnswerForQues(1)) {
//            System.out.println(answerForQue.getContent());
//        }
//    }
}
