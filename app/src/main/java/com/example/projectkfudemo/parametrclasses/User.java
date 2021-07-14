package com.example.projectkfudemo.parametrclasses;


import com.example.projectkfudemo.parametrclasses.forjson.EmployeeInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
//Если авторизация ебнется, могли переписать API на сервер
public class User implements Serializable {
    final static String LOG_TAG = "myLogs";

    @SerializedName("successful")
    @Expose
    private boolean successful;
    @SerializedName("user_id")
    @Expose
    private int userId = 0;
    @SerializedName("employee")
    @Expose
    private boolean employee;
    @SerializedName("employee_info")
    @Expose
    private EmployeeInfo employeeInfo;
    @SerializedName("student")
    @Expose
    private boolean student;
    @SerializedName("person")
    @Expose
    private boolean person;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("middlename")
    @Expose
    private String middlename;
    @SerializedName("p2")
    @Expose
    private String p2;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public boolean isPerson() {
        return person;
    }

    public void setPerson(boolean person) {
        this.person = person;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }
}
