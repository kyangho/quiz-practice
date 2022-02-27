/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import dal.HomeDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Subject;

/**
 *
 * @author Yankee
 */
public class AddSubjectController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO sdao = new SubjectDAO();
        HomeDAO hdao = new HomeDAO();
        request.setCharacterEncoding("UTF-8");
        ArrayList<Account> user = hdao.getAccounts();
        request.setAttribute("user_author", user);
        request.getRequestDispatcher("../../view/director/setting/newsubject.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO sdao = new SubjectDAO();
        request.setCharacterEncoding("UTF-8");
        Subject sub = new Subject();
        sub.setSubject_title(request.getParameter("subjectName"));
        sub.setSubject_status(request.getParameter("subjectStatus"));
        Account u = new Account();
        u.setId(Integer.parseInt(request.getParameter("subjectAuthor")));
        sub.setSubject_Author(u);
        sdao.inserSubject(sub);
        response.sendRedirect("subjectlist");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
