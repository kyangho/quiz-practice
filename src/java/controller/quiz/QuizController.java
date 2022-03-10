/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.quiz;

import com.google.gson.Gson;
import dal.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Quiz;

/**
 *
 * @author ducky
 */
@WebServlet(name = "QuizController", urlPatterns = {"/quiz/join/*"})
public class QuizController extends HttpServlet {

    private static final String quizGamePath = "/quiz/join/game";
    private static final String questionPath = "/quiz/join/game/getquestion";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(quizGamePath)) {
            int quizId = 2;
            QuizDAO quizDAO = new QuizDAO();
            Quiz quiz = quizDAO.getQuizDetail(quizId);
            request.setAttribute("quiz", quiz);
            Gson gs = new Gson();
            request.setAttribute("questionJSON", gs.toJson(quiz.getQuestions()));
            request.getRequestDispatcher("/view/quiz/game.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(questionPath)) {
            int quizId = 2;
            QuizDAO quizDAO = new QuizDAO();
            Quiz quiz = quizDAO.getQuizDetail(quizId);
            Gson gs = new Gson();
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(gs.toJson(quiz.getQuestions()));
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
