/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import dal.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Slider;

/**
 *
 * @author Yankee
 */
@WebServlet(name = "SliderController", urlPatterns = {"/director/slider/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class SliderController extends HttpServlet {

    private static final String sliderListPath = "/director/slider/list";
    private static final String sliderDetailPath = "/director/slider/detail";
    private static final String sliderEditPath = "/director/slider/edit";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(sliderListPath)) {
            doGetPostList(request, response);
        }

    }

    protected void doGetPostList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SliderDAO s = new SliderDAO();
        int pagesize = 5;
        String raw_page = request.getParameter("page");
        String status = request.getParameter("status");
        String search = request.getParameter("search");
        int pageindex;
        if (raw_page == null || raw_page.length() == 0) {
            pageindex = 1;
        } else {
            pageindex = Integer.parseInt(raw_page);
        }
        ArrayList<Slider> ss;
        int totalrows;
        String url;
        if (search == null) {
            ss = new ArrayList<>();
            if (status == null) {
                ss = s.getAllSliders(pageindex, pagesize, "all", null);
                totalrows = s.getRowcount(status, null);
                url = "list?page=";
            } else {
                ss = s.getAllSliders(pagesize, pageindex, status, null);
                totalrows = s.getRowcount(status, null);
                url = "list?status=" + status + "&page=";
                request.setAttribute("status", status);
            }
        } else {
            ss = new ArrayList<>();
            ss = s.getAllSliders(pagesize, pageindex, null, search);
            request.setAttribute("search", search);
            totalrows = s.getRowcount(null, search);
            url = "list?search=" + search + "&page=";

        }
        int totalpage = (totalrows % pagesize == 0) ? totalrows / pagesize : totalrows / pagesize + 1;
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("slider", ss);
        request.setAttribute("tag", "slider");
        request.setAttribute("url", url);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.getRequestDispatcher("../../view/director/slider/sliderList.jsp").forward(request, response);
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
