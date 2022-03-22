/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import controller.TypeConfigController;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Subject;
import model.Type;

/**
 *
 * @author Yankee
 */
public class SubjectDetailController extends HomeDirectorController {

    private final String settingAddPath = "/director/setting/newsubject";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        SubjectDAO sdao = new SubjectDAO();
        String id = request.getParameter("subject_id");
        Subject sub = sdao.getSubjectDetail(id);

        request.setAttribute("subject", sub);
        request.getRequestDispatcher("../../view/director/setting/subjectdetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGetAddSubject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("contentPageIncluded", "/view/director/setting/newsubject.jsp");
        //Get type list
        TypeConfigController tcc = new TypeConfigController();
        ArrayList<Type> types = tcc.getTypesList();
        request.setAttribute("types", types);
        this.loadHeader(request, response);
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
