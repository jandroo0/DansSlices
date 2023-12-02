package customComponents;

import config.Config;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

@SuppressWarnings("serial")
public class CustomPlaceholderField extends JTextField {

    // Fields
    private String placeholder;
    private int fontSize;

    // Constructors

    // Constructor for creating a text field with set width, height, and fontSize
    public CustomPlaceholderField(int width, int height, int fontSize) {
        // Set basic properties
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createLineBorder(Config.getTextColor()));
        setHorizontalAlignment(JTextField.CENTER);
        setForeground(Config.getTextColor());
        setBackground(Config.getTextFieldColor());
        setFont(Config.getTextFont(fontSize));  // Set font with the specified size
        setDocument(new JTextFieldLimit(14));  // Limit the number of characters in the text field

        this.fontSize = fontSize;
    }

    // Constructor with a placeholder
    public CustomPlaceholderField(final String pText, int width, int height, int fontSize) {
        this(width, height, fontSize);  // Call the default constructor
        this.placeholder = pText;  // Set the placeholder text
    }

    // Constructor with text limit
    public CustomPlaceholderField(int width, int height, int fontSize, int limit) {
        this(width, height, fontSize);  // Call the default constructor
        setDocument(new JTextFieldLimit(limit));  // Set the text limit
    }

    // Constructor with placeholder and text limit
    public CustomPlaceholderField(final String pText, int width, int height, int fontSize, int limit) {
        this(width, height, fontSize);  // Call the default constructor
        setDocument(new JTextFieldLimit(limit));  // Set the text limit
        this.placeholder = pText;  // Set the placeholder text
    }

    // Constructor with int filter and text limit
    public CustomPlaceholderField(int width, int height, int fontSize, boolean intFilter, int limit) {
        this(width, height, fontSize);  // Call the default constructor
        if (intFilter) {
            PlainDocument doc = (PlainDocument) getDocument();
            doc.setDocumentFilter(new MyIntFilter(limit));  // Apply integer filter
        }
    }

    // Constructor with placeholder, int filter, and text limit
    public CustomPlaceholderField(final String pText, int width, int height, int fontSize, boolean intFilter, int limit) {
        this(width, height, fontSize);  // Call the default constructor
        if (intFilter) {
            PlainDocument doc = (PlainDocument) getDocument();
            doc.setDocumentFilter(new MyIntFilter(limit));  // Apply integer filter
        }
        this.placeholder = pText;  // Set the placeholder text
    }

    // Constructor with placeholder, text limit, and echoChar
    public CustomPlaceholderField(final String pText, int width, int height, int fontSize, int limit, char echoChar) {
        this(width, height, fontSize);  // Call the default constructor
        setDocument(new JTextFieldLimit(limit));  // Set the text limit
        this.placeholder = pText;  // Set the placeholder text
    }

    // Getter for placeholder
    public String getPlaceholder() {
        return placeholder;
    }

    // Setter for placeholder
    public void setPlaceholder(final String s) {
        placeholder = s;
    }

    // Override paintComponent method to paint the placeholder text
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;  // If no placeholder or text present, do nothing
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Config.getTextColor());
        g.setFont(Config.getTextFont(fontSize));
        // Draw the placeholder text at the center of the text field
        g.drawString(placeholder, getWidth() / 2 - g.getFontMetrics().stringWidth(placeholder) / 2,
                pG.getFontMetrics().getMaxAscent() + getInsets().top);
    }

    // Inner class for filtering only integer input with a specified limit
    public static class MyIntFilter extends DocumentFilter {
        private final int limit;

        // Constructor with the limit parameter
        public MyIntFilter(int limit) {
            super();
            this.limit = limit;
        }

        // Override insertString method to filter and insert text
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (test(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            } else {
                // Warn the user and don't allow the insert
            }
        }

        // Helper method to test if the text is a valid integer within the limit
        private boolean test(String text) {
            try {
                if (text.length() <= limit) {
                    Long.parseLong(text);
                    return true;
                }
                return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        // Override replace method to filter and replace text
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (test(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                // Warn the user and don't allow the insert
            }
        }

        // Override remove method to handle removal of text
        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (sb.toString().length() == 0) {
                super.replace(fb, offset, length, "", null);
            } else {
                if (test(sb.toString())) {
                    super.remove(fb, offset, length);
                } else {
                    // Warn the user and don't allow the insert
                }
            }
        }
    }

    // Inner class to set character limit for the text field
    static class JTextFieldLimit extends PlainDocument {
        private final int limit;

        // Constructor with the limit parameter
        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        // Override insertString method to limit the number of characters
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}
