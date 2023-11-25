package gui;

import javax.swing.*;

import config.Config;
import customComponents.CustomLabel;

import java.awt.*;

public class TitlePanel extends JPanel {

    CustomLabel titleLabel; // custom label component

    public TitlePanel() {
        titleLabel = new CustomLabel("DANS SLICES", 48); // TODO: MAKE LOGO

        // center JLabel, container panel is already at the top of the frame so no need to center vertically
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        setLayout(new BorderLayout()); // set layout of this panel to a BorderLayout
        setBackground(Config.getBackgroundColor());

        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // set empty border

        add(titleLabel, BorderLayout.CENTER); // center label
    }
}
