/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import dal.AccountDAO;
import dal.RoleDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Role;

/**
 *
 * @author conmu
 */
public class AddNewUserController extends HttpServlet {

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
        RoleDAO rdao = new RoleDAO();
        ArrayList<Role> roles = rdao.getRoles();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("../../view/director/user/adduser.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        boolean gender = request.getParameter("gender").equalsIgnoreCase("male");
        String status = request.getParameter("status");
        String[] roleIDs = request.getParameterValues("roleID");
        String avatar = null;
        ArrayList<Role> roles = new ArrayList<>();
        for (String roleID : roleIDs) {
            roles.add(new Role(Integer.parseInt(roleID), ""));
        }

        Account account = new Account(-1, username, phone, email, phone, fullname, address, gender, status, roles, avatar);

        AccountDAO adbc = new AccountDAO();
        if (adbc.isExistAccountForAdd(phone, email, username) != null) {
            request.setAttribute("account", account);
            RoleDAO rdao = new RoleDAO();
            ArrayList<Role> rolesa = rdao.getRoles();
            request.setAttribute("roles", rolesa);
            String tag = "";
            if (adbc.isExistAccountForAdd(phone, email, username).getUsername().equalsIgnoreCase(username)) {
                tag = "username";
            } else if (adbc.isExistAccountForAdd(phone, email, username).getEmail().equalsIgnoreCase(email)) {
                tag = "email";
            }
            request.setAttribute("tag", tag);
            request.getRequestDispatcher("../../view/director/user/adduser.jsp").forward(request, response);
        } else {
            adbc.insertAccount(account);
            response.sendRedirect("userlist");
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
