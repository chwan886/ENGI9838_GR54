package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrescriptionForm extends JDialog {
    private JTextField nameField;
    private JComboBox<String> genderComboBox;
    private JTextField ageField;
    private JTextField birthDateField;
    private JTextField addressField;
    private JTextField contactField;
    private JTextField diagnosisField;
    private JTextField medicalHistoryField;

    public PrescriptionForm(NewEHRSystem ehrSystem) {
        setTitle("Prescription Form");
        setModal(true);
        setSize(400, 600);
        setLocationRelativeTo(null);

        // Use GridBagLayout for better control over layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Initialize form fields
        nameField = new JTextField(20);
        genderComboBox = new JComboBox<>(new String[] { "Male", "Female", "Other" });
        ageField = new JTextField(20);
        birthDateField = new JTextField(20);
        addressField = new JTextField(20);
        contactField = new JTextField(20);
        diagnosisField = new JTextField(20);
        medicalHistoryField = new JTextField(20);

        // Add components to form
        int row = 0;
        addField(gbc, "Name:", nameField, row++);
        addField(gbc, "Gender:", genderComboBox, row++);
        addField(gbc, "Age:", ageField, row++);
        addField(gbc, "Birth Date:", birthDateField, row++);
        addField(gbc, "Address:", addressField, row++);
        addField(gbc, "Contact:", contactField, row++);
        addField(gbc, "Diagnosis:", diagnosisField, row++);
        addField(gbc, "Medical History:", medicalHistoryField, row++);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect data from form fields and submit
                Object[] caseData = {
                    nameField.getText(),
                    genderComboBox.getSelectedItem().toString(),
                    ageField.getText(),
                    birthDateField.getText(),
                    addressField.getText(),
                    contactField.getText(),
                    diagnosisField.getText(),
                    medicalHistoryField.getText()
                };
                ehrSystem.addNewCase(caseData);

                // Close the form
                setVisible(false);
                dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);
    }

    private void addField(GridBagConstraints gbc, String label, Component field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        add(field, gbc);
    }
}
