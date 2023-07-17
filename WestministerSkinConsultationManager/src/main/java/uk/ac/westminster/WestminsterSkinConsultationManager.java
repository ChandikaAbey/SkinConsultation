package uk.ac.westminster;

import uk.ac.westminster.Utils.Specializations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    public DataBank db = null;
    private static GuiMain gui = null;

    WestminsterSkinConsultationManager(boolean menuOn) {
        try {
            this.db = Utils.readObjectFromFile();
        } catch (Exception e) {
            this.db = new DataBank();
        }

        gui = new GuiMain(db);

        if (menuOn) {
            int input = 0;

            do {
                input = displayMenu();
                if (input == 6)
                    continue;

                switch (input) {
                    case 1 -> addDoctor();
                    case 2 -> deleteDoctor();
                    case 3 -> listDoctors();
                    case 4 -> save();
                    default -> OpenGUI();
                }

            } while (input != 6);
            Utils.print("\nThank you for using our service.\n");
            gui.closeGui();
            Utils.closeInput();
        }
    }

    private static void OpenGUI() {
        Utils.print("Open GUI");
        gui.openGui();
    }

    public int displayMenu() {
        Utils.print("Welcome to WestMinister Skin Consultation Manager");
        Utils.print("_________________________________________________\n");
        Utils.print("\t\t1. Add a new Doctor");
        Utils.print("\t\t2. Delete a Doctor");
        Utils.print("\t\t3. List Doctors");
        Utils.print("\t\t4. Save Data");
        Utils.print("\t\t5. Open GUI");
        Utils.print("\t\t6. Quit\n");
        int inputChoice = 0;
        do {
            try {
                inputChoice = Utils.getConsoleInput(1, 6);
                if (inputChoice > 0) {
                    break;
                }
            } catch (Exception e) {
                Utils.print(e.getMessage());
            }
        } while (inputChoice <= 0);
        return inputChoice;
    }

    @Override
    public boolean addDoctor(Doctor providedDoctor) {

        if (db.doctorList.size() >= Utils.LIST_SIZE) {
            Utils.print("\n[Error] Maximum allocatable doctor count exceeded!\n[info] Operation aborted.\n");
            return false;
        }

        try {
            db.doctorList.add(providedDoctor);
            Utils.print(String.format("\nDr. %s %s has been added to the system.\n", providedDoctor.getName(),
                    providedDoctor.getSurName()));
            return true;
        } catch (Exception e) {
            Utils.print(e.getMessage());
            return false;
        }
    }

    @Override
    public void addDoctor() {

        if (db.doctorList.size() >= Utils.LIST_SIZE) {
            Utils.print("\n[Error] Maximum allocatable doctor count exceeded!\n[info] Operation aborted.\n");
            return;
        }

        Utils.print("\n--------- Adding a new Doctor ---------\n");
        String firstName = Utils.getInput("Enter first name: ");
        String surName = Utils.getInput("Enter surname: ");
        Utils.print("Enter date of birth,");
        LocalDate dateOfBirth = Utils.requestDateInput();
        String mobileNumber = Utils.getInput("Enter mobile: ");
        String medicalLicenseNumber = Utils.getInput("Enter medical license number: ");
        Specializations specialization = Utils.getDoctorSpecialization();

        Doctor doctor = new Doctor(medicalLicenseNumber, specialization, firstName, surName, dateOfBirth, mobileNumber);

        try {
            db.doctorList.add(doctor);
            Utils.print(String.format("\nDr. %s %s has been added to the system.\n", doctor.getName(),
                    doctor.getSurName()));
        } catch (Exception e) {
            Utils.print(e.getMessage());
        }
    }

    @Override
    public void deleteDoctor() {
        Utils.print("\n_______________Delete a Doctor_______________");
        listDoctors();
        String input = Utils.getInput("Enter the medical license of the doctor to be deleted: ");
        for (Doctor doc : db.doctorList) {
            if (doc.getMedicalLicenseNo().compareTo(input) == 0) {
                db.doctorList.remove(doc);
                Utils.print("\nDeleted following doctor: \n");
                Utils.printDoctorInfo(doc);
                if (db.doctorList.size() != 0) {
                    Utils.print(String.format("[info] %d %s left in the system.", db.doctorList.size(), db.doctorList.size() > 1 ? "Doctors are" : "Doctor is"));
                } else {
                    Utils.print("[info] No doctors are left in the system.");
                }
                return;
            }
        }
        Utils.print("\nNo matching medical license found.\n");
    }

    @Override
    public boolean deleteDoctor(String medicalLicenseNumber) {
        for (Doctor doc : db.doctorList) {
            if (doc.getMedicalLicenseNo().compareTo(medicalLicenseNumber) == 0) {
                db.doctorList.remove(doc);
                Utils.print("\nDeleted following doctor: \n");
                Utils.printDoctorInfo(doc);
                if (db.doctorList.size() != 0) {
                    Utils.print(String.format("[info] %d %s left in the system.", db.doctorList.size(), db.doctorList.size() > 1 ? "Doctors are" : "Doctor is"));
                } else {
                    Utils.print("[info] No doctors are left in the system.");
                }
                return true;
            }
        }
        Utils.print("\nNo matching medical license found.\n");
        return false;
    }

    @Override
    public void listDoctors() {
        List<Doctor> doctors = db.doctorList;
        Collections.sort(doctors);
        doctors.forEach(doctor -> {
            Utils.printDoctorInfo(doctor, db.doctorList.indexOf(doctor));
            if (doctors.indexOf(doctor) == doctors.size() - 1) {
                Utils.print("_______________End of List_______________\n");
            }
        });
    }

    @Override
    public void save() {
        try {
            Utils.writeObjectToFile(db);
            Utils.print("[info] Saved successfully.\n");
        } catch (IOException e) {
            Utils.print(e.getMessage());
        }
    }

    @Override
    public boolean saveMock() {
        try {
            Utils.writeMockObjectToFile(db);
            Utils.print("[info] Saved successfully.\n");
            return true;
        } catch (IOException e) {
            Utils.print(e.getMessage());
            return false;
        }
    }
}
