package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {
    private JLabel errorLabel; 

    public RegistrationForm() {
        createUI();
    }

    private void createUI() {
        setTitle("Registration Form");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel userLabel = new JLabel("New Username:");
        JTextField userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("New Password:");
        JPasswordField passwordText = new JPasswordField(20);
        JLabel repeatPasswordLabel = new JLabel("Repeat Password:");
        JPasswordField repeatPasswordText = new JPasswordField(20);
        JButton registerButton = new JButton("Register");
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED); 

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordText.getPassword());
                String repeatPassword = new String(repeatPasswordText.getPassword());
                if (!password.equals(repeatPassword)) {
                    errorLabel.setText("Passwords do not match!");
                } else {
                    errorLabel.setText("");
                    setVisible(false);
                    dispose();
                    LoginSystem loginSystem = new LoginSystem();
                    loginSystem.setVisible(true);
                }
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(userLabel, gbc);

        gbc.gridx = 1;
        add(userText, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(passwordText, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(repeatPasswordLabel, gbc);

        gbc.gridx = 1;
        add(repeatPasswordText, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(registerButton, gbc);

        gbc.gridy = 4;
        add(errorLabel, gbc);
    }
}
