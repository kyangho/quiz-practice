/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import dal.PostDAO;
import dal.SliderDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
    private static final String sliderEditPath = "/director/slider/edit";
    private static final String sliderAddPath = "/director/slider/add";
    private static final String sliderChangePath = "/director/slider/change_status";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(sliderListPath)) {
            doGetSliderList(request, response);
        } else if (URI.contains(sliderChangePath)) {
            doGetChangeList(request, response);
        } else if (URI.contains(sliderEditPath)) {
            request.setAttribute("edit", "edit");
            doGetEditList(request, response);
        } else if (URI.contains(sliderAddPath)) {
            request.setAttribute("add", "add");
            doGetAddSlider(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(sliderEditPath)) {
            doPostEditList(request, response);
        } else if (URI.contains(sliderAddPath)) {
            doPostAddSlider(request, response);
        }
    }

    protected void doPostAddSlider(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        InputStream fileContent = null;
        Part filePart = request.getPart("thumbnail");
        if (!filePart.getSubmittedFileName().isEmpty()) {
            fileContent = filePart.getInputStream();
        }
        String backlink = request.getParameter("backlink");
        String status = request.getParameter("status");
        String note = request.getParameter("note");
        SliderDAO s = new SliderDAO();
        s.addSlider(title, fileContent, backlink, status, note);
        response.sendRedirect("list");
    }

    protected void doGetAddSlider(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../../view/director/slider/sliderEdit.jsp").forward(request, response);
    }

    protected void doPostEditList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        InputStream fileContent = null;
        Part filePart = request.getPart("thumbnail");
        if (!filePart.getSubmittedFileName().isEmpty()) {
            fileContent = filePart.getInputStream();
        }
        String backlink = request.getParameter("backlink");
        String status = request.getParameter("status");
        String note = request.getParameter("note");
        SliderDAO s = new SliderDAO();
        s.updateSlider(id, title, fileContent, backlink, status, note);
        response.sendRedirect("list");
    }

    protected void doGetEditList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        SliderDAO s = new SliderDAO();
        Slider slider = s.GetSliderByID(id);
        request.setAttribute("slider", slider);
        request.getRequestDispatcher("../../view/director/slider/sliderEdit.jsp").forward(request, response);
    }

    protected void doGetChangeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SliderDAO s = new SliderDAO();
        String id = request.getParameter("slider_id");
        String slider_status = request.getParameter("slider_status");
        if (id != null && slider_status != null) {
            s.changeStatus(Integer.parseInt(id), slider_status);
        }
        response.sendRedirect("../slider/list");
    }

    protected void doGetSliderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SliderDAO s = new SliderDAO();
        int pagesize = 3;
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
                ss = s.getAllSliders(pageindex, pagesize, status, null);
                totalrows = s.getRowcount(status, null);
                url = "list?status=" + status + "&page=";
                request.setAttribute("status", status);
            }
        } else {
            ss = new ArrayList<>();
            ss = s.getAllSliders(pageindex, pagesize, null, search);
            request.setAttribute("search", search);
            request.setAttribute("status", status);
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
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
