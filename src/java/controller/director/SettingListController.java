/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import controller.TypeConfigController;
import dal.SettingDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.Type;

/**
 *
 * @author conmu
 */
public class SettingListController extends HttpServlet {

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

        int pageSize = 2;
        String pageIndex_raw = request.getParameter("pageindex");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        String setting_name = request.getParameter("setting_name");
        int pageIndex;
        if (pageIndex_raw == null || pageIndex_raw.length() == 0) {
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(pageIndex_raw);
        }
        SettingDAO stdb = new SettingDAO();
        ArrayList<Setting> settings = null;
        int totalRows;
        int totalPage;
        String url;
        if (setting_name == null) {
            if (status == null && type == null) {
                settings = new ArrayList<>();
                settings = stdb.getALLSetting(pageSize, pageIndex, "all", "all", null);
                totalRows = stdb.totalRowsInSetting("all", "all", null);
                url = "settinglist?pageindex=";
            } else {
                settings = new ArrayList<>();
                settings = stdb.getALLSetting(pageSize, pageIndex, type, status, null);
                totalRows = stdb.totalRowsInSetting(status, type, null);
                url = "settinglist?status=" + status + "&type=" + type + "&pageindex=";
                request.setAttribute("type", type);
                request.setAttribute("status", status);
            }
        } else {
            settings = new ArrayList<>();
            settings = stdb.getALLSetting(pageSize, pageIndex, null, null, setting_name);
            request.setAttribute("valueSearch", setting_name);
            url = "settinglist?setting_name=" + setting_name + "&pageindex=";
            totalRows = stdb.totalRowsInSetting(null, null, setting_name);
        }
        totalPage = (totalRows % pageSize == 0) ? totalRows / pageSize : totalRows / pageSize + 1;
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("settings", settings);
        request.setAttribute("url", url);
        request.setAttribute("pageindex", pageIndex);
        request.setAttribute("tag", "settinglist");
        
        //ducky
        TypeConfigController tcc = new TypeConfigController();
        ArrayList<Type> types =  tcc.getTypesList();
        request.setAttribute("types", types);
        
        request.getRequestDispatcher("../../view/director/setting/settinglist.jsp").forward(request, response);
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
        int pageSize = 2;
        SettingDAO stdb = new SettingDAO();
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        String setting_name = request.getParameter("setting_name");
        int totalRows;
        int totalPage;
        String url;
        ArrayList<Setting> settings = null;
        if (setting_name == null) {
            settings = new ArrayList<>();
            settings = stdb.getALLSetting(pageSize, 1, type, status, null);
            totalRows = stdb.totalRowsInSetting(status, type, null);
            url = "settinglist?status=" + status + "&type=" + type + "&pageindex=";

            request.setAttribute("type", type);
            request.setAttribute("status", status);
        } else {
            settings = new ArrayList<>();
            settings = stdb.getALLSetting(pageSize, 1, null, null, setting_name);
            request.setAttribute("valueSearch", setting_name);
            url = "settinglist?setting_name=" + setting_name + "&pageindex=";
            totalRows = stdb.totalRowsInSetting(null, null, setting_name);
        }
        totalPage = (totalRows % pageSize == 0) ? totalRows / pageSize : totalRows / pageSize + 1;
        request.setAttribute("totalPage", totalPage);

        request.setAttribute("settings", settings);
        request.setAttribute("url", url);
        request.setAttribute("pageindex", 1);
        request.setAttribute("tag", "settinglist");
        TypeConfigController tcc = new TypeConfigController();
        ArrayList<Type> types =  tcc.getTypesList();
        request.setAttribute("types", types);
        request.getRequestDispatcher("../../view/director/setting/settinglist.jsp").forward(request, response);
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
