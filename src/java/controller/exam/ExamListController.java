package controller.exam;

import dal.ExamDAO;
import dal.QuestionDAO;
import dal.QuizDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Exam;
import model.Quiz;
import model.Subject;

/**
 *
 * @author kienct
 */
@WebServlet(name = "ExamListController", urlPatterns = {"/exam"})
public class ExamListController extends HttpServlet {

    ExamDAO dao = new ExamDAO();
    QuizDAO q = new QuizDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Exam> exams = dao.getExams();
        ArrayList<Subject> subs = q.getSubs();
        QuestionDAO questionDAO = new QuestionDAO();
        ArrayList<Category> cates = questionDAO.getCategory();
        request.setAttribute("exams", exams);
        request.setAttribute("cates", cates);
        request.setAttribute("subs", subs);
        request.getRequestDispatcher("/view/exam/examlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Subject> subs = q.getSubs();
        String subject = request.getParameter("subject");
        String category = request.getParameter("category");
        String type = request.getParameter("type");
        String search = request.getParameter("search_exam_title");
        if (category == null) {
            category = "";
        } else if (category.compareToIgnoreCase("all") == 0) {
            category = "";
        }
        if (search == null) {
            search = "";
        }
        ArrayList<Exam> exams = dao.getExams("", category, "", search);
        QuestionDAO questionDAO = new QuestionDAO();
        ArrayList<Category> cates = questionDAO.getCategory();
        request.setAttribute("search_exam_title", search);
        request.setAttribute("exams", exams);
        request.setAttribute("cates", cates);
        request.setAttribute("subject", subject);
        request.setAttribute("category", category);
        request.setAttribute("type", type);
        request.setAttribute("subs", subs);
        request.getRequestDispatcher("/view/exam/examlist.jsp").forward(request, response);
    }
}
