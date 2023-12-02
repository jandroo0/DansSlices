package gui.login;

import config.Config;
import customComponents.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignInPanel extends CustomPanel {

    // Components
    private final CustomLabel phoneNumberLabel; // Phone number label
    private final CustomLabel passwordLabel; // Password label
    private final CustomPlaceholderField phoneNumberField; // Phone number text field
    private final CustomPasswordField passwordField; // Password text field
    private final JButton newCustomerButton; // New customer button
    private final CustomButton submitButton; // Submit button

    // Event Listeners
    private SignInListener signInListener; // Event listener on login event

    public SignInPanel() {
        phoneNumberLabel = new CustomLabel("PHONE NUMBER", 24); // Set text for phoneNumberLabel
        passwordLabel = new CustomLabel("PASSWORD", 24); // Set text for password label
        phoneNumberField = new CustomPlaceholderField(140, 44, 24); // Phone number field
        passwordField = new CustomPasswordField(140, 44, 24); // Password field
        submitButton = new CustomButton("LOGIN", Config.getTextFont(20), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(), Config.getButtonBorder()); // Submit button
        newCustomerButton = new JButton("NEW CUSTOMER?"); // New customer button

        layoutComponents();
        styling();
        handleEvents();
    }

    public void setSignInListener(SignInListener listener) {
        this.signInListener = listener;
    }

    // Events
    private void handleEvents() {
        // Submit button action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField != null) { // If something is in the text field
                    if (signInListener != null) { // If there is a signInListener
                        SignInPanel.this.signInListener.signInEvent(phoneNumberField.getText(), passwordField.getText()); // Call signIn event with phone number & password
                        try {
                            phoneNumberField.getDocument().remove(0, phoneNumberField.getText().length()); // Clear phoneNumberField doc after submit
                            passwordField.getDocument().remove(0, passwordField.getText().length()); // Clear passwordField doc after submit
                        } catch (BadLocationException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        // New customer button action listener
        newCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInPanel.this.signInListener.newCustomerEvent();
            }
        });
    }

    // Component styling
    void styling() {
        // Phone number field
        PlainDocument doc = (PlainDocument) phoneNumberField.getDocument();
        doc.setDocumentFilter(new CustomPlaceholderField.MyIntFilter(10));

        // "New Customer?" button
        newCustomerButton.setFont(Config.getTextFont(14));
        newCustomerButton.setBackground(Config.getBackgroundColor());
        newCustomerButton.setForeground(Color.WHITE);
        newCustomerButton.setBorder(Config.getButtonBorder());

        // Hover effects for the "New Customer?" button
        newCustomerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // On mouse entered, set foreground to textColor
                newCustomerButton.setForeground(Config.getTextColor());
            }

            @Override
            public void mouseExited(MouseEvent e) { // On exit, set foreground to white
                super.mouseExited(e);
                newCustomerButton.setForeground(Color.WHITE);
            }
        });
    }

    public void layoutComponents() {
        Border border = BorderFactory.createEmptyBorder(0, 10, 200, 10);
        this.setBorder(border);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(0, 0, 10, 0);
        gc.gridy = 0;
        gc.gridx = 0;
        add(phoneNumberLabel, gc);
        gc.gridy++;
        add(phoneNumberField, gc);
        gc.gridy++;
        add(passwordLabel, gc);
        gc.gridy++;
        add(passwordField, gc);
        gc.gridy++;
        add(submitButton, gc);
        gc.gridy++;
        add(newCustomerButton, gc);
    }

    public CustomPlaceholderField getPhoneNumberField() {
        return phoneNumberField;
    }
}
