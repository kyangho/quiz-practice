/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import dal.SettingDBContext;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;

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

        if (request.getSession().getAttribute("account") != null) {

            int pageSize = 10;
            String filter = request.getParameter("filter");
            String value = request.getParameter("value");
            int pageIndex;
            String lastID;
            int lastID_raw;
            SettingDBContext stdb = new SettingDBContext();
            ArrayList<Setting> settinglist;
            String url;
            String pageIndex_raw = request.getParameter("pageindex");
            if (filter == null) {
                if (pageIndex_raw == null) {
                    lastID = "> 0";
                    pageIndex = 1;
                } else {
                    pageIndex = Integer.parseInt(pageIndex_raw);
                    lastID_raw = pageIndex * pageSize - 1;
                    lastID = ">" + lastID_raw;
                }

                settinglist = stdb.getSettings(lastID, pageSize, null, null);
                int totalRows = stdb.toltalRowsInSetting(null, null);
                int toltalPage = (totalRows % pageSize == 0) ? totalRows / pageSize : totalRows / pageSize + 1;
                request.setAttribute("totalPage", toltalPage);
                url = "settinglist?pageindex=";
            } else {
                if (pageIndex_raw == null) {
                    lastID = ">0";
                    pageIndex = 1;
                } else {
                    pageIndex = Integer.parseInt(pageIndex_raw);
                    lastID = request.getParameter("lastID");
                }

                if (filter.equals("setting_type")) {
                    settinglist = new ArrayList<>();
                    if (value.equals("all")) {
                        settinglist = stdb.getSettings(lastID, pageSize, null, null);
                    } else {
                        settinglist = stdb.getSettings(lastID, pageSize, filter, value);
                    }
                    request.setAttribute("value", value);
                } else if (filter.equals("setting_status")) {
                    settinglist = new ArrayList<>();
                    if (value.equals("all")) {
                        settinglist = stdb.getSettings(lastID, pageSize, null, null);
                    } else {
                        settinglist = stdb.getSettings(lastID, pageSize, filter, value);
                    }
                    request.setAttribute("value", value);
                } else {
                    settinglist = new ArrayList<>();
                    if (value.length() == 0) {
                        settinglist = stdb.getSettings(lastID, pageSize, null, null);
                    } else {
                        settinglist = stdb.getSettings(lastID, pageSize, filter, value);
                    }
                    request.setAttribute("valueSearch", value);
                }

                int totalRows = stdb.toltalRowsInSetting(filter, value);
                int toltalPage = (totalRows % pageSize == 0) ? totalRows / pageSize : totalRows / pageSize + 1;
                request.setAttribute("totalPage", toltalPage);
                lastID_raw = settinglist.get(settinglist.size() - 1).getId();
                request.setAttribute("lastID", lastID_raw);
                String s = lastID.charAt(0) + Integer.toString(lastID_raw);
                url = "settinglist?lastID=" + s + "&filter=" + filter + "&value=" + value + "&pageindex=";
            }

//        2 => lastID = 10*2-1 = 19
            request.setAttribute("pageindex", pageIndex);
            request.setAttribute("url", url);
            request.setAttribute("settings", settinglist);
            request.getRequestDispatcher("../../view/director/setting/settinglist.jsp").forward(request, response);
        }else{
            response.sendRedirect("../../home");
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
        int pageSize = 10;
        SettingDBContext stdb = new SettingDBContext();
        ArrayList<Setting> settinglist = null;
        String filter = request.getParameter("filter");
        String value;
        String pageIndex_raw = request.getParameter("pageindex");
        int pageIndex;
        String lastID;
        int lastID_raw;
        if (pageIndex_raw == null) {
            lastID = ">0";
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(pageIndex_raw);
            lastID = request.getParameter("lastID");
        }

        if (filter.equals("setting_type")) {
            value = request.getParameter("type");
            settinglist = new ArrayList<>();
            if (value.equals("all")) {
                settinglist = stdb.getSettings(lastID, pageSize, null, null);
            } else {
                settinglist = stdb.getSettings(lastID, pageSize, filter, value);
            }
            request.setAttribute("value", value);
        } else if (filter.equals("setting_status")) {
            value = request.getParameter("status");
            settinglist = new ArrayList<>();
            if (value.equals("all")) {
                settinglist = stdb.getSettings(lastID, pageSize, null, null);
            } else {
                settinglist = stdb.getSettings(lastID, pageSize, filter, value);
            }
            request.setAttribute("value", value);
        } else {
            value = request.getParameter("setting_name");
            settinglist = new ArrayList<>();
            if (value.length() == 0) {
                settinglist = stdb.getSettings(lastID, pageSize, null, null);
            } else {
                settinglist = stdb.getSettings(lastID, pageSize, filter, value);
            }
            request.setAttribute("valueSearch", value);
        }
        request.setAttribute("pageindex", pageIndex);
        int totalRows = stdb.toltalRowsInSetting(filter, value);
        int toltalPage = (totalRows % pageSize == 0) ? totalRows / pageSize : totalRows / pageSize + 1;
        request.setAttribute("totalPage", toltalPage);
        lastID_raw = settinglist.get(settinglist.size() - 1).getId();
        request.setAttribute("lastID", lastID_raw);
        String s = lastID.charAt(0) + Integer.toString(lastID_raw);
        String url = "settinglist?lastID=" + s + "&filter=" + filter + "&value=" + value + "&pageindex=";
        request.setAttribute("url", url);
        request.setAttribute("settings", settinglist);

//        response.getWriter().print(lastID);
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
