<%-- 
    Document   : MonthlyReportForm
    Created on : 18 May 2025, 10:39:34 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String departname = session.getAttribute("department") != null ? (String) session.getAttribute("department") : "";
    String year = session.getAttribute("year") != null ? session.getAttribute("year").toString() : "";
    String month = session.getAttribute("month") != null ? session.getAttribute("month").toString() : "";
    String errorMonth = session.getAttribute("errorMonth") != null ? (String) session.getAttribute("errorMonth") : "";
    String errorYear = session.getAttribute("errorYear") != null ? (String) session.getAttribute("errorYear") : "";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Monthly Attendance</title>
    <style>
        header {
            background-color: #4CAF50;
            height: 4em;
        }
        .error {
            color: red;
            font-size: 0.9em;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <header class="d-flex justify-content-center align-item-center">
        <h1 class="mb-4 text-center text-white">Monthly Attendance</h1>
    </header>
    <div class="container w-50 mt-5 mx-24">
        <form action="MonthlyReportProcessServlet" method="post" class="p-4 border rounded shadow-sm bg-light">
            <div class="mb-3">
                <label for="department" class="form-label">Department Name:</label>
                <input type="text" class="form-control" id="department" name="department" value="<%= departname %>" required>
            </div>
            <div class="mb-3">
                <label for="year" class="form-label">Year:</label>
                <input type="text" class="form-control" id="year" name="year" value="<%= year %>" required>
                <% if (!errorYear.isEmpty() && errorYear.contains("Year")) { %>
                    <div class="error"><%= errorYear %></div>
                <% } %>
            </div>
            <div class="mb-3">
                <label for="month" class="form-label">Month:</label>
                <input type="text" class="form-control" id="month" name="month" value="<%= month %>" required>
                <% if (!errorMonth.isEmpty() && errorMonth.contains("Month")) { %>
                    <div class="error"><%= errorMonth %></div>
                <% } %>
            </div>
            <div class="d-flex justify-content-between">
                <input type="submit" class="btn btn-primary" value="Submit">
                <input type="reset" class="btn btn-secondary" value="Clear">
            </div>
        </form>
    </div>
</body>
</html>
