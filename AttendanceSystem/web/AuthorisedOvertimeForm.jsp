<%-- 
    Document   : authorisedOvertimeView
    Created on : 12 May 2025, 8:49:35 pm
    Author     : HP
--%>

<%@page import="java.util.*"%>
<%@page import="model.overtime"%>
<%@page import="dao.OvertimeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            /*            body {
                            font-family: 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif;
                        }*/

            .form,
            .form label {
                margin-top: 2%;
            }

            header {
                background-color: #4CAF50;
                height: 6em;
            }


            .bg-darkgrey {
                background-color: #2b2b2b;
                color: white;
            }
        </style>
    </head>
    <body>
        <header class="container-fluid ">
            <h1 class="display-5 text-center fw-bold text-white">Search Overtime Records</h1>
        </header>

        <form class="my-5 ms-2" action="AuthorisedViewOvertimeServlet" method="post">

            <label for="employeeSearchKey">
                Please enter the department code : 
            </label> 

            <input class="form-control d-inline-block w-auto mx-2 ms-2" type="text" name="department"  required>
            <button type="submit" name="submit" value="search" class="btn btn-primary btn-sm">Submit</button>
            <button type="reset" class="btn btn-secondary btn-sm ms-2">Clear</button>
        </form>
        <%
            if ("search".equals(session.getAttribute("Table"))) {
        %>

        <div class="container-fluid mt-4">
            <table class="table table-bordered table-hover table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>Number</th>
                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Department</th>
                        <th>Begin</th>
                        <th>End</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <%
                    String department = (String) session.getAttribute("department");
                    int i = 1;
                    List<overtime> list = OvertimeDAO.getOvertimeByDepartment(department);
                    for (overtime e : list) {
                %>
                <tbody>
                    <tr>
                        <td><%= i++%></td>
                        <td><%= e.getUserID()%>
                        <td><%= e.getFullUserName()%></td>   
                        <td><%= e.getDepartmentName()%></td>
                        <td><%= e.getStartTime()%></td>
                        <td><%= e.getEndTime()%></td>
                        <td><%= e.getDateOvertime()%></td>
                        <td><%= e.getVerification()%></td>
                        <td>
                            <a href='AuthorisedViewOvertimeServlet?overtimeId=<%= e.getOvertimeID()%>&verification=Approve' class="btn btn-outline-success "> Approve </a> 
                            <a href='AuthorisedViewOvertimeServlet?overtimeId=<%= e.getOvertimeID()%>&verification=Reject' class="btn btn-outline-danger ms-1"> Reject </a>
                        </td>
                    </tr>
                </tbody>
                <%  }%>

            </table>
            <div class="container mt-3">
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-5 mb-2">
                        <form action="AuthorisedViewOvertimeServlet" method="post">
                            <button type="submit" name="submit" class="btn btn-outline-dark w-100 d-flex align-items-center justify-content-center">
                                <i class="bi bi-arrow-left-circle me-2"></i> Back
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <%}%>
    </body>
</html>
