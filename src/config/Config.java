package config;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Config {

    // CONFIGURATION //

    private static final int WIDTH = 720; // main frame width
    private static final int HEIGHT = 880; // main frame height


    // COLORS //

    private static final Color backgroundColor = Color.WHITE; // background color

    // TEXT

    private static final Color titleLabelColor = Color.BLACK; // title label color
    private static final Color textColor = Color.BLACK; // text color

    // INPUT
    private static final Color textFieldColor = new Color(204, 222, 211); // text field color


    // BUTTON
    private static final Color buttonHoverColor = new Color(232, 191, 139); // button hover color
    private static final Color buttonBackgroundColor = new Color(251, 251, 212); // button background color

    private static final Border buttonBorder = BorderFactory.createEmptyBorder(6, 9, 6, 9); // default button size

    // FONT

    private static final Font textFont = new Font("Serif", Font.BOLD, 16); // default text font


    // GETTERS ////////////////////////////////

    // get frame width
    public static int getWIDTH() {
        return WIDTH;
    }
    // get frame height
    public static int getHEIGHT() {
        return HEIGHT;
    }
    // COLORS //

    public static Color getBackgroundColor() { // title label color
        return backgroundColor;
    }


    // TEXT
    public static Color getTitleLabelColor() { // title label color
        return titleLabelColor;
    }

    public static Color getTextColor() { // text color
        return textColor;
    }

    // INPUT
    public static Color getTextFieldColor() { // text field color
        return textFieldColor;
    }
    // BUTTON
    public static Color getButtonBackgroundColor() { // text field color
        return buttonBackgroundColor;
    }
    public static Color getButtonHoverColor() { // text field color
        return buttonHoverColor;
    }

    public static Border getButtonBorder() { // button border
        return buttonBorder;
    }





    // FONT //

    // get default text font
    public static final Font getTextFont() {
        return textFont;
    }

    // get resized text font
    public static Font getTextFont(int size) {
        return new Font(textFont.getFontName(), textFont.getStyle(), size);
    }





}
