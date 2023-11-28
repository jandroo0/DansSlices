package gui.home.menu;

import model.MenuItem;

import java.util.EventListener;

public interface AddToCartListener extends EventListener {

    void itemAdded(MenuItem item);

}
