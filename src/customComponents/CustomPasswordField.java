package customComponents;

import config.Config;

import javax.swing.*;
import java.awt.*;

/**
 * CustomPasswordField class extends JPasswordField to create a password field with customizable features.
 */
public class CustomPasswordField extends JPasswordField {

    // Fields for storing placeholder text and font size
    private String placeholder;
    private int fontSize;

    /**
     * Constructor for creating a CustomPasswordField with specified dimensions and font size.
     *
     * @param width     The width of the password field.
     * @param height    The height of the password field.
     * @param fontSize  The font size of the text in the password field.
     */
    public CustomPasswordField(int width, int height, int fontSize) {
        // Set preferred size and border color based on Config class
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createLineBorder(Config.getTextColor()));

        // Set text alignment, text color, background color, and font based on Config class
        setHorizontalAlignment(JTextField.CENTER);
        setForeground(Config.getTextColor());
        setBackground(Config.getTextFieldColor());
        setFont(Config.getTextFont(fontSize)); // set font with size

        // Set echo character for password masking
        setEchoChar('*');
        this.fontSize = fontSize; // Store the font size
    }

    /**
     * Constructor for creating a CustomPasswordField with a placeholder, dimensions, and font size.
     *
     * @param placeholder The placeholder text to be displayed when the field is empty.
     * @param width       The width of the password field.
     * @param height      The height of the password field.
     * @param fontSize    The font size of the text in the password field.
     */
    public CustomPasswordField(String placeholder, int width, int height, int fontSize) {
        this(width, height, fontSize); // Call the parameterized constructor for common initialization
        this.placeholder = placeholder; // Set the placeholder text
    }

    /**
     * Override the paintComponent method to customize the appearance of the password field.
     *
     * @param pG The graphics context to paint.
     */
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        // If there is no placeholder or the field is not empty, do nothing
        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        // Cast graphics context to Graphics2D for advanced rendering
        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Set color, font, and draw the placeholder text at the center of the field
        g.setColor(Config.getTextColor());
        g.setFont(Config.getTextFont(fontSize));
        g.drawString(placeholder, getWidth() / 2 - g.getFontMetrics().stringWidth(placeholder) / 2,
                pG.getFontMetrics().getMaxAscent() + getInsets().top);
    }
}
