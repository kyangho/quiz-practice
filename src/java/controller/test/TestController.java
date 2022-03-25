/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.test;

import controller.*;
import dal.PostDAO;
import dal.QuizDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Account;
import model.Quiz;
import model.Setting;
import model.post.Post;
import model.post.PostCategory;
import model.post.PostFile;

/**
 *
 * @author kienct
 */
@WebServlet(name = "TestController", urlPatterns = {"/test/*"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024 * 17)
public class TestController extends HttpServlet {

    private static final String testListPath = "/test/list";
    private static final String testDetailPath = "/test/detail";
    private static final String postNewPath = "/post/new";
    private static final String testImagePath = "/test/image";
    private static final String postUpdatePath = "/post/update";
    private static final String testRetrievePath = "/test/file";

    private static final int PAGESIZE = 7;

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
        Account account = (Account) request.getSession().getAttribute("account");
        if (URI.contains(testListPath)) {
            doGetTestList(request, response);
        } else if (URI.contains(testDetailPath)) {
            doGetTestDetail(request, response);
        } else if (URI.contains(testImagePath)) {
            doGetTestThumbnail(request, response);
        } else if (URI.contains(testRetrievePath)) {
            doGetFileRetrieve(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
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
        Account account = (Account) request.getSession().getAttribute("account");

        if (URI.contains("/post/thumbnail")) {
            doPostTestThumbnail(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    protected void doGetTestDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDAO quizDAO = new QuizDAO();
        Quiz test = null;
        try {
            String id = request.getParameter("id");
            test = quizDAO.getQuizDetailById(Integer.parseInt(id));
        } catch (Exception e) {
            test = null;
        }
        ArrayList<Quiz> tests = quizDAO.getTestsListSortByUpdateDate("", "", "", 5, 1);

        ArrayList<Quiz> featureTests = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            featureTests.add(tests.get(i));
        }
        request.setAttribute("featureTests", featureTests);
        ArrayList<Setting> categories = quizDAO.getQuizCategories();
        request.setAttribute("test", test);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/view/test/testdetail.jsp").forward(request, response);
    }

    protected void doGetTestList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Quiz> tests = new ArrayList<>();
        Map<String, String[]> paraMap = request.getParameterMap();
        String search = "";
        String category = "";
        String author = "";
        String status = "";
        int pageIndex;
        try {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        } catch (Exception e) {
            pageIndex = 1;
        }
        if (paraMap.containsKey("search")) {
            String[] values = paraMap.get("search");
            search = values[0];
        }
        if (paraMap.containsKey("category")) {
            String[] values = paraMap.get("category");
            category = values[0];
        }
        if (paraMap.containsKey("author")) {
            String[] values = paraMap.get("author");
            author = values[0];
        }
        if (paraMap.containsKey("status")) {
            String[] values = paraMap.get("status");
            status = values[0];
        }
        QuizDAO quizDAO = new QuizDAO();
        tests = quizDAO.getTestsListSortByUpdateDate(search, category, author, PAGESIZE, pageIndex);
        ArrayList<Setting> categories = quizDAO.getQuizCategories();
        PostCategory currentCategory = null;

        int postTotal = quizDAO.countTotalTestWithCondition(search, category, author, PAGESIZE, pageIndex);
        double pageTotal = Math.ceil((double) postTotal / (double) PAGESIZE);
        ArrayList<Quiz> featureTests = quizDAO.getTestsListSortByUpdateDate("", "", "", 5, 1);
        request.setAttribute("featureTests", featureTests);
        request.setAttribute("pageTotal", pageTotal);
        request.setAttribute("pageSize", PAGESIZE);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("currentCategory", currentCategory);
        request.setAttribute("categories", categories);
        request.setAttribute("tests", tests);
        request.getRequestDispatcher("/view/test/testlist.jsp").forward(request, response);
    }

    public void doGetTestThumbnail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO postDAO = new PostDAO();
        String id = request.getParameter("id");
        int postId = Integer.parseInt(id);
        Blob blob = postDAO.getPostWithId(postId).getThumbnail();
        byte[] buffer;
        try {
            response.reset();
            buffer = blob.getBytes(1, (int) blob.length());
            OutputStream os = response.getOutputStream();
            response.setContentType("image/*");
            ServletOutputStream out = response.getOutputStream();
            out.write(buffer, 0, (int) blob.length());
            os.flush();
            os.close();
        } catch (SQLException ex) {
        }
    }

    public void doPostTestThumbnail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        PostDAO postDAO = new PostDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        postDAO.updatePostThumbnail(id, fileContent);
    }

    public void doGetFileRetrieve(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        PostDAO postDAO = new PostDAO();
        PostFile pf = postDAO.getPostFileWithId(Integer.parseInt(id));
        Blob blob = pf.getFileBlob();
        byte[] buffer;
        try {
            response.reset();
            buffer = blob.getBytes(1, (int) blob.length());
            OutputStream os = response.getOutputStream();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + pf.getName());
            ServletOutputStream out = response.getOutputStream();
            out.write(buffer, 0, (int) blob.length());
            os.flush();
            os.close();
        } catch (SQLException ex) {
        }
    }
}
