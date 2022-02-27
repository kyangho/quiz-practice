/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import controller.TypeConfigController;
import dal.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.Type;

/**
 * 1
 *
 * @author ducky
 */
@WebServlet(name = "SettingController", urlPatterns = {"/director/setting/*"})
public class SettingController extends HomeDirectorController {

    private final String settingAddPath = "/director/setting/add";
    private final String settingDetailPath = "/director/setting/details";
    private final String settingUpdatePath = "/director/setting/update";
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
        String URI = request.getRequestURI().replaceFirst("\\w+/", "");
        if (URI.compareTo("/director/setting") == 0) {
            super.doGet(request, response);
        } else if (URI.compareTo(settingAddPath) == 0) {
            doGetAddSetting(request, response);
        }else if (URI.compareTo(settingDetailPath) == 0) {
            doGetSettingDetails(request, response);
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
        String URI = request.getRequestURI().replaceFirst("\\w+/", "");
        String URL = request.getServletPath();
        String test = request.getHeaderNames().nextElement();
        Enumeration<String> names = request.getHeaderNames();
        if (URI.compareTo("/director/setting") == 0) {
            super.doPost(request, response);
        } else if (URI.compareTo(settingAddPath) == 0) {
            doPostAddSetting(request, response);
        } else if (URI.compareTo(settingUpdatePath) == 0) {
            doPostSettingUpdate(request, response);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Add Setting Servlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Method doGet of add setting servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void doGetAddSetting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("contentPageIncluded", "/view/director/setting/settingadd.jsp");
        //Get type list
        TypeConfigController tcc = new TypeConfigController();
        ArrayList<Type> types = tcc.getTypesList();
        request.setAttribute("types", types);
        this.loadHeader(request, response);
    }
    /**
     * Method doPost of add setting servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void doPostAddSetting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("settingName");
        String description = request.getParameter("settingDescription");
        String type = request.getParameter("settingType");
        String status = request.getParameter("settingStatus");
        String value = request.getParameter("settingValue");
        SettingDAO sdb = new SettingDAO();
        Setting setting = new Setting(0, name, type, description, value, status);

        boolean isSuccess = sdb.insertSetting(setting);
        if (isSuccess) {
            response.getWriter().print("success");
        } else {
            response.getWriter().print(setting);
        }
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setting Details Servlet methods. Click on the + sign on the left to edit the code.">
    protected void doGetSettingDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Add jsp link for body content
        request.setAttribute("contentPageIncluded", "/view/director/setting/settingdetails.jsp");
        
        //Get setting
        SettingDAO sdb = new SettingDAO();
        Integer id = null;
            //Check id is valid or not
        try{
            id = Integer.parseInt(request.getParameter("id"));
        }catch(Exception e){
            response.sendRedirect("settinglist");
            return;
        }
        if (id == null){
            response.sendRedirect("settinglist");
            return;
        }
            //Get setting by id from database
        Setting setting = sdb.getSettingById(id);
        if (setting == null){
            response.sendRedirect("settinglist");
            return;
        }
        request.setAttribute("setting", setting);
        
        //Get type list
        TypeConfigController tcc = new TypeConfigController();
        ArrayList<Type> types = tcc.getTypesList();
        for (int i = 0; i < types.size(); i++) {
            if (types.get(i).getName().compareTo(setting.getType()) == 0) {
                types.remove(i);
            }
        }
        request.setAttribute("types", types);
        this.loadHeader(request, response);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setting Update Servlet methods. Click on the + sign on the left to edit the code.">
    protected void doPostSettingUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("settingId");
        String name = request.getParameter("settingName");
        String description = request.getParameter("settingDescription");
        String type = request.getParameter("settingType");
        
        
        
        String status = request.getParameter("settingStatus");
        String value = request.getParameter("settingValue");
        SettingDAO sdb = new SettingDAO();
        Setting setting = new Setting(Integer.parseInt(id), name, type, description, value, status);
        boolean isSuccess = sdb.updateSetting(setting);
        if (isSuccess){
            response.getWriter().print("success");
        }else{
            response.getWriter().print("fail");
        }
    }// </editor-fold>
}
