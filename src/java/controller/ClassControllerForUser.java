/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.ClassDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.classes.Classes;

/**
 *
 * @author Vu Duc Tien
 */
@WebServlet(name = "ClassControllerForUser", urlPatterns = {"/class/*"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024 * 17)
public class ClassControllerForUser extends HttpServlet {

    private static final String classListForUserPath = "/class/list";
    private static final String classDetailForUserPath = "/class/detail";
    private static final String changeClassStatusForUserPath = "/class/changestatus";
    private static final String addNewClassForUserPath = "/class/addNewClass";

    private static final int PAGESIZE = 3;

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
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(classListForUserPath)) {
            doGetClassList(request, response);
        }
        if (URI.contains(classDetailForUserPath)) {
            doGetClassDetail(request, response);
        }
        if (URI.contains(changeClassStatusForUserPath)) {
            doGetChangeClassStatus(request, response);
        }
        if (URI.contains(addNewClassForUserPath)) {
            doGetAddNewClass(request, response);
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
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(classListForUserPath)) {
            doPostClassList(request, response);
        }
        if (URI.contains(classDetailForUserPath)) {
            doPostClassDetail(request, response);
        }
        if (URI.contains(changeClassStatusForUserPath)) {
            doPostChangeClassStatus(request, response);
        }
        if (URI.contains(addNewClassForUserPath)) {
            doPostAddNewClass(request, response);
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

    private void doGetClassList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassDAO cdao = new ClassDAO();

        int pageIndex;
        String pageIndex_raw = request.getParameter("pageIndex");
        if (pageIndex_raw == null) {
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(pageIndex_raw);
        }

        ArrayList<Classes> classes = new ArrayList<>();

        String key = request.getParameter("key");
        int totalRows;
        String url;
        if (key == null || key.trim().length() == 0) {
            classes = cdao.getClassList(null, PAGESIZE, pageIndex);
            totalRows = cdao.getTotalRows(null);
            url = "classeslist?pageIndex=";
        } else {
            classes = cdao.getClassList(key, PAGESIZE, pageIndex);
            totalRows = cdao.getTotalRows(key);
            url = "classeslist?key=" + key + "&pageIndex=";
            request.setAttribute("key", key);
        }
        int totalPages = (totalRows % PAGESIZE == 0) ? totalRows / PAGESIZE : totalRows / PAGESIZE + 1;

        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("classes", classes);
        request.setAttribute("url", url);
        request.setAttribute("tag", "classeslist");
        request.getRequestDispatcher("../../view/classes/classeslist.jsp").forward(request, response);
    }

    private void doPostClassList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void doGetClassDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classID_raw = request.getParameter("classID");
        if (classID_raw == null) {
            response.sendRedirect("classeslist");
        } else {
            ClassDAO cdao = new ClassDAO();
            AccountDAO adao = new AccountDAO();
            int classID = Integer.parseInt(classID_raw);
            Classes c = cdao.getClassByID(classID);
            ArrayList<Account> teachers = adao.getTeacherOrStudent("Teacher");
            request.setAttribute("teachers", teachers);
            request.setAttribute("class", c);
            request.getRequestDispatcher("../../view/classes/classdetail.jsp").forward(request, response);
        }
    }

    private void doPostClassDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int classID = Integer.parseInt(request.getParameter("classID"));
        String className = request.getParameter("className");
        int author = Integer.parseInt(request.getParameter("author"));
        String status = request.getParameter("status");
        String note = request.getParameter("note");

        ClassDAO cdao = new ClassDAO();
        AccountDAO adao = new AccountDAO();
        cdao.updateClass(classID, className, status, note, author);
        ArrayList<Account> teachers = adao.getTeacherOrStudent("Teacher");
        request.setAttribute("teachers", teachers);
        request.setAttribute("tag", "done");
        request.setAttribute("class", cdao.getClassByID(classID));
        request.getRequestDispatcher("../../view/classes/classdetail.jsp").forward(request, response);
    }

    private void doGetChangeClassStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classID = request.getParameter("id");
        String status = request.getParameter("status");
        ClassDAO cdao = new ClassDAO();
        cdao.ChangeStatus(Integer.parseInt(classID), status);
        response.sendRedirect("classeslist");
    }

    private void doPostChangeClassStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void doGetAddNewClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO adao = new AccountDAO();
        ArrayList<Account> teachers = adao.getTeacherOrStudent("Teacher");
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("../../view/classes/addnewclass.jsp").forward(request, response);
    }

    private void doPostAddNewClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("className");
        int author = Integer.parseInt(request.getParameter("author"));
        String note = request.getParameter("note");
        String status = request.getParameter("status");

        ClassDAO cdao = new ClassDAO();
        cdao.insertClass(name, note, status, author);
        request.setAttribute("done", true);
        request.getRequestDispatcher("../../view/classes/addnewclass.jsp").forward(request, response);
    }
}
