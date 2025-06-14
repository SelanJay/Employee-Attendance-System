<%-- 
    Document   : AuthorisedOvertimeRecord
    Created on : 18 May 2025, 7:46:15 pm
    Author     : HP
--%>

<%@page import="java.util.*"%>
<%@page import="dao.OvertimeDAO"%>
<%@page import="model.overtime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <th>Number</th>
                <th>User ID</th>
                <th>Date</th>
                <th>Begin</th>
                <th>End</th>
                <th>Status</th>
            </tr>

            <%
                String department = (String) session.getAttribute("department");
                int i =1;
                List<overtime> list = OvertimeDAO.getAllOvertimeRequestDepartment(department);
                for (overtime e : list) {
            %>
            <tr>
                <td><%= i++%></td>
                <td><%= e.getUserID()%>
                <td><%= e.getDateOvertime()%></td>
                <td><%= e.getStartTime()%></td>
                <td><%= e.getEndTime()%></td>
                <td><%= e.getVerification()%></td>
                <td>
                    <a href='AuthorisedViewOvertimeServlet?overtimeId=<%= e.getOvertimeID() %>&verification=Approve'> Approve </a>
                </td>
                <td> 
                    <a href='AuthorisedViewOvertimeServlet?overtimeId=<%= e.getOvertimeID() %>&verification=Reject'> Reject </a>
                </td>
            </tr>
            <%  }%>
        </table>
        <form action="AuthorisedOvertimeForm.jsp" method="post">
            <input type="submit" value="back">
        </form>
    </body>
    
</html>
