package controller;

import gui.login.LoginEvent;
import model.Customer;
import model.Database;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Controller {
    Database db = new Database();

    public Controller() {

        load();

    }

    public void load() {
        try {
            this.db.loadCustomers();
        } catch(Exception e) {
            System.out.println("ERROR LOADING CUSTOMERS");
        }

    }

    // CUSTOMER

    public void addCustomer(Customer c) throws IOException {
        this.db.addCustomer(c);
    }

    public void saveCustomers() throws IOException {
        this.db.saveCustomers();
    }

    public Customer customerLogin(LoginEvent e) throws ParseException, IOException {
        return this.db.customerLogin(e);
    }

    public boolean existingCustomer(String phoneNumber) {
        return this.db.existingCustomer(phoneNumber);
    }


    // PAYMENTS

    public void savePayments() throws IOException {
        this.db.savePayments();
    }

    public void loadPayments() throws IOException, ParseException {
        this.db.loadPayments();
    }

}
