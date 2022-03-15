/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmp;

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
@WebServlet(name = "tmpQuizResultController", urlPatterns = {"/quiz/game/result"})
public class tmpQuizResultController extends HttpServlet {

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
        tmpDAO dao = new tmpDAO();
        ArrayList<Ques_Ans> ques_Anses = dao.getQuestion_AnswerList("all");
        int numCorrect = countCorrectAnswer(ques_Anses);
        double percent = (double) numCorrect / ques_Anses.size() * 100;
        boolean pass;
        if (percent > 80) {
            pass = true;
        } else {
            pass = false;
        }
        request.setAttribute("numCorrect", numCorrect);
        request.setAttribute("numWrong", ques_Anses.size() - numCorrect);
        request.setAttribute("percent", String.format("%.2f", percent));
        request.setAttribute("pass", pass);
        request.setAttribute("ques_Anses", ques_Anses);
        request.getRequestDispatcher("../../view/quiz/result.jsp").forward(request, response);
    }

    private int countCorrectAnswer(ArrayList<Ques_Ans> ques_Anses) {
        int count = 0;
        for (Ques_Ans qa : ques_Anses) {
            if (qa.getAnswer().equals(qa.getQuestion().getCorrectAnswer())) {
                count++;
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
