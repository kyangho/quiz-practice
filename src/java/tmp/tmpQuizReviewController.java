/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmp;

import model.Ques_Ans;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vu Duc Tien
 */
@WebServlet(name = "tmpQuizReviewController", urlPatterns = {"/quiz/game/review"})
public class tmpQuizReviewController extends HttpServlet {

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
//        tmpDAO dao = new tmpDAO();
//        ArrayList<Ques_Ans> ques_Anses = dao.getQuestion_AnswerList("all");
//        request.setAttribute("ques_Anses", ques_Anses);
//        request.setAttribute("search", "all");
//        request.getRequestDispatcher("../../view/quiz/review.jsp").forward(request, response);
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
//        String search = request.getParameter("search");
//        response.getWriter().print(search);
//        tmpDAO dao = new tmpDAO();
//        ArrayList<Ques_Ans> ques_Anses = dao.getQuestion_AnswerList(search);
//        request.setAttribute("ques_Anses", ques_Anses);
//        request.setAttribute("search", search);
//        request.getRequestDispatcher("../../view/quiz/review.jsp").forward(request, response);
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
