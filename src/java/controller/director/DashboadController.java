/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import dal.DashboardDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.QuestionViewModel;
import model.QuizViewModel;
import model.SubjectViewModel;

/**
 *
 * @author Cao Tam Kien
 */
@WebServlet(name = "DashboadController", urlPatterns = {"/director/dashboard","/filter"})
public class DashboadController extends HttpServlet {
    DashboardDAO _dashboardDAO;
    @Override
    public void init() throws ServletException {
       _dashboardDAO = new DashboardDAO();
    }
    
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int totalSubject = _dashboardDAO.getTotalSubject();
        int totalQuiz = _dashboardDAO.getTotalQuiz();
        int totalTest = _dashboardDAO.getTotalTest();
        int totalQuestion = _dashboardDAO.getTotalQuestion();
        
        ArrayList<QuizViewModel> listQuiz = _dashboardDAO.getListQuizViewModels();
        ArrayList<QuestionViewModel> listQuestion = _dashboardDAO.getListQuestion();
        ArrayList<SubjectViewModel> listSubject = _dashboardDAO.getListSubject();
        if (listQuiz!=null) {
            request.setAttribute("listQuiz",listQuiz);
        }
        if (listQuestion!=null) {
            request.setAttribute("listQuestion",listQuestion);
        }
        if (listSubject!=null) {
            request.setAttribute("listSubject",listSubject);
        }
        
        request.setAttribute("totalSubject", totalSubject);
        request.setAttribute("totalQuiz", totalQuiz);
        request.setAttribute("totalTest", totalTest);
        request.setAttribute("totalQuestion", totalQuestion);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../view/director/dashboard.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        int totalSubject = _dashboardDAO.getTotalSubject();
        int totalQuiz = _dashboardDAO.getTotalQuiz();
        int totalTest = _dashboardDAO.getTotalTest();
        int totalQuestion = _dashboardDAO.getTotalQuestion();
        String param = request.getParameter("filter");
       
        ArrayList<QuestionViewModel> listQuestion = _dashboardDAO.getListQuestion();
        ArrayList<SubjectViewModel> listSubject = _dashboardDAO.getListSubject();
        ArrayList<QuizViewModel> listQuiz = null;
        if (param.equals("1")) {
            listQuiz = _dashboardDAO.getListQuizViewModels();
        }else{
            listQuiz = _dashboardDAO.getListQuizViewModelsByFilter();
        }
        if (listQuiz!=null) {
            request.setAttribute("listQuiz",listQuiz);
        }
        if (listQuestion!=null) {
            request.setAttribute("listQuestion",listQuestion);
        }
        if (listSubject!=null) {
            request.setAttribute("listSubject",listSubject);
        }
        
        request.setAttribute("totalSubject", totalSubject);
        request.setAttribute("totalQuiz", totalQuiz);
        request.setAttribute("totalTest", totalTest);
        request.setAttribute("totalQuestion", totalQuestion);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../view/director/dashboard.jsp");
        dispatcher.forward(request, response);
        
    }
    
}
