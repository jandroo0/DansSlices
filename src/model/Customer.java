package model;

import java.util.LinkedList;

public class Customer {

    // Fields representing customer details
    private String ID, phoneNumber, firstName, lastName, address, details;
    private String password;

    // Lists to store customer payments and orders
    private LinkedList<Payment> payments;
    private LinkedList<Order> orders;

    // Constructor to initialize Customer with provided details
    public Customer(String phoneNumber, String password, String firstName, String lastName, String address, String details) {
        this.ID = phoneNumber; // Set ID to the phoneNumber (assuming phoneNumber is a unique identifier)
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.details = details;

        payments = new LinkedList<>(); // Initialize the payments list
        orders = new LinkedList<>(); // Initialize the orders list
    }

    // Getter for the orders list
    public LinkedList<Order> getOrders() {
        return orders;
    }

    // Getter for the payments list
    public LinkedList<Payment> getPayments() {
        return payments;
    }

    // Setter for the payments list
    public void setPayments(LinkedList<Payment> payments) {
        this.payments = payments;
    }

    // Method to add a payment to the payments list
    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    // Getter for the phoneNumber (assumed to be the ID)
    public String getPhoneNumber() {
        return ID;
    }

    // Setter for the phoneNumber
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter for the password
    public String getPassword() {
        return password;
    }

    // Setter for the password
    public void setPassword(String password) {
        this.password = password;
    }

    // Setter for the orders list
    public void setOrders(LinkedList<Order> orders) {
        this.orders = orders;
    }

    // Getter for the ID
    public String getID() {
        return phoneNumber;
    }

    // Setter for the ID
    public void setID(String ID) {
        this.ID = ID;
    }

    // Getter for the firstName
    public String getFirstName() {
        return firstName;
    }

    // Setter for the firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter for the lastName
    public String getLastName() {
        return lastName;
    }

    // Setter for the lastName
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter for the address
    public String getAddress() {
        return address;
    }

    // Setter for the address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter for the details
    public String getDetails() {
        return details;
    }

    // Setter for the details
    public void setDetails(String details) {
        this.details = details;
    }
}
