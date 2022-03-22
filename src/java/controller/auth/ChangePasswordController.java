/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dal.AccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author kienct
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/changepassword"})
public class ChangePasswordController extends HttpServlet {

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
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("view/home/changepassword.jsp").forward(request, response);
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
        String oldPassword = request.getParameter("oldpass");
        String newPassword = request.getParameter("newpass");
        String repeatPassword = request.getParameter("repeatpass");

        AccountDAO adbc = new AccountDAO();
        Account account = (Account) request.getSession().getAttribute("account");

        if (newPassword == null ? repeatPassword != null : !newPassword.equals(repeatPassword)) {
            request.setAttribute("wrongRepeat", true);
            request.getRequestDispatcher("view/home/changepassword.jsp").forward(request, response);
        } else if (adbc.getAccount(account.getUsername(), oldPassword) == null) {
            request.setAttribute("wrongPassword", true);
            request.getRequestDispatcher("view/home/changepassword.jsp").forward(request, response);
        } else {
            account.setPassword(BCrypt.withDefaults().hashToString(12, newPassword.toCharArray()));
            adbc.changePassword(account);
            response.sendRedirect(request.getContextPath() + "/login");
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
    }// </editor-fold>

}
