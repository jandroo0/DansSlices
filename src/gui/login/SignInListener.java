package gui.login;

import java.util.EventListener;

public interface SignInListener extends EventListener {

    void signInEvent(String phoneNumber, String password);

    void newCustomerEvent();
}
