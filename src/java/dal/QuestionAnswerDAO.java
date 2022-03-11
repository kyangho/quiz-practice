/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
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
        String sql = "INSERT INTO `quiz_practice_db`.`question`\n"
                + "(`question_content`,`correct_answer`,`subject_id`,\n"
                + "`category_id`,`question_media`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, question.getContent());
            stm.setInt(2, Integer.parseInt(question.getCorrectAnswer()));
            stm.setInt(3, question.getSubject().getSubject_id());
            stm.setInt(4, question.getCategory().getCategory_id());
            stm.setBlob(5, question.getMedia());
        } catch (SQLException ex) {
            Logger.getLogger(QuestionAnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean insertAnswer(List<Answer> answers){
        String sql = ""
        return true;
    }
}
