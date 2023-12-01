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
    private final MenuNavPanel buttonsPanel;// nav buttons panel

    // COMPONENTS
    private final CustomLabel titleLabel; // title label
    private final OrderViewPanel orderViewPanel; // cart view panel
    LinkedList<String> categories; // categories of menu items list
    private LinkedList<MenuCategoryPanel> categoryPanels; // list of menu category panels
    private String currentMenuName; // category name of current menu

    private Customer currentCustomer; // current customer

    private MenuListener menuListener;


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


        buttonsPanel.setMenuNavListener(new MenuNavListener() {

            @Override
            public void navbarClicked(String buttonName) {
                setCurrentMenuPanel(buttonName);
            }
        });


        layoutComponents();
        styling();
    }

    public void setCustomer(Customer customer) {
        currentCustomer = customer;
        orderViewPanel.setCustomer(customer);
    }


    public void setMenuListener(MenuListener listener) {
        orderViewPanel.setMenuListener(listener);
    }

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

    private void styling() {

    }


    public void setMenu(LinkedList<model.MenuItem> items) {
        for (model.MenuItem item : items) {
            if (!categories.contains(item.getCategory())) {
                categories.add(item.getCategory()); // add category to categories list
                MenuCategoryPanel panel = new MenuCategoryPanel(item.getCategory()); // create new panel with category naame
                categoryPanels.add(panel); // add panel to category panels list
                containerPanel.add(panel, item.getCategory()); // add panel to container panel with card layout, with category name as ID
                buttonsPanel.addButton(item.getCategory()); // add button to nav buttons
            }
        }

        for (MenuCategoryPanel categoryPanel : categoryPanels) {
            categoryPanel.clearList();
            for (model.MenuItem item : items) {
                if (categoryPanel.getCategory().equalsIgnoreCase(item.getCategory())) {
                    categoryPanel.addItem(item);
                }
            }

            categoryPanel.setAddToCartListener(new AddToCartListener() { // set listener for each category panel to add items to orderViewPanel

                @Override
                public void itemAdded(MenuItem item) {
                    orderViewPanel.addItem(item);
                }
            });
        }
    }


    public void setCurrentMenuPanel(String panelName) {
        CardLayout cl = (CardLayout) containerPanel.getLayout(); // get current card/panel
        cl.show(containerPanel, panelName); // show new card/panel
        currentMenuName = panelName; // set current card/panel name
        buttonsPanel.setButtonHoverEffect(panelName); // set button hover effect for nav buttons
        buttonsPanel.setButtonColors(panelName); // set button colors for nav buttons
    }


}
