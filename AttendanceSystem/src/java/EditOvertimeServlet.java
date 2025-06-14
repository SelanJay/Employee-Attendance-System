/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.overtime;
import dao.OvertimeDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class EditOvertimeServlet extends HttpServlet {

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
            out.println("<title>Servlet editServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editServlet at " + request.getContextPath() + "</h1>");
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

        String id = request.getParameter("overtimeId");

        overtime idRecord = OvertimeDAO.getOvertimeRequestById(id);
        if (idRecord != null) {
            int userID = idRecord.getUserID();
            LocalTime startTime = LocalTime.parse(idRecord.getStartTime());
            LocalTime endTime = LocalTime.parse(idRecord.getEndTime());
            LocalDate dateOvertime = LocalDate.parse(idRecord.getDateOvertime());
            String verification = idRecord.getVerification();
              
            request.setAttribute("userID", userID);
            request.setAttribute("startOvertime", startTime.toString());
            request.setAttribute("endOvertime", endTime.toString());
            request.setAttribute("dateOvertime", dateOvertime.toString());
            request.setAttribute("verification", verification);

            request.getRequestDispatcher("editOvertimeForm.jsp").forward(request, response);
        } else {
            // Handle not found case
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Overtime record not found.");
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
        
        String userIDStr = request.getParameter("userID");
        int userID = Integer.parseInt(userIDStr);
        LocalTime startTime = LocalTime.parse(request.getParameter("startOvertime"));
        LocalTime endTime = LocalTime.parse(request.getParameter("endOvertime"));
        LocalDate dateOvertime = LocalDate.parse(request.getParameter("dateOvertime"));
        

        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        if(!OvertimeDAO.isUserExist(userID)){
            request.setAttribute("errorMsgUserID", "Invalid UserID");
            request.setAttribute("userID", userIDStr);
            request.setAttribute("startOvertime", startTime.toString());
            request.setAttribute("endOvertime", endTime.toString());
            request.setAttribute("dateOvertime", dateOvertime.toString());
            request.getRequestDispatcher("editOvertimeForm.jsp").forward(request, response);
            return;
        }
        
        // Check if startTime is after endTime (which is invalid)
        if (startTime.isAfter(endTime)) {
            request.setAttribute("errorMsgTimeEnd", "Start Time cannot be after End Time.");
            request.setAttribute("userID", userIDStr);
            request.setAttribute("startOvertime", startTime.toString());
            request.setAttribute("endOvertime", endTime.toString());
            request.setAttribute("dateOvertime", dateOvertime.toString());
            request.getRequestDispatcher("editOvertimeForm.jsp").forward(request, response);
            return;
        }

        if (dateOvertime.isEqual(date)) {
            if (startTime.isBefore(time)) {
                request.setAttribute("errorMsgStartTime", "Start Time cannot be after local Time.");
                request.setAttribute("userID", userIDStr);
                request.setAttribute("startOvertime", startTime.toString());
                request.setAttribute("endOvertime", endTime.toString());
                request.setAttribute("dateOvertime", dateOvertime.toString());
                request.getRequestDispatcher("editOvertimeForm.jsp").forward(request, response);
                return;
            }
        }

        if (dateOvertime.isBefore(date)) {
            request.setAttribute("errorMsgDate", "Please enter the correct date");
            request.setAttribute("userID", userIDStr);
            request.setAttribute("startOvertime", startTime.toString());
            request.setAttribute("endOvertime", endTime.toString());
            request.setAttribute("dateOvertime", dateOvertime.toString());
            request.getRequestDispatcher("editOvertimeForm.jsp").forward(request, response);
            return; 
        }

        // Valid case: continue saving the data
        overtime ot = new overtime();
        ot.setOvertimeID(request.getParameter("overtimeID"));
        ot.setUserID(userID);
        ot.setStartTime(startTime);
        ot.setEndTime(endTime);
        ot.setDateOvertime(dateOvertime);
        ot.setVerification(request.getParameter("verification"));
        
        
        int status = OvertimeDAO.updateOvertimeRequest(ot);
        HttpSession session = request.getSession();
        if (status > 0) {
            session.setAttribute("messageSuccess", "Edited overtime request saved successfully!");
            request.setAttribute("userID", userIDStr);
            request.getRequestDispatcher("overtimeRecord.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMsg", "Failed to save overtime request.");
            request.getRequestDispatcher("editOvertimeForm.jsp").forward(request, response);
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
