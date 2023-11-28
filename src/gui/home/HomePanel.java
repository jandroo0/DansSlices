package gui.home;

import config.Config;
import customComponents.CustomButton;
import customComponents.CustomPanel;
import model.Customer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class HomePanel extends CustomPanel {

    private final CustomButton menuButton; // button for menu
    private final CustomButton orderHistoryButton; // button for orderHistory
    private final CustomButton accountButton; // button for account details


    private final JPanel containerPanel; // container for buttonPanel, menuPanel, and other panels


    private final CustomPanel buttonPanel; // buttonPanel to display navigation buttons
//    private final MenuPanel menuPanel; // add the menuPanel

    // TODO:
//    private AccountPanel accountPanel;
//    private OrderHistoryPanel orderHistoryPanel;
    private Customer currentCustomer; // current customer

//    private HomeListener homeListener;

    public HomePanel() {

        currentCustomer = null;

        // home panel buttons

        int buttonSize = 32; // button size

        menuButton = new CustomButton("MENU", Config.getTextFont(buttonSize), Config.getTextColor(), Config.getButtonBackgroundColor(),
                Config.getButtonHoverColor(), Config.getButtonBorder());
        orderHistoryButton = new CustomButton("ORDERS", Config.getTextFont(buttonSize), Config.getTextColor(), Config.getButtonBackgroundColor(),
                Config.getButtonHoverColor(), Config.getButtonBorder());
        accountButton = new CustomButton("ACCOUNT", Config.getTextFont(buttonSize), Config.getTextColor(), Config.getButtonBackgroundColor(),
                Config.getButtonHoverColor(), Config.getButtonBorder());

        //panels
        buttonPanel = new CustomPanel(); // container buttonPanel for buttons

//        menuPanel = new MenuPanel(); // menu panel

        containerPanel = new JPanel(new CardLayout()); // create card layout in container panel
        containerPanel.add(buttonPanel, "BUTTON_PANEL"); // add buttonPanel to container panel
//        containerPanel.add(menuPanel, "MENU_PANEL");

        CardLayout cl = (CardLayout) containerPanel.getLayout();
        cl.show(containerPanel, "BUTTON_PANEL"); // show the default home/button panel which contains the nav buttons i.e (menu, etc


        layoutComponents();
        setLayout(new BorderLayout());
        add(containerPanel, BorderLayout.CENTER);
    }

    public void setCustomer(Customer customer) {
        currentCustomer = customer;
    }


    private void layoutComponents() {

        // employee homePanel
        Border border = BorderFactory.createEmptyBorder(0, 0, 100, 0);
        buttonPanel.setBorder(border);
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 20, 0);
        buttonPanel.add(menuButton, gc);
        gc.gridy++;
        buttonPanel.add(orderHistoryButton, gc);
        gc.gridy++;
        buttonPanel.add(accountButton, gc);
    }
}
