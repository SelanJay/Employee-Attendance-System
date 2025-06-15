package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author HP
 */
import model.overtime;
//import model.users;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import model.UserAttendance;

public class OvertimeDAO {

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/attendance", "root", "admin");
        } catch (Exception e) {
            System.out.println(e);
        }
        // return the connection object
        return con;
    }

    public static int requestOvertime(overtime e) {
        int status = 0;

        try {
            Connection con = OvertimeDAO.getConnection();

            PreparedStatement ps = con.prepareStatement("insert into overtime(userID , startTime, endTime ,dateOvertime, verification ) values (?,?,?,?,?)");
            ps.setInt(1, e.getUserID());
            ps.setString(2, e.getStartTime());
            ps.setString(3, e.getEndTime());
            ps.setString(4, e.getDateOvertime());
            ps.setString(5, e.getVerification());

            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static List<overtime> getOvertimeByDepartment(String department) {
        List<overtime> list = new ArrayList<>();

        String selectSql = "SELECT "
                + "o.overtimeID, o.userID, u.fullName, u.department, "
                + "o.startTime, o.endTime, o.dateOvertime, o.verification "
                + "FROM overtime o "
                + "JOIN users u ON o.userID = u.userID "
                + "WHERE STR_TO_DATE(dateOvertime, '%Y-%m-%d') >= CURDATE() && u.department = ?";

        try (Connection con = OvertimeDAO.getConnection(); PreparedStatement ps = con.prepareStatement(selectSql)) {

            ps.setString(1, department);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                overtime e = new overtime();

                e.setOvertimeID(rs.getString(1));
                e.setUserID(rs.getInt(2));
                e.setFullName(rs.getString(3));
                e.setDepartmentName(rs.getString(4));
                e.setStartTime(LocalTime.parse(rs.getString(5)));
                e.setEndTime(LocalTime.parse(rs.getString(6)));
                e.setDateOvertime(LocalDate.parse(rs.getString(7)));
                e.setVerification(rs.getString(8));
                list.add(e);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<overtime> getAllOvertimeRequestDepartment(String keyword) {
        List<overtime> list = new ArrayList<>();

        try {
            Connection con = OvertimeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM overtime WHERE STR_TO_DATE(dateOvertime, '%Y-%m-%d') >= CURDATE() && userID LIKE ?;");
            ps.setString(1, keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                overtime e = new overtime();
                e.setOvertimeID(rs.getString(1));
                e.setUserID(rs.getInt(2));
                e.setStartTime(LocalTime.parse(rs.getString(3)));
                e.setEndTime(LocalTime.parse(rs.getString(4)));
                e.setDateOvertime(LocalDate.parse(rs.getString(5)));
                e.setVerification(rs.getString(6));
                list.add(e);

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static overtime getOvertimeRequestById(String id) {
        overtime e = null;

        try {
            Connection con = OvertimeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from overtime where overtimeID = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e = new overtime();
                e.setOvertimeID(rs.getString(1));
                e.setUserID(rs.getInt(2));
                e.setStartTime(LocalTime.parse(rs.getString(3)));
                e.setEndTime(LocalTime.parse(rs.getString(4)));
                e.setDateOvertime(LocalDate.parse(rs.getString(5)));
                e.setVerification(rs.getString(6));
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public static List<overtime> getOvertimeRequestUserById(String id) {
        List<overtime> list = new ArrayList<>();

        try {
            Connection con = OvertimeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM overtime WHERE userID = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                overtime e = new overtime();
                e.setOvertimeID(rs.getString("overtimeID"));
                e.setUserID(rs.getInt("userID"));
                e.setStartTime(LocalTime.parse(rs.getString("startTime")));
                e.setEndTime(LocalTime.parse(rs.getString("endTime")));
                e.setDateOvertime(LocalDate.parse(rs.getString("dateOvertime")));
                e.setVerification(rs.getString("verification"));
                list.add(e);
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    //to check the validty of the userID in overtime form
    public static Boolean isUserExist(int userID) {

        boolean exist = false;
        try {
            overtime e = new overtime();
            Connection con = OvertimeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT 1 FROM users WHERE userID = ?");
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            exist = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return exist;
    }

    public static int deleteOvertimeRequestByID(String id) {
        int status = 0;
        try {
            Connection con = OvertimeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from overtime where overtimeID =?");
            ps.setString(1, id);
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static List<overtime> getAllUsers() {
        List<overtime> list = new ArrayList<>();

        try {
            Connection con = OvertimeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                overtime e = new overtime();
                e.setUserID(rs.getInt(1));
                e.setVerification(rs.getString(5));
                list.add(e);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<UserAttendance> getMonthlyAttendanceReport(String departmentM, int year, int month) {
        List<UserAttendance> reports = new ArrayList<>();
        String formattedMonth = String.format("%04d-%02d", year, month);

        String sql = "SELECT "
                + "  a.userID, "
                + "  u.username, " 
                + "  u.department, "
                + "  DATE_FORMAT(a.date, '%Y-%m') AS month, "
                + "  SUM(CASE WHEN a.status IN ('Present', 'Late') THEN 1 ELSE 0 END) AS attended_days, "
                + "  ROUND(SUM(CASE WHEN a.status IN ('Present', 'Late') THEN 1 ELSE 0 END) / wd.total_working_days * 100, 2) AS attendance_percentage "
                + "FROM Attendance a "
                + "JOIN Users u ON a.userID = u.userID "
                + "JOIN working_days wd ON DATE_FORMAT(a.date, '%Y-%m') = wd.yearMonth "
                + "WHERE DATE_FORMAT(a.date, '%Y-%m') = ? AND u.department = ? "
                + "GROUP BY a.userID, u.department, wd.total_working_days "
                + "ORDER BY a.userID;";

        try {
            Connection con = OvertimeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, formattedMonth);
            ps.setString(2, departmentM);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UserAttendance userAtt = new UserAttendance();
                    userAtt.setUserId(rs.getInt("userID"));
                    userAtt.setUsername(rs.getString("username"));
                    userAtt.setDepartment(rs.getString("department"));
                    userAtt.setMonth(rs.getString("month"));
                    userAtt.setAttendedDays(rs.getInt("attended_days"));
                    userAtt.setAttendancePercentage(rs.getDouble("attendance_percentage"));
                    reports.add(userAtt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    public static int updateOvertimeRequest(overtime e) {
        int status = 0;
        try {
            Connection con = OvertimeDAO.getConnection();
            String updateOvertimeQuery = "UPDATE overtime SET userID= ?, startTime= ?, endTime= ?,dateOvertime=?, "
                    + "verification= ? WHERE overtimeID = ?";
            PreparedStatement ps = con.prepareStatement(updateOvertimeQuery);
            ps.setInt(1, e.getUserID());
            ps.setString(2, e.getStartTime());
            ps.setString(3, e.getEndTime());
            ps.setString(4, e.getDateOvertime());
            ps.setString(5, e.getVerification());
            ps.setString(6, e.getOvertimeID());

            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

}
