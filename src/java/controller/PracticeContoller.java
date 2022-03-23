/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.QuestionDAO;
import dal.QuizDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Category;
import model.Question;
import model.Quiz;
import model.Quiz_Account;
import model.Subcategory;
import model.Subject;

/**
 *
 * @author conmu
 */
@WebServlet(name = "PracticeContoller", urlPatterns = {"/practice/*"})
public class PracticeContoller extends HttpServlet {

    private final String practiceListPath = "/practice/list";
    private final String practiceDetailPath = "/practice/details";
    private final String addPracticePath = "/practice/add";
    private final int pageSize = 4;

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
        if (URI.contains(practiceListPath)) {
            doGetPracticeList(request, response);
        } else if (URI.contains(practiceDetailPath)) {
            doGetPracticeDetail(request, response);
        } else {
            doGetAddPractice(request, response);
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
        if (URI.contains(practiceListPath)) {
            doGetPracticeList(request, response);
        } else if (URI.contains(practiceDetailPath)) {
            doGetPracticeDetail(request, response);
        } else {
            doPostAddPractice(request, response);
        }
    }

    private void doGetPracticeList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageindex_raw = request.getParameter("pageindex");
        int pageindex;
        if (pageindex_raw == null || pageindex_raw.length() == 0) {
            pageindex = 1;
        } else {
            pageindex = Integer.parseInt(pageindex_raw);
        }
        Account account = (Account) request.getSession().getAttribute("account");
        QuizDAO qdb = new QuizDAO();
        ArrayList<Quiz_Account> quizzesPractice = qdb.getQuizzesPractice(account.getId(), pageindex, pageSize);
        int totalRows = qdb.totalRowsForQuizPractice(account.getId());
        int totalPage = (totalRows % pageSize == 0) ? totalRows / pageSize : totalRows / pageSize + 1;
        request.setAttribute("practices", quizzesPractice);
        String url = "list?pageindex=";
        request.setAttribute("url", url);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("tag", "list");
        request.getRequestDispatcher("../view/quiz/practicelist.jsp").forward(request, response);
    }

    private void doGetPracticeDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        String quizID = request.getParameter("quizID");
        QuizDAO qdb = new QuizDAO();
        ArrayList<Quiz_Account> quizzesPractice = qdb.getQuizzesPractice(account.getId(), 0, 0);
        request.setAttribute("practices", quizzesPractice);
        Quiz_Account practice = qdb.getPracticeByQuizID(Integer.parseInt(quizID), account.getId());
        request.setAttribute("practice", practice);
        request.getRequestDispatcher("../view/quiz/practicedetails.jsp").forward(request, response);
    }

    private void doGetAddPractice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cate = request.getParameter("category");
        String subject = request.getParameter("subject");
        String subcate = request.getParameter("subcate");
        String noQues = request.getParameter("noQues");
        request.setAttribute("noQues", noQues);
        request.setAttribute("cateID", cate);
        if (noQues == null || noQues.length() == 0) {
            noQues = new String("10");
        }
        QuizDAO qdbt = new QuizDAO();
        QuestionDAO qdao = new QuestionDAO();
        ArrayList<Subject> subs = qdbt.getsubs();
        request.setAttribute("subject", subs);
        ArrayList<Category> category = qdao.getCategory();
        request.setAttribute("categories", category);
        if (cate != null && cate.equals("all")) {
            cate = Integer.toString(category.get(0).getCategory_id());
        }
        ArrayList<Subcategory> sub;
        if (subcate == null) {
//            qdao = new QuestionDAO();
            sub = qdao.getSubCategoryByCate(category.get(0).getCategory_id());
            request.setAttribute("subcate", sub);
        } else {
//            qdao = new QuestionDAO();
            sub = qdao.getSubCategoryByCate(Integer.parseInt(cate));
            request.setAttribute("subcate", sub);
//            qdao = new QuestionDAO();
            ArrayList<Question> questions = qdao.getQuestions(1, 1, Integer.parseInt(noQues), null, subject, subcate, null, "publish");
            request.setAttribute("questions", questions);
            request.setAttribute("subjectID", subject);
            request.setAttribute("subcateId", subcate);
        }
        request.getRequestDispatcher("../view/quiz/addpratice.jsp").forward(request, response);
    }

    private void doPostAddPractice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cate = request.getParameter("cateid");
        String subject = request.getParameter("subjectid");
        String[] questions = request.getParameterValues("questionId");

        Quiz q = new Quiz();
        if (!cate.equals("all")) {
            q.setCategory(new Category(Integer.parseInt(cate), null));
        } else {
            q.setCategory(new Category(0, null));
        }

        if (!subject.equals("all")) {
            q.setSubject(new Subject(Integer.parseInt(subject), null));
        } else {
            q.setSubject(new Subject(0, null));
        }
        ArrayList<Question> ques = new ArrayList<>();
        for (String question : questions) {
            ques.add(new Question(Integer.parseInt(question), null, null));
        }

        String name = LocalDate.now().toString();
        q.setName("Practice at " + name);
        q.setType("publish");
        q.setTitle("Quiz");
        q.setQuestions(ques);
        Account account = (Account) request.getSession().getAttribute("account");
        q.setAuthor(account);
        QuizDAO quizDAO = new QuizDAO();
        int id = quizDAO.insertPractice(q);
        if (id != -1) {
            Quiz doQuiz = quizDAO.getQuizDetail(id);
            request.getSession().setAttribute("quiz", doQuiz);
//            request.getRequestDispatcher("quiz/join?quizId=" + id).forward(request, response);
            response.sendRedirect("../quiz/join/game");
        } else {
            response.getWriter().print("Error");
        }
    }
}
