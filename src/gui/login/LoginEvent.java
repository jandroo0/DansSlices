package gui.login;

import java.util.EventObject;


/**
 * Event object for the employee/customer login event which will take the given ID in the JTextField on the login panel
 **/
public class LoginEvent extends EventObject {

    private String ID;

    public LoginEvent(Object source, String ID) {
        super(source);
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
