/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmp;

import dal.DBContext;
import dal.QuestionDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Category;
import model.Question;
import model.Subcategory;
import model.Subject;

/**
 *
 * @author Vu Duc Tien
 */
public class tmpDAO extends DBContext {

    public ArrayList<Ques_Ans> getQuestion_AnswerList(String search) {
        QuestionDAO qdao = new QuestionDAO();
        ArrayList<Ques_Ans> ques_Anses = new ArrayList<>();
        try {
            String sql = "SELECT q.question_id, answer_id FROM user_answer ua\n"
                    + "join  question q on ua.question_id = q.question_id\n";
            if (search.equals("wrong")) {
                sql += "where answer_id <> correct_answer";
            } if (search.equals("correct")){
                sql += "where answer_id = correct_answer";
            }
            
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Ques_Ans qa = new Ques_Ans();
                qa.setQuestion(qdao.tmpgetQuestionById(rs.getInt(1)));
                qa.setAnswer(rs.getString(2));
                ques_Anses.add(qa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ques_Anses;
    }

    public Answer getAnswerByID(int id) {
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
