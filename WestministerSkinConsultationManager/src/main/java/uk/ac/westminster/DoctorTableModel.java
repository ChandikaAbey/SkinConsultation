package uk.ac.westminster;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DoctorTableModel extends AbstractTableModel {
    private String columnNames[] = {"Name", "Specialization", "Mobile Number"};
    private List<Doctor> doctorList = null;
    public DoctorTableModel(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @Override
    public int getRowCount() {
        return doctorList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if (columnIndex == 0) {
            temp = String.format("Dr. %s, %s", doctorList.get(rowIndex).getSurName(), doctorList.get(rowIndex).getName());
        } if (columnIndex == 1) {
            temp = doctorList.get(rowIndex).getSpecialization().label;
        } if (columnIndex == 2) {
            temp = doctorList.get(rowIndex).getMobileNo();
        }
        return temp;
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public Class getColumnClass(int columnIndex) {
        return String.class;
    }
}
