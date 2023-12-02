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
    private TitlePanel titlePanel; // Title panel at the top of the window
    private JPanel containerPanel; // Main container panel holding login and home panels
    private LoginPanel loginPanel; // Panel containing sign in and register screens
    private HomePanel homePanel; // Panel displaying the home screen with menu

    private Controller controller; // Controller for database interaction

    public MainFrame() {
        super("DANS SLICES"); // Set window title

        controller = new Controller(); // Create controller, loads data, and manages database interaction

        // Window settings
        setSize(Config.getWIDTH(), Config.getHEIGHT()); // Set frame size
        setResizable(false); // Make the window non-resizable
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when clicking 'X'
        setVisible(true); // Make the window visible

        setLayout(new BorderLayout()); // Set window layout to a border layout

        // COMPONENTS

        // Title panel
        titlePanel = new TitlePanel();

        // Login panel (contains sign in and register panels)
        loginPanel = new LoginPanel();

        // Home panel
        homePanel = new HomePanel();
        homePanel.setMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getPrebuiltPizzas()); // Set the menu items

        containerPanel = new JPanel(new CardLayout()); // Main container panel setup with CardLayout
        containerPanel.add(loginPanel, "LOGIN_PANEL"); // Add login panel to the main container panel
        containerPanel.add(homePanel, "HOME_PANEL"); // Add home panel to the main container panel

        CardLayout cl = (CardLayout) containerPanel.getLayout(); // Get CardLayout from the main container panel
        cl.show(containerPanel, "LOGIN_PANEL"); // Show the login panel by default

        // Add components to MainFrame
        add(titlePanel, BorderLayout.NORTH); // Add title panel to NORTH position
        add(containerPanel, BorderLayout.CENTER); // Add container panel to CENTER position

        handleEventListeners(); // Set up event listeners
    }

    // Set up event listeners for login and menu actions
    private void handleEventListeners() {
        loginPanel.setLoginListener(new LoginListener() { // Login panel listener
            @Override
            public void accountCreatedEvent(Customer newCustomer) {
                // Handle new customer account creation
                if (!MainFrame.this.controller.existingCustomer(newCustomer.getPhoneNumber())) {
                    MainFrame.this.controller.addCustomer(newCustomer);
                    System.out.println("CUSTOMER CREATED: " + newCustomer.getID() + ":" + newCustomer.getFirstName());

                    if (!newCustomer.getPayments().isEmpty()) {
                        MainFrame.this.controller.savePayments();
                        System.out.println("PAYMENT METHOD SAVED: " + newCustomer.getPayments().getFirst());
                    }

                    JOptionPane.showMessageDialog(MainFrame.this, "Account Registered!", "New Account Registered", JOptionPane.ERROR_MESSAGE);

                } else {
                    // Display error if phone number is already in use
                    JOptionPane.showMessageDialog(MainFrame.this, "\"" + newCustomer.getPhoneNumber() + "\" is already in use.", "Phone # in use", JOptionPane.ERROR_MESSAGE);

                    CardLayout cl = (CardLayout) loginPanel.getContainerPanel().getLayout();
                    cl.show(loginPanel.getContainerPanel(), "SIGN_IN_PANEL");
                }
            }

            @Override
            public void loginEvent(String phoneNumber, String password) {
                // Handle customer login
                if (MainFrame.this.controller.customerLogin(phoneNumber, password) != null) {
                    Customer customer = MainFrame.this.controller.customerLogin(phoneNumber, password);

                    CardLayout cl = (CardLayout) containerPanel.getLayout();
                    cl.show(containerPanel, "HOME_PANEL");

                    homePanel.setCustomer(customer);
                    System.out.println("CUSTOMER : " + customer.getID() + " : " + customer.getFirstName() + " | LOGGED IN");

                } else {
                    // Display error if login fails
                    JOptionPane.showMessageDialog(MainFrame.this, "No account linked to phone number / Password is incorrect",
                            "Account not found", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.homePanel.setMenuListener(new MenuListener() { // Menu panel listener
            @Override
            public void newOrderEvent(Order newOrder) {
                // Handle new order creation
                MainFrame.this.controller.createOrder(newOrder);
                MainFrame.this.controller.saveOrders();
            }

            @Override
            public void cancelMenuEvent() {
                // Handle menu cancellation
                CardLayout cl = (CardLayout) homePanel.getContainerPanel().getLayout();
                cl.show(homePanel.getContainerPanel(), "BUTTON_PANEL");
            }
        });
    }
}
