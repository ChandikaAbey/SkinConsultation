package uk.ac.westminster;

import java.time.LocalDate;

import uk.ac.westminster.Utils.Specializations;

public class Doctor extends Person implements Comparable<Object> {

    // Properties
    private String medicalLicenseNo;
    private Specializations specialization;

    // Constructors
    public Doctor(String medicalLicenseNo, Specializations specialization, String firstName, String surName,
            LocalDate dateOfBirth, String mobileNo) {
        super(firstName, surName, dateOfBirth, mobileNo);
        this.medicalLicenseNo = medicalLicenseNo;
        this.specialization = specialization;
    }

    // Property Methods
    public String getMedicalLicenseNo() {
        return medicalLicenseNo;
    }

    public void setMedicalLicenseNo(String medicalLicenseNo) {
        this.medicalLicenseNo = medicalLicenseNo;
    }

    public Specializations getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specializations specialization) {
        this.specialization = specialization;
    }

    @Override
    public int compareTo(Object o) {
        Doctor doc = (Doctor) o;
        return this.getSurName().compareToIgnoreCase(doc.getSurName());
    }

    @Override
    public String toString() {
        return String.format("Dr. %s, %s", this.getSurName(), this.getName());
    }

}
