package UserInterface.Screen;

import java.awt.*;

import javax.swing.*;

import UserInterface.Utility.StyleConfig;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;

public class MainMenu {
    private static JComponent[][] buttons;

    public static JPanel gameMenu() {
        JPanel main = new JPanel(new BorderLayout());
        JLabel tittle = StyleConfig.tittleConfig();
        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel(
                ReusableMethods.getImageBackground());
        main.add(tittle, BorderLayout.NORTH);
        main.add(backgroundPanel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        JPanel buttonPlay = new JPanel();
        buttonPlay.setOpaque(true);
        buttonPlay.setBackground(StyleConfig.ButtonPrimaryPanel());
        buttonPlay.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton playButton = StyleConfig.createButton("Jugar", StyleConfig.ButtonPrimary(), 200, 50);
        playButton.addActionListener(e -> {
            MainFrame.setContentPane(CreatePlayer.createPlayerPanel());
        });
        buttonPlay.add(playButton);
        JPanel buttonssecond = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonssecond.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonssecond.setOpaque(true);
        buttonssecond.setBackground(StyleConfig.ButtonSecondaryPanel());
        JButton leaderboardButton = StyleConfig.createButton("Marcador", StyleConfig.ButtonSecondary(), 150, 40);
        leaderboardButton.addActionListener(e -> {
            String[] columnNames = { "Id Jugador", "Jugador", "Puntuación", "Ultima Jugada" };
            JPanel panel = new JPanel();
            JScrollPane tableScrollPane = ReusableMethods.createTablePlayer(columnNames, panel, true);
            panel.setLayout(new BorderLayout());
            panel.add(tableScrollPane, BorderLayout.CENTER);
            JOptionPane.showMessageDialog(panel, panel, "Marcador De Jugadores",
                    JOptionPane.INFORMATION_MESSAGE);

        });
        JButton exitButton = StyleConfig.createButton("Salir", StyleConfig.ButtonSecondary(), 150, 40);
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        JButton accessAdmin = StyleConfig.createButton("Acceso Admin", StyleConfig.ButtonSecondary(), 150, 40);
        accessAdmin.addActionListener(e -> {
            MainFrame.setContentPane(LoginScreen.loginPanel());
        });
        buttonssecond.add(leaderboardButton);
        buttonssecond.add(accessAdmin);
        buttonssecond.add(exitButton);
        southPanel.add(buttonPlay);
        southPanel.add(buttonssecond);
        main.add(southPanel, BorderLayout.SOUTH);

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