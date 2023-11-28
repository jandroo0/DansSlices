package gui.home.menu;

import model.Order;

import java.util.EventListener;

public interface MenuListener extends EventListener {

    void newOrderEvent(Order order);
}
