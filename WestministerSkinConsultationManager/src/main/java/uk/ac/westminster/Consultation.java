package uk.ac.westminster;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class Consultation {

    //Properties
    private String date;
    private String time;
    private double cost;
    private String notes;

    //Property Methods
    public String getDate() {
        return date;
    }

    public void setDate(int year, int month, int dayOfMonth) {
        LocalDate date = LocalDate.of(year, Month.of(month), dayOfMonth);
        this.date = date.toString();
    }

    public String getTime() {
        return time;
    }

    public void setTime(int hour, int minute) {
        LocalTime time = LocalTime.of(hour, minute);
        this.time = time.toString();
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

}
