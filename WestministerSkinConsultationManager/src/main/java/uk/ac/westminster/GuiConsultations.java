package uk.ac.westminster;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiConsultations implements ActionListener {

    private JFrame frame = new JFrame();
    private JTable table = new JTable();
    private DataBank db;

    GuiConsultations(DataBank db) {
        this.db = db;

    }

    public void close() {
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
