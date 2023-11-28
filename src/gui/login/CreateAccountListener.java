package gui.login;

//import gui.login.createAccount.event.CreateAccountEvent;

import model.Customer;

import java.util.EventListener;

public interface CreateAccountListener extends EventListener {

    void createAccount(Customer newCustomer);

    void cancelEvent();
}
