package uk.ac.westminster;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;

public class GuiMain implements ActionListener {
    private final JFrame mainFrame = new JFrame("WestMinister Skin Consultation");
    private final JTable table;
    private final DataBank db;
    private final JRadioButton radAscending = new JRadioButton("Ascending");
    private final JRadioButton radDescending = new JRadioButton("Descending");

    GuiMain(DataBank db) {
        this.db = db;
        sort(this.db.doctorList);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new GridLayout(2, 1, 5, 5));
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DoctorTableModel tableModel = new DoctorTableModel(this.db.doctorList);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = table.getTableHeader();
        header.setForeground(Color.WHITE);
        header.setBackground(Color.GRAY);
        table.setTableHeader(header);
        JScrollPane scrollPane = new JScrollPane(table);
        mainFrame.add(scrollPane);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Sort Doctors Alphabetically: "));
        radAscending.addActionListener(this);
        radDescending.addActionListener(this);
        radAscending.setSelected(true);
        controlPanel.add(radAscending);
        controlPanel.add(radDescending);
        mainFrame.add(controlPanel);
    }

    public void closeGui() {
        mainFrame.dispose();
    }

    public void openGui() {
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedIndex = table.getSelectedRow();
        Doctor selectedDoctor = null;
        if (selectedIndex >= 0) {
            selectedDoctor = db.doctorList.get(selectedIndex);
        }
        if (e.getActionCommand().equals("Ascending")) {
            radAscending.setSelected(true);
            radDescending.setSelected(false);
            sort(db.doctorList);
        } else {
            radDescending.setSelected(true);
            radAscending.setSelected(false);
            db.doctorList.sort(reverseOrder());
        }
        if (db.doctorList.contains(selectedDoctor)) {
            table.setRowSelectionInterval(db.doctorList.indexOf(selectedDoctor), db.doctorList.indexOf(selectedDoctor));
        }
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }
}
