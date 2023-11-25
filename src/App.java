import gui.MainFrame;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { // program start
                new MainFrame(); // create mainFrame
            }
        });
        }
    }
