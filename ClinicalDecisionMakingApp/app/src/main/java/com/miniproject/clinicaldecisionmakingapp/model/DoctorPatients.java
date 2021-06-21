package com.miniproject.clinicaldecisionmakingapp.model;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class DoctorPatients implements Serializable {

    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientSex;
    private String patientWeight;
    private String patientEmail;
    private String patientPhone;

    @Exclude
    private String id;

    public DoctorPatients() {

    }

    public DoctorPatients(String doctorName, String patientName, String patientAge, String patientSex, String patientWeight, String patientEmail, String patientPhone) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientSex = patientSex;
        this.patientWeight = patientWeight;
        this.patientEmail = patientEmail;
        this.patientPhone = patientPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(String patientWeight) {
        this.patientWeight = patientWeight;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }
}
