package UserInterface.Screen;

import java.awt.*;

import javax.swing.*;

import Infrastructure.AppException;
import UserInterface.Utility.StyleConfig;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;

public class MainMenu {
    private static JComponent[][] buttons;

    public static JPanel gameMenu() {
        JPanel main = new JPanel(new BorderLayout());
        JLabel tittle = StyleConfig.titleConfig();
        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel(
                ReusableMethods.getImageBackground());
        backgroundPanel.add(tittle);
        main.add(backgroundPanel, BorderLayout.CENTER);
        JPanel buttonPlay = new JPanel();
        buttonPlay.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
        buttonPlay.setOpaque(false);
        buttonPlay.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton playButton = StyleConfig.createButton("Iniciar Juego", StyleConfig.buttonPrimary(), 500, 100);
        playButton.setFont(new Font("Cooper Black", Font.PLAIN, 25));
        playButton.addActionListener(e -> {
            try {
                MainFrame.setContentPane(CreatePlayer.createPlayerPanel());
            } catch (Exception ex) {
                throw new RuntimeException(new AppException("Error al cargar la pantalla de crear jugador", ex,
                        MainMenu.class, "gameMenu"));
            }
        });
        buttonPlay.add(playButton);
        JPanel buttonssecond = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonssecond.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonssecond.setOpaque(false);
        JButton leaderboardButton = StyleConfig.createButton("Marcador", StyleConfig.buttonSecondary(), 150, 40);
        leaderboardButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
        leaderboardButton.addActionListener(e -> {
            String[] columnNames = { "Id Jugador", "Jugador", "Puntuación", "Ultima Jugada" };
            JPanel panel = new JPanel();
            JScrollPane tableScrollPane = ReusableMethods.createTablePlayer(columnNames, panel, true);
            panel.setLayout(new BorderLayout());
            panel.add(tableScrollPane, BorderLayout.CENTER);
            JOptionPane.showMessageDialog(panel, panel, "Marcador De Jugadores",
                    JOptionPane.INFORMATION_MESSAGE);

        });
        JButton exitButton = StyleConfig.createButton("Salir", StyleConfig.buttonSecondary(), 150, 40);
        exitButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        JButton accessAdmin = StyleConfig.createButton("Acceso Admin", StyleConfig.buttonSecondary(), 150, 40);
        accessAdmin.setFont(new Font("Cooper Black", Font.PLAIN, 15));
        accessAdmin.addActionListener(e -> {
            MainFrame.setContentPane(LoginScreen.loginPanel());
        });
        buttonssecond.add(leaderboardButton);
        buttonssecond.add(accessAdmin);
        buttonssecond.add(exitButton);
        backgroundPanel.add(buttonPlay);
        backgroundPanel.add(buttonssecond);
        JPanel icon = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ImageIcon icontittle = new ImageIcon(ReusableMethods.getTitle());
        JLabel labelIcon = new JLabel(icontittle);
        icon.setOpaque(false);
        icon.add(labelIcon);
        backgroundPanel.add(icon);
        main.add(backgroundPanel, BorderLayout.CENTER);

        buttons = new JComponent[][] { { playButton, playButton, playButton },
                { leaderboardButton, accessAdmin, exitButton } };
        ControllerDualsense ControllerDualsense = new ControllerDualsense();
        ControllerDualsense.setupKeyBindings(main, buttons);

        ControllerDualsense.focusComponent(ControllerDualsense.getCurrentIndexX(),
                ControllerDualsense.getCurrentIndexY(),
                buttons);

        return main;
    }
}