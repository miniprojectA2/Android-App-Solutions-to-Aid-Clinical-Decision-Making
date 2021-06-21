package com.miniproject.clinicaldecisionmakingapp.model;

public class Doctor {
    private String doctorName;
    private String doctorAge;
    private String doctorDepartment;
    private String doctorEmail;
    private String doctorPhone;
    private String doctorSex;
    private String password;

    public Doctor() {
    }

    public Doctor(String doctorName, String doctorAge, String department, String doctorEmail, String doctorPhone, String password, String doctorSex) {
        this.doctorName = doctorName;
        this.doctorAge = doctorAge;
        this.doctorDepartment = department;
        this.doctorEmail = doctorEmail;
        this.doctorPhone = doctorPhone;
        this.password = password;
        this.doctorSex = doctorSex;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorAge() {
        return doctorAge;
    }

    public void setDoctorAge(String doctorAge) {
        this.doctorAge = doctorAge;
    }

    public String getDepartment() {
        return doctorDepartment;
    }

    public void setDepartment(String department) {
        this.doctorDepartment = department;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDoctorSex() {
        return doctorSex;
    }

    public void setDoctorSex(String doctorSex) {
        this.doctorSex = doctorSex;
    }
}
