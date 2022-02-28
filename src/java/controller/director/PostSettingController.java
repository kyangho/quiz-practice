/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import dal.PostDAO;
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
import model.post.Post;
import model.post.PostCategory;
import model.post.PostFile;

/**
 *
 * @author ducky
 */
@WebServlet(name = "PostSettingController", urlPatterns = {"/director/post/*"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024 * 17)
public class PostSettingController extends HttpServlet {

    private static final String postListPath = "/director/post/list";
    private static final String postDetailPath = "/director/post/view";
    private static final String postNewPath = "/director/post/new";
    private static final String postImagePath = "/director/post/image";
    private static final String postUpdatePath = "/director/post/update";
    private static final String postRetrievePath = "/director/post/file";
    private static final String postChangeStatusPath = "/director/post/changestatus";
    private static final String postChangeFeaturePath = "/director/post/changefeature";
    private static final int PAGESIZE = 5;

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
        if (URI.contains(postListPath)) {
            doGetPostList(request, response);
        } else if (URI.contains(postDetailPath)) {
            doGetPostDetail(request, response);
        } else if (URI.contains(postUpdatePath) && account != null) {
            doGetUpdatePost(request, response);
        } else if (URI.contains(postImagePath)) {
            doGetPostThumbnail(request, response);
        } else if (URI.contains(postNewPath) && account != null) {
            doGetNewPost(request, response);
        } else if (URI.contains("/post/thumbnail")) {
            request.getRequestDispatcher("/view/post/postthumbnail.jsp").forward(request, response);
        } else if (URI.contains(postRetrievePath)) {
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

        if (URI.contains(postNewPath) && account != null) {
            doPostNewPost(request, response);
        } else if (URI.contains(postUpdatePath) && account != null) {
            doPostUpdatePost(request, response);
        } else if (URI.contains("thumbnail")) {
            doPostPostThumbnail(request, response);
        } else if (URI.contains("file")) {
            doPostUploadFile(request, response);
        } else if (URI.contains(postChangeStatusPath)) {
            doPostChangeStatus(request, response);
        } else if (URI.contains(postChangeFeaturePath)) {
            doPostChangeFeature(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
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
        request.getRequestDispatcher("/view/director/post/postview.jsp").forward(request, response);
    }

    protected void doGetNewPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO pd = new PostDAO();

        ArrayList<PostCategory> categories = pd.getPostCategories("", "");
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/view/director/post/postnew.jsp").forward(request, response);
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
        p.setIsFeature(false);
        p.setAuthor(account.getUsername());
        p.setBrief(brief);
        PostDAO postDAO = new PostDAO();

        Part thumbnailPart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        InputStream fileThumbnail = thumbnailPart.getInputStream();

        String id = postDAO.insertPost(p, fileThumbnail);

        Part filesPart = request.getPart("files"); // Retrieves <input type="file" name="files">
        String fileName = Paths.get(filesPart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filesPart.getInputStream();
        if (filesPart.getSize() > 0) {
            postDAO.insertPostFile(Integer.parseInt(id), fileName, "", fileContent);
        }
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

        try {
            request.setAttribute("fileName", pd.getPostFileListWithId(post.getId()).get(0).getName());
        } catch (Exception e) {

        }
        request.setAttribute("post", post);
        request.getRequestDispatcher("/view/director/post/postupdate.jsp").forward(request, response);
    }

    protected void doPostUpdatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> parasMap = request.getParameterMap();
        String id = parasMap.get("id")[0];
        String title = parasMap.get("postTitle")[0];
        String brief = parasMap.get("postBrief")[0];
        String content = parasMap.get("postContent")[0];
        String feature = parasMap.get("feature")[0];
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
        p.setIsFeature(Boolean.valueOf(feature));
        p.setBrief(brief);

        Part thumbnailPart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        InputStream fileContent = thumbnailPart.getInputStream();
        if (thumbnailPart.getSize() > 0) {
            postDAO.updatetPost(p, fileContent);
        } else {
            postDAO.updatetPost(p);
        }
        Part filesPart = request.getPart("files"); // Retrieves <input type="file" name="files">
        String fileName = Paths.get(filesPart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        fileContent = filesPart.getInputStream();
        if (filesPart.getSize() > 0) {
            postDAO.updatePostFile(p.getId(), fileName, "", fileContent);
        }
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
        String status = "";
        String title = "";
        String feature = "";
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
        if (paraMap.containsKey("title")) {
            String[] values = paraMap.get("title");
            title = values[0];
        }
        if (paraMap.containsKey("feature")) {
            String[] values = paraMap.get("feature");
            feature = values[0];
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
        Account acc = (Account) request.getSession().getAttribute("account");
        if (acc.getUsername().compareToIgnoreCase("admin") != 0){
            author = acc.getUsername();
        }   
        if (paraMap.containsKey("sort")) {
            posts = pd.getPostsListSortBy(title, category, author, feature, status, PAGESIZE, pageIndex);
        } else {
            Boolean isFeature = null;
            if (feature.compareToIgnoreCase("true") == 0) {
                isFeature = true;
            } else if (feature.compareToIgnoreCase("false") == 0) {
                isFeature = false;
            }
            posts = pd.getPostsList(search, category, author, status, isFeature, PAGESIZE, pageIndex);
        }
        ArrayList<PostCategory> categories = pd.getPostCategories("", "");
        PostCategory currentCategory = null;
//        for (int i = 0; i < categories.size(); i++) {
//            if (categories.get(i).getName().compareTo(category) == 0) {
//                currentCategory = categories.get(i);
//                categories.remove(i);
//                break;
//            }
//        }

        int postTotal = 0;
        if (paraMap.containsKey("sort")) {
            postTotal = pd.countTotalPostWithCondition("", "", "", "", PAGESIZE, pageIndex);
        } else {
            postTotal = pd.countTotalPostWithCondition(search, category, author, status, PAGESIZE, pageIndex);
        }
        double pageTotal = Math.ceil((double) postTotal / (double) PAGESIZE);
        if (pageIndex < 1 || pageIndex > pageTotal) {
            response.sendRedirect("list");
            return;
        }

        request.setAttribute("pageTotal", pageTotal);
        request.setAttribute("pageSize", PAGESIZE);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("currentCategory", currentCategory);
        request.setAttribute("categories", categories);
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/view/director/post/postlist.jsp").forward(request, response);
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
            response.setContentType("image/*");
            ServletOutputStream out = response.getOutputStream();
            out.write(buffer, 0, (int) blob.length());
            os.flush();
            os.close();
        } catch (SQLException ex) {
        }
    }

    public void doPostPostThumbnail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        PostDAO postDAO = new PostDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        postDAO.updatePostThumbnail(id, fileContent);
    }

    protected void doPostUploadFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="thumbnail">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        PostDAO postDAO = new PostDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        String type = request.getParameter("type");

        postDAO.updatePostFile(id, fileName, type, fileContent);
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

    public void doPostChangeStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        PostDAO postDAO = new PostDAO();
        Post post = postDAO.getPostWithId(Integer.parseInt(id));
        if (post.getStatus().compareToIgnoreCase("publish") == 0) {
            post.setStatus("UNPUBLISH");
        } else {
            post.setStatus("PUBLISH");
        }
        boolean isSuccess = postDAO.updateStatus(Integer.parseInt(id), post.getStatus());
    }
    public void doPostChangeFeature(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        PostDAO postDAO = new PostDAO();
        Post post = postDAO.getPostWithId(Integer.parseInt(id));
        Date now = new Date(System.currentTimeMillis());
        post.setIsFeature(!post.isIsFeature());
        boolean isSuccess = postDAO.updateFeature(Integer.parseInt(id), post.isIsFeature());
    }
}
