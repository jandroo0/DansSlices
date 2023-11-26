package gui.login;

import model.Customer;

import java.util.EventListener;

public interface LoginListener extends EventListener {

    public void accountCreatedEvent(Customer customer);
    public void loginEvent();
}
