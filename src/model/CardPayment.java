package model;

public class CardPayment extends Payment {

    // Fields specific to CardPayment
    private String cardName, cardNumber, expDate, CVC;

    // Constructor to initialize CardPayment with provided details
    public CardPayment(String ID, String cardName, String cardNumber, String expDate, String CVC) {
        super(ID); // Call the constructor of the superclass (Payment) with the provided ID
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.CVC = CVC;
    }

    // Getter for CardName
    public String getCardName() {
        return cardName;
    }

    // Setter for CardName
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    // Getter for CardNumber
    public String getCardNumber() {
        return cardNumber;
    }

    // Setter for CardNumber
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Getter for ExpDate
    public String getExpDate() {
        return expDate;
    }

    // Setter for ExpDate
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    // Getter for CVC
    public String getCVC() {
        return CVC;
    }

    // Setter for CVC
    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    // Override toString method to provide a string representation of CardPayment
    @Override
    public String toString() {
        return "[Type: " + cardName + ", Number: " + cardNumber + ", ExpDate: " + expDate + ", CVC: " + CVC + "]";
    }
}
