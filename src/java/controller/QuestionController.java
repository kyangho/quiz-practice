/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.QuestionDAO;
import dal.QuizDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
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
import model.Account;
import model.Answer;
import model.Category;
import model.Question;
import model.Subcategory;
import model.Subject;

/**
 *
 * @author conmu
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024 * 17)
@WebServlet(name = "QuestionController", urlPatterns = {"/question/*"})
public class QuestionController extends HttpServlet {

    private final String questionListPath = "/question/list";
    private final String questionDetailsPath = "/question/details";
    private final String deleteAnswerPath = "/question/delete";
//    private final String mediaPath = "/question/media";
    private final int pagesize = 4;

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(questionListPath)) {
            doGetQuestionList(request, response);
        } else if (URI.contains(questionDetailsPath)) {
            doGetQuestionDetails(request, response);
        } else if (URI.contains(deleteAnswerPath)) {
            doGetDeleteAnswer(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String URI = request.getRequestURI().replaceFirst("/\\w+", "");
        if (URI.contains(questionListPath)) {
//            doPostQuestionList(request, response);
        } else if (URI.contains(questionDetailsPath)) {
            doPostQuestionDetails(request, response);
        } else {
            doGetDeleteAnswer(request, response);
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

    private void doGetQuestionList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        String pageindex_raw = request.getParameter("pageindex");
        if (pageindex_raw == null || pageindex_raw.trim().length() == 0) {
            pageindex_raw = "1";
        }
        int pageindex = Integer.parseInt(pageindex_raw);
        QuestionDAO qdao = new QuestionDAO();
        String key = request.getParameter("key");
        if (key != null && key.trim().length() != 0) {
            ArrayList<Question> questions = qdao.getQuestions(acc.getId(), pageindex, pagesize, key, "all", "all", "all", "all");
            request.setAttribute("questions", questions);
            int totalRow = qdao.getTotalRows(acc.getId(), key, "all", "all", "all", "all");
            int totalPage = (totalRow % pagesize == 0) ? totalRow / pagesize : totalRow / pagesize + 1;
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageindex", pageindex);
            String url = "list?key=" + key + "&pageindex=";
            request.setAttribute("url", url);
            request.setAttribute("key", key);
        } else {
            String subject = request.getParameter("subject");
            String subcategory = request.getParameter("subcategory");
            String level = request.getParameter("level");
            String status = request.getParameter("status");
//            response.getWriter().print(subject + " " + subcategory + " " + level + " " + status);
            if (subject != null && subcategory != null && level != null && status != null) {
                ArrayList<Question> questions = qdao.getQuestions(acc.getId(), pageindex, pagesize, null, subject, subcategory, level, status);
                request.setAttribute("questions", questions);
                int totalRow = qdao.getTotalRows(acc.getId(), null, subject, subcategory, level, status);
                int totalPage = (totalRow % pagesize == 0) ? totalRow / pagesize : totalRow / pagesize + 1;
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("pageindex", pageindex);
                String url = "list?subject=" + subject + "&subcategory=" + subcategory + "&level=" + level + "&status=" + status + "&pageindex=";
                request.setAttribute("url", url);
                request.setAttribute("status", status);
                request.setAttribute("subject", subject);
                request.setAttribute("subcategory", subcategory);
                request.setAttribute("level", level);
            } else {
                ArrayList<Question> questions = qdao.getQuestions(acc.getId(), pageindex, pagesize, null, null, null, null, null);
                request.setAttribute("questions", questions);
                int totalRow = qdao.getTotalRows(acc.getId(), null, "all", "all", "all", "all");
                int totalPage = (totalRow % pagesize == 0) ? totalRow / pagesize : totalRow / pagesize + 1;
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("pageindex", pageindex);
                String url = "list?pageindex=";
                request.setAttribute("url", url);
            }
        }
        QuizDAO q = new QuizDAO();
        request.setAttribute("subjects", q.getsubs());
        request.setAttribute("subcate", qdao.getSubcategorys());
        request.getRequestDispatcher("../view/director/question/questionlist.jsp").forward(request, response);
    }

    private void doGetQuestionDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        String quesId = request.getParameter("questionId");
        String cateId = request.getParameter("cateid");
        QuestionDAO qdao = new QuestionDAO();
        Question question = qdao.getQuestionById(Integer.parseInt(quesId));
        request.setAttribute("question", question);
        if (cateId == null) {
            request.setAttribute("subcate", qdao.getSubCategoryByCate(question.getCategory().getCategory_id()));
        } else {
            request.setAttribute("subcate", qdao.getSubCategoryByCate(Integer.parseInt(cateId)));
        }
        QuizDAO q = new QuizDAO();
        request.setAttribute("categories", qdao.getCategory());
        request.setAttribute("subjects", q.getsubs());
        request.setAttribute("quizs", qdao.getQuizForQuestion(account.getId()));
        request.setAttribute("quizId", qdao.getQuizIdOfQuestion(question.getId()));
        request.getRequestDispatcher("../view/director/question/questiondetails.jsp").forward(request, response);
    }

    private void doGetDeleteAnswer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
//        response.getWriter().print(id);
    }

    private void doPostQuestionDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part mediaPart = request.getPart("media");
//        String mediaName = Paths.get(mediaPart.getSubmittedFileName()).getFileName().toString();
        InputStream mediaContent = null;
        if (!mediaPart.getSubmittedFileName().isEmpty()) {
            mediaContent = mediaPart.getInputStream();
        }
        Question q = new Question();
        q.setId(Integer.parseInt(request.getParameter("questionId")));
        q.setCategory(new Category(Integer.parseInt(request.getParameter("category")), null));
        q.setSubject(new Subject(Integer.parseInt(request.getParameter("subject")), null));
        q.setSubCategory(new Subcategory(Integer.parseInt(request.getParameter("subcategory")), null));
        q.setLevel(request.getParameter("level"));
        q.setStatus(request.getParameter("status"));
        q.setContent(request.getParameter("content").replaceAll("\\s+", " ").trim());
        String[] answers = request.getParameterValues("answer");
        ArrayList<Answer> ans = new ArrayList<>();
        for (String answer : answers) {
            ans.add(new Answer(0, answer));
        }
        q.setAnswers(ans);
//        q.setMedia((Blob) mediaContent);
        String quizid = request.getParameter("quiz");
//        response.getWriter().print("quesId:" + q.getId() + ", level: " + q.getLevel() + ",content: " + q.getContent() + ", status " + q.getStatus() 
//                + ", cate: " + q.getCategory().getCategory_id() + ", sub: " + q.getSubCategory().getId() + ", subject: " + q.getSubject().getSubject_id()
//                + ", quizID:" + quizid + ", media: " + q.getMedia());
        response.getWriter().print(q.getContent() + " " + q.getContent().length() + " " + q.getLevel() + " ");
        QuestionDAO qdao = new QuestionDAO();
//        if (qdao.updateQuestion(quizid, q, mediaContent)) {
            qdao.updateQuestion(quizid, q, mediaContent);
            request.setAttribute("subcate", qdao.getSubCategoryByCate(q.getCategory().getCategory_id()));
            request.setAttribute("categories", qdao.getCategory());
            QuizDAO qiAO = new QuizDAO();
            Account account = (Account) request.getSession().getAttribute("account");
            request.setAttribute("subjects", qiAO.getsubs());
            request.setAttribute("quizs", qdao.getQuizForQuestion(account.getId()));
            request.setAttribute("quizId", qdao.getQuizIdOfQuestion(q.getId()));
            request.setAttribute("tag", "done");
            request.setAttribute("question", q);
            request.getRequestDispatcher("../view/director/question/questiondetails.jsp").forward(request, response);
            
//        } else {
//            response.getWriter().print("update failed");
//        }

    }

}
