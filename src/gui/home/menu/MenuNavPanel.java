package gui.home.menu;

import config.Config;
import customComponents.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MenuNavPanel extends JPanel {

    private final List<CustomButton> buttons;
    private JPanel buttonsPanel;
    private MenuNavListener listener;
    private GridBagConstraints gc;

    public MenuNavPanel() {
        buttons = new ArrayList<>();
        setLayout(new BorderLayout());
        setBackground(Config.getBackgroundColor());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 4, 0, 2),
                BorderFactory.createLineBorder(Config.getTextColor(), 2, true)));
//        setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 2));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.insets = new Insets(0, 0, 12, 0);
        buttonsPanel.setBackground(Config.getBackgroundColor());

        add(buttonsPanel, BorderLayout.CENTER);

    }


    public void setButtonHoverEffect(String currentMenu) {

        for (CustomButton button : buttons) {
            button.setCustomMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (button.getText().equals(currentMenu)) {
                        button.setBackground(Config.getButtonBackgroundColor());
                        button.setForeground(Config.getTextColor());
                    } else {
                        button.setBackground(Config.getButtonHoverColor());
                        button.setForeground(Config.getButtonBackgroundColor());
                    }

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!button.getText().equals(currentMenu)) {
                        button.setBackground(Config.getButtonBackgroundColor());
                        button.setForeground(Config.getTextColor());
                    } else {
                        button.setBackground(Config.getButtonHoverColor());
                        button.setForeground(Config.getButtonBackgroundColor());
                    }

                }
            });
        }
    }

    public void addButton(String text) {

        CustomButton button = new CustomButton(text, Config.getTextFont(12), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(), BorderFactory.createEmptyBorder(3, 6, 3, 6));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.navbarClicked(e.getActionCommand());

            }
        });
        buttons.add(button);
        gc.gridy++;
        buttonsPanel.add(button, gc);
        revalidate();
        repaint();
    }

    public void removeButton(String text) {
        for (int i = 0; i < buttons.size(); i++) {
            CustomButton button = buttons.get(i);
            if (button.getText().equals(text)) {
                buttons.remove(i);
                remove(button);
                revalidate();
                repaint();
                break;
            }
        }
    }


    public void setButtonColors(String panelName) {
        for (CustomButton button : buttons) {

            if (button.getText().equals(panelName)) {
                button.setBackground(Config.getButtonHoverColor());
                button.setForeground(Config.getButtonBackgroundColor());
            } else {
                button.setBackground(Config.getButtonBackgroundColor());
                button.setForeground(Config.getTextColor());
            }
        }
    }

    public void removeAllButtons() {
        buttons.clear();
        removeAll();
        revalidate();
        repaint();
    }


    public void setMenuNavListener(MenuNavListener listener) {
        this.listener = listener;
    }

}
