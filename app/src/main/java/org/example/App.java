package org.example;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginSystem frame = new LoginSystem();
                frame.setVisible(true);
                // EHRSystem ex = new EHRSystem();
                // ex.setVisible(true);
            }
        });
    }
}
