package uk.ac.westminster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBank implements Serializable {
    public List<Doctor> doctorList = new ArrayList<Doctor>();
    public static List<Patient> patientList = new ArrayList<Patient>();
    public static List<Consultation> consultationList = new ArrayList<>();
}
