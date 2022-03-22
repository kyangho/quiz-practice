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
public class EditQuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        HomeDAO h = new HomeDAO();
        ArrayList<Account> acc = h.getAccounts();
        request.setAttribute("account", acc);
        QuizDAO q = new QuizDAO();
        ArrayList<Category> cates = q.getCates();
        ArrayList<Subject> subs = q.getsubs();
        Quiz quiz = q.getQuizDetail(id);
        request.setAttribute("subs", subs);
        request.setAttribute("category", cates);
        request.setAttribute("quiz", quiz);
        request.getRequestDispatcher("../view/quiz/updatequiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Quiz q = new Quiz();
        q.setId(Integer.parseInt(request.getParameter("id").trim()));
        q.setTitle(request.getParameter("quiz_title"));

        Subject s = new Subject();
        s.setSubject_id(Integer.parseInt(request.getParameter("subject")));
        q.setSubject(s);

        Category c = new Category();
        c.setCategory_id(Integer.parseInt(request.getParameter("category")));
        q.setCategory(c);

        q.setLevel(request.getParameter("level"));
        q.setType(request.getParameter("type"));

        String[] ques = request.getParameterValues("question");
        if (ques != null) {
            for (String que : ques) {
                Question question = new Question();
                question.setContent(que);
                q.getQuestions().add(question);
            }
        }
        QuizDAO qdao = new QuizDAO();
        qdao.editQuiz(q);
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
