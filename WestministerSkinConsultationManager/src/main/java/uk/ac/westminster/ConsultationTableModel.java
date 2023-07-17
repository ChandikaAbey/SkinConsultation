package uk.ac.westminster;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ConsultationTableModel extends AbstractTableModel {

    private List<Consultation> consultationList = null;
    private final String[] columnNames = {"ID", "Doctor", "Specialization", "Patient", "Date", "Time", "Duration", "Rate", "Amount"};

    ConsultationTableModel(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    @Override
    public int getRowCount() {
        return consultationList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp;
        Consultation consultation = consultationList.get(rowIndex);
        switch (columnIndex) {
            case 0 -> temp = consultation.getConsultationNumber();
            case 1 -> temp = String.format("Dr. %s, %s", consultation.getDoctor().getSurName(), consultation.getDoctor().getName());
            case 2 -> temp = consultation.getDoctor().getSpecialization().label;
            case 3 -> temp = String.format("%s, %s", consultation.getPatient().getName(), consultation.getPatient().getSurName());
            case 4 -> temp = consultation.getDate().toString();
            case 5 -> temp = consultation.getTime().toString();
            case 6 -> temp = consultation.getHours();
            case 7 -> temp = consultation.getCost();
            default -> temp = consultation.getHours() * consultation.getCost();
        }
        return temp;
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return Integer.class;
            }
            case 6, 7, 8 -> {
                return Double.class;
            }
            default -> {
                return String.class;
            }
        }
    }
}
