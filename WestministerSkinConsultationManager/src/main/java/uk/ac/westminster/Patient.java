package uk.ac.westminster;

import java.time.LocalDate;

public class Patient extends Person {

    // Properties
    private String patientId;

    // Constructors
    public Patient(String patientId, String firstName, String surName, LocalDate dateOfBirth, String mobileNo) {
        super(firstName, surName, dateOfBirth, mobileNo);
        this.patientId = patientId;
    }

    // Property Methods
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

}
