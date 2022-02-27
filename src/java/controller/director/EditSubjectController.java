/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import dal.HomeDAO;
import dal.SubjectDAO;
import java.io.IOException;
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
public class EditSubjectController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO sdao = new SubjectDAO();
        HomeDAO hdao = new HomeDAO();
        String id = request.getParameter("subject_id");
        ArrayList<Account> acc = hdao.getAccounts();
        Subject sub = sdao.getSubjectDetail(id);
        request.setAttribute("subject_author", acc);
        request.setAttribute("subject", sub);
        request.getRequestDispatcher("../../view/director/setting/editsubject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SubjectDAO sdao = new SubjectDAO();
        Subject sub = new Subject();
        sub.setSubject_id(Integer.parseInt(request.getParameter("subject_id")));
        sub.setSubject_title(request.getParameter("subjectName"));
        Account u = new Account();
        u.setId(Integer.parseInt(request.getParameter("subjectAuthor")));
        sub.setSubject_Author(u);
        sub.setSubject_status(request.getParameter("subjectStatus"));
        sdao.editSubject(sub);
        response.sendRedirect("subjectlist");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
