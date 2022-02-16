/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dal.AccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Vu Duc Tien
 */
public class RegisterController extends HttpServlet {

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
        if (request.getSession().getAttribute("account") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("view/home/register.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        AccountDAO adbc = new AccountDAO();

        Account account = new Account();
        String phone = request.getParameter("register_phone");
        String email = request.getParameter("register_email");
        String username = request.getParameter("register_username");

        if (adbc.isExistAccount(phone, email, username, "OR") != null) {
            request.setAttribute("isFail", true);
            request.getRequestDispatcher("view/home/register.jsp").forward(request, response);
        } else {
            account.setUsername(username);
            account.setPassword(BCrypt.withDefaults().hashToString(12, request.getParameter("register_password").toCharArray()));
            account.setEmail(email);
            account.setPhone(phone);
            account.setFullname(request.getParameter("register_fullname"));
            account.setAddress(request.getParameter("register_address"));
            account.setStatus("ACTIVE");
            account.setGender(request.getParameter("gender").equalsIgnoreCase("male"));
            adbc.insertAccount(account);
            response.sendRedirect(request.getContextPath() + "/home");
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
