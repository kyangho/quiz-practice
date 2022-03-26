package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.QuestionViewModel;
import model.Quiz;
import model.QuizViewModel;
import model.Subject;
import model.SubjectViewModel;

/**
 *
 * @author Cao Tam Kien
 */
public class DashboardDAO extends DBContext {

    public int getTotalSubject() {
        String query = "SELECT Count(subject_id) FROM subject";
        Connection connection = getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            int total = 0;
            while (rs.next()) {
                total = rs.getInt(1);
                return total;
            }
        } catch (SQLException ex) {
        }
        return -1;
    }

    public int getTotalQuiz() {
        String query = "SELECT count(quiz_id) FROM quiz where quiz_title =\"Quiz\"";
        Connection connection = getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            stm.close();
        } catch (SQLException ex) {
        }
        return -1;
    }

    public int getTotalTest() {
        String query = "SELECT count(quiz_id) FROM quiz where quiz_title =\"Test\"";
        Connection connection = getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            stm.close();
        } catch (SQLException ex) {
        }
        return -1;
    }

    public int getTotalQuestion() {
        String query = "SELECT count(question_id) FROM question";
        Connection connection = getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            stm.close();
        } catch (SQLException ex) {
        }
        return -1;
    }

    public ArrayList<QuizViewModel> getListQuizViewModels() {
        String query = "select q.quiz_name, s.subject_title,sc.subcategory_name, q.views, q.dateCreated from quiz as q \n"
                + "left join subject as s on q.quiz_subject = s.subject_id \n"
                + "left JOIN subcategory as sc on q.quiz_category = sc.subcategory_id \n"
                + "where q.quiz_title ='quiz'";
        Connection connection = getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            ArrayList<QuizViewModel> listQuiz = new ArrayList<QuizViewModel>();
            while (rs.next()) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String date = "10-03-2022";
                if (rs.getDate(5) != null) {
                    date = formatter.format(rs.getDate(5));
                }
                listQuiz.add(new QuizViewModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), date));
            }
            return listQuiz;
        } catch (SQLException ex) {
        }
        return null;
    }

    public ArrayList<QuizViewModel> getListQuizViewModelsByFilter() {
        String query = "select q.quiz_name, s.subject_title,sc.subcategory_name, q.views, q.dateCreated from quiz as q \n"
                + "left join subject as s on q.quiz_subject = s.subject_id \n"
                + "left JOIN subcategory as sc on q.quiz_category = sc.subcategory_id\n"
                + "where  q.dateCreated BETWEEN DATE_SUB(NOW(), INTERVAL 7 DAY) AND NOW();";
        Connection connection = getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            ArrayList<QuizViewModel> listQuiz = new ArrayList<QuizViewModel>();
            while (rs.next()) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String date = "10-02-2022";
                if (rs.getDate(5) != null) {
                    date = formatter.format(rs.getDate(5));
                }
                listQuiz.add(new QuizViewModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), date));
            }
            return listQuiz;
        } catch (SQLException ex) {
        }
        return null;
    }

    public ArrayList<QuestionViewModel> getListQuestion() {
        String query = "SELECT q.question_content, s.subject_title, sc.subcategory_name, q.question_level \n"
                + "FROM question as q left join subcategory as sc on q.subcategory_id = sc.subcategory_id \n"
                + "left join subject as s on q.subject_id = s.subject_id";
        Connection connection = getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            ArrayList<QuestionViewModel> listQuestion = new ArrayList<QuestionViewModel>();
            while (rs.next()) {
                listQuestion.add(new QuestionViewModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return listQuestion;
        } catch (SQLException ex) {
        }
        return null;
    }

    public ArrayList<SubjectViewModel> getListSubject() {
        String query = "SELECT s.subject_title, a.username,s.subject_status\n"
                + "from subject as s \n"
                + "left join account as a on s.subject_author = a.account_id";
        Connection connection = getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            ArrayList<SubjectViewModel> listSubject = new ArrayList<SubjectViewModel>();
            while (rs.next()) {
                listSubject.add(new SubjectViewModel(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listSubject;
        } catch (SQLException ex) {
        }
        return null;
    }
}
