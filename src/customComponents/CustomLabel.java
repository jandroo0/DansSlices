package customComponents;

import config.Config;
import javax.swing.*;

public class CustomLabel extends JLabel {

    // create JLabel with text and different size
    public CustomLabel(String text, int size) {
        super(text);

        // use set font
        setFont(Config.getTextFont(size));
    }


}
