package gui;

import config.Config;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private TitlePanel titlePanel;


    public MainFrame() {
        super("DANS SLICES");

        // window settings
        setSize(Config.getWIDTH(), Config.getHEIGHT()); // set frame size
        setResizable(false); // set resizable true
        setLocationRelativeTo(null); // centers window to screen when opened
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes when clicking 'X'
        setVisible(true); // set window visible


        setLayout(new BorderLayout()); // set window layout to a border layout

        // COMPONENTS

        // title panel
        titlePanel = new TitlePanel();








        // add components to MainFrame
        add(titlePanel, BorderLayout.NORTH); // add title panel to NORTH position


    }
}
