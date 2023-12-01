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

        setLayout(new BorderLayout());

        // PANELS

        titlePanel = new CustomPanel();
        orderPanel = new CustomPanel();
        paymentInfoPanel = new CustomPanel();
        buttonsPanel = new CustomPanel();


        // COMPONENTS
        titleLabel = new CustomLabel("CHECKOUT", 30);

        // current order list
        orderListComponent = new CustomMenuListComponent(15, new Dimension(250, 300));
        orderListComponent.setDisplayItemCount(true);

        // payment select box
        paymentSelectBox = new JComboBox<String>(); // create payment selection combo box
        paymentSelectModel = new DefaultComboBoxModel<String>(); // create payment selection model

        // payment info fields
        cardNameField = new CustomPlaceholderField(" Name on Card ", 140, 30, 18);
        cardNumberField = new CustomPlaceholderField(" Number", 140, 30, 18, true, 20);
        cardExpDateField = new CustomPlaceholderField("MM/YY", 140, 30, 18);
        CVCField = new CustomPlaceholderField(" CVC", 140, 30, 18, true, 3);

        // buttons
        checkoutButton = new CustomButton("Checkout");
        cancelButton = new CustomButton("Cancel");


        layoutComponents();
        handleEvents();

    }

    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    public void setOrder(Order newOrder) {
        for (MenuItem item : newOrder.getItems()) {
            orderListComponent.addItem(item);
        }
    }

    public void setPaymentOptions() {
        for (Payment newPayment : currentCustomer.getPayments()) {
            System.out.println(newPayment);
            CardPayment payment = (CardPayment) newPayment;
            paymentSelectModel.addElement(payment.getCardNumber().substring(0, 4));
        }
        paymentSelectModel.addElement("CASH"); // add cash for a default payment
        paymentSelectBox.setModel(paymentSelectModel);

    }

    private void handleEvents() {


    }


    private void layoutComponents() {
        GridBagConstraints gc;

        // title panel
        titlePanel.add(titleLabel);


        // order panel

        orderPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        orderPanel.add(orderListComponent, gc);
        gc.gridy++;
        orderPanel.add(paymentSelectBox, gc);
        gc.gridy++;
        orderPanel.add(paymentInfoPanel, gc);

        // buttons panel
        buttonsPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        buttonsPanel.add(cancelButton, gc);
        gc.gridx++;
        buttonsPanel.add(checkoutButton, gc);


        add(titlePanel, BorderLayout.NORTH);
        add(orderPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
