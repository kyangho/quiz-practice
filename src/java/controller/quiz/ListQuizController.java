/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.quiz;

import dal.QuizDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Quiz;
import model.Subject;

/**
 *
 * @author Yankee
 */
public class ListQuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDAO q = new QuizDAO();
        int pagesize = 5;
        String raw_page = request.getParameter("page");
        String subject = request.getParameter("subject");
        String category = request.getParameter("category");
        String type = request.getParameter("type");
        String search_quiz_title = request.getParameter("search_quiz_title");
        int pageindex;
        if (raw_page == null || raw_page.length() == 0) {
            pageindex = 1;
        } else {
            pageindex = Integer.parseInt(raw_page);
        }
        ArrayList<Quiz> quiz = null;
        int totalrows;
        String url;
        if (search_quiz_title == null) {
            quiz = new ArrayList<>();
            if (subject == null && category == null && type == null) {
                quiz = q.getQuiz(pageindex, pagesize, "all", "all", "all", null);
                totalrows = q.getRowcount("all", "all", "all", null);
                url = "listquiz?page=";
            } else {
                quiz = q.getQuiz(pageindex, pagesize, subject, category, type, null);
                totalrows = q.getRowcount(subject, category, type, null);
                url = "listquiz?subject=" + subject + "&category=" + category + "&type=" + type + "&page=";
                request.setAttribute("subject", subject);
                request.setAttribute("category", category);
                request.setAttribute("search_quiz_title", search_quiz_title);
            }
        } else {
            quiz = new ArrayList<>();
            quiz = q.getQuiz(pageindex, pagesize, null, null, null, search_quiz_title);
            request.setAttribute("search_quiz_title", search_quiz_title);
            url = "listquiz?title=" + search_quiz_title + "&page=";
            totalrows = q.getRowcount(null, null, null, search_quiz_title);
        }
        request.setAttribute("type", type);
        request.setAttribute("category", category);
        request.setAttribute("subject", subject);
        int totalpage = (totalrows % pagesize == 0) ? totalrows / pagesize : totalrows / pagesize + 1;
        ArrayList<Subject> subs = q.getsubs();
        ArrayList<Category> cates = q.getCates();
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("quiz", quiz);
        request.setAttribute("tag", "listquiz");
        request.setAttribute("url", url);
        request.setAttribute("cates", cates);
        request.setAttribute("subs", subs);
        request.getRequestDispatcher("../view/director/quiz/quizlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDAO q = new QuizDAO();
        int pagesize = 5;
        String raw_page = request.getParameter("page");
        String subject = request.getParameter("subject");
        String category = request.getParameter("category");
        String type = request.getParameter("type");
        String search_quiz_title = request.getParameter("search_quiz_title");
        int pageindex;
        if (raw_page == null || raw_page.length() == 0) {
            pageindex = 1;
        } else {
            pageindex = Integer.parseInt(raw_page);
        }
        ArrayList<Quiz> quiz = null;
        int totalrows;
        String url;
        if (search_quiz_title == null) {
            quiz = new ArrayList<>();
            quiz = q.getQuiz(pageindex, pagesize, subject, category, type, null);
            totalrows = q.getRowcount(subject, category, type, null);
            url = "listquiz?subject=" + subject + "&category=" + category + "&type=" + type + "&page=";
        } else {
            quiz = new ArrayList<>();
            quiz = q.getQuiz(pageindex, pagesize, null, null, null, search_quiz_title);
            totalrows = q.getRowcount(null, null, null, search_quiz_title);
            request.setAttribute("search_quiz_title", search_quiz_title);
            url = "listquiz?search_quiz_title=" + search_quiz_title + "&page=";
        }
        int totalpage = (totalrows % pagesize == 0) ? totalrows / pagesize : totalrows / pagesize + 1;
        ArrayList<Subject> subs = q.getsubs();
        ArrayList<Category> cates = q.getCates();
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("type", type);
        request.setAttribute("category", category);
        request.setAttribute("subject", subject);
        request.setAttribute("quiz", quiz);
        request.setAttribute("tag", "listquiz");
        request.setAttribute("url", url);
        request.setAttribute("cates", cates);
        request.setAttribute("subs", subs);
        request.getRequestDispatcher("../view/director/quiz/quizlist.jsp").forward(request, response);
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
