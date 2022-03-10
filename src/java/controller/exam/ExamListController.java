package controller.exam;

import dal.ExamDAO;
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
        ArrayList<Subject> subs = q.getsubs();
        ArrayList<Category> cates = q.getCates();
        request.setAttribute("exams", exams);
        request.setAttribute("cates", cates);
        request.setAttribute("subs", subs);
        request.getRequestDispatcher("/view/exam/examlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Subject> subs = q.getsubs();
        String subject = request.getParameter("subject");
        String category = request.getParameter("category");
        String type = request.getParameter("type");
        String search = request.getParameter("search_exam_title");
        ArrayList<Exam> exams = dao.getExams(subject, category, type, search);
        ArrayList<Category> cates = q.getCates();
        request.setAttribute("search_exam_title", search);
        request.setAttribute("exams", exams);
        request.setAttribute("cates", cates);
        request.setAttribute("subject", subject);
        request.setAttribute("category", category);
        request.setAttribute("type", type);
        request.setAttribute("subs", subs);
        request.getRequestDispatcher("../view/exam/examlist.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
