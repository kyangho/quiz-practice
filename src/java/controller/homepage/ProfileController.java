/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.homepage;

import dal.AccountDAO;
import dal.HomeDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Account;

/**
 *
 * @author Tebellum
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024 * 17)
@WebServlet(name = "ProfileController", urlPatterns = {"/profile/*"})
public class ProfileController extends HttpServlet {

    private final String profilePath = "/profile";
//    private final String avatarPath = "/profile/avatar";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(profilePath)) {
            doGetProfile(request, response);
        }
//        else if (URI.contains(avatarPath)) {
//            doGetAvatar(request, response);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(profilePath)) {
            doPostProfile(request, response);
        }
    }

    private void doGetProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/home/profile.jsp").forward(request, response);
    }

    private void doPostProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        boolean gender = request.getParameter("gender").equalsIgnoreCase("male");
        String phone = request.getParameter("phone");
        Part avatarPart = request.getPart("avatar");
        InputStream avatarContent = null;
        if (!avatarPart.getSubmittedFileName().isEmpty()) {
            avatarContent = avatarPart.getInputStream();
        }

        Account account = (Account) request.getSession().getAttribute("account");
        account.setGender(gender);
        account.setFullname(fullname);
        account.setPhone(phone);

        AccountDAO adao = new AccountDAO();
        adao.updateAccountProfile(account, avatarContent);
        account = adao.getAccountById(account.getId());
        request.getSession().setAttribute("account", account);
        response.sendRedirect("profile");
    }

//    private void doGetAvatar(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int accountID = Integer.parseInt(request.getParameter("accountID"));
//        AccountDAO adao = new AccountDAO();
//        Blob blob = adao.getAccountById(accountID).getAvatar();
//        byte[] buffer;
//        try {
//            response.reset();
//            buffer = blob.getBytes(1, (int) blob.length());
//            OutputStream os = response.getOutputStream();
//            response.setContentType("image/*");
//            ServletOutputStream out = response.getOutputStream();
//            out.write(buffer, 0, (int) blob.length());
//            os.flush();
//            os.close();
//        } catch (SQLException ex) {
//        }
//    }
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
