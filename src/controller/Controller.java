package controller;

import model.*;

import java.util.LinkedList;

public class Controller {
    // Database instance to manage data
    Database db = new Database();

    // Constructor - automatically loads data when the Controller is created
    public Controller() {
        load();
    }

    // Method to load data from files into the Database
    public void load() {
        try {
            // Load customer data
            this.db.loadCustomers();
        } catch (Exception e) {
            System.out.println("ERROR LOADING CUSTOMERS");
            e.printStackTrace();
        }
        try {
            // Load payment data
            this.db.loadPayments();
        } catch (Exception e) {
            System.out.println("ERROR LOADING PAYMENTS");
            e.printStackTrace();
        }
        try {
            // Load menu data
            this.db.loadMenu();
        } catch (Exception e) {
            System.out.println("ERROR LOADING MENU");
            e.printStackTrace();
        }
    }

    // CUSTOMER

    // Method to add a customer to the Database
    public void addCustomer(Customer c) {
        this.db.addCustomer(c);
    }

    // Method to save customer data to a file
    public void saveCustomers() {
        this.db.saveCustomers();
    }

    // Method to validate customer login credentials
    public Customer customerLogin(String phoneNumber, String password) {
        return this.db.customerLogin(phoneNumber, password);
    }

    // Method to check if a customer with a given phone number already exists
    public boolean existingCustomer(String phoneNumber) {
        return this.db.existingCustomer(phoneNumber);
    }

    // ORDERS

    // Method to save order data to a file
    public void saveOrders() {
        this.db.saveOrders();
    }

    // Method to create a new order and add it to the Database
    public void createOrder(Order newOrder) {
        this.db.createOrder(newOrder);
    }

    // PAYMENTS

    // Method to save payment data to a file
    public void savePayments() {
        this.db.savePayments();
    }

    // Method to load payment data from a file
    public void loadPayments() {
        this.db.loadPayments();
    }

    // MENU

    // Method to load menu data from a file
    public void loadMenu() {
        this.db.loadMenu();
    }

    // Method to get the menu items from the Database
    public LinkedList<MenuItem> getMenu() {
        return this.db.getMenu();
    }

    // Method to get prebuilt pizzas from the Database
    public LinkedList<PrebuiltPizza> getPrebuiltPizzas() {
        return this.db.getPrebuiltPizzas();
    }
}
