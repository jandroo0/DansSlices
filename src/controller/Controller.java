package controller;

import model.Customer;
import model.Database;

public class Controller {
    Database db = new Database();

    public Controller() {

        load();

    }

    public void load() {
        try {
            this.db.loadCustomers();
            this.db.loadPayments();
        } catch (Exception e) {
            System.out.println("ERROR LOADING CUSTOMERS");
        }

    }

    // CUSTOMER

    public void addCustomer(Customer c) {
        this.db.addCustomer(c);
    }

    public void saveCustomers() {
        this.db.saveCustomers();
    }

    public Customer customerLogin(String phoneNumber, String password) {
        return this.db.customerLogin(phoneNumber, password);
    }

    public boolean existingCustomer(String phoneNumber) {
        return this.db.existingCustomer(phoneNumber);
    }


    // PAYMENTS

    public void savePayments() {
        this.db.savePayments();
    }

    public void loadPayments() {
        this.db.loadPayments();
    }

}
