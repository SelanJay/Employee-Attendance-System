<%-- 
    Document   : overtimeForm
    Created on : 9 May 2025, 5:29:52 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Form Page</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>

            .formColor {
                background-color: rgba(211, 211, 211, 0.4);
            }

            header {
                background-color: #4CAF50;
                height: 5em;
            }
            .nav-link:hover {
                background-color: darkgreen;
                text-decoration: none;
                border-radius: 10px;
            }
        </style>
    </head>
    <body>
        <%
            session.setAttribute("userID", "2");

            String errormsg = (String) session.getAttribute("errormsg");
            if (errormsg != null) {
        %>
        <div class="container-fluid px-3 pt-3">
            <div class="bg-success text-white p-2 rounded">
                <p class="m-0"><%= errormsg%></p>
            </div>
        </div>
        <%}
            session.removeAttribute("messageSuccess");
        %>
        <header class="container-fluid ">
            <div class="d-flex justify-content-between align-items-center">
                <h1 class="p-3 text-white">Overtime Request </h1>

                <!-- Navigation Bar -->
                <nav class="navbar navbar-expand">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link " href="viewOvertimeServlet">View</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </header>

        <%
            String userID = request.getAttribute("userID") != null ? (String) request.getAttribute("userID") : "";
            String startOvertime = request.getAttribute("startOvertime") != null ? (String) request.getAttribute("startOvertime") : "";
            String endOvertime = request.getAttribute("endOvertime") != null ? (String) request.getAttribute("endOvertime") : "";
            String dateOvertime = request.getAttribute("dateOvertime") != null ? (String) request.getAttribute("dateOvertime") : "";
            String errorMsgTimeEnd = request.getAttribute("errorMsgTimeEnd") != null ? (String) request.getAttribute("errorMsgTimeEnd") : "";
            String errorMsgStartTime = request.getAttribute("errorMsgStartTime") != null ? (String) request.getAttribute("errorMsgStartTime") : "";
            String errorMsgDate = request.getAttribute("errorMsgDate") != null ? (String) request.getAttribute("errorMsgDate") : "";
            String errorMsgUserID = request.getAttribute("errorMsgUserID") != null ? (String) request.getAttribute("errorMsgUserID") : "";
        %>

        <div class="container mt-5 w-100 ">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-5">
                    <div class="formColor rounded-3">
                        <h2 class="text-center mb-4 pt-2">Overtime Request form </h2>
                        <form action="viewOvertimeServlet" method="post">

                            <div class="mb-3 px-5">
                                <label for="userID" class="form-label"> User ID :</label>
                                <input class="form-control w-100" type="text" id="userID" name="userID" value="<%= userID%>" required>
                                <% if (!errorMsgUserID.isEmpty()) {%>
                                <div style="color: red; font-size: 0.9em;"><%= errorMsgUserID%></div>
                                <% }%>
                            </div>

                            <div class="mb-3 px-5 ">
                                <label for="startOvertime" class="form-label"> Start time : </label>
                                <input class="form-control w-100" type="time" id="startOvertime" name="startOvertime" value="<%= startOvertime%>" required>

                                <% if (!errorMsgStartTime.isEmpty()) {%>
                                <div style="color: red; font-size: 0.9em;"><%= errorMsgStartTime%></div>
                                <% }%>
                            </div>

                            <div class="mb-3 px-5">
                                <label for="endOvertime" class="form-label"> End time : </label>
                                <input class="form-control w-100" type="time" id="endOvertime" name="endOvertime" value="<%= endOvertime%>" required>

                                <% if (!errorMsgTimeEnd.isEmpty()) {%>
                                <div style="color: red; font-size: 0.9em;"><%= errorMsgTimeEnd%></div>
                                <% }%>
                            </div>

                            <div class="mb-3 px-5">
                                <label for="dateOvertime" class="form-label"> Date : </label>
                                <input class="form-control w-100" type="date" id="dateOvertime" name="dateOvertime" value="<%= dateOvertime%>" required>

                                <% if (!errorMsgDate.isEmpty()) {%>
                                <div style="color: red; font-size: 0.9em;"><%= errorMsgDate%></div>
                                <% }%>
                            </div>

                            <div class="pt-3 pb-3 px-5">
                                <button class="btn btn-primary" name="btnSubmit" value="submitOvertimeReport" type="submit">
                                    Submit
                                </button>
                                <buttom class="btn btn-secondary ms-3" name="txtReset" value="Reset" type="reset">
                                    Reset
                                </buttom>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
