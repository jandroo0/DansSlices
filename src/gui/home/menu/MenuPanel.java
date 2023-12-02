package gui.home.menu;

import customComponents.CustomLabel;
import customComponents.CustomPanel;
import model.Customer;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MenuPanel extends CustomPanel {

    // PANELS
    private final CustomPanel titlePanel; // title panel
    private final JPanel containerPanel; // container panel
    private final MenuNavPanel buttonsPanel; // nav buttons panel

    // COMPONENTS
    private final CustomLabel titleLabel; // title label
    private final OrderViewPanel orderViewPanel; // cart view panel
    LinkedList<String> categories; // categories of menu items list
    private LinkedList<MenuCategoryPanel> categoryPanels; // list of menu category panels
    private String currentMenuName; // category name of the current menu

    private Customer currentCustomer; // current customer

    private MenuListener menuListener;

    // Constructor
    public MenuPanel() {

        currentCustomer = null;

        // title
        titlePanel = new CustomPanel();
        titleLabel = new CustomLabel("MENU", 30);

        categories = new LinkedList<String>();

        // container panel
        containerPanel = new JPanel(new CardLayout());

        // category panels
        categoryPanels = new LinkedList<MenuCategoryPanel>();

        // nav buttons
        buttonsPanel = new MenuNavPanel();

        // order view panel
        orderViewPanel = new OrderViewPanel();

        // Set the listener for navigation buttons
        buttonsPanel.setMenuNavListener(new MenuNavListener() {
            @Override
            public void navbarClicked(String buttonName) {
                setCurrentMenuPanel(buttonName);
            }
        });

        // Initialize and set up the GUI components
        layoutComponents();
        styling();
    }

    // Method to set the current customer
    public void setCustomer(Customer customer) {
        currentCustomer = customer;
        orderViewPanel.setCustomer(customer);
    }

    // Method to set the menu listener
    public void setMenuListener(MenuListener listener) {
        orderViewPanel.setMenuListener(listener);
    }

    // Method to set up the layout of components
    private void layoutComponents() {
        setLayout(new BorderLayout());

        // title panel
        add(titlePanel, BorderLayout.NORTH);

        // title label
        titlePanel.add(titleLabel);

        // buttonsPanel
        add(buttonsPanel, BorderLayout.WEST);
        add(containerPanel, BorderLayout.CENTER);
        add(orderViewPanel, BorderLayout.SOUTH);
    }

    // Method for styling (can be filled with styling configurations)
    private void styling() {
        // Add styling configurations if needed
    }

    // Method to set the menu items
    public void setMenu(LinkedList<model.MenuItem> items) {
        // Iterate through menu items and update the UI
        for (model.MenuItem item : items) {
            if (!categories.contains(item.getCategory())) {
                // If the category is not in the list, add it and update UI
                categories.add(item.getCategory());
                MenuCategoryPanel panel = new MenuCategoryPanel(item.getCategory());
                categoryPanels.add(panel);
                containerPanel.add(panel, item.getCategory());
                buttonsPanel.addButton(item.getCategory());
            }
        }

        // Update each category panel with corresponding items
        for (MenuCategoryPanel categoryPanel : categoryPanels) {
            categoryPanel.clearList();
            for (model.MenuItem item : items) {
                if (categoryPanel.getCategory().equalsIgnoreCase(item.getCategory())) {
                    categoryPanel.addItem(item);
                }
            }

            // Set the listener for each category panel to add items to orderViewPanel
            categoryPanel.setAddToCartListener(new AddToCartListener() {
                @Override
                public void itemAdded(MenuItem item) {
                    orderViewPanel.addItem(item);
                }
            });
        }
    }

    // Method to switch to the specified menu panel
    public void setCurrentMenuPanel(String panelName) {
        CardLayout cl = (CardLayout) containerPanel.getLayout();
        cl.show(containerPanel, panelName);
        currentMenuName = panelName;
        buttonsPanel.setButtonHoverEffect(panelName);
        buttonsPanel.setButtonColors(panelName);
    }
}
