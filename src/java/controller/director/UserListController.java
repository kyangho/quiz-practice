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
public class UserListController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int pageSize = 10;

        String pageindex_raw = request.getParameter("pageindex");
        int pageindex;
        AccountDAO adbc = new AccountDAO();

        if (pageindex_raw == null) {
            pageindex = 1;
        } else {
            pageindex = Integer.parseInt(pageindex_raw);
        }
        int totalRows;
        int totalPage;
        String keySearch = request.getParameter("keySearch");
        ArrayList<Account> accounts = null;
        String url;
        if (keySearch == null) {
            String id = request.getParameter("id");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String roleID = request.getParameter("roleID");
            String status = request.getParameter("status");
            accounts = new ArrayList<>();

            if (fullname == null && email == null && phone == null && roleID == null && status == null && id == null) {
                accounts = adbc.getAllAccountsByFilter(pageindex, pageSize, null, null, null, null, null, null, null);
                totalRows = adbc.totalRowsByAccountInfor(null, null, null, null, null, null, null);
                url = "userlist?pageindex=";
            } else {
                accounts = adbc.getAllAccountsByFilter(pageindex, pageSize, id, fullname, email, phone, roleID, status, null);
                totalRows = adbc.totalRowsByAccountInfor(id, fullname, email, phone, roleID, status, null);
                url = "userlist?id=" + id + "&fullname=" + fullname + "&email=" + email
                        + "&phone=" + phone + "&roleID=" + roleID + "&status=" + status + "&pageindex=";

                request.setAttribute("id", id);
                request.setAttribute("fullname", fullname);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("roleID", roleID);
                request.setAttribute("status", status);
            }
        } else {
            accounts = new ArrayList<>();
            accounts = adbc.getAllAccountsByFilter(pageindex, pageSize, null, null, null, null, null, null, keySearch);
            request.setAttribute("valueSearch", keySearch);
            url = "userlist?keySearch=" + keySearch + "&pageindex=";
            totalRows = adbc.totalRowsByAccountInfor(null, null, null, null, null, null, keySearch);
        }
        totalPage = (totalRows % pageSize == 0) ? totalRows / pageSize : totalRows / pageSize + 1;
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("accounts", accounts);
        request.setAttribute("tag", "userlist");
        request.setAttribute("url", url);
        RoleDAO rdb = new RoleDAO();
        ArrayList<Role> roles = rdb.getRoles();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("../../view/director/user/userlist.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        int pageSize = 10;
        int totalRows;
        int totalPage;

        String keySearch = request.getParameter("keySearch");
        AccountDAO adbc = new AccountDAO();
        ArrayList<Account> accounts = null;
        String url;
        if (keySearch == null) {
            String id = request.getParameter("id");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String roleID = request.getParameter("roleID");
            String status = request.getParameter("status");
            accounts = new ArrayList<>();
            accounts = adbc.getAllAccountsByFilter(1, pageSize, id, fullname, email, phone, roleID, status, null);

            totalRows = adbc.totalRowsByAccountInfor(id, fullname, email, phone, roleID, status, null);
            url = "userlist?id=" + id + "&fullname=" + fullname + "&email=" + email
                    + "&phone=" + phone + "&roleID=" + roleID + "&status=" + status + "&pageindex=";

            request.setAttribute("id", id);
            request.setAttribute("fullname", fullname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("roleID", roleID);
            request.setAttribute("status", status);
        } else {
            accounts = new ArrayList<>();
            accounts = adbc.getAllAccountsByFilter(1, pageSize, null, null, null, null, null, null, keySearch);
            request.setAttribute("valueSearch", keySearch);
            url = "userlist?keySearch=" + keySearch + "&pageindex=";
            totalRows = adbc.totalRowsByAccountInfor(null, null, null, null, null, null, keySearch);
        }
        totalPage = (totalRows % pageSize == 0) ? totalRows / pageSize : totalRows / pageSize + 1;
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageindex", 1);
        request.setAttribute("accounts", accounts);
        request.setAttribute("tag", "userlist");
        request.setAttribute("url", url);
        RoleDAO rdb = new RoleDAO();
        ArrayList<Role> roles = rdb.getRoles();
        request.setAttribute("roles", roles);
//                response.getWriter().print("page size: " + pageSize + ", total page: " + totalPage);
        request.getRequestDispatcher("../../view/director/user/userlist.jsp").forward(request, response);
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
