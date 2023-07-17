package uk.ac.westminster;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

public class GuiConsultations implements ActionListener {

    private JFrame frame = new JFrame("Consultations");
    private JTable table = new JTable();
    private DataBank db;

    private JPanel panelSelectPatient = new JPanel();
    private JPanel panelAddPatient = new JPanel(new GridLayout(5, 2));
    private JRadioButton radAddPatient, radSelectPatient;

    JTextField txtPatientID;
    JTextField txtFirstName;
    JTextField txtLastName;
    JTextField txtMobile;
    JTextField txtConsultTime;
    JTextField txtDuration;

    JDatePickerImpl datePicker;
    JDatePickerImpl consultDate;

    JComboBox<Patient> cmbPatient;
    JComboBox<Doctor> cmbDoctor;

    GuiConsultations(DataBank db) {
        this.db = db;
        ConsultationTableModel model = new ConsultationTableModel(this.db.consultationList);
        table.setModel(model);
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1, 5, 5));

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = table.getTableHeader();
        header.setForeground(Color.WHITE);
        header.setBackground(Color.GRAY);
        table.setTableHeader(header);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel controls = new JPanel();

        JLabel lblName = new JLabel("Select Doctor");
        cmbDoctor = new JComboBox<>();
        this.db.doctorList.forEach(doctor -> cmbDoctor.addItem(doctor));
        controls.add(lblName);
        controls.add(cmbDoctor);

        radAddPatient = new JRadioButton("Add New Patient");
        radSelectPatient = new JRadioButton("Select a Patient");

        radAddPatient.addActionListener(this);
        radSelectPatient.addActionListener(this);
        radSelectPatient.setSelected(true);

        controls.add(radAddPatient);
        controls.add(radSelectPatient);

        JLabel lblSelectPatient = new JLabel("Select a Patient");
        cmbPatient = new JComboBox<>();
        this.db.patientList.forEach(patient -> cmbPatient.addItem(patient));

        panelSelectPatient.add(lblSelectPatient);
        panelSelectPatient.add(cmbPatient);

        controls.add(panelSelectPatient);

        JLabel lblPatientID = new JLabel("Patient ID");
        JLabel lblFirstName = new JLabel("First Name");
        JLabel lblLastName = new JLabel("Last Name");
        JLabel lblDateOfBirth = new JLabel("Date of Birth");
        JLabel lblMobile = new JLabel("Mobile");
        txtPatientID = new JTextField(3);
        txtFirstName = new JTextField(10);
        txtLastName = new JTextField(10);
        txtMobile = new JTextField(10);
        UtilDateModel dateModel = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
        datePicker = new JDatePickerImpl(datePanel);
        txtPatientID.setEnabled(false);
        txtPatientID.setText(String.format("%d", this.db.patientList.size() + 1));

        panelAddPatient.add(lblPatientID);
        panelAddPatient.add(txtPatientID);
        panelAddPatient.add(lblFirstName);
        panelAddPatient.add(txtFirstName);
        panelAddPatient.add(lblLastName);
        panelAddPatient.add(txtLastName);
        panelAddPatient.add(lblDateOfBirth);
        panelAddPatient.add(datePicker);
        panelAddPatient.add(lblMobile);
        panelAddPatient.add(txtMobile);

        JLabel lblConsultationTime = new JLabel("Consultation Time");
        JLabel lblConsultationDate = new JLabel("Consultation Date");
        JLabel lblDuration = new JLabel("Duration (hrs)");

        UtilDateModel consultationDateModel = new UtilDateModel();
        JDatePanelImpl consultationDatePanel = new JDatePanelImpl(consultationDateModel);
        consultDate = new JDatePickerImpl(consultationDatePanel);
        txtConsultTime = new JTextField(10);
        txtDuration = new JTextField(2);

        panelAddPatient.add(lblConsultationDate);
        panelAddPatient.add(consultDate);
        panelAddPatient.add(lblConsultationTime);
        panelAddPatient.add(txtConsultTime);
        panelAddPatient.add(lblDuration);
        panelAddPatient.add(txtDuration);

        JButton addConsultation = new JButton("Add Consultation");
        addConsultation.addActionListener(this);

        panelAddPatient.add(addConsultation);

        controls.add(panelAddPatient);

        frame.add(scrollPane);
        frame.add(controls);
    }

    public void close() {
        frame.dispose();
    }

    public void open() {
        frame.setVisible(true);
    }

    public void dispose() {
        frame.dispose();
    }

    public void save() {

    }

    public void refresh() {
        SwingUtilities.updateComponentTreeUI(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add New Patient")) {
            radAddPatient.setSelected(true);
            radSelectPatient.setSelected(false);
        } else if (e.getActionCommand().equals("Select a Patient")) {
            radAddPatient.setSelected(false);
            radSelectPatient.setSelected(true);
        } else if (e.getActionCommand().equals("Add Consultation")) {
            Patient patient = new Patient(Integer.parseInt(txtPatientID.getText()), txtFirstName.getText(), txtLastName.getText(), LocalDate.of(datePicker.getModel().getYear(), datePicker.getModel().getMonth(), datePicker.getModel().getDay()), txtMobile.getText());
            db.patientList.add(patient);
            cmbPatient.removeAllItems();
            this.db.patientList.forEach(patient1 -> cmbPatient.addItem(patient1));

            AtomicReference<Double> costProvided = new AtomicReference<>(25.0);

            db.patientList.forEach(patient1 -> {
                if (patient1.getName().equals(txtFirstName.getText()) && patient1.getSurName().equals(txtLastName.getText())) {
                    costProvided.set(15.0);
                }
            });

            Consultation consultation = new Consultation((Doctor) cmbDoctor.getSelectedItem(), patient, LocalDate.of(consultDate.getModel().getYear(), consultDate.getModel().getMonth(), consultDate.getModel().getDay()), txtConsultTime.getText(), costProvided.get(), Double.parseDouble(txtDuration.getText()), "");
            this.db.consultationList.add(consultation);

            System.out.println(consultation.getCost());

            save();
        }
        refresh();
    }
}
