<%-- 
    Document   : overtimeRecord
    Created on : 9 May 2025, 10:06:57 pm
    Author     : HP
--%>

<%@page import="java.util.*"%>
<%@page import="model.overtime"%>
<%@page import="dao.OvertimeDAO"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.LocalTime"%>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    </head>
    <body>

        <header class="container-fluid py-3 shadow-sm">
            <div class=" d-flex justify-content-center align-items-center">
                <h2 class="m-0 fw-semibold text-white">Overtime Records</h2>
            </div>
        </header>

        <%

            String messageSuccess = (String) session.getAttribute("messageSuccess");
            String messageFailure = (String) session.getAttribute("errorMsg");
            if (messageSuccess != null) {
        %>

        <div class="container-fluid px-2 pt-3">
            <div class="bg-success text-white p-3 rounded">
                <p class="m-0"><%= messageSuccess%></p>
            </div>
        </div>
        <%
        } else if (messageFailure != null) {
        %>
        <div class="container-fluid px-2 pt-3">
            <div class="bg-danger text-white p-3 rounded">
                <p class="m-0"><%= messageFailure%></p>
            </div>
        </div>
        <%
            }
            session.removeAttribute("messageSuccess");
            session.removeAttribute("errorMsg");
        %>
        <main>
            <div class="container-fluid mt-4">
                <table class="table table-bordered table-hover table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th>Number</th>
                            <th>User ID</th>
                            <th>Date</th>
                            <th>Begin</th>
                            <th>End</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <%
                        String id = (String) session.getAttribute("userID");
                        List<overtime> list = OvertimeDAO.getOvertimeRequestUserById(id);

                        LocalDate today = LocalDate.now();
                        request.removeAttribute("username");
                        int i = 1;
                        for (overtime e : list) {
                            if (LocalDate.parse(e.getDateOvertime()).equals(today) || LocalDate.parse(e.getDateOvertime()).isAfter(today)) {
                    %>

                    <tr>
                        <td><%= i++%></td>
                        <td><%= e.getUserID()%>
                        <td><%= e.getDateOvertime()%></td>
                        <td><%= e.getStartTime()%></td>
                        <td><%= e.getEndTime()%></td>
                        <td><%= e.getVerification()%></td>
                        <td><%= e.getDateOvertime().equals(today) ? "" : "<a href='EditOvertimeServlet?overtimeId=" + e.getOvertimeID() + "' class='btn btn-sm btn-primary' >Edit</a>"%>
                            <%= e.getDateOvertime().equals(today) ? "" : "<a href='DeleteOvertimeRequest?overtimeId=" + e.getOvertimeID() + "'class='btn btn-sm btn-danger'>Delete</a>"%>
                        </td>
                    </tr>
                    <%  }
                        }%>
                </table>
            </div>
            <div class="container mt-3">
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-5">
                        <form action="viewOvertimeServlet" method="get">
                            <button type="back" name="back" value="back" class="btn btn-outline-dark w-100 d-flex align-items-center justify-content-center">
                                <i class="bi bi-arrow-left-circle me-2"></i> Back
                            </button>
                        </form>
                    </div>
                </div>
            </div>  
        </main>
    </body>
</html>
