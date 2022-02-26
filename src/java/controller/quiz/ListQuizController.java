/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.quiz;

import dal.QuizDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Quiz;
import model.Subject;

/**
 *
 * @author Yankee
 */
public class ListQuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDAO q = new QuizDAO();
        ArrayList<Quiz> quiz = q.getQuiz("all", "all", "all", null);
        ArrayList<Subject> subs = q.getsubs();
        ArrayList<Category> cates = q.getCates();
        request.setAttribute("quiz", quiz);
        request.setAttribute("cates", cates);
        request.setAttribute("subs", subs);
        request.getRequestDispatcher("../view/quiz/listquiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDAO q = new QuizDAO();
        ArrayList<Subject> subs = q.getsubs();
        String subject = request.getParameter("subject");
        String category = request.getParameter("category");
        String type = request.getParameter("type");
        String search = request.getParameter("search_quiz_title");
        ArrayList<Quiz> quiz = q.getQuiz(subject, category, type, search);
        ArrayList<Category> cates = q.getCates();
        request.setAttribute("search_quiz_title", search);
        request.setAttribute("quiz", quiz);
        request.setAttribute("cates", cates);
        request.setAttribute("subject", subject);
        request.setAttribute("category", category);
        request.setAttribute("type", type);
        request.setAttribute("quiz", quiz);
        request.setAttribute("subs", subs);
        request.getRequestDispatcher("../view/quiz/listquiz.jsp").forward(request, response);

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
