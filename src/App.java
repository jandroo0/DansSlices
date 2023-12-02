// Import the FlatLaf IntelliJ Look and Feel library
import com.formdev.flatlaf.FlatIntelliJLaf;

// Import the MainFrame class from the gui package
import gui.MainFrame;

// Import classes from the javax.swing package
import javax.swing.*;

// Main class for the application
public class App {

    // Main method, entry point for the application
    public static void main(String[] args) {

        // Try to set the Look and Feel of the UI to FlatLaf's IntelliJ Look and Feel
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace if an exception occurs while setting the Look and Feel
        }

        // Run the GUI-related code on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame(); // Create an instance of the MainFrame class when the EDT is ready
            }
        });
    }
}
