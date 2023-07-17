package uk.ac.westminster;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation implements Serializable {

    //Properties
    private final Doctor doctor;
    private final Patient patient;
    private LocalDate date;
    private String time;
    private double cost;
    private String notes;
    private double hours;

    Consultation( Doctor doctor, Patient patient, LocalDate date, String time, double cost, double hours, String notes) {
        this.doctor = doctor;
        this.patient = patient;
        this.time = time;
        this.date = date;
        this.hours = hours;
        this.notes = notes;
        this.cost = cost;
    }

    //Property Methods
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getHours() {
        return hours;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }
}
