/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import controller.TypeConfigController;
import dal.SettingDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.Type;

/**
 *
 * @author ducky
 */
public class AddSettingController extends HomeDirectorController {

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
        request.setAttribute("contentPageIncluded", "/view/director/setting/settingadd.jsp");
        //Get type list
        TypeConfigController tcc = new TypeConfigController();
        ArrayList<Type> types = tcc.getTypesList();
        request.setAttribute("types", types);
        this.loadHeader(request, response);
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
        String name = request.getParameter("settingName");
        String description = request.getParameter("settingDescription");
        String type = request.getParameter("settingType");
        String status = request.getParameter("settingStatus");
        String value = request.getParameter("settingValue");
        SettingDBContext sdb = new SettingDBContext();
        Setting setting = new Setting(0, name, type, description, value, status);
        
        boolean isSuccess = sdb.insertSetting(setting);
        if (isSuccess) {
            response.getWriter().print("success");
        } else {
            response.getWriter().print(setting);
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
