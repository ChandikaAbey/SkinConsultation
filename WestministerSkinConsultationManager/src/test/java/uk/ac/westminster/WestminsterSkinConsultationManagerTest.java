package uk.ac.westminster;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class WestminsterSkinConsultationManagerTest {

    private WestminsterSkinConsultationManager manager;

    @BeforeEach
    public void setUp() {
        manager = new WestminsterSkinConsultationManager(false);
    }

    @Test
    public void testAddDoctor_Successful() {
        int originalSize = manager.db.doctorList.size();

        String firstName = "John";
        String surName = "Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String mobileNumber = "1234567890";
        String medicalLicenseNumber = "12345";
        Utils.Specializations specialization = Utils.Specializations.COSMETIC_DERMATOLOGY;

        boolean result = manager.addDoctor(new Doctor(medicalLicenseNumber, specialization, firstName, surName, dateOfBirth, mobileNumber));

        Assertions.assertTrue(result);
        Assertions.assertEquals(originalSize + 1, manager.db.doctorList.size());
    }

    @Test
    public void testAddDoctor_ExceedMaxLimit() {
        for (int i = 0; i < Utils.LIST_SIZE; i++) {
            manager.addDoctor(new Doctor("123", Utils.Specializations.MEDICAL_DERMATOLOGY, "John", "Doe", LocalDate.of(1990, 1, 1), "1234567890"));
        }

        boolean result = manager.addDoctor(new Doctor("54321", Utils.Specializations.COSMETIC_DERMATOLOGY, "Jane", "Smith", LocalDate.of(1992, 5, 5), "0987654321"));

        Assertions.assertFalse(result);
        Assertions.assertEquals(Utils.LIST_SIZE, manager.db.doctorList.size());
    }

    @Test
    void deleteDoctorTest() {
        manager.addDoctor(new Doctor("54321", Utils.Specializations.COSMETIC_DERMATOLOGY, "Jane", "Smith", LocalDate.of(1992, 5, 5), "0987654321"));

        Assertions.assertTrue(manager.deleteDoctor("54321"));
    }

    @Test
    void listDoctorsTest() {
        WestminsterSkinConsultationManager mockManager = mock(WestminsterSkinConsultationManager.class);
        mockManager.listDoctors();

        verify(mockManager, times(1)).listDoctors();
    }

    @Test
    void saveTest() {
        Assertions.assertTrue(manager.saveMock());
    }
}