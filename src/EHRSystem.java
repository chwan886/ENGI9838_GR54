import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class EHRSystem extends JFrame {

    private DefaultTableModel tableModel;

    // Constructor to set up the GUI components
    public EHRSystem() {
        createUI();
    }

    private void createUI() {
        setTitle("Electronic Health Record System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Dashboard/Home Screen
        JPanel dashboardPanel = new JPanel(new BorderLayout());

        // Top bar for alerts and tasks
        JPanel topBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel alertsLabel = new JLabel("Alerts");
        JLabel tasksLabel = new JLabel("Tasks");
        topBarPanel.add(alertsLabel);
        topBarPanel.add(tasksLabel);

        dashboardPanel.add(topBarPanel, BorderLayout.NORTH);

        // Search Functionality
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        dashboardPanel.add(searchPanel, BorderLayout.SOUTH);

        // Centralized Patient Data
        JPanel patientDataPanel = new JPanel(new BorderLayout());
        patientDataPanel.setBorder(BorderFactory.createTitledBorder("Patient Cases"));

        // Table to display patient cases
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Case ID");
        tableModel.addColumn("Patient Name");
        tableModel.addColumn("Diagnosis");
        tableModel.addColumn("Prescription");

        JTable casesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(casesTable);
        patientDataPanel.add(scrollPane, BorderLayout.CENTER);

        // Add all panels to the main frame
        add(dashboardPanel, BorderLayout.NORTH);
        add(patientDataPanel, BorderLayout.CENTER);

        // Insert Case Form
        JPanel insertPanel = new JPanel(new GridLayout(0, 2));
        insertPanel.setBorder(BorderFactory.createTitledBorder("Insert New Case"));

        JLabel nameLabel = new JLabel("Patient Name:");
        JTextField nameField = new JTextField();
        JLabel diagnosisLabel = new JLabel("Diagnosis:");
        JTextField diagnosisField = new JTextField();
        JLabel prescriptionLabel = new JLabel("Prescription:");
        JTextField prescriptionField = new JTextField();

        JButton insertButton = new JButton("Insert Case");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = nameField.getText();
                String diagnosis = diagnosisField.getText();
                String prescription = prescriptionField.getText();

                // Here you would typically insert the data into a database or a data structure
                // For demonstration purposes, we'll just add it directly to the table model
                tableModel.addRow(new Object[] { tableModel.getRowCount() + 1, patientName, diagnosis, prescription });

                // Clear the input fields after insertion
                nameField.setText("");
                diagnosisField.setText("");
                prescriptionField.setText("");
            }
        });

        insertPanel.add(nameLabel);
        insertPanel.add(nameField);
        insertPanel.add(diagnosisLabel);
        insertPanel.add(diagnosisField);
        insertPanel.add(prescriptionLabel);
        insertPanel.add(prescriptionField);
        insertPanel.add(insertButton);

        // Add the insert panel below the patient data panel
        add(insertPanel, BorderLayout.SOUTH);

        // Other UI elements would be created here in a similar manner...
    }
}