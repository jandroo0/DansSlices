package gui;

import config.Config;
import controller.Controller;
import gui.login.LoginListener;
import gui.login.LoginPanel;
import gui.login.RegisterPanel;
import model.Customer;

import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // PANELS
    private TitlePanel titlePanel; // title panel
    private JPanel containerPanel; // main container panel, contains: login panel,
    private LoginPanel loginPanel; // login panel which contains both the sign in and register screens


    // "DATABASE" CONTROLLER
    private Controller controller;

    public MainFrame() {
        super("DANS SLICES");
        controller = new Controller();

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

        handleEventListeners();


    }

    private void handleEventListeners() {
        loginPanel.setLoginListener(new LoginListener() {
            @Override
            public void accountCreatedEvent(Customer newCustomer) {
                if(!MainFrame.this.controller.existingCustomer(newCustomer.getPhoneNumber())) { // if there is no existing customer with the same phone number
                    try {
                        MainFrame.this.controller.addCustomer(newCustomer); // add customer to customers list
//                        MainFrame.this.controller.saveCustomers(); // save new list to -> stored customers list

                        if(!newCustomer.getPayments().isEmpty()) {
                            MainFrame.this.controller.savePayments();
                            System.out.println("CUSTOMER CREATED: " + newCustomer.getID() + ":" + newCustomer.getFirstName() + " PAYMENT METHOD SAVED: " + newCustomer.getPayments().getFirst());
                        } else {
                            System.out.println("CUSTOMER CREATED: " + newCustomer.getID() + ":" + newCustomer.getFirstName());
                        }

                        JOptionPane.showMessageDialog(MainFrame.this, "Account Registered!", "New Account Registered", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
                        e.printStackTrace();

                        //TODO: PROMPT
                    }
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this,  "\"" + newCustomer.getPhoneNumber() + "\" is already in use.","Phone # in use", JOptionPane.ERROR_MESSAGE);

                    CardLayout cl = (CardLayout) loginPanel.getContainerPanel().getLayout();
                    cl.show(loginPanel.getContainerPanel(), "SIGN_IN_PANEL");
                }


            }

            @Override
            public void loginEvent() {

            }
        });
    }
}
