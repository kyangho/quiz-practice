/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.QuizDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Quiz;

/**
 *
 * @author conmu
 */
@WebServlet(name = "PracticeContoller", urlPatterns = {"/practice/*"})
public class PracticeContoller extends HttpServlet {

    private final String practiceListPath = "/practice/list";
    private final String practiceDetailPath = "/practice/details";
    private final int pageSize = 4;

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
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(practiceListPath)) {
            doGetPracticeList(request, response);
        } else {
            doGetPracticeDetail(request, response);
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
        if (URI.contains(practiceListPath)) {
            doGetPracticeList(request, response);
        } else {
            doPostPracticeDetail(request, response);
        }
    }

    private void doGetPracticeList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageindex_raw = request.getParameter("pageindex");
        int pageindex;
        if (pageindex_raw == null || pageindex_raw.length() == 0) {
            pageindex = 1;
        } else {
            pageindex = Integer.parseInt(pageindex_raw);
        }
        Account account = (Account) request.getSession().getAttribute("account");
        QuizDAO qdb = new QuizDAO();
        ArrayList<Quiz> quizzes = qdb.getQuizzesPractice(account.getId(), pageindex, pageSize);
        request.setAttribute("quizs", quizzes);
        String url = "list?pageindex=";
        request.setAttribute("url", url);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("pagesize", pageSize);
        request.getRequestDispatcher("../view/quiz/practicelist.jsp").forward(request, response);
    }

    private void doGetPracticeDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String quizID = request.getParameter("quizID");
        if (quizID == null || quizID.length() == 0) {
            request.getRequestDispatcher("../view/quiz/addpratice.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("../view/quiz/practicedetails.jsp").forward(request, response);
        }
    }

    private void doPostPracticeDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("keySearch");
        response.getWriter().print(key);
    }

}
