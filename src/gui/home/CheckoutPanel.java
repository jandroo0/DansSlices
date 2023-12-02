package gui.home;

import customComponents.*;
import model.MenuItem;
import model.*;

import javax.swing.*;
import java.awt.*;

public class CheckoutPanel extends CustomPanel {

    // PANELS
    private final CustomPanel titlePanel;
    private final CustomPanel orderPanel;
    private final CustomPanel paymentInfoPanel; // payment info panel
    private final CustomPanel buttonsPanel;
    private final CustomLabel titleLabel;

    // COMPONENTS
    private final CustomMenuListComponent orderListComponent;
    private final JComboBox<String> paymentSelectBox;
    private final DefaultComboBoxModel<String> paymentSelectModel;

    // PAYMENT INFO
    private final CustomPlaceholderField cardNameField;
    private final CustomPlaceholderField cardNumberField;
    private final CustomPlaceholderField cardExpDateField;
    private final CustomPlaceholderField CVCField;

    // buttons
    private final CustomButton checkoutButton;
    private final CustomButton cancelButton;

    private Customer currentCustomer;

    public CheckoutPanel() {

        // Set layout for the main panel
        setLayout(new BorderLayout());

        // Initialize panels
        titlePanel = new CustomPanel();
        orderPanel = new CustomPanel();
        paymentInfoPanel = new CustomPanel();
        buttonsPanel = new CustomPanel();

        // Initialize label
        titleLabel = new CustomLabel("CHECKOUT", 30);

        // Initialize order list component
        orderListComponent = new CustomMenuListComponent(15, new Dimension(250, 300));
        orderListComponent.setDisplayItemCount(true);

        // Initialize payment select box and model
        paymentSelectBox = new JComboBox<String>();
        paymentSelectModel = new DefaultComboBoxModel<String>();

        // Initialize payment info fields
        cardNameField = new CustomPlaceholderField(" Name on Card ", 140, 30, 18);
        cardNumberField = new CustomPlaceholderField(" Number", 140, 30, 18, true, 20);
        cardExpDateField = new CustomPlaceholderField("MM/YY", 140, 30, 18);
        CVCField = new CustomPlaceholderField(" CVC", 140, 30, 18, true, 3);

        // Initialize buttons
        checkoutButton = new CustomButton("Checkout");
        cancelButton = new CustomButton("Cancel");

        // Arrange components and handle events
        layoutComponents();
        handleEvents();
    }

    // Set the current customer
    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    // Set the order details
    public void setOrder(Order newOrder) {
        for (MenuItem item : newOrder.getItems()) {
            orderListComponent.addItem(item);
        }
    }

    // Set payment options for the customer
    public void setPaymentOptions() {
        for (Payment newPayment : currentCustomer.getPayments()) {
            System.out.println(newPayment);
            CardPayment payment = (CardPayment) newPayment;
            paymentSelectModel.addElement(payment.getCardNumber().substring(0, 4));
        }
        paymentSelectModel.addElement("CASH"); // add cash for a default payment
        paymentSelectBox.setModel(paymentSelectModel);
    }

    // Handle GUI events
    private void handleEvents() {
        // Event handling code can be added here
    }

    // Arrange components in the layout
    private void layoutComponents() {
        GridBagConstraints gc;

        // Title panel
        titlePanel.add(titleLabel);

        // Order panel
        orderPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        orderPanel.add(orderListComponent, gc);
        gc.gridy++;
        orderPanel.add(paymentSelectBox, gc);
        gc.gridy++;
        orderPanel.add(paymentInfoPanel, gc);

        // Buttons panel
        buttonsPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        buttonsPanel.add(cancelButton, gc);
        gc.gridx++;
        buttonsPanel.add(checkoutButton, gc);

        // Add panels to the main layout
        add(titlePanel, BorderLayout.NORTH);
        add(orderPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
