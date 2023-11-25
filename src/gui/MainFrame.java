package gui;

import config.Config;
import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("DANS SLICES");

        setSize(Config.getWIDTH(), Config.getHEIGHT()); // set frame size
        setResizable(false); // set resizable true
        setLocationRelativeTo(null); // centers window to screen when opened
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes when clicking 'X'
        setVisible(true); // set window visible

    }
}
