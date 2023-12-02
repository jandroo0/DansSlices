package model;

import java.util.LinkedList;

/* PrebuiltPizza class representing a preconfigured pizza option */

public class PrebuiltPizza extends MenuItem {

    // Additional attributes specific to PrebuiltPizza
    private String type;
    private String cheeseAmt;
    private String sauceAmt;
    private String crustType;

    private int quantity;

    private LinkedList<Ingredient> toppings;

    // Constructor to initialize a prebuilt pizza with type, category, name, price, crust type, and toppings
    public PrebuiltPizza(String type, String category, String name, float price, String crustType, LinkedList<Ingredient> toppings) {
        super(type, category, name, price);
        this.cheeseAmt = cheeseAmt;
        this.sauceAmt = sauceAmt;
        this.crustType = crustType;
        this.toppings = toppings;
        this.type = type;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter for cheese amount
    public String getCheeseAmt() {
        return cheeseAmt;
    }

    // Setter for cheese amount
    public void setCheeseAmt(String cheeseAmt) {
        this.cheeseAmt = cheeseAmt;
    }

    // Getter for sauce amount
    public String getSauceAmt() {
        return sauceAmt;
    }

    // Setter for sauce amount
    public void setSauceAmt(String sauceAmt) {
        this.sauceAmt = sauceAmt;
    }

    // Getter for crust type
    public String getCrustType() {
        return crustType;
    }

    // Setter for crust type
    public void setCrustType(String crustType) {
        this.crustType = crustType;
    }

    // Getter for toppings
    public LinkedList<Ingredient> getToppings() {
        return toppings;
    }

    // Setter for toppings
    public void setToppings(LinkedList<Ingredient> toppings) {
        this.toppings = toppings;
    }

    // Getter for pizza type
    public String getType() {
        return type;
    }
}
