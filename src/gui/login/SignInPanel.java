package gui.login;

import config.Config;
import customComponents.CustomButton;
import customComponents.CustomPanel;
import customComponents.PlaceholderTextField;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class SignInPanel extends CustomPanel {

    // COMPONENTS
    private final Label idLabel; // id label
    private final PlaceholderTextField idField; // id text field
    private final JButton newCustomerButton; // new customer button
    private final CustomButton submitButton; // form submit button

    // EVENT LISTENERS
//    private LoginListener loginListener; // event listener on login event
//    private NewCustomerListener newCustomerListener; // event listener new customer click event

    public SignInPanel() {

        idLabel = new Label("PHONE NUMBER", 24); // set text for idLabel

        idField = new PlaceholderTextField(140, 44, 24); // idField

        submitButton = new CustomButton("LOGIN", Config.getTextFont(20), Config.getTextColor(), Config.getButtonBackgroundColor(),
                Config.getButtonHoverColor(), Config.getButtonBorder()); // submit button

        newCustomerButton = new JButton("NEW CUSTOMER?"); // new customer button

        layoutComponents();
        styling();

    }

    // component styling
    void styling() {

        // idField
        PlainDocument doc = (PlainDocument) idField.getDocument();
        doc.setDocumentFilter(new PlaceholderTextField.MyIntFilter(10));

        // "newCustomer ?" button
        newCustomerButton.setFont(Config.getTextFont(14));
        newCustomerButton.setBackground(Config.getBackgroundColor());
        newCustomerButton.setForeground(Color.WHITE);
        newCustomerButton.setBorder(Config.getButtonBorder());


    }

    public void layoutComponents() {
        Border border = BorderFactory.createEmptyBorder(0, 10, 200, 10);
        this.setBorder(border);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(0, 0, 10, 0);
        gc.gridy = 0;
        gc.gridx = 0;
        add(idLabel, gc);
        gc.gridy++;
        add(idField, gc);
        gc.gridy++;
        add(submitButton, gc);
        gc.gridy++;
        add(newCustomerButton, gc);
    }

    public PlaceholderTextField getIdField() {
        return idField;
    }
}
