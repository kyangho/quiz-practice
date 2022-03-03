/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.quiz;

import dal.HomeDAO;
import dal.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Category;
import model.Question;
import model.Quiz;
import model.Subject;

/**
 *
 * @author Yankee
 */
public class NewQuiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HomeDAO h = new HomeDAO();
        ArrayList<Account> acc = h.getAccounts();
        request.setAttribute("account", acc);
        QuizDAO q = new QuizDAO();
        ArrayList<Category> cates = q.getCates();
        ArrayList<Subject> subs = q.getsubs();
        request.setAttribute("subs", subs);
        request.setAttribute("category", cates);
        request.getRequestDispatcher("../view/quiz/newquiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Quiz quiz = new Quiz();
        quiz.setTitle(request.getParameter("quiz_title"));
        
        Subject sub = new Subject();
        sub.setSubject_id(Integer.parseInt(request.getParameter("subject")));
        quiz.setSubject(sub);

        Category cate = new Category();
        cate.setCategory_id(Integer.parseInt(request.getParameter("category")));
        quiz.setCategory(cate);

        quiz.setLevel(request.getParameter("level"));
        quiz.setType(request.getParameter("type"));
        Account acc = new Account();
        acc.setId(Integer.parseInt(request.getParameter("author")));
        quiz.setAuthor(acc);
        

        String[] question = request.getParameterValues("question");
        if (question != null) {
            for (String ques : question) {
                Question qu = new Question();
                qu.setContent(ques);
                quiz.getQuestions().add(qu);
            }
        }

        QuizDAO q = new QuizDAO();
        q.insertQuiz(quiz);
        response.sendRedirect("listquiz");

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
