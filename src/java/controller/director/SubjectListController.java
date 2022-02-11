/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import dal.SubjectDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Subject;

/**
 *
 * @author Yankee
 */
public class SubjectListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO sdao = new SubjectDAO();
        int pagesize = 4;
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.length() == 0) {
            raw_page = "1";
        }
        int pageindex = Integer.parseInt(raw_page);
        ArrayList<Subject> subjects = sdao.getAllSetting_2(pagesize, pageindex);
        int totalrows = sdao.getRowcount();
        int totalpage = (totalrows % pagesize == 0) ? totalrows / pagesize : totalrows / pagesize+1;
        
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("subjects", subjects);
        request.setAttribute("tag", "subjectlist");
        request.getRequestDispatcher("../../view/director/setting/subjectlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
