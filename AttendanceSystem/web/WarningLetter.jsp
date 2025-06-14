<%-- 
    Document   : WarningLetter
    Created on : 14 Jun 2025, 12:51:45 am
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> 
        <title>Warning Letter - Low Attendance</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 40px;
                line-height: 1.6;
            }
            .letter-container {
                border: 1px solid #ccc;
                padding: 30px;
                max-width: 800px;
                margin: auto;
            }
            .header, .footer {
                text-align: center;
                margin-bottom: 20px;
            }
            .subject {
                font-weight: bold;
                text-decoration: underline;
                margin-top: 20px;
                margin-bottom: 20px;
            }
            .signature {
                margin-top: 40px;
            }
        </style>
    </head>
    <body>


        <div class="letter-container">
            <div class="header">
                <h2>XYZ Corporation</h2>
                <p>Human Resources Department</p>
            </div>

            <p>Date: <strong>13 June 2025</strong></p>

            <p>To:<br>
                <strong>Mr. John Doe</strong><br>
                Employee ID: 987654<br>
                Software Engineer<br>
                XYZ Corporation</p>

            <p class="subject">Subject: Warning Letter for Low Attendance</p>

            <p>Dear Mr. John Doe,</p>

            <p>This letter serves as a formal warning regarding your low attendance record at XYZ Corporation. We have observed that your attendance rate for the month of May 2025 was only <strong>72%</strong>, which is significantly below the company’s required minimum of <strong>90%</strong>.</p>

            <p>This level of attendance is unacceptable and has a direct impact on project timelines and team coordination. We emphasize the importance of consistent attendance as part of your responsibilities as a Software Engineer.</p>

            <p>You are hereby advised to improve your attendance and comply with the company's attendance policies. Continued poor attendance may lead to further disciplinary action, including suspension or termination of employment.</p>

            <p>Kindly submit a written explanation for your low attendance and arrange a meeting with your immediate supervisor within the next 3 working days.</p>

            <p>We trust you will treat this matter with the seriousness it deserves.</p>

            <p>Sincerely,</p>

            <div class="signature">
                <p><strong>Human Resources Manager</strong><br>
                    XYZ Corporation</p>
            </div>

            <div class="footer">
                <hr>
                <small>This is a system-generated letter. No signature is required.</small>
            </div>
        </div>
    </body>
</html>

</body>
</html>
