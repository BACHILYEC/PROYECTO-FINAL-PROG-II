package UserInterface.Screen;

import java.awt.*;
import javax.swing.*;

import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;
import UserInterface.Utility.StyleConfig;

public class ScreenLosing {
    public static JPanel losingScreen(int score, Boolean end) {
        Font tittlefont = new Font("Comic Sans MS", Font.BOLD, 30);
        JLabel tittle = StyleConfig.tittleConfig();
        JPanel tittlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tittlePanel.setOpaque(false);
        tittlePanel.add(tittle);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel(
                ReusableMethods.getImageBackground());
        backgroundPanel.add(tittlePanel);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        JPanel endPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        endPanel.setOpaque(false);
        endPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel endLabel = new JLabel(end ? "GANASTE" : "PERDISTE", SwingConstants.CENTER);
        endLabel.setFont(new Font("Comic Sans MC", Font.BOLD, 50));
        endLabel.setForeground(end ? Color.GREEN : Color.RED);
        endPanel.add(endLabel);
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        scorePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scorePanel.setOpaque(false);
        JLabel scoreLabel = new JLabel("Tu puntaje final es: " + score, SwingConstants.CENTER);
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 100, 10));
        scoreLabel.setForeground(Color.black);
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
        JComponent[][] buttons = { { retryButton, exitButton } };
        ControllerDualsense controllerDualsense = new ControllerDualsense();
        controllerDualsense.setupKeyBindings(buttonPanel, buttons);
        controllerDualsense.focusComponent(controllerDualsense.getCurrentIndexX(),
                controllerDualsense.getCurrentIndexY(), buttons);
        buttonPanel.add(retryButton);
        buttonPanel.add(exitButton);
        centerPanel.add(endPanel);
        centerPanel.add(scorePanel);
        centerPanel.add(buttonPanel);
        backgroundPanel.add(centerPanel);

        return backgroundPanel;
    }
}
