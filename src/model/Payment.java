package model;

// Payment class representing a generic payment
public class Payment {

    // Attribute for payment ID
    protected String ID;

    // Constructor to initialize a payment with an ID
    public Payment(String ID) {
        this.ID = ID;
    }

    // Getter for payment ID
    public String getID() {
        return ID;
    }

    // Setter for payment ID
    public void setID(String ID) {
        this.ID = ID;
    }
}
