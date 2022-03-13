/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.quiz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dal.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Question;
import model.Quiz;

/**
 *
 * @author ducky
 */
@WebServlet(name = "QuizController", urlPatterns = {"/quiz/join/*"})
public class QuizController extends HttpServlet {

    private static final String quizJoinPath = "/quiz/join";
    private static final String quizGamePath = "/quiz/join/game";
    private static final String questionPath = "/quiz/join/game/getquestion";
    private static final String submitPath = "/quiz/join/submit";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(quizGamePath)) {
            Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
            if (quiz == null){
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            request.setAttribute("quiz", quiz);
            Gson gs = new Gson();
            Type listType = new TypeToken<ArrayList<Question>>() {
            }.getType();
            request.setAttribute("questionJSON", gs.toJson(quiz.getQuestions(), listType));
            request.getRequestDispatcher("/view/quiz/game.jsp").forward(request, response);
        }else if (URI.contains(quizJoinPath)) {
            String id = request.getParameter("quizId");
            QuizDAO quizDAO = new QuizDAO();
            Quiz quiz = quizDAO.getQuizDetail(Integer.parseInt(id));
            request.getSession().setAttribute("quiz", quiz);
            response.sendRedirect("join/game");
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
            Quiz quiz = (Quiz)request.getSession().getAttribute("quiz");
            QuizDAO quizDAO = new QuizDAO();
            quiz = quizDAO.getQuizDetail(quiz.getId());
            Gson gs = new Gson();
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(gs.toJson(quiz.getQuestions()));
        } else if (URI.contains(submitPath)) {
            Gson gs = new Gson();
            Type listType = new TypeToken<ArrayList<Question>>() {
            }.getType();
            ArrayList<Question> questions = gs.fromJson(request.getParameter("questions"), listType);
            listType = new TypeToken<String[]>() {
            }.getType();
            String[] userAnswer = gs.fromJson(request.getParameter("userAnswer"), String[].class);
            for (int i = 0; i < questions.size(); i++) {
                questions.get(i).setCorrectAnswer(userAnswer[i]);
            }
            System.out.println(questions);
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
