package model;

public class MenuItem {

    private String category;
    private String typeID;
    private String itemName;
    private float price;

    private int quantity;

    public MenuItem(String typeID, String category, String itemName, float price) {
        this.typeID = typeID;
        this.category = category;
        this.itemName = itemName;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTypeID() {
        return typeID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return itemName + " $" + price;
    }
}
