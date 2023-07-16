package uk.ac.westminster;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GuiMain implements ActionListener {
    private final JFrame mainFrame = new JFrame("WestMinister Skin Consultation");
    private DataBank db = null;
    private JTable table = null;
    private boolean sortAscending = true;
    private JRadioButton radAscending = new JRadioButton("Ascending");
    private JRadioButton radDescending = new JRadioButton("Descending");
    private List<Doctor> doctorList;
    private DoctorTableModel tableModel;

    GuiMain(DataBank db) {
        this.db = db;
        this.doctorList = db.doctorList;
        Collections.sort(this.doctorList);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new GridLayout(2, 1, 5, 5));
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableModel = new DoctorTableModel(this.doctorList);
        table = new JTable(tableModel);
        JTableHeader header = table.getTableHeader();
        header.setForeground(Color.WHITE);
        header.setBackground(Color.GRAY);
        table.setTableHeader(header);
        JScrollPane scrollPane = new JScrollPane(table);
        mainFrame.add(scrollPane);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Sort Doctors Alphabetically"));
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
        if (e.getActionCommand() == "Ascending") {
            radAscending.setSelected(true);
            radDescending.setSelected(false);
            Collections.sort(this.doctorList);
        } else {
            radDescending.setSelected(true);
            radAscending.setSelected(false);
            Collections.sort(this.doctorList, Collections.reverseOrder());
        }
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }
}
