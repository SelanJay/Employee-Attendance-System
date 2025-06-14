package model;


import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class users {
    int ICNumber;
    String id, username, password, email, empType, jobTitle ;
    LocalDate datOfBirth;
    
    public users( String id, String username, String password, int ICNumber, LocalDate dateOfBirth, String email, String empType, String jobTitle){
        this.id = id;
        this.username = username;
        this.password = password;
        this.ICNumber = ICNumber;
        this.datOfBirth = dateOfBirth;
        this.email = email;
        this.empType= empType;
        this.jobTitle= jobTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getICNumber() {
        return ICNumber;
    }

    public void setICNumber(int ICNumber) {
        this.ICNumber = ICNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public LocalDate getDatOfBirth() {
        return datOfBirth;
    }

    public void setDatOfBirth(LocalDate datOfBirth) {
        this.datOfBirth = datOfBirth;
    }
    
    
}
