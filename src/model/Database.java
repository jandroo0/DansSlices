package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Database {
    // FILE PATHS
    private final String customersFilePath = "customers.json";
    private final String paymentsFilePath = "payments.json";


    // LISTS of DATA
    private final LinkedList<Customer> customers; // create list of customer
    private final LinkedList<Payment> payments; // create list of customer


    private FileWriter fileWriter;

    public Database() {

        customers = new LinkedList<Customer>(); // create list of customers
        payments = new LinkedList<Payment>(); // create list of customers

    }

    // CUSTOMERS //////////////////////////////////


    // add customer
    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        saveCustomers();
    }

    // check if given phoneNumber exists
    public boolean existingCustomer(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getID().equals(phoneNumber)) return true;
        }
        return false;
    }

    //  checks if customer login matches customer in list and returns customer or null
    public Customer customerLogin(String phoneNumber, String password) {
        loadCustomers(); // refresh list
        for (Customer customer : customers) {
            if (phoneNumber.equals(customer.getID()) && password.equals(customer.getPassword())) {
                return customer;
            }
        }
        return null;
    }

    //save customers
    public void saveCustomers() {
        try {
            fileWriter = new FileWriter(customersFilePath);
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            // for each employee create json object and write to file
            for (Customer customer : customers) {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("Phone_Number", customer.getPhoneNumber());
                jsonObject.put("Password", customer.getPassword());
                jsonObject.put("First_Name", customer.getFirstName());
                jsonObject.put("Last_Name", customer.getLastName());
                jsonObject.put("Address", customer.getAddress());
                jsonObject.put("Details", customer.getDetails());


                sb.append(jsonObject.toJSONString() + ',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");

            fileWriter.write(sb.toString());
            System.out.println("CUSTOMER JSON CREATED AND SAVED");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load customers
    public void loadCustomers() {
        customers.clear(); // clear current list of customers

        // LOAD CUSTOMERS---------------------------------------------
        try {
            JSONParser parser = new JSONParser(); //create JSON parser
            JSONArray customersJSON = (JSONArray) parser.parse(new FileReader(customersFilePath)); // create JSONarray from file

            System.out.println("LOADING CUSTOMERS --------------");
            for (Object customerJSON : customersJSON) { // for each employee JSON in employees file

                JSONObject customer = (JSONObject) customerJSON; // create employee object from json

                // gather values

                String phoneNumber = (String) customer.get("Phone_Number");
                String password = (String) customer.get("Password");
                String firstName = (String) customer.get("First_Name");
                String lastName = (String) customer.get("Last_Name");
                String address = (String) customer.get("Address");
                String details = (String) customer.get("Details");


                // add new employee to current employees list

                Customer newCustomer = new Customer(phoneNumber, password, firstName, lastName, address, details);
                customers.add(newCustomer); // add each customer to list

                //log message
                System.out.println("CUSTOMER LOADED: " + newCustomer.getID() + ":" + newCustomer.getFirstName());
            }
            System.out.println("CUSTOMERS LOADED --------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // PAYMENTS //////////////////////////////////

    // save payments to file
    public void savePayments() {
        try {
            fileWriter = new FileWriter(paymentsFilePath);

            JSONObject categoriesJSON = new JSONObject();

            // Group payments by category (payment ID)
            for (Customer customer : customers) {
                for (int i = 0; i < customer.getPayments().size(); i++) {
                    Payment payment = customer.getPayments().get(i);

                    JSONObject paymentJSONObject = new JSONObject();
                    paymentJSONObject.put("ID", payment.getID());
                    paymentJSONObject.put("Card_Name", ((CardPayment) payment).getCardName());
                    paymentJSONObject.put("Card_Number", ((CardPayment) payment).getCardNumber());
                    paymentJSONObject.put("Exp_Date", ((CardPayment) payment).getExpDate());
                    paymentJSONObject.put("CVC", ((CardPayment) payment).getCVC());

                    String category = customer.getID(); // Use customer ID as the category (payment ID)

                    JSONArray categoryArray = (categoriesJSON.containsKey(category))
                            ? (JSONArray) categoriesJSON.get(category)
                            : new JSONArray();
                    categoryArray.add(paymentJSONObject);
                    categoriesJSON.put(category, categoryArray);
                }
            }

            // Write categories JSON to file
            fileWriter.write(categoriesJSON.toJSONString());
            System.out.println("PAYMENT JSON CREATED AND SAVED");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //  load payment
    public void loadPayments() {
        // Clear all listed payments
        for (Customer customer : customers) {
            customer.getPayments().clear();
        }
        try {
            // LOAD PAYMENTS---------------------------------------------
            JSONParser parser = new JSONParser(); // Create JSON parser
            JSONObject categoriesJSON = (JSONObject) parser.parse(new FileReader(paymentsFilePath)); // Create JSON object from file


            System.out.println("LOADING PAYMENTS --------------");

            for (Object categoryKey : categoriesJSON.keySet()) { // For each category (customer ID)
                String category = (String) categoryKey;
                JSONArray paymentsArray = (JSONArray) categoriesJSON.get(category); // Get payments array

                for (Object paymentJSON : paymentsArray) { // For each payment JSON in category
                    JSONObject paymentObj = (JSONObject) paymentJSON; // Create payment object from JSON

                    String ID = (String) paymentObj.get("ID"); // In this case, the category is the ID
                    String cardName = (String) paymentObj.get("Card_Name");
                    String cardNumber = (String) paymentObj.get("Card_Number");
                    String expDate = (String) paymentObj.get("Exp_Date");
                    String CVC = (String) paymentObj.get("CVC");

                    Payment newPayment = new CardPayment(ID, cardName, cardNumber, expDate, CVC);
                    for (Customer customer : customers) {
                        if (customer.getID().equals(ID)) {
                            customer.getPayments().add(newPayment);
                        }
                    }

                    System.out.println("PAYMENT METHOD LOADED : " + ID + " : " + newPayment);
                }
            }

            System.out.println("PAYMENTS LOADED --------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
