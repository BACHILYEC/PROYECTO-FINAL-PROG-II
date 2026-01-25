package UserInterface.Screen;

import java.awt.*;
import javax.swing.*;

import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;
import UserInterface.Utility.StyleConfig;

public class ScreenLosing {
    public static JPanel losingScreen(int score, Boolean end) {
        JPanel losingPanel = new JPanel(new BorderLayout());
        Font tittlefont = new Font("Comic Sans MS", Font.BOLD, 20);
        JLabel tittle = StyleConfig.tittleConfig();
        losingPanel.add(tittle, BorderLayout.NORTH);
        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel(
                ReusableMethods.getImageTranslucent());
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        JPanel endPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        endPanel.setOpaque(false);
        endPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        JLabel endLabel = new JLabel(end ? "GANASTE" : "PERDISTE", SwingConstants.CENTER);
        endLabel.setFont(new Font("Comic Sans MC", Font.BOLD, 30));
        endLabel.setForeground(end ? Color.RED : Color.GREEN);
        endPanel.add(endLabel);
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        scorePanel.setOpaque(false);
        JLabel scoreLabel = new JLabel("Tu puntaje final es: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(tittlefont);
        scorePanel.add(scoreLabel);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);
        JButton retryButton = StyleConfig.createButton("Volver a Jugar", StyleConfig.ButtonPrimary(), 150, 40);
        retryButton.addActionListener(e -> {
            try {
                MainFrame.setContentPane(GameScreen.game());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al iniciar el juego: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton exitButton = StyleConfig.createButton("Volver al Menú", StyleConfig.ButtonPrimary(), 150, 40);
        exitButton.addActionListener(e -> {
            MainFrame.setContentPane(MainMenu.gameMenu());
        });
        buttonPanel.add(retryButton);
        buttonPanel.add(exitButton);
        centerPanel.add(endPanel);
        centerPanel.add(scorePanel);
        centerPanel.add(buttonPanel);
        backgroundPanel.add(centerPanel);

        losingPanel.add(backgroundPanel, BorderLayout.CENTER);
        return losingPanel;
    }
}
