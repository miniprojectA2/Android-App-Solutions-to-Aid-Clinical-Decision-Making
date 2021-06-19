package com.miniproject.clinicaldecisionmakingapp.model;

public class Doctor {
    private String doctorName;
    private String doctorAge;
    private String department;
    private String doctorEmail;
    private String doctorPhone;

    public Doctor(String doctorName, String doctorAge, String department, String doctorEmail, String doctorPhone) {
        this.doctorName = doctorName;
        this.doctorAge = doctorAge;
        this.department = department;
        this.doctorEmail = doctorEmail;
        this.doctorPhone = doctorPhone;
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
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
}
