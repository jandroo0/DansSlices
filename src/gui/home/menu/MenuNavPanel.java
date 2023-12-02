package gui.home.menu;

import config.Config;
import customComponents.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

// Panel for navigation buttons in the application's menu
public class MenuNavPanel extends JPanel {

    // List to store navigation buttons
    private final List<CustomButton> buttons;

    // Panel to hold the navigation buttons
    private JPanel buttonsPanel;

    // Listener for menu navigation events
    private MenuNavListener listener;

    // GridBagConstraints for managing button layout
    private GridBagConstraints gc;

    // Constructor
    public MenuNavPanel() {
        buttons = new ArrayList<>();
        setLayout(new BorderLayout());

        // Set panel background color and border
        setBackground(Config.getBackgroundColor());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 4, 0, 2),
                BorderFactory.createLineBorder(Config.getTextColor(), 2, true)));

        // Create the panel for buttons
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.insets = new Insets(0, 0, 12, 0);
        buttonsPanel.setBackground(Config.getBackgroundColor());

        // Add the buttons panel to the main panel
        add(buttonsPanel, BorderLayout.CENTER);
    }

    // Method to set the hover effect for buttons based on the current menu
    public void setButtonHoverEffect(String currentMenu) {
        for (CustomButton button : buttons) {
            button.setCustomMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (button.getText().equals(currentMenu)) {
                        // Change colors for the current menu button on hover
                        button.setBackground(Config.getButtonBackgroundColor());
                        button.setForeground(Config.getTextColor());
                    } else {
                        // Change colors for other buttons on hover
                        button.setBackground(Config.getButtonHoverColor());
                        button.setForeground(Config.getButtonBackgroundColor());
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Reset colors when mouse exits
                    if (!button.getText().equals(currentMenu)) {
                        button.setBackground(Config.getButtonBackgroundColor());
                        button.setForeground(Config.getTextColor());
                    } else {
                        button.setBackground(Config.getButtonHoverColor());
                        button.setForeground(Config.getButtonBackgroundColor());
                    }
                }
            });
        }
    }

    // Method to add a button to the panel
    public void addButton(String text) {
        CustomButton button = new CustomButton(text, Config.getTextFont(12), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(), BorderFactory.createEmptyBorder(3, 6, 3, 6));

        // Add action listener for button click
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Notify the listener when a button is clicked
                listener.navbarClicked(e.getActionCommand());
            }
        });

        // Add the button to the list and panel
        buttons.add(button);
        gc.gridy++;
        buttonsPanel.add(button, gc);
        revalidate();
        repaint();
    }

    // Method to remove a button from the panel
    public void removeButton(String text) {
        for (int i = 0; i < buttons.size(); i++) {
            CustomButton button = buttons.get(i);
            if (button.getText().equals(text)) {
                // Remove the button from the list and panel
                buttons.remove(i);
                remove(button);
                revalidate();
                repaint();
                break;
            }
        }
    }

    // Method to set colors for buttons based on the selected panel
    public void setButtonColors(String panelName) {
        for (CustomButton button : buttons) {
            if (button.getText().equals(panelName)) {
                // Set colors for the selected button
                button.setBackground(Config.getButtonHoverColor());
                button.setForeground(Config.getButtonBackgroundColor());
            } else {
                // Set colors for other buttons
                button.setBackground(Config.getButtonBackgroundColor());
                button.setForeground(Config.getTextColor());
            }
        }
    }

    // Method to remove all buttons from the panel
    public void removeAllButtons() {
        // Clear the list and remove all buttons from the panel
        buttons.clear();
        removeAll();
        revalidate();
        repaint();
    }

    // Method to set the menu navigation listener
    public void setMenuNavListener(MenuNavListener listener) {
        this.listener = listener;
    }
}
