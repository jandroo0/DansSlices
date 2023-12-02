package customComponents;

import config.Config;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// Custom JPanel for displaying a list of MenuItems
public class CustomMenuListComponent extends JPanel {

    // Components
    private JList<MenuItem> list;
    private DefaultListModel<MenuItem> model;
    private JScrollPane scrollPane;
    private boolean displayItemCount, displayInSpecialFormat, alignCenter;
    private Map<MenuItem, Integer> itemCounter;

    // Constructor
    public CustomMenuListComponent(int fontSize, Dimension preferredSize) {
        setLayout(new BorderLayout());
        setPreferredSize(preferredSize);

        // Initialize components
        list = new JList<>();
        scrollPane = new JScrollPane(list);

        // Set preferred size and border for the scroll pane
        scrollPane.setPreferredSize(preferredSize);
        scrollPane.setBorder(BorderFactory.createLineBorder(Config.getButtonBackgroundColor(), 2, true));

        // Initialize the DefaultListModel for the JList
        model = new DefaultListModel<>();
        list.setModel(model);
        list.setCellRenderer(new CustomListCellRenderer());

        // Set font, background, foreground, and selection mode for the JList
        list.setFont(Config.getTextFont(fontSize));
        list.setBorder(BorderFactory.createLineBorder(Config.getButtonBackgroundColor(), 2, true));
        list.setBackground(Config.getButtonBackgroundColor());
        list.setForeground(Config.getTextColor());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Add the scroll pane to the center of the panel
        add(scrollPane, BorderLayout.CENTER);

        // Set default values for optional features
        this.displayItemCount = false;
        this.itemCounter = new HashMap<>();
        this.displayInSpecialFormat = false;
    }

    // Set whether to display item count
    public void setDisplayItemCount(boolean displayItemCount) {
        this.displayItemCount = displayItemCount;
    }

    // Set whether to display items in a special format
    public void setDisplayInSpecialFormat(boolean displayInSpecialFormat) {
        this.displayInSpecialFormat = displayInSpecialFormat;
    }

    // Set whether to align items to the center
    public void setAlignCenter(boolean alignCenter) {
        this.alignCenter = alignCenter;
    }

    // Add an item to the list
    public void addItem(MenuItem item) {
        if (itemCounter.containsKey(item)) {
            // Increment the count if the item is already in the list
            int count = itemCounter.get(item) + 1;
            itemCounter.put(item, count);
        } else {
            // Add the item to the list and set count to 1
            itemCounter.put(item, 1);
            model.addElement(item);
        }
        list.repaint(); // Force the list to repaint
    }

    // Get the selected item from the list
    public MenuItem getSelectedItem() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
            return model.getElementAt(selectedIndex);
        }
        return null;
    }

    // Set the list of MenuItems
    public void setList(LinkedList<MenuItem> items) {
        DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
        model.removeAllElements();
        for (MenuItem item : items) {
            model.addElement(item);
        }
    }

    // Get the list of MenuItems
    public LinkedList<MenuItem> getList() {
        DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
        LinkedList<MenuItem> items = new LinkedList<>();
        for (int i = 0; i < model.getSize(); i++) {
            items.add(model.getElementAt(i));
        }
        return items;
    }

    // Remove the selected item from the list
    public void removeSelectedItem() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
            model.remove(selectedIndex);
        }
    }

    // Clear the list and item counter
    public void clearList() {
        DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
        model.removeAllElements();
        itemCounter.clear();
    }

    // Custom cell renderer for formatting list items
    private class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Format the display text based on options and item type
            if (value instanceof MenuItem) {
                MenuItem menuItem = (MenuItem) value;
                if (displayItemCount) {
                    // Display item count if enabled
                    int count = itemCounter.get(menuItem);
                    setText(menuItem.getItemName() + " $" + String.format("%.2f", menuItem.getPrice()) + " x" + count);
                } else if (displayInSpecialFormat) {
                    // Display in special format if enabled
                    setText(menuItem.getCategory() + " - " + menuItem.getItemName() + " $" + String.format("%.2f", menuItem.getPrice()));
                }
            }

            // Align text to the center if specified
            if (alignCenter) {
                setHorizontalAlignment(SwingConstants.CENTER);
            }

            return c;
        }
    }
}



//package gui.tools;
//
//import gui.config.Utils;
//import model.MenuItem;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.LinkedList;
//
//public class MenuCustomList extends JPanel {
//
//    private JList<MenuItem> list;
//    private DefaultListModel<MenuItem> model;
//    private JScrollPane scrollPane;
//    private boolean displayInSpecialFormat, alignCenter;
//
//    public MenuCustomList(int fontSize, Dimension preferredSize) {
//        setLayout(new BorderLayout());
//        setPreferredSize(preferredSize);
//
//        list = new JList<>();
//        scrollPane = new JScrollPane(list);
//
//        scrollPane.setPreferredSize(preferredSize);
//        scrollPane.setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));
//
//        model = new DefaultListModel<>();
//        list.setModel(model);
//        list.setCellRenderer(new CustomListCellRenderer());
//
//        list.setFont(Utils.getTextFont(fontSize));
//        list.setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));
//        list.setBackground(Utils.getButtonBackgroundColor());
//        list.setForeground(Utils.getTextColor());
//        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//
//        add(scrollPane, BorderLayout.CENTER);
//
//        this.displayInSpecialFormat = false; // Set the default value
//    }
//
//    public void setDisplayInSpecialFormat(boolean displayInSpecialFormat) {
//        this.displayInSpecialFormat = displayInSpecialFormat;
//    }
//
//    public void setAlignCenter(boolean alignCenter) {
//        this.alignCenter = alignCenter;
//    }
//
//    // Add an item to the list
//    public void addItem(MenuItem item) {
//        DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
//        model.addElement(item);
//    }
//
//    // get selected item from list
//    public MenuItem getSelectedItem() {
//        int selectedIndex = list.getSelectedIndex();
//        if (selectedIndex != -1) {
//            DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
//            return model.getElementAt(selectedIndex);
//        }
//        return null;
//    }
//
//    // set list of ingredient items
//    public void setList(LinkedList<MenuItem> items) {
//        DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
//        model.removeAllElements();
//        for (MenuItem item : items) {
//            model.addElement(item);
//        }
//    }
//
//    // get the list model items as a menu item linked list
//    public LinkedList<MenuItem> getList() {
//        DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
//        LinkedList<MenuItem> items = new LinkedList<>();
//        for (int i = 0; i < model.getSize(); i++) {
//            items.add(model.getElementAt(i));
//        }
//        return items;
//    }
//
//    // Remove the selected item from the list
//    public void removeSelectedItem() {
//        int selectedIndex = list.getSelectedIndex();
//        if (selectedIndex != -1) {
//            DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
//            model.remove(selectedIndex);
//        }
//    }
//
//    public void clearList() {
//        DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
//        model.removeAllElements();
//    }
//
//    private class CustomListCellRenderer extends DefaultListCellRenderer {
//        @Override
//        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//
//            if (displayInSpecialFormat) {
//                if (value instanceof MenuItem) {
//                    MenuItem menuItem = (MenuItem) value;
//                    setText(menuItem.getCategory() + " - " + menuItem.getItemName() + " $" + menuItem.getPrice());
//                }
//            }
//
//            if (alignCenter) {
//                setHorizontalAlignment(SwingConstants.CENTER);
//
//            }
//
//            return c;
//        }
//    }
//}
