/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.QuestionAnswerDAO;
import dal.QuestionDAO;
import dal.QuizDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private final String changeStatusPath = "/question/changestatus";
    private final String mediaPath = "/question/media";
    private final String importPath = "/question/import";
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
//        System.out.println(URI);
        if (URI.contains(questionListPath)) {
            doGetQuestionList(request, response);
        } else if (URI.contains(questionDetailsPath)) {
            doGetQuestionDetails(request, response);
//            response.getWriter().print(URI);
        } else if (URI.contains(deleteAnswerPath)) {
            doGetDeleteAnswer(request, response);
//            response.getWriter().print(URI);
        } else if (URI.contains(changeStatusPath)) {
            doGetChangeStatus(request, response);
//            response.getWriter().print(URI);
        } else if (URI.contains(mediaPath)) {
            doGetMedia(request, response);
//            response.getWriter().print(URI);
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
            doGetQuestionList(request, response);
        } else if (URI.contains(questionDetailsPath)) {
            doPostQuestionDetails(request, response);
//            response.getWriter().print(URI);
        } else if (URI.contains(deleteAnswerPath)) {
            doGetDeleteAnswer(request, response);
//            response.getWriter().print(URI);
        } else if (URI.contains(changeStatusPath)) {
            doGetChangeStatus(request, response);
//            response.getWriter().print(URI);
        } else if (URI.contains(mediaPath)) {
            doGetMedia(request, response);
//            response.getWriter().print(URI);
        } else if (URI.contains(importPath)) {
            doPostQuestionImport(request, response);
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
        request.setAttribute("tag", "question");
        request.setAttribute("subjects", q.getsubs());
        request.setAttribute("subcate", qdao.getSubcategorys());
        request.getRequestDispatcher("../view/director/question/questionlist.jsp").forward(request, response);
    }

    private void doGetQuestionDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        String quesId = request.getParameter("questionId");
        if (quesId == null) {
            quesId = request.getAttribute("quesid").toString();
        }
        String cateId = request.getParameter("cateid");
        QuestionDAO qdao = new QuestionDAO();
        Question question = qdao.getQuestionById(Integer.parseInt(quesId), account.getId());
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
        request.setAttribute("tag", "question");
        request.getRequestDispatcher("../view/director/question/questiondetails.jsp").forward(request, response);
    }

    private void doGetDeleteAnswer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String anId = request.getParameter("anId");
        String quesid = request.getParameter("quesid");
        QuestionDAO qdao = new QuestionDAO();
        response.getWriter().print(anId + " " + quesid);
        if (anId != null && quesid != null) {
            qdao.deleteAnswer(Integer.parseInt(anId));
            request.setAttribute("quesid", quesid);
            request.setAttribute("tag", "question");
            request.getRequestDispatcher("details").forward(request, response);
        }

        if (anId == null && quesid != null) {
            response.getWriter().print(anId + " " + quesid);
            qdao.deleteQuestion(Integer.parseInt(quesid));
            response.sendRedirect("list");
        }
    }

    private void doPostQuestionDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        Part mediaPart = request.getPart("media");
        String mediaName = Paths.get(mediaPart.getSubmittedFileName()).getFileName().toString();
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
        q.setMediaName(mediaName);
        String[] answers = request.getParameterValues("answer");
        ArrayList<Answer> ans = new ArrayList<>();
        for (String answer : answers) {
            ans.add(new Answer(0, answer));
//            response.getWriter().println(answer);
        }
        q.setAnswers(ans);
        String correctAns = request.getParameter("correctAnswer");
        if (correctAns != null) {
            try {
                int index = Integer.parseInt(correctAns);
                q.setCorrectAnswer(answers[index]);
            } catch (Exception e) {
                q.setCorrectAnswer(correctAns);
            }
        } else {
            q.setCorrectAnswer(correctAns);
        }

        response.getWriter().print(correctAns + " " + q.getCorrectAnswer());
        String quizid = request.getParameter("quiz");
        QuestionDAO qdao = new QuestionDAO();
        qdao.updateQuestion(quizid, q, mediaContent);
        request.setAttribute("subcate", qdao.getSubCategoryByCate(q.getCategory().getCategory_id()));
        request.setAttribute("categories", qdao.getCategory());
        QuizDAO qiAO = new QuizDAO();
        q.setMedia(qdao.getQuestionById(q.getId(), account.getId()).getMedia());
        request.setAttribute("subjects", qiAO.getsubs());
        request.setAttribute("quizs", qdao.getQuizForQuestion(account.getId()));
        request.setAttribute("quizId", qdao.getQuizIdOfQuestion(q.getId()));
        request.setAttribute("tag", "done");

        request.setAttribute("question", qdao.getQuestionById(q.getId(), account.getId()));
        request.setAttribute("tag", "question");
        request.getRequestDispatcher("../view/director/question/questiondetails.jsp").forward(request, response);

    }

    private void doGetChangeStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        String id = request.getParameter("id");
        QuestionDAO qdao = new QuestionDAO();
        if (status.equals("publish")) {
            qdao.changeStatusQues(Integer.parseInt(id), "unpublish");
        } else if (status.equals("unpublish")) {
            qdao.changeStatusQues(Integer.parseInt(id), "publish");
        }
        response.sendRedirect("list");
//            response.getWriter().print(id + " " + status);
    }

    private void doGetMedia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        int quesId = Integer.parseInt(request.getParameter("questionid"));
        QuestionDAO qdao = new QuestionDAO();
        Blob blob = qdao.getQuestionById(quesId, account.getId()).getMedia();
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

    private void doPostQuestionImport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filesPart = request.getPart("file"); // Retrieves <input type="file" name="files">
        String fileName = Paths.get(filesPart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filesPart.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(fileContent));
        ArrayList<Question> questions = new ArrayList<>();
        QuizDAO quizDAO = new QuizDAO();
        ArrayList<Category> categories = quizDAO.getCates();
        QuestionDAO questionDAO = new QuestionDAO();
        ArrayList<Subcategory> subcategories = questionDAO.getSubcategorys();
        if (filesPart.getSize() > 0) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine){
                    isFirstLine = false;
                    continue;
                }
                Question question = new Question();
                String[] infors = line.split("(?<=\"),|(?!(.{1,9999}\\\")),");
                if (infors[0].isEmpty()){
                    continue;
                }
                question.setContent(infors[0]);
                for(Category c : categories){
                    if (c.getCategory_value().compareToIgnoreCase(infors[1]) == 0){
                        question.setCategory(c);
                    }
                }
                for (Subcategory sc : subcategories){
                    if (sc.getName().compareToIgnoreCase(infors[1]) == 0){
                        question.setSubCategory(sc);
                    }
                }
                for (int i = 2; i < infors.length; i++){
                    question.getAnswers().add(new Answer(1, infors[i]));
                }
                if (question.getAnswers().size() >= 2){
                    questions.add(question);
                }
            }
            QuestionAnswerDAO qaDAO = new QuestionAnswerDAO();
            for (Question q : questions){
                qaDAO.insertQuestionInfor(q);
            }
        }
    }
}
