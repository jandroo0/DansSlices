package gui.login;

import config.Config;
import customComponents.*;
import model.CardPayment;
import model.Customer;
import model.Payment;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterPanel extends JPanel {

    // Panels
    private final CustomPanel titlePanel;
    private final CustomPanel infoPanels; // Info panel container
    private final CustomPanel userInfoPanel; // Customer info panel
    private final CustomPanel buttonPanel; // Button panel
    private final CustomLabel titleLabel; // Title label

    // User info fields
    private final CustomPlaceholderField firstNameField;
    private final CustomPlaceholderField lastNameField;
    private final CustomPlaceholderField phoneNumberField;
    private final CustomPasswordField passwordField;
    private final CustomPlaceholderField addressField;
    private final CustomPlaceholderField detailsField;

    // Payment info fields
    private final CustomPlaceholderField cardNameField;
    private final CustomPlaceholderField cardNumberField;
    private final CustomPlaceholderField cardExpDateField;
    private final CustomPlaceholderField CVCField;

    private final CustomButton createAccountButton; // Create account button
    private final CustomButton cancelButton; // Cancel button
    private CustomPanel paymentInfoPanel; // Payment info panel
    private JCheckBox addPaymentBox;// "Add payment?" checkBox

    private CreateAccountListener createAccountListener;

    public RegisterPanel() {
        setLayout(new BorderLayout()); // Set layout to BorderLayout

        // Initialize panels
        titlePanel = new CustomPanel();
        infoPanels = new CustomPanel();
        userInfoPanel = new CustomPanel();
        paymentInfoPanel = new CustomPanel();
        buttonPanel = new CustomPanel();

        // Initialize title label
        titleLabel = new CustomLabel("CREATE ACCOUNT", 26);

        // Initialize user info fields
        firstNameField = new CustomPlaceholderField(" First", 140, 30, 20);
        lastNameField = new CustomPlaceholderField(" Last", 140, 30, 20);
        phoneNumberField = new CustomPlaceholderField(" Phone", 140, 30, 20, true, 10);
        passwordField = new CustomPasswordField(" Password", 140, 30, 20);
        addressField = new CustomPlaceholderField(" Address", 140, 30, 20);
        detailsField = new CustomPlaceholderField(" Other", 140, 30, 20, 30);

        // Initialize payment info fields
        cardNameField = new CustomPlaceholderField(" Name on Card ", 140, 30, 18);
        cardNumberField = new CustomPlaceholderField(" Number", 140, 30, 18, true, 20);
        cardExpDateField = new CustomPlaceholderField("MM/YY", 140, 30, 18);
        CVCField = new CustomPlaceholderField(" CVC", 140, 30, 18, true, 3);

        // Initialize add payment checkbox
        addPaymentBox = new JCheckBox("Add Payment");

        // Initialize buttons
        createAccountButton = new CustomButton("REGISTER", Config.getTextFont(20), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        cancelButton = new CustomButton("CANCEL", Config.getTextFont(20), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        // Add components to the layout
        add(titlePanel, BorderLayout.NORTH);
        add(infoPanels, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set up layout, styling, and event handling
        layoutComponents();
        styling();
        handleEvents();
    }

    private void handleEvents() {

        // Add payment box, on select show payment field
        addPaymentBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                styling();
            }
        });

        // Create account event listener
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fieldsEmpty()) {
                    String details;
                    if (detailsField.getText().equals(" Other Info")) {
                        details = "";
                    } else details = detailsField.getText();

                    Customer newCustomer = new Customer(phoneNumberField.getText(), passwordField.getText(), firstNameField.getText(),
                            lastNameField.getText(), addressField.getText(), details);

                    if (addPaymentBox.isSelected()) {
                        Payment newPayment = new CardPayment(phoneNumberField.getText(), cardNameField.getText(),
                                cardNumberField.getText(), cardExpDateField.getText(), CVCField.getText());
                        newCustomer.addPayment(newPayment);
                    }

                    try {
                        clearFields();
                        RegisterPanel.this.createAccountListener.createAccount(newCustomer);
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(RegisterPanel.this, "Info fields can not be left empty",
                            "Empty Fields", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Cancel button event
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clearFields();
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                RegisterPanel.this.createAccountListener.cancelEvent();
            }
        });
    }

    public void setCreateAccountListener(CreateAccountListener listener) {
        this.createAccountListener = listener;
    }

    private void layoutComponents() {
        GridBagConstraints gc = new GridBagConstraints();

        // Title panel
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titlePanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        titlePanel.add(titleLabel, gc);
        gc.gridy++;
        titlePanel.add(addPaymentBox, gc);

        // Info panels
        infoPanels.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        infoPanels.setLayout(new GridLayout(0, 1));
        infoPanels.add(userInfoPanel);

        // User info panel
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        userInfoPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        userInfoPanel.add(firstNameField, gc);
        gc.gridy++;
        userInfoPanel.add(lastNameField, gc);
        gc.gridy++;
        userInfoPanel.add(phoneNumberField, gc);
        gc.gridy++;
        userInfoPanel.add(passwordField, gc);
        gc.gridy++;
        userInfoPanel.add(addressField, gc);
        gc.gridy++;
        userInfoPanel.add(detailsField, gc);

        // Payment info panel
        paymentInfoPanel.setBorder(BorderFactory.createCompoundBorder());
        paymentInfoPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        paymentInfoPanel.add(cardNameField, gc);
        gc.gridy++;
        paymentInfoPanel.add(cardNumberField, gc);
        gc.gridy++;
        paymentInfoPanel.add(cardExpDateField, gc);
        gc.gridy++;
        paymentInfoPanel.add(CVCField, gc);

        // Button panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        buttonPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 10, 10);
        buttonPanel.add(cancelButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 10, 0);
        buttonPanel.add(createAccountButton, gc);

        // Check box hover effect
        addPaymentBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                addPaymentBox.setForeground(Config.getButtonBackgroundColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (!addPaymentBox.isSelected()) {
                    addPaymentBox.setForeground(Config.getTextColor());
                }
            }
        });
    }

    private void styling() {
        // Add payment checkbox
        addPaymentBox.setBackground(Config.getBackgroundColor());
        addPaymentBox.setBorder(BorderFactory.createEmptyBorder());
        addPaymentBox.setFont(Config.getTextFont(18));
        switchPaymentFields();
    }

    private boolean fieldsEmpty() {
        if (addPaymentBox.isSelected()) {
            if (cardNameField.getText().equals("")) {
                return true;
            } else if (cardNumberField.getText().equals("")) {
                return true;
            } else if (cardExpDateField.getText().equals("")) {
                return true;
            } else return CVCField.getText().equals("");
        }

        if (firstNameField.getText().equals("")) {
            return true;
        } else if (lastNameField.getText().equals("")) {
            return true;
        } else if (phoneNumberField.getText().equals("")) {
            return true;
        } else return addressField.getText().equals("");
    }

    private void switchPaymentFields() {
        if (addPaymentBox.isSelected()) {
            infoPanels.add(paymentInfoPanel);
        } else infoPanels.remove(paymentInfoPanel);
    }

    public void clearFields() throws BadLocationException {
        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.getDocument().remove(0, phoneNumberField.getText().length());
        passwordField.getDocument().remove(0, passwordField.getText().length());
        addressField.setText("");
        detailsField.setText("");

        cardNameField.setText("");
        cardNumberField.getDocument().remove(0, cardNumberField.getText().length());
        cardExpDateField.setText("");
        CVCField.getDocument().remove(0, CVCField.getText().length());

        addPaymentBox.setSelected(false);
        switchPaymentFields();
    }
}
