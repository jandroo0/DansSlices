package gui.home.menu;

import config.Config;
import customComponents.CustomButton;
import customComponents.CustomLabel;
import customComponents.CustomMenuListComponent;
import customComponents.CustomPanel;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/*
   This class represents a panel for a specific category of menu items.
   For each category, an instance of this class is created to display and handle items of that category.
*/
public class MenuCategoryPanel extends CustomPanel {

    // PANELS
    private final CustomPanel titlePanel;
    private final CustomPanel listPanel;

    // COMPONENTS
    private final CustomLabel titleLabel; // Title label for the category
    private final CustomMenuListComponent itemList; // Custom component to display a list of menu items
    private final LinkedList<MenuItem> items; // List to store menu items for this category
    private final CustomButton addButton; // Button to add selected item to the cart
    String category; // Category name for this panel
    private AddToCartListener addToCartListener; // Listener to notify when an item is added to the cart

    // Constructor
    public MenuCategoryPanel(String category) {
        this.category = category;

        // Title
        titleLabel = new CustomLabel(category, 30);
        titlePanel = new CustomPanel();

        // List panel
        listPanel = new CustomPanel();

        // Category items
        items = new LinkedList<MenuItem>();

        // Items list
        itemList = new CustomMenuListComponent(13, new Dimension(150, 200));
        itemList.setAlignCenter(true);

        // Add button
        addButton = new CustomButton("ADD", Config.getTextFont(16), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        // Add ActionListener to the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the add button is clicked, check if an item is selected and notify the addToCartListener
                if (itemList.getSelectedItem() != null) {
                    addToCartListener.itemAdded(itemList.getSelectedItem());
                }
            }
        });

        // Set up the layout and apply styling
        layoutComponents();
        styling();
    }

    // Set the listener for item addition to the cart
    public void setAddToCartListener(AddToCartListener listener) {
        this.addToCartListener = listener;
    }

    // Layout the components in the panel
    private void layoutComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 2, 0, 4),
                BorderFactory.createLineBorder(Config.getTextColor(), 2, true)));

        // Title panel
        titlePanel.add(titleLabel);

        // List panel
        listPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        listPanel.add(itemList, gc);
        gc.gridy++;
        listPanel.add(addButton, gc);

        // Add title panel and list panel to the MenuCategoryPanel
        add(titlePanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }

    // Apply custom styling to the components
    private void styling() {
        // Custom styling can be added here if needed
    }

    // Add an item to the category
    public void addItem(MenuItem item) {
        items.add(item);
        itemList.addItem(item);
    }

    // Clear the list of items in the category
    public void clearList() {
        itemList.clearList();
    }

    // Get the category name
    public String getCategory() {
        return category;
    }
}
