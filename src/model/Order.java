package model;

import java.awt.*;
import java.util.LinkedList;

public class Order {

    String ID;
    LinkedList<MenuItem> items;

    float totalPrice;


    public Order(String ID, LinkedList<MenuItem> items, float totalPrice) {
        this.ID = ID;
        this.items = items;
        this.totalPrice = totalPrice;

    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getID() {
        return ID;
    }

    public LinkedList<MenuItem> getItems() {
        return items;
    }

    public String toString() {
        return ID + " " + items.toString() + " " + totalPrice;
    }
}
