/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.homepage;

import dal.HomeDAO;
import dal.QuizDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Quiz;

/**
 *
 * @author conmu
 */
public class HomePageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HomeDAO hdbc = new HomeDAO();
        ArrayList<Account> accounts = hdbc.getAccounts();
        String admin = "admin";
        Account account = (Account) request.getSession().getAttribute("account");
        request.setAttribute("IsAdmin", admin);
        if (account != null) {
            QuizDAO qdb = new QuizDAO();
            ArrayList<Quiz> quizzes = qdb.getQuizzesPractice(account.getId(), 1, 4);
            request.setAttribute("quizs", quizzes);
        }
        request.setAttribute("tag", "home");
        request.setAttribute("information", accounts);
        request.getRequestDispatcher("view/home/homepage.jsp").forward(request, response);
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
        response.getWriter().print("da login");
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
