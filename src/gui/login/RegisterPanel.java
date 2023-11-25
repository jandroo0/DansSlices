package gui.login;

import config.Config;
import customComponents.CustomButton;
import customComponents.CustomPanel;
import customComponents.PlaceholderTextField;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterPanel extends JPanel{

    // PANELS
    private final CustomPanel titlePanel;
    private final CustomPanel infoPanels; // info panel container
    private final CustomPanel userInfoPanel; // customer info panel
    private CustomPanel paymentInfoPanel; // payment info panel
    private final CustomPanel buttonPanel; // button panel

    private final Label titleLabel; // title label

    // INFO FIELDS

    // USER INFO
    private final PlaceholderTextField firstNameField;
    private final PlaceholderTextField lastNameField;
    private final PlaceholderTextField phoneNumberField;
    private final PlaceholderTextField addressField;
    private final PlaceholderTextField detailsField;

    // PAYMENT INFO
    private final PlaceholderTextField cardNameField;
    private final PlaceholderTextField cardNumberField;
    private final PlaceholderTextField cardExpDateField;
    private final PlaceholderTextField CVCField;

    private final CustomButton createAccountButton; // create account  button
    private final CustomButton cancelButton; // cancel button

    private JCheckBox addPaymentBox;// "add payment ?" checkBox


//    private CreateAccountListener createAccountListener;

    public RegisterPanel(Frame frame) {

        setLayout(new BorderLayout()); // set layout to border layout

        // PANELS
        titlePanel = new CustomPanel();
        infoPanels = new CustomPanel();
        userInfoPanel = new CustomPanel();
        paymentInfoPanel = new CustomPanel();
        buttonPanel = new CustomPanel();

        // title label
        titleLabel = new Label("CREATE ACCOUNT", 26);


        //user info fields
        firstNameField = new PlaceholderTextField(" First", 140, 30, 20);
        lastNameField = new PlaceholderTextField(" Last", 140, 30, 20);
        phoneNumberField = new PlaceholderTextField(" Phone", 140, 30, 20, true, 10);
        addressField = new PlaceholderTextField(" Address", 140, 30, 20);
        detailsField = new PlaceholderTextField(" Other", 140, 30, 20, 30);

        //payment info fields
        cardNameField = new PlaceholderTextField(" Name on Card ", 140, 30, 18);
        cardNumberField = new PlaceholderTextField(" Number", 140, 30, 18, true, 20);
        cardExpDateField = new PlaceholderTextField("MM/YY", 140, 30, 18);
        CVCField = new PlaceholderTextField(" CVC", 140, 30, 18, true, 3);


        addPaymentBox = new JCheckBox("Add Payment"); // add payment check box

        // create account button
        createAccountButton = new CustomButton("REGISTER", Config.getTextFont(20), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        // cancel button
        cancelButton = new CustomButton("CANCEL", Config.getTextFont(20), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        add(titlePanel, BorderLayout.NORTH);
        add(infoPanels, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        layoutComponents();
        styling();

        }


    private void layoutComponents() {
        GridBagConstraints gc = new GridBagConstraints();

        // title panel
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titlePanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        titlePanel.add(titleLabel, gc);
        gc.gridy++;
        titlePanel.add(addPaymentBox, gc);

        //info panels
        infoPanels.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        infoPanels.setLayout(new GridLayout(0, 1));

        infoPanels.add(userInfoPanel);


        //user info panel
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
        userInfoPanel.add(addressField, gc);
        gc.gridy++;
        userInfoPanel.add(detailsField, gc);


        //payment info panel
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


        //button panel
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


        // check box hover effect
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
        // add payment checkbox
        addPaymentBox.setBackground(Config.getBackgroundColor());
        addPaymentBox.setBorder(BorderFactory.createEmptyBorder());
        addPaymentBox.setFont(Config.getTextFont(18));


        switchPaymentFields();
    }

    // for each placeholder textField, check whether it still contains the placeholder, or nothing
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

    // check if add payment box is selected, display payment fields
    private void switchPaymentFields() {
        if (addPaymentBox.isSelected()) {
            infoPanels.add(paymentInfoPanel);
        } else infoPanels.remove(paymentInfoPanel);

    }

    // clear textFields
    public void clearFields() throws BadLocationException {
        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.getDocument().remove(0, phoneNumberField.getText().length());
        addressField.setText("");
        detailsField.setText("");

        cardNameField.setText("");
        cardNumberField.getDocument().remove(0, cardNumberField.getText().length());
        cardExpDateField.setText("");
        CVCField.getDocument().remove(0, CVCField.getText().length());

        addPaymentBox.setSelected(false);
        switchPaymentFields();
    }

    // if they dont have an account, then phone number will be preset on acct creation after attempting to login
    public void setPhoneNumber(String phoneNumber) {
        phoneNumberField.setText(phoneNumber);

    }

    }
