import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EHRSystem ex = new EHRSystem();
                ex.setVisible(true);
            }
        });
    }
}
