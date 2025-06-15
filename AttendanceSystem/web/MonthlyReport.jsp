<%-- 
    Document   : MonthReport
    Created on : 18 May 2025, 10:55:17 pm
    Author     : HP
--%>

<%@page import="java.util.*"%>
<%@page import="model.UserAttendance"%>
<%@page import="dao.OvertimeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            header {
                background-color: #4CAF50;
                height: 5em;
            }
        </style>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <header class="container-fluid py-3 shadow-sm">
            <div class=" d-flex justify-content-center align-items-center">
                <h2 class="m-0 fw-semibold text-white">Monthly Report</h2>
            </div>
        </header>
        <div class="container-fluid mt-5">
            <table class="table table-bordered table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <td> Employee ID</td>
                        <td> Employee Name</td>
                        <td> Role</td>
                        <td> Department </td>
                        <td> Year-Month</td>
                        <td> Attendance Day</td>
                        <td> Attendance Percentage</td>
                        <td> Action</td>
                    </tr>
                </thead>
                <%
                    String department = (String) request.getParameter("department");
                    int year = Integer.parseInt(request.getParameter("year"));
                    int month = Integer.parseInt(request.getParameter("month"));
                    List<UserAttendance> list = OvertimeDAO.getMonthlyAttendanceReport(department, year, month);

                    for (UserAttendance e : list) {
                        String warning = e.getAttendancePercentage() < 60 ? "table-danger " : "";
                %>

                <tr class="<%= warning%>">
                <form action="attendanceReportGenerator" method="post">
                    <input type="hidden" name="userId" value="<%= e.getUserId()%>">
                    <input type="hidden" name="userName" value="<%= e.getFullName()%>">
                    <input type="hidden" name="role" value="<%= e.getRole()%>">
                    <input type="hidden" name="department" value="<%= e.getDepartment()%>">
                    <input type="hidden" name="month" value="<%= e.getMonth()%>">
                    <input type="hidden" name="attendedDays" value="<%= e.getAttendedDays()%>">
                    <input type="hidden" name="attendancePercentage" value="<%= e.getAttendancePercentage()%>">

                    <td><%= e.getUserId()%></td>
                    <td><%= e.getFullName()%></td>
                    <td><%= e.getRole()%></td>
                    <td><%= e.getDepartment()%></td>
                    <td><%= e.getMonth()%></td>
                    <td><%= e.getAttendedDays()%></td>
                    <td><%= e.getAttendancePercentage()%>%</td>
                    <% if (!warning.equals("")) { %>
                    <td><button type="submit" class="btn btn-primary">Generate Report</button></td>
                    <% } %>
                </form>
                </tr>
                <% }%>

            </table>
        </div>
    </body>
</html>
