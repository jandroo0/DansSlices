package gui;

import config.Config;
import controller.Controller;
import gui.home.HomePanel;
import gui.home.menu.MenuListener;
import gui.login.LoginListener;
import gui.login.LoginPanel;
import model.Customer;
import model.Order;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // PANELS
    private TitlePanel titlePanel; // title panel
    private JPanel containerPanel; // main container panel, contains: login panel,
    private LoginPanel loginPanel; // login panel which contains both the sign in and register screens
    private HomePanel homePanel; // home panel which contains: menuPanel,

    private Controller controller; // "DATABASE" CONTROLLER

    public MainFrame() {
        super("DANS SLICES"); // set window title

        controller = new Controller(); // create controller, loads all data, controls database interaction

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

        // login panel(contains sign in and register panels)
        loginPanel = new LoginPanel();

        // home panel
        homePanel = new HomePanel();
        homePanel.setMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getPrebuiltPizzas()); // set the menu items

        containerPanel = new JPanel(new CardLayout()); // main container panel setup with cardLayout
        containerPanel.add(loginPanel, "LOGIN_PANEL"); // add login panel to the main container panel
        containerPanel.add(homePanel, "HOME_PANEL"); // add home panel to the main container panel

        CardLayout cl = (CardLayout) containerPanel.getLayout(); // get cardLayout from main container panel
        cl.show(containerPanel, "LOGIN_PANEL"); // show login panel on default

        // add components to MainFrame
        add(titlePanel, BorderLayout.NORTH); // add title panel to NORTH position
        add(containerPanel, BorderLayout.CENTER); // add container panel to CENTER position

        handleEventListeners();
    }

    private void handleEventListeners() {
        loginPanel.setLoginListener(new LoginListener() { // login panel listener
            @Override
            public void accountCreatedEvent(Customer newCustomer) {

                if (!MainFrame.this.controller.existingCustomer(newCustomer.getPhoneNumber())) { // if there is no existing customer with the same phone number
                    MainFrame.this.controller.addCustomer(newCustomer); // add customer to customers list
                    System.out.println("CUSTOMER CREATED: " + newCustomer.getID() + ":" + newCustomer.getFirstName());

                    if (!newCustomer.getPayments().isEmpty()) { // if there are payments
                        MainFrame.this.controller.savePayments(); // save payments
                        System.out.println("PAYMENT METHOD SAVED: " + newCustomer.getPayments().getFirst());
                    }

                    JOptionPane.showMessageDialog(MainFrame.this, "Account Registered!", "New Account Registered", JOptionPane.ERROR_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "\"" + newCustomer.getPhoneNumber() + "\" is already in use.", "Phone # in use", JOptionPane.ERROR_MESSAGE);

                    CardLayout cl = (CardLayout) loginPanel.getContainerPanel().getLayout();
                    cl.show(loginPanel.getContainerPanel(), "SIGN_IN_PANEL");
                }
            }

            @Override
            public void loginEvent(String phoneNumber, String password) { // login event
                if (MainFrame.this.controller.customerLogin(phoneNumber, password) != null) { // if the returned customer is not null
                    Customer customer = MainFrame.this.controller.customerLogin(phoneNumber, password); // set customer to logged in customer

                    CardLayout cl = (CardLayout) containerPanel.getLayout(); // get the cardLayout from the main container
                    cl.show(containerPanel, "HOME_PANEL"); // switch to the homePanel containing the menu,

                    homePanel.setCustomer(customer); // set the current customer
                    System.out.println("CUSTOMER : " + customer.getID() + " : " + customer.getFirstName() + " | LOGGED IN"); // print log in messsage

                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "No account linked to phone number / Password is incorrect",
                            "Account not found", JOptionPane.ERROR_MESSAGE); // if account not found show error message
                }
            }
        });

        this.homePanel.setMenuListener(new MenuListener() { // handles the employee new order panel events
            @Override
            public void newOrderEvent(Order newOrder) {
//                MainFrame.this.controller.createOrder(newOrder); // add the order to the database
//                MainFrame.this.controller.saveOrders(); // save the orders to the database
//                homePanel.setNewOrderMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getPrebuiltPizzas()); // set the menu items
            }
        });

    }
}
