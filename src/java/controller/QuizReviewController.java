/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.auth.BaseRequiredAuthController;
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
@WebServlet(name = "QuizReviewController", urlPatterns = {"/quiz/game/review"})
public class QuizReviewController extends BaseRequiredAuthController {

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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDAO qdao = new QuizDAO();
//        Account a = (Account) request.getSession().getAttribute("account");
        ArrayList<Ques_Ans> ques_Anses = qdao.getQuestion_AnswerList("all", 3);
        request.setAttribute("ques_Anses", ques_Anses);
        request.setAttribute("search", "all");
        request.getRequestDispatcher("../../view/quiz/review.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        QuizDAO qdao = new QuizDAO();
//        Account a = (Account) request.getSession().getAttribute("account");
        ArrayList<Ques_Ans> ques_Anses = qdao.getQuestion_AnswerList(search, 3);
        request.setAttribute("ques_Anses", ques_Anses);
        request.setAttribute("search", search);
        request.getRequestDispatcher("../../view/quiz/review.jsp").forward(request, response);
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
