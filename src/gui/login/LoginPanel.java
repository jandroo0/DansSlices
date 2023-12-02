package gui.login;

import customComponents.CustomPanel;
import model.Customer;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends CustomPanel {

    // Panels
    private final RegisterPanel registerPanel;
    private final SignInPanel signInPanel;
    private final JPanel containerPanel;

    // Event listeners
    private LoginListener loginListener;

    public LoginPanel() {
        setLayout(new BorderLayout());

        // Initialize panels
        registerPanel = new RegisterPanel(); // Register panel
        signInPanel = new SignInPanel(); // Login panel
        containerPanel = new JPanel(new CardLayout()); // Container panel, set with card layout

        // Add panels to the container card layout
        containerPanel.add(signInPanel, "SIGN_IN_PANEL");
        containerPanel.add(registerPanel, "REGISTER_PANEL");

        CardLayout cl = (CardLayout) containerPanel.getLayout(); // Get the container card layout
        cl.show(containerPanel, "SIGN_IN_PANEL"); // Show sign-in panel by default

        add(containerPanel, BorderLayout.CENTER);

        // Event handling //

        // SIGN IN
        signInPanel.setSignInListener(new SignInListener() { // Set the listener for the "new customer" button in the sign-in panel
            @Override
            public void signInEvent(String phoneNumber, String password) {
                LoginPanel.this.loginListener.loginEvent(phoneNumber, password); // Pass phone number and password to mainframe loginEvent through loginListener
            }

            @Override
            public void newCustomerEvent() {
                cl.show(containerPanel, "REGISTER_PANEL"); // On click, show the register panel
            }
        });

        // REGISTER
        registerPanel.setCreateAccountListener(new CreateAccountListener() { // Set the listener for the "cancel" button in the register panel
            @Override
            public void createAccount(Customer newCustomer) {
                LoginPanel.this.loginListener.accountCreatedEvent(newCustomer);
                cl.show(containerPanel, "SIGN_IN_PANEL"); // On account creation, show the login panel
            }

            @Override
            public void cancelEvent() {
                cl.show(containerPanel, "SIGN_IN_PANEL"); // On click, show the sign-in panel
            }
        });
    }

    // Set the login listener for handling login panel events
    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    // Getter for the container panel
    public JPanel getContainerPanel() {
        return containerPanel;
    }
}
