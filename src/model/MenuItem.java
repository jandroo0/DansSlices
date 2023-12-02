package model;

// MenuItem class representing a generic menu item
public class MenuItem {

    // Attributes of a menu item
    private String category;
    private String typeID;
    private String itemName;
    private float price;

    // Quantity of the menu item
    private int quantity;

    // Constructor to initialize a menu item with typeID, category, itemName, and price
    public MenuItem(String typeID, String category, String itemName, float price) {
        this.typeID = typeID;
        this.category = category;
        this.itemName = itemName;
        this.price = price;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter for itemName
    public String getItemName() {
        return itemName;
    }

    // Setter for itemName
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // Getter for price
    public float getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(float price) {
        this.price = price;
    }

    // Getter for typeID
    public String getTypeID() {
        return typeID;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    // Setter for category
    public void setCategory(String category) {
        this.category = category;
    }

    // Override toString method to provide a string representation of the menu item
    @Override
    public String toString() {
        return itemName + " $" + price;
    }
}
