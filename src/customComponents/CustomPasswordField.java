package customComponents;

import config.Config;

import javax.swing.*;
import java.awt.*;

public class CustomPasswordField extends JPasswordField {

    private String placeholder;
    private int fontSize;

    public CustomPasswordField(int width, int height, int fontSize) {
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createLineBorder(Config.getTextColor()));

        setHorizontalAlignment(JTextField.CENTER);
        setForeground(Config.getTextColor());
        setBackground(Config.getTextFieldColor());
        setFont(Config.getTextFont(fontSize)); // set font with size

        setEchoChar('*');
        this.fontSize = fontSize;
    }

    public CustomPasswordField(String placeholder, int width, int height, int fontSize) {
        this(width, height, fontSize);
        this.placeholder = placeholder;

    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Config.getTextColor());
        g.setFont(Config.getTextFont(fontSize));
        g.drawString(placeholder, getWidth() / 2 - g.getFontMetrics().stringWidth(placeholder) / 2, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }
}
