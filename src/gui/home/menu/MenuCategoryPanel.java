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


/*For each category of menu item, a new panel is made to fit the items of that category.*/
public class MenuCategoryPanel extends CustomPanel {

    // PANELS
    private final CustomPanel titlePanel;
    private final CustomPanel listPanel;
    // COMPONENTS
    private final CustomLabel titleLabel; //  title label
    private final CustomMenuListComponent itemList;
    private final LinkedList<MenuItem> items;
    private final CustomButton addButton;
    String category; // set item category for this panel
    private AddToCartListener addToCartListener;

    public MenuCategoryPanel(String category) {

        this.category = category;

        // title
        titleLabel = new CustomLabel(category, 30);
        titlePanel = new CustomPanel();

        // list panel
        listPanel = new CustomPanel();

        // category items
        items = new LinkedList<MenuItem>();

        // items list
        itemList = new CustomMenuListComponent(13, new Dimension(150, 200));
        itemList.setAlignCenter(true);

        // add button
        addButton = new CustomButton("ADD", Config.getTextFont(16), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        addButton.addActionListener(new ActionListener() { // add button actionListener

            @Override
            public void actionPerformed(ActionEvent e) {
                addToCartListener.itemAdded(itemList.getSelectedItem());
            } //  on add button click, pass the selected item to the addToCartListener in the menuPanel, which will pass it to the cartViewPanel
        });


        layoutComponents();
        styling();

    }

    public void setAddToCartListener(AddToCartListener listener) {
        this.addToCartListener = listener;
    }


    private void layoutComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 2, 0, 4),
                BorderFactory.createLineBorder(Config.getTextColor(), 2, true)));

        // title panel
        titlePanel.add(titleLabel);

        //list panel
        listPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        listPanel.add(itemList, gc);
        gc.gridy++;
        listPanel.add(addButton, gc);


        add(titlePanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }

    private void styling() { // custom styling


    }

    public void addItem(MenuItem item) {
        items.add(item);
        itemList.addItem(item);
    }

    public void clearList() {
        itemList.clearList();
    }


    // get category
    public String getCategory() {
        return category;
    }
}
