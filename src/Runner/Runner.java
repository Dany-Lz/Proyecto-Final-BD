package Runner;

import GUI.Login;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Runner {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("Error configurando Look and Feel: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
