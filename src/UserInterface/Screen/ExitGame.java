package UserInterface.Screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Infrastructure.AppException;
import UserInterface.Utility.StyleConfig;

public class ExitGame {

    
    public static JPanel confirmExitPanel() {
        JPanel confirmPanel = new JPanel(new BorderLayout());
        confirmPanel.setBackground(StyleConfig.ButtonSecondaryPanel());
        confirmPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("¿Estás seguro de que quieres salir?");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(new Color(60, 60, 60));
        confirmPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton yesButton = StyleConfig.createButton("Sí", new Color(220, 53, 69), 100, 50);
        yesButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        yesButton.addActionListener(e -> {
            System.exit(0);
        });

        JButton noButton = StyleConfig.createButton("No", new Color(40, 167, 69), 100, 50);
        noButton.addActionListener(e -> {
            try {
                MainFrame.setContentPane(GameScreen.game());
            } catch (AppException ex) {
                JOptionPane.showMessageDialog(null, "Error al volver al juego: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        confirmPanel.add(buttonPanel, BorderLayout.CENTER);

        return confirmPanel;
    }

}
