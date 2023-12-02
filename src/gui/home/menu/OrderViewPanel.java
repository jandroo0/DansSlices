package gui.home.menu;

import config.Config;
import customComponents.CustomButton;
import customComponents.CustomLabel;
import customComponents.CustomMenuListComponent;
import customComponents.CustomPanel;
import model.Customer;
import model.MenuItem;
import model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class OrderViewPanel extends CustomPanel {

    // Components for the OrderViewPanel
    private CustomLabel titleLabel;
    private CustomPanel titlePanel;
    private CustomPanel contentPanel;
    private CustomPanel buttonPanel;
    private CustomMenuListComponent orderList;
    private LinkedList<MenuItem> orderItems;
    private CustomLabel totalPriceLabel;
    private float currentTotalPrice;
    private CustomButton orderButton;
    private CustomButton cancelButton;
    private MenuListener menuListener;
    private Customer currentCustomer;

    // Constructor
    public OrderViewPanel() {
        // Initialize variables
        currentCustomer = null;

        // Title panel setup
        titlePanel = new CustomPanel();
        titleLabel = new CustomLabel("CART", 32);

        // Content panel setup
        contentPanel = new CustomPanel();

        // Order list setup
        orderList = new CustomMenuListComponent(16, new Dimension(240, 120));
        orderItems = new LinkedList<>();
        orderList.setDisplayItemCount(true);

        // Price label setup
        totalPriceLabel = new CustomLabel("$ 0.00", 24);
        currentTotalPrice = 0;

        // Button panel setup
        buttonPanel = new CustomPanel();

        // Order button setup
        orderButton = new CustomButton("ORDER", Config.getTextFont(16), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        // Cancel button setup
        cancelButton = new CustomButton("CANCEL", Config.getTextFont(16), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        // Order button action
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the list of items and create a new order
                LinkedList<MenuItem> items = orderList.getList();
                Order newOrder = new Order(currentCustomer.getID(), items, currentTotalPrice);
                menuListener.newOrderEvent(newOrder);
            }
        });

        // Layout components and apply styling
        layoutComponents();
        styling();

        // Cancel button action
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuListener.cancelMenuEvent();
            }
        });
    }

    // Set the current customer for the order
    public void setCustomer(Customer customer) {
        currentCustomer = customer;
    }

    // Layout components in the panel
    private void layoutComponents() {
        // Title panel layout
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        // Content panel layout
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        contentPanel.add(orderList, gc);
        gc.gridy++;
        contentPanel.add(totalPriceLabel, gc);

        // Button panel layout
        buttonPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 5, 10);
        buttonPanel.add(cancelButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 5, 0);
        buttonPanel.add(orderButton, gc);

        // Set the main panel layout
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 4, 5, 4),
                BorderFactory.createLineBorder(Config.getTextColor(), 2, true)));
        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Add an item to the order
    public void addItem(MenuItem item) {
        // Update total price
        currentTotalPrice += item.getPrice();
        // Format the price to display two decimal places
        String formattedPrice = String.format("%.2f", currentTotalPrice);
        totalPriceLabel.setText("$ " + formattedPrice);

        // Add the item to the order list
        orderItems.add(item);
        orderList.addItem(item);
    }

    // Set the menu listener for handling events
    public void setMenuListener(MenuListener listener) {
        this.menuListener = listener;
    }

    // Apply styling to components
    private void styling() {
        // Styling code goes here
        // Commented out for brevity
    }
}
