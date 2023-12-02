package model;

// Ingredient class extends MenuItem
public class Ingredient extends MenuItem {

    // Attributes specific to Ingredient
    private String name;
    private String category;
    private String typeID;
    private float price;

    // Constructor to initialize Ingredient with typeID, category, name, and price
    public Ingredient(String typeID, String category, String name, float price) {
        // Call the constructor of the superclass (MenuItem)
        super(typeID, category, name, price);

        // Initialize attributes specific to Ingredient
        this.typeID = typeID;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    // Getter for typeID
    public String getTypeID() {
        return typeID;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    // Setter for category
    public void setCategory(String category) {
        this.category = category;
    }

    // Getter for price
    public float getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(float price) {
        this.price = price;
    }
}
