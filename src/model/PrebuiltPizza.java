package model;

import java.util.LinkedList;

/*Pizza class which will hold the different options for each pizza*/

public class PrebuiltPizza extends MenuItem {

    private String type;
    //    private Size size;
    private String cheeseAmt;
    private String sauceAmt;
    private String crustType;

    private int quantity;

    private LinkedList<Ingredient> toppings;

    public PrebuiltPizza(String type, String category, String name, float price, String crustType, LinkedList<Ingredient> toppings) {
        super(type, category, name, price);
//        this.size = size;
        this.cheeseAmt = cheeseAmt;
        this.sauceAmt = sauceAmt;
        this.crustType = crustType;
        this.toppings = toppings;
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCheeseAmt() {
        return cheeseAmt;
    }

    public void setCheeseAmt(String cheeseAmt) {
        this.cheeseAmt = cheeseAmt;
    }

    public String getSauceAmt() {
        return sauceAmt;
    }

    public void setSauceAmt(String sauceAmt) {
        this.sauceAmt = sauceAmt;
    }

    public String getCrustType() {
        return crustType;
    }

    public void setCrustType(String crustType) {
        this.crustType = crustType;
    }

    public LinkedList<Ingredient> getToppings() {
        return toppings;
    }

    public void setToppings(LinkedList<Ingredient> toppings) {
        this.toppings = toppings;
    }

    public String getType() {
        return type;
    }

}
