package customComponents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * CustomButton class extends JButton to create a customizable button with additional features.
 */
public class CustomButton extends JButton {

    // MouseAdapter to handle mouse events for hover effects
    private MouseAdapter customMouseListener;

    /**
     * Constructor to create a CustomButton with the specified text.
     *
     * @param text The text to be displayed on the button.
     */
    public CustomButton(String text) {
        super(text);
    }

    /**
     * Constructor to create a CustomButton with custom appearance and hover effects.
     *
     * @param text           The text to be displayed on the button.
     * @param font           The font of the button text.
     * @param textColor      The color of the button text.
     * @param backgroundColor The background color of the button.
     * @param hoverColor      The background color when the mouse hovers over the button.
     * @param borderType      The border type of the button.
     */
    public CustomButton(String text, Font font, Color textColor, Color backgroundColor, Color hoverColor, Border borderType) {
        // Call the superclass constructor with the specified text
        super(text);

        // Set the specified font, background color, text color, and border type
        setFont(font);
        setBackground(backgroundColor);
        setForeground(textColor);
        setBorder(borderType);

        // Initialize the MouseAdapter for handling mouse hover effects
        customMouseListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change background and text color on mouse enter
                setBackground(hoverColor);
                setForeground(backgroundColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Change background and text color back to original on mouse exit
                setBackground(backgroundColor);
                setForeground(textColor);
            }
        };

        // Add the MouseAdapter to the button to enable hover effects
        addMouseListener(customMouseListener);
    }

    /**
     * Setter method to update the MouseAdapter for mouse event handling.
     *
     * @param mouseListener The new MouseAdapter to handle mouse events.
     */
    public void setCustomMouseListener(MouseAdapter mouseListener) {
        // Remove the existing MouseAdapter if it exists
        if (this.customMouseListener != null) {
            removeMouseListener(this.customMouseListener);
        }

        // Set the new MouseAdapter and add it to the button
        this.customMouseListener = mouseListener;
        addMouseListener(mouseListener);
    }

    /**
     * Overridden method to get the text of the button.
     *
     * @return The text of the button.
     */
    @Override
    public String getText() {
        return super.getText();
    }
}
