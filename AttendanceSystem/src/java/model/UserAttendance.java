/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class UserAttendance {
    private int userId;
    private String department;
    private String month;
    private int totalDays;
    private int attendedDays;
    private double attendancePercentage;

    public UserAttendance(int userId, String department, String month, int totalDays, int attendedDays, double attendancePercentage) {
        this.userId = userId;
        this.department = department;
        this.month = month;
        this.totalDays = totalDays;
        this.attendedDays = attendedDays;
        this.attendancePercentage = attendancePercentage;
    }

    public int getUserId() {
        return userId;
    }

    public String getDepartment() {
        return department;
    }

    public String getMonth() {
        return month;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getAttendedDays() {
        return attendedDays;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public void setAttendedDays(int attendedDays) {
        this.attendedDays = attendedDays;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
    
    
    
}
