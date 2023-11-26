package gui;

import config.Config;
import gui.login.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // PANELS
    private TitlePanel titlePanel; // title panel
    private JPanel containerPanel; // main container panel, contains: login panel,
    private LoginPanel loginPanel; // login panel which contains both the sign in and register screens

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

        loginPanel = new LoginPanel();

        containerPanel = new JPanel(new CardLayout()); // container panel setup with cardLayout
        containerPanel.add(loginPanel, "LOGIN_PANEL");
        // TODO: add home panel

        CardLayout cl = (CardLayout) containerPanel.getLayout();
        cl.show(containerPanel, "LOGIN_PANEL");


        // add components to MainFrame
        add(titlePanel, BorderLayout.NORTH); // add title panel to NORTH position
        add(containerPanel, BorderLayout.CENTER); // add container panel to CENTER position


    }
}
