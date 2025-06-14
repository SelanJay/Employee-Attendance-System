/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dao.OvertimeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.overtime;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class viewOvertimeServlet extends HttpServlet {

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
        processRequest(request, response);

        if (request.getParameter("back") != null) {
            request.getRequestDispatcher("overtimeForm.jsp").forward(request, response);
        }

        request.getRequestDispatcher("overtimeRecord.jsp").forward(request, response);

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

        String userIDStr = request.getParameter("userID");
        int userID = Integer.parseInt(userIDStr);
        LocalTime startTime = LocalTime.parse(request.getParameter("startOvertime"));
        LocalTime endTime = LocalTime.parse(request.getParameter("endOvertime"));
        LocalDate dateOvertime = LocalDate.parse(request.getParameter("dateOvertime"));

        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        String formRetuenType = request.getParameter("btnSubmit");
        if (!OvertimeDAO.isUserExist(userID)) {
            request.setAttribute("errorMsgUserID", "Invalid UserID");
            request.setAttribute("userID", userIDStr);
            request.setAttribute("startOvertime", startTime.toString());
            request.setAttribute("endOvertime", endTime.toString());
            request.setAttribute("dateOvertime", dateOvertime.toString());
            if ("submitOvertimeReport".equals(formRetuenType)) {
                request.getRequestDispatcher("overtimeForm.jsp").forward(request, response);
                return;
            } else {
                request.getRequestDispatcher("editovertimeForm.jsp").forward(request, response);
                return;
            }

        }

        // Check if startTime is after endTime (which is invalid)
        if (startTime.isAfter(endTime)) {
            request.setAttribute("errorMsgTimeEnd", "Start Time cannot be after End Time.");
            request.setAttribute("userID", userIDStr);
            request.setAttribute("startOvertime", startTime.toString());
            request.setAttribute("endOvertime", endTime.toString());
            request.setAttribute("dateOvertime", dateOvertime.toString());
            if ("submitOvertimeReport".equals(formRetuenType)) {
                request.getRequestDispatcher("overtimeForm.jsp").forward(request, response);
                return;
            } else {
                request.getRequestDispatcher("editovertimeForm.jsp").forward(request, response);
                return;
            }
        }

        if (dateOvertime.isEqual(date)) {
            if (startTime.isBefore(time)) {
                request.setAttribute("errorMsgStartTime", "Start Time cannot be after local Time.");
                request.setAttribute("userID", userIDStr);
                request.setAttribute("startOvertime", startTime.toString());
                request.setAttribute("endOvertime", endTime.toString());
                request.setAttribute("dateOvertime", dateOvertime.toString());
                if ("submitOvertimeReport".equals(formRetuenType)) {
                    request.getRequestDispatcher("overtimeForm.jsp").forward(request, response);
                    return;
                } else {
                    request.getRequestDispatcher("editovertimeForm.jsp").forward(request, response);
                    return;
                }
            }
        }

        if (dateOvertime.isBefore(date)) {
            request.setAttribute("errorMsgDate", "Please check and enter a valid date");
            request.setAttribute("userID", userIDStr);
            request.setAttribute("startOvertime", startTime.toString());
            request.setAttribute("endOvertime", endTime.toString());
            request.setAttribute("dateOvertime", dateOvertime.toString());
            if( "submitOvertimeReport".equals(formRetuenType) ) {
                request.getRequestDispatcher("overtimeForm.jsp").forward(request, response);
                return;
            } else {
                request.getRequestDispatcher("editovertimeForm.jsp").forward(request, response);
                return;
            }
        }

        // Valid case: continue saving the data
        overtime ot = new overtime();
        ot.setUserID(Integer.parseInt(userIDStr));
        ot.setStartTime(startTime);
        ot.setEndTime(endTime);
        ot.setDateOvertime(dateOvertime);
        ot.setVerification("In process");

        HttpSession session = request.getSession();
        int status = OvertimeDAO.requestOvertime(ot);
        if (status > 0) {
            session.setAttribute("messageSuccess", "Overtime request received successfully!");
            session.setAttribute("userID", userIDStr);
            request.getRequestDispatcher("overtimeRecord.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMsg", "Failed to save overtime request.");
            request.getRequestDispatcher("overtimeForm.jsp").forward(request, response);
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
