package controller;

import model.Customer;
import model.Database;
import model.MenuItem;
import model.PrebuiltPizza;

import java.util.LinkedList;

public class Controller {
    Database db = new Database();

    public Controller() {

        load();

    }

    public void load() {
        try {
            this.db.loadCustomers();
        } catch (Exception e) {
            System.out.println("ERROR LOADING CUSTOMERS");
            e.printStackTrace();
        }
        try {
            this.db.loadPayments();
        } catch (Exception e) {
            System.out.println("ERROR LOADING PAYMENTS");
            e.printStackTrace();
        }
        try {
            this.db.loadMenu();
        } catch (Exception e) {
            System.out.println("ERROR LOADING MENU");
            e.printStackTrace();
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


    // MENU

    public void loadMenu() {
        this.db.loadMenu();
    }

    public LinkedList<MenuItem> getMenu() {
        return this.db.getMenu();
    }


    public LinkedList<PrebuiltPizza> getPrebuiltPizzas() {
        return this.db.getPrebuiltPizzas();
    }
}
