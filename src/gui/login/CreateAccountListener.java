package gui.login;

//import gui.login.createAccount.event.CreateAccountEvent;

import model.Customer;

import java.io.IOException;
import java.util.EventListener;

public interface CreateAccountListener extends EventListener {

    void createAccount(Customer newCustomer) throws IOException;

    void cancelEvent();
}
