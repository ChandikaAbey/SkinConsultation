package uk.ac.westminster;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {

    // Properties
    private String name;
    private String surName;
    private LocalDate dateOfBirth;
    private String mobileNo;
    private String address;

    public Person(String name, String surName, LocalDate dateOfBirth, String mobileNo) {
        this.name = name;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNo = mobileNo;
    }

    // Property Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
