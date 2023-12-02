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
    private final String customersFilePath = "data\\customers.json";
    private final String ordersFilePath = "data\\orders.json";
    private final String paymentsFilePath = "data\\payments.json";
    private final String menuFilePath = "data\\menu.json";

    // LISTS of DATA
    private final LinkedList<Customer> customers; // List of customers
    private final LinkedList<Payment> payments; // List of payments
    private final LinkedList<MenuItem> menu; // List of menu items
    private LinkedList<PrebuiltPizza> prebuiltPizzas; // List of prebuilt pizzas

    private FileWriter fileWriter;

    // Constructor to initialize Database with empty lists
    public Database() {
        customers = new LinkedList<Customer>();
        payments = new LinkedList<Payment>();
        menu = new LinkedList<MenuItem>();
        prebuiltPizzas = new LinkedList<PrebuiltPizza>();
    }

    // CUSTOMERS //////////////////////////////////

    // Add customer to the list and save to file
    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        saveCustomers();
    }

    // Check if a customer with the given phoneNumber exists
    public boolean existingCustomer(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getID().equals(phoneNumber)) return true;
        }
        return false;
    }

    // Check customer login credentials and return the customer or null
    public Customer customerLogin(String phoneNumber, String password) {
        loadCustomers(); // Refresh the list
        for (Customer customer : customers) {
            if (phoneNumber.equals(customer.getID()) && password.equals(customer.getPassword())) {
                return customer;
            }
        }
        return null;
    }

    // Save customers to a JSON file
    public void saveCustomers() {
        try {
            fileWriter = new FileWriter(customersFilePath);
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            for (Customer customer : customers) {
                JSONObject jsonObject = new JSONObject();
                // Create a JSON object for each customer
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

    // Load customers from a JSON file
    public void loadCustomers() {
        customers.clear(); // Clear the current list of customers

        try {
            JSONParser parser = new JSONParser();
            JSONArray customersJSON = (JSONArray) parser.parse(new FileReader(customersFilePath));

            System.out.println("LOADING CUSTOMERS --------------");
            for (Object customerJSON : customersJSON) {
                JSONObject customer = (JSONObject) customerJSON;

                String phoneNumber = (String) customer.get("Phone_Number");
                String password = (String) customer.get("Password");
                String firstName = (String) customer.get("First_Name");
                String lastName = (String) customer.get("Last_Name");
                String address = (String) customer.get("Address");
                String details = (String) customer.get("Details");

                Customer newCustomer = new Customer(phoneNumber, password, firstName, lastName, address, details);
                customers.add(newCustomer);

                System.out.println("CUSTOMER LOADED: " + newCustomer.getID() + ":" + newCustomer.getFirstName());
            }
            System.out.println("CUSTOMERS LOADED --------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CUSTOMER ORDERS

    // Save orders to a JSON file
    public void saveOrders() {
        try {
            fileWriter = new FileWriter(ordersFilePath);

            JSONObject categoriesJSON = new JSONObject();

            for (Customer customer : customers) {
                for (Order order : customer.getOrders()) {
                    JSONObject orderJSONObject = new JSONObject();
                    orderJSONObject.put("ID", order.getID());
                    orderJSONObject.put("Items", order.getItems());
                    orderJSONObject.put("Total", order.getTotalPrice());

                    String category = order.getID(); // Use customer ID as the category (order ID)

                    StringBuilder itemsString = new StringBuilder();

                    for (MenuItem item : order.getItems()) {
                        itemsString.append(item.getCategory() + "-" + item.getItemName() + ",");
                    }

                    itemsString.deleteCharAt(itemsString.length() - 1);
                    orderJSONObject.put("Items", itemsString.toString());

                    JSONArray categoryArray = (categoriesJSON.containsKey(category))
                            ? (JSONArray) categoriesJSON.get(category)
                            : new JSONArray();
                    categoryArray.add(orderJSONObject);
                    categoriesJSON.put(category, categoryArray);
                }
            }

            fileWriter.write(categoriesJSON.toJSONString());
            System.out.println("ORDERS JSON CREATED AND SAVED");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create a new order for a customer
    public void createOrder(Order order) {
        for (Customer customer : customers) {
            if (customer.getID().equals(order.getID())) {
                customer.getOrders().add(order);
                System.out.println("NEW ORDER: ID : " + customer.getID());
                for (MenuItem item : order.getItems()) {
                    System.out.println("    ITEM : " + item.getItemName() + " - $" + item.getPrice());
                }
            }
        }
//        saveOrders();
    }

    // PAYMENTS //////////////////////////////////

    // Save payments to a JSON file
    public void savePayments() {
        try {
            fileWriter = new FileWriter(paymentsFilePath);

            JSONObject categoriesJSON = new JSONObject();

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

            fileWriter.write(categoriesJSON.toJSONString());
            System.out.println("PAYMENT JSON CREATED AND SAVED");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Load payments from a JSON file
    public void loadPayments() {
        for (Customer customer : customers) {
            customer.getPayments().clear();
        }
        try {
            JSONParser parser = new JSONParser();
            JSONObject categoriesJSON = (JSONObject) parser.parse(new FileReader(paymentsFilePath));

            System.out.println("LOADING PAYMENTS --------------");

            for (Object categoryKey : categoriesJSON.keySet()) {
                String category = (String) categoryKey;
                JSONArray paymentsArray = (JSONArray) categoriesJSON.get(category);

                for (Object paymentJSON : paymentsArray) {
                    JSONObject paymentObj = (JSONObject) paymentJSON;

                    String ID = (String) paymentObj.get("ID");
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

    // MENU //////////////////////////////////

    // Load menu items from a JSON file
    public void loadMenu() {
        menu.clear(); // Clear the current menu list
        prebuiltPizzas.clear(); // Clear the current prebuilt pizza list

        try {
            JSONParser parser = new JSONParser();
            JSONObject categoriesJSON = (JSONObject) parser.parse(new FileReader(menuFilePath));

            System.out.println("LOADING MENU --------------");

            for (Object categoryKey : categoriesJSON.keySet()) {
                String category = (String) categoryKey;
                JSONArray categoryArray = (JSONArray) categoriesJSON.get(category);

                for (Object menuItemJSON : categoryArray) {
                    JSONObject menuItemObj = (JSONObject) menuItemJSON;

                    String typeID = (String) menuItemObj.get("Type");
                    String itemName = (String) menuItemObj.get("Item_Name");
                    float price = Float.parseFloat(menuItemObj.get("Price").toString());

                    if (category.equalsIgnoreCase("prebuilt")) {
                        PrebuiltPizza prebuiltPizza = loadPrebuiltPizza(menuItemObj);
                        prebuiltPizzas.add(prebuiltPizza);
                    } else {
                        MenuItem menuItem = new MenuItem(typeID, category, itemName, price);
                        menu.add(menuItem);
                    }

                    System.out.println("MENU ITEM : " + category + " : " + itemName + " : $" + price);
                }
            }

            System.out.println("MENU LOADED --------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load a prebuilt pizza from a JSON object
    private PrebuiltPizza loadPrebuiltPizza(JSONObject menuItemObj) {
        String typeID = (String) menuItemObj.get("Type");
        String itemName = (String) menuItemObj.get("Item_Name");
        float price = Float.parseFloat(menuItemObj.get("Price").toString());
        String crustType = (String) menuItemObj.get("Crust");

        // Load toppings
        String toppingsString = (String) menuItemObj.get("Toppings");
        String[] toppingsArray = toppingsString.split(",");

        LinkedList<Ingredient> toppings = new LinkedList<>();
        for (String toppingStr : toppingsArray) {
            String[] toppingParts = toppingStr.split("-");
            String category = toppingParts[0];
            String name = toppingParts[1];
            float toppingPrice = 0.0f; // You may need to adjust this based on your data structure
            Ingredient topping = new Ingredient(typeID, category, name, toppingPrice);
            toppings.add(topping);
        }

        return new PrebuiltPizza(typeID, "PREBUILT", itemName, price, crustType, toppings);
    }

    // Getter for the prebuilt pizzas list
    public LinkedList<PrebuiltPizza> getPrebuiltPizzas() {
        return prebuiltPizzas;
    }

    // Getter for the menu items list
    public LinkedList<MenuItem> getMenu() {
        return menu;
    }
}
