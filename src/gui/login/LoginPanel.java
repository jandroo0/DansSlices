package gui.login;


import customComponents.CustomPanel;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends CustomPanel {

    // PANELS
    private final RegisterPanel registerPanel;
    private final SignInPanel signInPanel;
    private final JPanel containerPanel;

    public LoginPanel() {
        setLayout(new BorderLayout());

        // PANELS
        registerPanel = new RegisterPanel(); // register panel
        signInPanel = new SignInPanel(); // login panel
        containerPanel = new JPanel(new CardLayout()); // container panel, set with card layout

        containerPanel.add(signInPanel, "SIGN_IN_PANEL"); // add sign in panel to container cardLayout
        containerPanel.add(registerPanel, "REGISTER_PANEL"); // add register panel to container cardLayout

        CardLayout cl = (CardLayout) containerPanel.getLayout(); // get the container cardLayout
        cl.show(containerPanel, "SIGN_IN_PANEL"); // on default show sign in panel

        add(containerPanel, BorderLayout.CENTER);



        // EVENTS //

        // SIGN IN
        signInPanel.setNewCustomerListener(new NewCustomerListener() { // set the listener for the new customer? button in the sign in panel
            @Override
            public void newCustomerEvent() {
                cl.show(containerPanel, "REGISTER_PANEL"); // on click show the register panel
            }
        });

        // REGISTER
        registerPanel.setCreateAccountListener(new CreateAccountListener() { // set the listener for the cancel button in the register panel
            @Override
            public void cancelEvent() {
                cl.show(containerPanel, "SIGN_IN_PANEL"); // on click show the sign in panel
            }
        });


    }

}
