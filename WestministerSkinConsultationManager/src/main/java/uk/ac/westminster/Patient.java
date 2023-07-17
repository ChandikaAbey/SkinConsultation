package uk.ac.westminster;

import java.time.LocalDate;

public class Patient extends Person {

    // Properties
    private int patientId;

    // Constructors
    public Patient(int patientId, String firstName, String surName, LocalDate dateOfBirth, String mobileNo) {
        super(firstName, surName, dateOfBirth, mobileNo);
        this.patientId = patientId;
    }

    // Property Methods
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

}
