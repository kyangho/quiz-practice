/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.PostDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Account;
import model.post.Post;
import model.post.PostCategory;
import model.post.PostFile;

/**
 *
 * @author ducky
 */
@WebServlet(name = "PostController", urlPatterns = {"/post/*"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024 * 17)
public class PostController extends HttpServlet {

    private static final String postListPath = "/post/list";
    private static final String postDetailPath = "/post/detail";
    private static final String postNewPath = "/post/new";
    private static final String postImagePath = "/post/image";
    private static final String postUpdatePath = "/post/update";
    private static final String postRetrievePath = "/post/file";

    private static final int PAGESIZE = 3;

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
        ArrayList<Post> posts = new ArrayList<>();

        if (URI.contains(postListPath)) {
            doGetPostList(request, response);
        } else if (URI.contains(postDetailPath)) {
            doGetPostDetail(request, response);
        } else if (URI.contains(postUpdatePath)) {
            doGetUpdatePost(request, response);
        } else if (URI.contains(postImagePath)) {
            doGetPostThumbnail(request, response);
        } else if (URI.contains(postNewPath)) {
            doGetNewPost(request, response);
        } else if (URI.contains("/post/thumbnail")) {
            request.getRequestDispatcher("/view/post/postthumbnail.jsp").forward(request, response);
        } else if (URI.contains(postRetrievePath)) {
            doGetFileRetrieve(request, response);
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
        if (URI.contains(postNewPath)) {
            doPostNewPost(request, response);
        } else if (URI.contains(postUpdatePath)) {
            doPostUpdatePost(request, response);
        } else if (URI.contains("/post/thumbnail")) {
            doPostPostThumbnail(request, response);
        }
    }

    protected void doGetPostDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO pd = new PostDAO();
        Post post = null;
        try {
            String id = request.getParameter("id");
            post = pd.getPostWithId(Integer.parseInt(id));
        } catch (Exception e) {
            post = null;
        }
        try {
            request.setAttribute("fileName", pd.getPostFileListWithId(post.getId()).get(0).getName());
        } catch (Exception e) {

        }
        request.setAttribute("post", post);
        request.getRequestDispatcher("/view/post/postdetail.jsp").forward(request, response);
    }

    protected void doGetNewPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO pd = new PostDAO();

        ArrayList<PostCategory> categories = pd.getPostCategories("", "");
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/view/post/postnew.jsp").forward(request, response);
    }

    protected void doPostNewPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> parasMap = request.getParameterMap();
        String title = parasMap.get("postTitle")[0];
        String brief = parasMap.get("postBrief")[0];
        String content = parasMap.get("postContent")[0];
        String[] categories = parasMap.get("categories");
        Post p = new Post();
        ArrayList<PostCategory> categoryList = new ArrayList<>();
        Date now = new Date(System.currentTimeMillis());

        for (String s : categories) {
            categoryList.add(new PostCategory(Integer.parseInt(s), "", ""));
        }
        Account account = (Account) request.getSession().getAttribute("account");
        p.setTitle(title);
        p.setContent(content);
        p.setCategories(categoryList);
        p.setDateCreated(now);
        p.setDateModified(now);
        p.setStatus("PUBLISH");
        p.setFeaturing(false);
        p.setAuthor(account.getUsername());
        p.setBrief(brief);
        PostDAO postDAO = new PostDAO();

        Part thumbnailPart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        InputStream fileContent = thumbnailPart.getInputStream();

        String id = postDAO.insertPost(p, fileContent);

        Part filesPart = request.getPart("thumbnail"); // Retrieves <input type="file" name="files">
        String fileName = Paths.get(filesPart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        fileContent = filesPart.getInputStream();

        postDAO.insertPostFile(Integer.parseInt(id), fileName, "", fileContent);

        response.sendRedirect("list");
    }

    protected void doGetUpdatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO pd = new PostDAO();
        Post post = null;
        try {
            String id = request.getParameter("id");
            post = pd.getPostWithId(Integer.parseInt(id));
        } catch (Exception e) {
            post = null;
        }
        ArrayList<PostCategory> categories = pd.getPostCategories("", "");
        request.setAttribute("categories", categories);

        request.setAttribute("post", post);
        request.getRequestDispatcher("/view/post/postupdate.jsp").forward(request, response);
    }

    protected void doPostUpdatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> parasMap = request.getParameterMap();
        String id = parasMap.get("id")[0];
        String title = parasMap.get("postTitle")[0];
        String brief = parasMap.get("postBrief")[0];
        String content = parasMap.get("postContent")[0];
        String[] categories = parasMap.get("categories");
        PostDAO postDAO = new PostDAO();
        Post p = postDAO.getPostWithId(Integer.parseInt(id));
        ArrayList<PostCategory> categoryList = new ArrayList<>();
        Date now = new Date(System.currentTimeMillis());

        for (String s : categories) {
            categoryList.add(new PostCategory(Integer.parseInt(s), "", ""));
        }
        Account account = (Account) request.getSession().getAttribute("account");
        p.setTitle(title);
        p.setContent(content);
        p.setCategories(categoryList);
        p.setDateModified(now);
        p.setStatus("PUBLISH");
        p.setFeaturing(false);
        p.setBrief(brief);

        Part thumbnailPart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        InputStream fileContent = thumbnailPart.getInputStream();

        postDAO.updatetPost(p, fileContent);

        Part filesPart = request.getPart("thumbnail"); // Retrieves <input type="file" name="files">
        String fileName = Paths.get(filesPart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        fileContent = filesPart.getInputStream();

        response.sendRedirect("list");
    }

    protected void doGetPostList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO pd = new PostDAO();
        ArrayList<Post> posts = new ArrayList<>();
        Map<String, String[]> paraMap = request.getParameterMap();
        String search = "";
        String category = "";
        String author = "";
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
        posts = pd.getPostsList(search, category, author, "", PAGESIZE, pageIndex);
        ArrayList<PostCategory> categories = pd.getPostCategories("", "");
        PostCategory currentCategory = null;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getName().compareTo(category) == 0) {
                currentCategory = categories.get(i);
                categories.remove(i);
                break;
            }
        }
        request.setAttribute("pageSize", PAGESIZE);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("currentCategory", currentCategory);
        request.setAttribute("categories", categories);
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/view/post/postlist.jsp").forward(request, response);
    }

    public void doGetPostThumbnail(HttpServletRequest request, HttpServletResponse response)
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
            response.setContentType("image/png");
            ServletOutputStream out = response.getOutputStream();
            out.write(buffer, 0, (int) blob.length());
            os.flush();
            os.close();
        } catch (SQLException ex) {
        }
    }

    public void doPostPostThumbnail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String description = request.getParameter("thumbnail"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        PostDAO postDAO = new PostDAO();
        postDAO.updatePostThumbnail(4, fileContent);
    }

    protected void doPostUploadFile(HttpServletRequest request, HttpServletResponse response, String partName)
            throws ServletException, IOException {

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
