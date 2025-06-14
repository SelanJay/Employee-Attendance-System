/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dao.OvertimeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.overtime;

/**
 *
 * @author HP
 */
public class DeleteOvertimeServlet extends HttpServlet {

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
            out.println("<title>Servlet deleteOvertimeRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteOvertimeRequest at " + request.getContextPath() + "</h1>");
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

        List<overtime> list = new ArrayList<>();

        String overtimeID = request.getAttribute("overtimeId") != null ? (String) request.getAttribute("overtimeId") : request.getParameter("overtimeId");

        // Fetch the record by ID (possibly to get the userID for redirect after deletion)
        overtime idRecord = OvertimeDAO.getOvertimeRequestById(overtimeID);

        if (idRecord == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Overtime record not found.");
            return;
        }

        // Perform delete operation
        int complete = OvertimeDAO.deleteOvertimeRequestByID(overtimeID);
        
        HttpSession session = request.getSession();
        // If delete was successful, forward to the overtimeRecord.jsp with userID
        if (complete > 0) {
            request.setAttribute("userID", idRecord.getUserID());
            session.setAttribute("messageSuccess", "Overtime request deleted successfully");
            request.getRequestDispatcher("overtimeRecord.jsp").forward(request, response);
        } else {
            session.setAttribute("messageSuccess", "Overtime request deleted unsuccessfully");
            request.getRequestDispatcher("overtimeRecord.jsp").forward(request, response);
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
        processRequest(request, response);
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
