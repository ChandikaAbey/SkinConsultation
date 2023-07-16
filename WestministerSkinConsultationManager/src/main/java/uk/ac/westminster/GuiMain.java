package uk.ac.westminster;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class GuiMain extends JFrame {
    private final JFrame mainFrame = new JFrame("WestMinister Skin Consultation");
    private DataBank db = null;
    private JTable table = null;

    GuiMain(DataBank db) {
        this.db = db;
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DoctorTableModel tableModel = new DoctorTableModel(db.doctorList);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainFrame.add(scrollPane);
    }

    public void closeGui() {
        mainFrame.dispose();
    }

    public void openGui() {
        mainFrame.setVisible(true);
    }


}
