/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.homepage;

import dal.HomeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Tebellum
 */
public class Homepage extends HttpServlet {

       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
              HomeDBContext hdbc = new HomeDBContext();
              ArrayList<Account> accounts = hdbc.getAccounts();

              request.setAttribute("information", accounts);
              request.getRequestDispatcher("view/home/homepage.jsp").forward(request, response);
       }

       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {

//              request.setAttribute("information", this);
//              request.getRequestDispatcher("view/home/homepage.jsp").forward(request, response);
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
