package model;

import java.util.LinkedList;

// Order class representing a customer's order
public class Order {

    // Attributes of an order
    String ID;
    LinkedList<MenuItem> items;
    float totalPrice;

    // Constructor to initialize an order with ID, items, and total price
    public Order(String ID, LinkedList<MenuItem> items, float totalPrice) {
        this.ID = ID;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    // Getter for total price
    public float getTotalPrice() {
        return totalPrice;
    }

    // Setter for total price
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Getter for order ID
    public String getID() {
        return ID;
    }

    // Getter for order items
    public LinkedList<MenuItem> getItems() {
        return items;
    }

    // Override toString method to provide a string representation of the order
    @Override
    public String toString() {
        return ID + " " + items.toString() + " " + totalPrice;
    }
}
