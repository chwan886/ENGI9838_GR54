package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class NewEHRSystem extends JFrame {
    private DefaultTableModel tableModel;
    private int caseIdCounter = 1; // 用于跟踪CaseID的计数器

    public NewEHRSystem() {
        createUI();
    }

    private void createUI() {
        setTitle("Electronic Health Record System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Centralized Patient Data
        JPanel patientDataPanel = new JPanel(new BorderLayout());
        patientDataPanel.setBorder(BorderFactory.createTitledBorder("Patient Cases"));

        // Column names including Case ID
        String[] columnNames = {"Case ID", "Name", "Gender", "Age", "Birth Date", "Address", "Contact", "Diagnosis", "Medical History"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the Case ID column non-editable
                return column != 0;
            }
        };
        JTable casesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(casesTable);
        patientDataPanel.add(scrollPane, BorderLayout.CENTER);
        add(patientDataPanel, BorderLayout.CENTER);

        // Insert Case Button
        JButton insertButton = new JButton("Insert Case");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionForm prescriptionForm = new PrescriptionForm(NewEHRSystem.this);
                prescriptionForm.setVisible(true);
            }
        });

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.add(insertButton);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public void addNewCase(Object[] caseData) {
        // Add the case ID as the first element of the array
        Object[] rowData = new Object[caseData.length + 1];
        rowData[0] = caseIdCounter++; // Assign the next case ID
        System.arraycopy(caseData, 0, rowData, 1, caseData.length);
        tableModel.addRow(rowData);
    }
    
}
