package controller;

import model.*;

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

    // ORDERS

    public void saveOrders() {
        this.db.saveOrders();
    }

//    public void loadOrders() {
//        this.db.loadOrders();
//    }

    public void createOrder(Order newOrder) {
        this.db.createOrder(newOrder);
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
