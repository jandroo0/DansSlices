package model;

public class Ingredient extends MenuItem {

    private String name;
    private String category;
    private String typeID;

    private float price;

    public Ingredient(String typeID, String category, String name, float price) {
        super(typeID, category, name, price);
        this.typeID = typeID;
        this.category = category;
        this.name = name;
        this.price = price;

    }

    public String getTypeID() {
        return typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
}
