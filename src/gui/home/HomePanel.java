package gui.home;

import config.Config;
import customComponents.CustomButton;
import customComponents.CustomPanel;
import gui.home.menu.HomeListener;
import gui.home.menu.MenuListener;
import gui.home.menu.MenuPanel;
import model.Customer;
import model.MenuItem;
import model.Order;
import model.PrebuiltPizza;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class HomePanel extends CustomPanel {

    // Navigation buttons
    private final CustomButton menuButton;
    private final CustomButton orderHistoryButton;
    private final CustomButton accountButton;

    // Panels
    private final JPanel containerPanel;
    private final CustomPanel buttonPanel;
    private final MenuPanel menuPanel;
    private final CheckoutPanel checkoutPanel;

    private Customer currentCustomer; // Current customer
    private HomeListener homeListener;

    public HomePanel() {
        currentCustomer = null;

        // Initialize navigation buttons
        int buttonSize = 32;
        menuButton = new CustomButton("MENU", Config.getTextFont(buttonSize), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(), Config.getButtonBorder());
        orderHistoryButton = new CustomButton("ORDERS", Config.getTextFont(buttonSize), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(), Config.getButtonBorder());
        accountButton = new CustomButton("ACCOUNT", Config.getTextFont(buttonSize), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(), Config.getButtonBorder());

        // Initialize panels
        buttonPanel = new CustomPanel();
        menuPanel = new MenuPanel();
        checkoutPanel = new CheckoutPanel();
        containerPanel = new JPanel(new CardLayout());
        containerPanel.add(buttonPanel, "BUTTON_PANEL");
        containerPanel.add(menuPanel, "MENU_PANEL");
        containerPanel.add(checkoutPanel, "CHECKOUT_PANEL");

        // Set default panel to show (BUTTON_PANEL)
        CardLayout cl = (CardLayout) containerPanel.getLayout();
        cl.show(containerPanel, "BUTTON_PANEL");

        // Layout components and set up the initial view
        layoutComponents();
        setLayout(new BorderLayout());
        add(containerPanel, BorderLayout.CENTER);

        // Set up event handling
        handleEvents();
    }

    private void handleEvents() {
        // MenuButton event listener
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle menu button click event
                CardLayout cl = (CardLayout) containerPanel.getLayout();
                cl.show(containerPanel, "MENU_PANEL");
            }
        });

        // TODO: Add event listeners for other buttons as needed
    }

    // Set the current customer for the panel
    public void setCustomer(Customer customer) {
        currentCustomer = customer;

        // Set the customer for menu and checkout panels
        menuPanel.setCustomer(customer);
        checkoutPanel.setCustomer(customer);
    }

    // Set up the checkout panel with an order
    public void setCheckoutPanel(Order newOrder) {
        checkoutPanel.setOrder(newOrder);
        checkoutPanel.setPaymentOptions();
    }

    // Set menu items for the menu panel
    public void setMenuItems(LinkedList<MenuItem> items, LinkedList<PrebuiltPizza> pizzas) {
        LinkedList<MenuItem> menu = new LinkedList<>();
        menu.addAll(items);
        menu.addAll(pizzas);

        menuPanel.setMenu(menu);
    }

    // Set the home listener for handling home panel events
    public void setHomeListener(HomeListener listener) {
        this.homeListener = listener;
    }

    // Set the menu listener for handling menu panel events
    public void setMenuListener(MenuListener listener) {
        menuPanel.setMenuListener(listener);
    }

    // Getters for panels
    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }

    // Layout components in the button panel
    private void layoutComponents() {
        // Layout for the button panel
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
