package model;

public class CardPayment extends Payment {

    private String cardName, cardNumber, expDate, CVC;

    public CardPayment(String ID, String cardName, String cardNumber, String expDate, String CVC) {
        super(ID);
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.CVC = CVC;

    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    @Override
    public String toString() {
        return "[Type: " + cardName + ", Number: " + cardNumber + ", ExpDate: " + expDate + ", CVC: " + CVC + "]";
    }
}
