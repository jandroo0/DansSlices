import com.formdev.flatlaf.FlatIntelliJLaf;
import gui.MainFrame;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { // program start
                new MainFrame(); // create mainFrame
            }
        });
        }
    }
