package UserInterface.Screen;

import java.awt.*;
import javax.swing.*;
import UserInterface.Utility.AppConfig;
import UserInterface.Utility.ReusableMethods;

public class MainMenu {
    private static JComponent[][] buttons;

    public static JPanel gameMenu() {
        JPanel main = new JPanel(new BorderLayout());
        JLabel tittle = AppConfig.tittleConfig();
        ImageIcon Backgroundicon = new ImageIcon("src\\UserInterface\\Resources\\LittleBackground.png");
        Image iconGame = Backgroundicon.getImage().getScaledInstance(700, 464, Image.SCALE_SMOOTH);
        Backgroundicon = new ImageIcon(iconGame);
        main.add(tittle, BorderLayout.NORTH);
        JLabel iconLabel = new JLabel(Backgroundicon);
        iconLabel.setOpaque(false);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        main.add(iconLabel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        JPanel buttonPlay = new JPanel();
        buttonPlay.setOpaque(true);
        buttonPlay.setBackground(AppConfig.ButtonPrimaryPanel());
        buttonPlay.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton playButton = AppConfig.createButton("Jugar", AppConfig.ButtonPrimary(), 200, 50);
        buttonPlay.add(playButton);
        JPanel buttonssecond = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonssecond.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonssecond.setOpaque(true);
        buttonssecond.setBackground(AppConfig.ButtonSecondaryPanel());
        JButton leaderboardButton = AppConfig.createButton("Marcador", AppConfig.ButtonSecondary(), 150, 40);
        leaderboardButton.addActionListener(e -> {
            String[] columnNames = { "Usuario", "Score" };
            JPanel panel = new JPanel();
            JScrollPane tableScrollPane = ReusableMethods.createTableAdmin(columnNames, panel, true);
            panel.setLayout(new BorderLayout());
            panel.add(tableScrollPane, BorderLayout.CENTER);
            JOptionPane.showMessageDialog(panel, panel, "Marcador De Jugadores",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        JButton exitButton = AppConfig.createButton("Salir", AppConfig.ButtonSecondary(), 150, 40);
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        JButton accessAdmin = AppConfig.createButton("Acceso Admin", AppConfig.ButtonSecondary(), 150, 40);
        accessAdmin.addActionListener(e -> {
            MainFrame.setContentPane(LoginScreen.loginPanel());
        });
        buttonssecond.add(leaderboardButton);
        buttonssecond.add(accessAdmin);
        buttonssecond.add(exitButton);
        southPanel.add(buttonPlay);
        southPanel.add(buttonssecond);
        main.add(southPanel, BorderLayout.SOUTH);

        buttons = new JComponent[][] { { playButton }, { leaderboardButton, accessAdmin, exitButton } };
        ControllerDualsense ControllerDualsense = new ControllerDualsense();
        ControllerDualsense.setupKeyBindings(main, buttons);

        ControllerDualsense.focusComponent(ControllerDualsense.getCurrentIndexX(),
                ControllerDualsense.getCurrentIndexY(),
                buttons);

        return main;
    }
}