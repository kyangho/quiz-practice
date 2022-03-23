/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Question;

/**
 *
 * @author ducky
 */
public class QuestionAnswerDAO extends DBContext {

    public boolean insertQuestionInfor(Question question) {
        Connection connection = getConnection();
        String sql = "INSERT INTO `question`\n"
                + "(`question_content`,`subject_id`,\n"
                + "`category_id`,`question_media`)\n"
                + "VALUES\n"
                + "(?,?,?,?);";
        try {
            connection.setAutoCommit(true);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, question.getContent());
            if (question.getSubject() == null) {
                stm.setNull(2, Types.INTEGER);
            } else {
                stm.setInt(2, question.getSubject().getSubject_id());
            }
            if (question.getCategory() == null) {
                stm.setNull(3, Types.INTEGER);
            } else {
                stm.setInt(3, question.getCategory().getCategory_id());
            }
            stm.setBlob(4, question.getMedia());
            stm.executeUpdate();
            String id = "";
            String sqlGetLastId = "select LAST_INSERT_ID() as id";
            PreparedStatement idSTM = connection.prepareStatement(sqlGetLastId);
            ResultSet idRS = idSTM.executeQuery();
            if (idRS.next()) {
                id = idRS.getString("id");
            }
            ArrayList<Answer> answers = insertAnswer(Integer.parseInt(id), question.getAnswers());
            question.setCorrectAnswer(answers.get(0).getId() + "");
            String updateSQL = "UPDATE `question`\n"
                    + "SET\n"
                    + "`correct_answer` = ?\n"
                    + "WHERE `question_id` = ?;\n";
            PreparedStatement updateSTM = connection.prepareStatement(updateSQL);
            updateSTM.setInt(1, Integer.parseInt(question.getCorrectAnswer()));
            updateSTM.setInt(2, Integer.parseInt(id));
            updateSTM.executeUpdate();
            connection.setAutoCommit(true);
            stm.close();
            updateSTM.close();
            idRS.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionAnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionAnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public ArrayList<Answer> insertAnswer(int questionId, ArrayList<Answer> answers) {
        Connection connection = getConnection();
        String sql = "INSERT INTO `answer`\n"
                + "(`question_id`,\n"
                + "`answer_content`)\n"
                + "VALUES\n"
                + "(?,?);";
        ArrayList<Answer> result = new ArrayList<>();
        try {
            connection.setAutoCommit(true);
            for (Answer a : answers) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, questionId);
                stm.setString(2, a.getContent());
                stm.executeUpdate();
                String id = "";
                String sqlGetLastId = "select LAST_INSERT_ID() as id";
                PreparedStatement idSTM = connection.prepareStatement(sqlGetLastId);
                ResultSet idRS = idSTM.executeQuery();
                if (idRS.next()) {
                    id = idRS.getString("id");
                }
                a.setId(Integer.parseInt(id));
            }
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionAnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionAnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return answers;
    }
}
