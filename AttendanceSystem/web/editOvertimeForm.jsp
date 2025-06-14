<%-- 
    Document   : editOvertimeForm
    Created on : 17 May 2025, 5:04:25 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <style>

            .formColor {
                background-color: rgba(211, 211, 211, 0.4);
            }

            header {
                background-color: #4CAF50;
                height: 5em;
            }

        </style>
    </head>
    <body>

        <%
            String userID = request.getAttribute("userID") != null ? request.getAttribute("userID").toString() : "";
            String startOvertime = request.getAttribute("startOvertime") != null ? (String) request.getAttribute("startOvertime") : "";
            String endOvertime = request.getAttribute("endOvertime") != null ? (String) request.getAttribute("endOvertime") : "";
            String dateOvertime = request.getAttribute("dateOvertime") != null ? (String) request.getAttribute("dateOvertime") : "";
            String errorMsgTimeEnd = request.getAttribute("errorMsgTimeEnd") != null ? (String) request.getAttribute("errorMsgTimeEnd") : "";
            String errorMsgStartTime = request.getAttribute("errorMsgStartTime") != null ? (String) request.getAttribute("errorMsgStartTime") : "";
            String errorMsgDate = request.getAttribute("errorMsgDate") != null ? (String) request.getAttribute("errorMsgDate") : "";
            String errorMsgUserID = request.getAttribute("errorMsgUserID") != null ? (String) request.getAttribute("errorMsgUserID") : "";
            
            String overtimeID = request.getAttribute("overtimeId") != null ? (String) request.getAttribute("overtimeId") : request.getParameter("overtimeId");
            String verification = request.getAttribute("verification") != null ? (String) request.getAttribute("verification") : "";

        %>
        <header class="container-fluid ">
            <div class="d-flex justify-content-between align-items-center">
                <h1 class="p-3 text-white">Edit Overtime Request</h1>
        </header>
        <div class="container mt-5 w-100 ">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-5">
                    <div class="formColor rounded-3">
                        <h2 class="text-center mb-4 pt-2">Overtime Edit Form</h2>
                        <form action="EditOvertimeServlet" method="post">

                            <div class="mb-3 px-5">
                                <label for="userID" class="form-label"> User ID : </label>
                                <input class="form-control w-100" type="text" id="userID" name="userID" value="<%= userID%>" required>  
                                <% if (!errorMsgUserID.isEmpty()) {%>
                                <div style="color: red; font-size: 0.9em;"><%= errorMsgUserID%></div>
                                <% }%>
                            </div>

                            <div class="mb-3 px-5">
                                <label for="startOvertime"class="form-label"> Start time : </label>
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
                            <div>
                                <input type="hidden" name="overtimeID" value="<%= overtimeID%>">
                                <input type="hidden" name="verification" value= "<%= verification%>">
                            </div>
                            <div class=" pt-3 pb-3 px-5">
                                <input class="btn btn-primary" name="btnLogin" value="submit" type="submit">
                                <input class="btn btn-secondary ms-3" name="txtReset" value="Reset" type="reset">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="container mt-3">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-5">
                    <form action="overtimeRecord.jsp" method="post">
                        <button type="submit" class="btn btn-outline-dark w-100 d-flex align-items-center justify-content-center">
                            <i class="bi bi-arrow-left-circle me-2"></i> Back
                        </button>
                    </form>
                </div>
            </div>
        </div>  
    </body>
</html>
