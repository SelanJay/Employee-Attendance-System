/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dao.OvertimeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.OvertimeDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import javax.servlet.http.HttpSession;
import model.overtime;

/**
 *
 * @author HP
 */
public class AuthorisedViewOvertimeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthorisedViewOvertimeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthorisedViewOvertimeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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

        String verification = request.getParameter("verification") != null ? request.getParameter("verification") : (String) request.getAttribute("verification");
        String overtimeID = request.getParameter("overtimeId") != null ? request.getParameter("overtimeId") : (String) request.getAttribute("overtimeId");

        overtime list = OvertimeDAO.getOvertimeRequestById(overtimeID);

        overtime ot = new overtime();
        ot.setOvertimeID(list.getOvertimeID());
        ot.setUserID(list.getUserID());
        ot.setStartTime(LocalTime.parse(list.getStartTime()));
        ot.setEndTime(LocalTime.parse(list.getEndTime()));
        ot.setDateOvertime(LocalDate.parse(list.getDateOvertime()));
        ot.setVerification(verification);

        int status = OvertimeDAO.updateOvertimeRequest(ot);
        if (status > 0) {
            request.getRequestDispatcher("AuthorisedOvertimeForm.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        
        String action = request.getParameter("submit");
        session.setAttribute("Table", action);
        
        if (action.equals("back")) {
            // Clear session attributes or go back to form
            
            session.removeAttribute("keyword");  // or session.invalidate() if needed

            response.sendRedirect("AuthorisedOvertimeForm.jsp");
        } else {
            // Default action: handle department search
            String department = request.getParameter("department");
            session.setAttribute("department", department);

            response.sendRedirect("AuthorisedOvertimeForm.jsp");
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

}
