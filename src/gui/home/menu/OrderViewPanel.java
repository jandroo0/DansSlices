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

    private CustomLabel titleLabel;

    // panels
    private CustomPanel titlePanel;

    private CustomPanel contentPanel;

    private CustomPanel buttonPanel;


    // current order list
    private CustomMenuListComponent orderList;

    private LinkedList<MenuItem> orderItems;

    // price label
    private CustomLabel totalPriceLabel;
    private float currentTotalPrice;

    // buttons
    private CustomButton orderButton;
    private CustomButton cancelButton;

    private MenuListener menuListener;

    private Customer currentCustomer;

    public OrderViewPanel() {

        currentCustomer = null;

        // title panel
        titlePanel = new CustomPanel();
        titleLabel = new CustomLabel("ORDER", 32);

        // content panel
        contentPanel = new CustomPanel();


        // order list
        orderList = new CustomMenuListComponent(16, new Dimension(240, 120));
        orderItems = new LinkedList<MenuItem>();

        orderList.setDisplayItemCount(true);

        // price label
        totalPriceLabel = new CustomLabel("$ 0.00", 24);
        currentTotalPrice = 0;

        // button panel
        buttonPanel = new CustomPanel();

        // order button
        orderButton = new CustomButton("ORDER", Config.getTextFont(16), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));
        // cancel button
        cancelButton = new CustomButton("CANCEL", Config.getTextFont(16), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        orderButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<MenuItem> items = orderList.getList();

                Order newOrder = new Order(currentCustomer.getID(), items, currentTotalPrice);
                menuListener.newOrderEvent(newOrder);
            }
        });

        layoutComponents();
        styling();
    }

    public void setCustomer(Customer customer) {
        currentCustomer = customer;
    }


    private void layoutComponents() {
        // title panel
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));


        // contentPanel
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        contentPanel.add(orderList, gc);
        gc.gridy++;
        contentPanel.add(totalPriceLabel, gc);

        // button panel
        buttonPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 5, 10);
        buttonPanel.add(cancelButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 5, 0);
        buttonPanel.add(orderButton, gc);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 4, 5, 4),
                BorderFactory.createLineBorder(Config.getTextColor(), 2, true)));
        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    public void addItem(MenuItem item) {
        // add up price
        currentTotalPrice += item.getPrice();
        // Format the price to display two decimal places
        String formattedPrice = String.format("%.2f", currentTotalPrice);
        totalPriceLabel.setText("$ " + formattedPrice);

        orderItems.add(item);
        orderList.addItem(item);
    }

    public void setMenuListener(MenuListener listener) {
        this.menuListener = listener;
    }


    private void styling() {
//        setBackground(Utils.getBackgroundColor());
//
//        // title
//        titlePanel.setBackground(Utils.getBackgroundColor());
//        titleLabel.setForeground(Utils.getTextColor());
//        titleLabel.setFont(Utils.getTextFont(28));
//
//        // content panel
//        contentPanel.setBackground(Utils.getBackgroundColor());
//
//        // price label
//        totalPriceLabel.setForeground(Utils.getTextColor());
//        totalPriceLabel.setFont(Utils.getTextFont(24));
//        totalPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        totalPriceLabel.setVerticalAlignment(SwingConstants.NORTH);
//
//        // buttons panel
//        buttonPanel.setBackground(Utils.getBackgroundColor());

    }
}
