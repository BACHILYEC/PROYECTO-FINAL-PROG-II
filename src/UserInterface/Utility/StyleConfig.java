package UserInterface.Utility;

import java.awt.*;
import javax.swing.*;

public class StyleConfig {
    public static JLabel titleConfig() {
        Font titleFont = new Font("Cooper Black", Font.BOLD, 40);
        Color titleColorPanel = new Color(173, 160, 219);
        JLabel title = new JLabel("LIMINALIS", SwingConstants.LEFT);
        title.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 20));
        title.setOpaque(false);
        title.setBackground(titleColorPanel);
        title.setForeground(Color.WHITE);
        title.setFont(titleFont);
        return title;
    }

    public static JLabel questionLabel(String question) {
        Font titleFont = new Font("Comic Sans MS", Font.BOLD, 18);
        Color titleColorPanel = new Color(225, 210, 250);
        JLabel title = new JLabel(question, SwingConstants.CENTER);
        title.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        title.setOpaque(true);
        title.setBackground(titleColorPanel);
        title.setForeground(Color.BLACK);
        title.setFont(titleFont);
        return title;
    }

    public static Color buttonPrimary() {
        return new Color(217, 163, 187);
    }

    public static Color buttonSecondary() {
        return new Color(171, 157, 204);
    }

    public static Color keyboardButtons() {
        return new Color(218, 209, 237);
    }

    public static JButton createButton(String text, Color backgroundColor, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
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
