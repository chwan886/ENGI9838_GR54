package org.example;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EHRSystem ex = new EHRSystem();
                ex.setVisible(true);
            }
        });
    }
}
