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
        String subject_status = request.getParameter("subject_status");
        String subject_name = request.getParameter("subject_name");
        int pageindex;
        if (raw_page == null || raw_page.length() == 0) {
            pageindex = 1;
        } else {
            pageindex = Integer.parseInt(raw_page);
        }
        ArrayList<Subject> subjects = null;
        int totalrows;
        String url;
        if (subject_name == null) {
            if (subject_status == null) {
                subjects = new ArrayList<>();
                subjects = sdao.getAllSubject_2(pagesize, pageindex, "all", null);
                totalrows = sdao.getRowcount("all", null);
                url = "subjectlist?page=";
            } else {
                subjects = new ArrayList<>();
                subjects = sdao.getAllSubject_2(pagesize, pageindex, subject_status, null);
                totalrows = sdao.getRowcount(subject_status, null);
                url = "subjectlist?subject_status=" + subject_status + "&page=";
                request.setAttribute("subject_status", subject_status);
            }
        } else {
            subjects = new ArrayList<>();
            subjects = sdao.getAllSubject_2(pagesize, pageindex, null, subject_name);
            request.setAttribute("valueSearch", subject_name);
            url = "subjectlist?subject_name=" + subject_name + "&page=";
            totalrows = sdao.getRowcount(null, subject_name);
        }
        int totalpage = (totalrows % pagesize == 0) ? totalrows / pagesize : totalrows / pagesize + 1;
        request.setAttribute("url", url);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("subjects", subjects);
        request.setAttribute("tag", "subjectlist");
        request.getRequestDispatcher("../../view/director/setting/subjectlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO sdao = new SubjectDAO();
        int pagesize = 4;
        String subject_status = request.getParameter("subject_status");
        String subject_name = request.getParameter("subject_name");
        int totalrows;
        String url;
        String raw_page = request.getParameter("page");
        int pageindex;
        if (raw_page == null || raw_page.length() == 0) {
            pageindex = 1;
        } else {
            pageindex = Integer.parseInt(raw_page);
        }
        ArrayList<Subject> subjects = null;
        if (subject_name == null) {
            subjects = new ArrayList<>();
            subjects = sdao.getAllSubject_2(pagesize, pageindex, subject_status, null);
            totalrows = sdao.getRowcount(subject_status, null);
            url = "subjectlist?subject_status=" + subject_status + "&page=";
            request.setAttribute("subject_status", subject_status);
        } else {
            subjects = new ArrayList<>();
            subjects = sdao.getAllSubject_2(pagesize, pageindex, null, subject_name);
            request.setAttribute("valueSearch", subject_name);
            url = "settinglist?subject_name=" + subject_name + "&page=";
            totalrows = sdao.getRowcount(null, subject_name);
        }
        int totalpage = (totalrows % pagesize == 0) ? totalrows / pagesize : totalrows / pagesize + 1;
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("url", url);
        request.setAttribute("subjects", subjects);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("tag", "subjectlist");
        request.getRequestDispatcher("../../view/director/setting/subjectlist.jsp").forward(request, response);

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
