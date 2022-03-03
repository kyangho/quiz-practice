package controller.exam;

import dal.ExamDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Exam;

/**
 *
 * @author kienct
 */
@WebServlet(name = "ExamDetailController", urlPatterns = {"/exam/detail"})
public class ExamDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ExamDAO dao = new ExamDAO();
        Exam exam = dao.getExamDetail(id);
        request.setAttribute("quiz", exam);
        request.getRequestDispatcher("/view/exam/examdetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
