package uk.ac.westminster;

public interface SkinConsultationManager {
    void addDoctor();
    boolean addDoctor(Doctor providedDoctor);
    void deleteDoctor();
    boolean deleteDoctor(String medicalLicenseNumber);
    void listDoctors();
    void save();
    boolean saveMock();
}
