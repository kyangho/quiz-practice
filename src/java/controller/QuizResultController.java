/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.QuizDAO;
import model.Ques_Ans;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Vu Duc Tien
 */
@WebServlet(name = "QuizResultController", urlPatterns = {"/quiz/game/result"})
public class QuizResultController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDAO qdao = new QuizDAO();
//        Account a = (Account) request.getSession().getAttribute("account");
        ArrayList<Ques_Ans> ques_Anses = qdao.getQuestion_AnswerList("all", 3);
        int numCorrect = countAnswer(ques_Anses, "correct");
        int numNone = countAnswer(ques_Anses, "none");
        double percent = (double) numCorrect / ques_Anses.size() * 100;
        boolean pass;
        if (percent > ques_Anses.get(0).getQuiz().getRate()) {
            pass = true;
        } else {
            pass = false;
        }
        request.setAttribute("numCorrect", numCorrect);
        request.setAttribute("numNone", numNone);
        request.setAttribute("numWrong", ques_Anses.size() - numCorrect - numNone);
        request.setAttribute("percent", String.format("%.2f", percent));
        request.setAttribute("pass", pass);
        request.setAttribute("ques_Anses", ques_Anses);
        request.getRequestDispatcher("../../view/quiz/result.jsp").forward(request, response);
    }

    private int countAnswer(ArrayList<Ques_Ans> ques_Anses, String condition) {
        int count = 0;
        for (Ques_Ans qa : ques_Anses) {
            if (condition.equals("correct") && qa.getAnswer() != null) {
                if (qa.getAnswer().equals(qa.getQuestion().getCorrectAnswer())) {
                    count++;
                }
            }
            if(condition.equals("none")){
                if (qa.getAnswer() == null) {
                    count++;
                }
            }
        }
        return count;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
