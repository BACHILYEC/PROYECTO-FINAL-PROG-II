package UserInterface.Utility;

import java.awt.*;
import javax.swing.*;

public class StyleConfig {
    public static JLabel tittleConfig() {
        Font tittlefont = new Font("Comic Sans MS", Font.BOLD, 36);
        Color tittleColorPanel = new Color(173, 160, 219);
        JLabel tittle = new JLabel("Liminalis", SwingConstants.CENTER);
        tittle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tittle.setOpaque(true);
        tittle.setBackground(tittleColorPanel);
        tittle.setForeground(Color.BLACK);
        tittle.setFont(tittlefont);
        return tittle;
    }

    public static JLabel questionLabel(String question) {
        Font tittlefont = new Font("Comic Sans MS", Font.BOLD, 20);
        Color tittleColorPanel = new Color(Color.WHITE.getRGB());
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
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        button.setBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setFocusPainted(false);
        return button;
    }
}
