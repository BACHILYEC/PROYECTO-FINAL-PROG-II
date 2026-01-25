package UserInterface.Utility;

import java.awt.*;
import javax.swing.*;

public class StyleConfig {
    public static JLabel tittleConfig() {
        Font tittlefont = new Font("Cooper Black", Font.BOLD, 40);
        Color tittleColorPanel = new Color(173, 160, 219);
        JLabel tittle = new JLabel("LIMINALIS", SwingConstants.LEFT);
        tittle.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 20));
        tittle.setOpaque(false);
        tittle.setBackground(tittleColorPanel);
        tittle.setForeground(Color.WHITE);
        tittle.setFont(tittlefont);
        return tittle;
    }

    public static JLabel questionLabel(String question) {
        Font tittlefont = new Font("Comic Sans MS", Font.BOLD, 18);
        Color tittleColorPanel = new Color(225, 210, 250);
        JLabel tittle = new JLabel(question, SwingConstants.CENTER);
        tittle.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        tittle.setOpaque(true);
        tittle.setBackground(tittleColorPanel);
        tittle.setForeground(Color.BLACK);
        tittle.setFont(tittlefont);
        return tittle;
    }

    public static Color ButtonPrimaryPanel() {
        return new Color(156, 130, 189);
    }

    public static Color ButtonSecondaryPanel() {
        return new Color(199, 186, 212);
    }

    public static Color ButtonPrimary() {
        return new Color(217, 163, 187);
    }

    public static Color ButtonSecondary() {
        return new Color(177, 151, 204);
    }

    public static Color keyboardButtons() {
        return new Color(200, 222, 230);
    }

    public static JButton createButton(String text, Color backgroundColor, int a, int b) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(a, b));
        button.setBackground(backgroundColor);
        button.setForeground(Color.black);
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        button.setBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setFocusPainted(false);
        return button;
    }
}
