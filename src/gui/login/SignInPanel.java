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

    // COMPONENTS
    private final CustomLabel phoneNumberLabel; // phoneNumber label
    private final CustomLabel passwordLabel; // password label
    private final CustomPlaceholderField phoneNumberField; // phoneNumber text field
    private final CustomPasswordField passwordField; // password text field
    private final JButton newCustomerButton; // new customer button
    private final CustomButton submitButton; // form submit button

    // EVENT LISTENERS
//    private SignInListener signInListener; // event listener on login event
    private SignInListener signInListener; // event listener new customer click event

    public SignInPanel() {

        phoneNumberLabel = new CustomLabel("PHONE NUMBER", 24); // set text for phoneNumberLabel
        passwordLabel = new CustomLabel("PASSWORD", 24); // set text for passwordlabel

        phoneNumberField = new CustomPlaceholderField(140, 44, 24); // phoneNumberField
        passwordField = new CustomPasswordField(140, 44, 24); // passwordField

        submitButton = new CustomButton("LOGIN", Config.getTextFont(20), Config.getTextColor(), Config.getButtonBackgroundColor(),
                Config.getButtonHoverColor(), Config.getButtonBorder()); // submit button
//        submitButton = new CustomButton("LOGIN");

        newCustomerButton = new JButton("NEW CUSTOMER?"); // new customer button

        layoutComponents();
        styling();
        handleEvents();

    }

    public void setSignInListener(SignInListener listener) {
        this.signInListener = listener;
    }


    // events
    private void handleEvents() {


        // submit button actionListener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField != null) { // if something is in the textfield
                    if (signInListener != null) { // if there is a signInListener
                        SignInPanel.this.signInListener.signInEvent(phoneNumberField.getText(), passwordField.getText()); // call signIn event with phone number & password

                        try {
                            phoneNumberField.getDocument().remove(0, phoneNumberField.getText().length()); // clear phoneNumberField doc after submit
                            passwordField.getDocument().remove(0, passwordField.getText().length()); // clear passwordField doc after submit
                        } catch (BadLocationException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }

            }
        });


        // new customer actionListener
        newCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInPanel.this.signInListener.newCustomerEvent();
            }
        });


    }

    // component styling
    void styling() {

        // idField
        PlainDocument doc = (PlainDocument) phoneNumberField.getDocument();
        doc.setDocumentFilter(new CustomPlaceholderField.MyIntFilter(10));

        // "newCustomer ?" button
        newCustomerButton.setFont(Config.getTextFont(14));
        newCustomerButton.setBackground(Config.getBackgroundColor());
        newCustomerButton.setForeground(Color.WHITE);
        newCustomerButton.setBorder(Config.getButtonBorder());


        newCustomerButton.addMouseListener(new MouseAdapter() { // hover effects for the new customer? button
            @Override
            public void mouseEntered(MouseEvent e) { // on mouse entered set foreground to textColor
                newCustomerButton.setForeground(Config.getTextColor());
            }

            @Override
            public void mouseExited(MouseEvent e) { // on exit set foreground to white
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
