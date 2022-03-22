/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author ducky
 */
public class ExtendUtilsServlet extends HttpServlet {

//    public void doGetFileRetrieve(HttpServletRequest request, HttpServletResponse response, int fileId)
//            throws ServletException, IOException {
//        String id = request.getParameter(fileId + "");
//        int idImage = Integer.parseInt(id);
//        Blob blob;
//        byte[] buffer;
//        try {
//            response.reset();
//            buffer = blob.getBytes(1, (int) blob.length());
//            OutputStream os = response.getOutputStream();
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-Disposition",
//                    "attachment;filename=" + "SQL.sql");
//            ServletOutputStream out = response.getOutputStream();
//            out.write(buffer, 0, (int) blob.length());
//            os.flush();
//            os.close();
//        } catch (SQLException ex) {
//        }
//    }

//    protected void doPostUploadFile(HttpServletRequest request, HttpServletResponse response, String partName)
//            throws ServletException, IOException {
//        Part part = request.getPart(partName);
//        log(part.getContentType());
//        InputStream is = part.getInputStream();
//        ImageDAO imgDAO = new ImageDAO();
//        imgDAO.insertBlob(is);
//    }
}
