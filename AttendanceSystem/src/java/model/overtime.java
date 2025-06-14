package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author HP
 */
import java.util.*;
import java.time.LocalTime;
import java.time.LocalDate;

public class overtime {
    private int userID;
    private String overtimeID ;
    private LocalTime startTime, endTime;
    private LocalDate dateOvertime;
    private String verification, fullName, departmentName;
  
    public void setOvertimeID(String overtimeID) {
        this.overtimeID = overtimeID;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setDateOvertime(LocalDate dateOvertime) {
        this.dateOvertime = dateOvertime;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public void setFullName(String userName) {
        this.fullName = userName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public String getOvertimeID() {
        return overtimeID;
    }

    public int getUserID() {
        return userID;
    }

    public String getStartTime() {
        return startTime.toString();
    }

    public String getEndTime() {
        return endTime.toString();
    }

    public String getDateOvertime() {
        return dateOvertime.toString();
    }

    public String getVerification() {
        return verification;
    }

    public String getFullUserName() {
        return fullName;
    }

    public String getDepartmentName() {
        return departmentName;
    }
    
    
}
