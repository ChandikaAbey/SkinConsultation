package uk.ac.westminster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Scanner;

public class Utils {

    public static Scanner input = new Scanner(System.in);

    public static final int LIST_SIZE = 10;

    public static void closeInput() {
        input.close();
    }

    public static String INVALID_INPUT = "\nInvalid input, please try again.\n";

    public static final void print(String message) {
        System.out.println(message);
    }

    public static String getInput(String requestMessage) {
        String inputString = "";
        do {
            print(String.format("[?] %s", requestMessage));
            inputString = input.nextLine();
        } while (inputString == "");

        return inputString;
    }

    public static LocalDate requestDateInput() {
        do {
            try {
                print("[?] Please enter a date in YYYY-MM-DD format: ");
                String userInput = input.nextLine();
                return LocalDate.parse(userInput);
            } catch (Exception e) {
                print("[error] Please follow the YYYY-MM-DD format and try again.");
            }
        } while (true);
    }

    public static int getConsoleInput(int min, int max) throws Exception {
        print("[?] Please enter your choice:");
        try {
            var inputParsed = Integer.parseInt(input.nextLine());
            if (min <= inputParsed && inputParsed <= max) {
                return inputParsed;
            } else {
                throw new Exception(INVALID_INPUT);
            }
        } catch (Exception e) {
            throw new Exception(INVALID_INPUT);
        }
    }


    public enum Specializations {
        COSMETIC_DERMATOLOGY("Cosmetic Dermatology"),
        MEDICAL_DERMATOLOGY("Medical Dermatology"),
        PAEDIATRIC_DERMATOLOGY("Paediatric Dermatology");

        public final String label;

        private Specializations(String label) {
            this.label = label;
        }
    }

    public static Specializations getDoctorSpecialization() {
        print("[?] Select a specialization from the list below:");
        for (int i = 0; i < Specializations.values().length; i++) {
            print(String.format("\t\t%d. %s", i + 1, Specializations.values()[i].label));
        }
        int inputChoice = 0;
        do {
            try {
                inputChoice = getConsoleInput(1, Specializations.values().length);
                if (inputChoice > 0) {
                    break;
                }
            } catch (Exception e) {
                Utils.print(e.getMessage());
            }
        } while (inputChoice <= 0);
        Specializations selectedSpecialization = Specializations.values()[inputChoice - 1];
        print(String.format("[info] Selected specialization is: %s", selectedSpecialization.label));
        return selectedSpecialization;
    }

    private static File file = new File("ManagerStorage.dat");
    private static File mockFile = new File("MockManagerStorage.dat");

    public static void writeObjectToFile(DataBank obj) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    public static void writeMockObjectToFile(DataBank obj) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(mockFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    public static DataBank readObjectFromFile() throws IOException, ClassNotFoundException {
        DataBank result = null;
        try (FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (DataBank) ois.readObject();
        }
        return result;
    }

    public static void printDoctorInfo(Doctor doctor, int index) {
        Utils.print(String.format("\n\t%d. Dr. %s, %s", index + 1, doctor.getSurName(),
                doctor.getName()));
        Utils.print(String.format("\t\tSpecialization: %s", doctor.getSpecialization().label));
        Utils.print(String.format("\t\tMedical License: %s", doctor.getMedicalLicenseNo()));
        Utils.print(String.format("\t\tMobile Number: %s", doctor.getMobileNo()));
        Utils.print(String.format("\t\tDate of birth: %s\n", doctor.getDateOfBirth().toString()));
    }
    
    public static void printDoctorInfo(Doctor doctor) {
        Utils.print(String.format("\n\tDr. %s, %s", doctor.getSurName(),
                doctor.getName()));
        Utils.print(String.format("\t\tSpecialization: %s", doctor.getSpecialization().label));
        Utils.print(String.format("\t\tMedical License: %s", doctor.getMedicalLicenseNo()));
        Utils.print(String.format("\t\tMobile Number: %s", doctor.getMobileNo()));
        Utils.print(String.format("\t\tDate of birth: %s\n", doctor.getDateOfBirth().toString()));
    }
}