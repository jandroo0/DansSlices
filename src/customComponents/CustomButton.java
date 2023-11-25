package customComponents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

    private MouseAdapter customMouseListener;


    public CustomButton(String text, Font font, Color textColor, Color backgroundColor, Color hoverColor, Border borderType) {
        super(text);

        setFont(font);
        setBackground(backgroundColor);
        setForeground(textColor);
        setBorder(borderType);

        customMouseListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                setForeground(backgroundColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(backgroundColor);
                setForeground(textColor);
            }
        };

        addMouseListener(customMouseListener);

    }

    public void setCustomMouseListener(MouseAdapter mouseListener) {
        if (this.customMouseListener != null) {
            removeMouseListener(this.customMouseListener);
        }
        this.customMouseListener = mouseListener;
        addMouseListener(mouseListener);
    }

    @Override
    public String getText() {
        return super.getText();
    }
}
