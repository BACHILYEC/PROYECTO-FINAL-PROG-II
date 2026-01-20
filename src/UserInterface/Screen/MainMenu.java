package UserInterface.Screen;

import java.awt.*;

import javax.swing.*;

import UserInterface.Utility.ReusableMethods;

public class MainMenu {
    public static void gameMenu() {
        JFrame mainFrame = new JFrame("Liminalis");
        JPanel main = new JPanel(new BorderLayout());
        Font tittlefont = new Font("Comic Sans MS", Font.BOLD, 36);
        Font buttonFont = new Font("Comic Sans MS", Font.PLAIN, 18);
        Color tittleColorPanel = new Color(173, 160, 219);
        Color iconColorPanel = new Color(255, 255, 255);
        Color playColorPanel = new Color(156, 130, 189);
        Color secondColorPanel = new Color(199, 186, 212);
        Color colorplayButton = new Color(217, 163, 187);
        Color colorsecondButton = new Color(177, 151, 204);
        JLabel tittle = new JLabel("Liminalis", SwingConstants.CENTER);
        tittle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tittle.setOpaque(true);
        tittle.setBackground(tittleColorPanel);
        tittle.setForeground(Color.WHITE);
        tittle.setFont(tittlefont);
        ImageIcon Backgroundicon = new ImageIcon("src\\UserInterface\\Resources\\LittleBackground.png");
        Image iconGame = Backgroundicon.getImage().getScaledInstance(700, 464, Image.SCALE_SMOOTH);
        Backgroundicon = new ImageIcon(iconGame);
        main.add(tittle, BorderLayout.NORTH);
        JLabel iconLabel = new JLabel(Backgroundicon);
        iconLabel.setOpaque(false);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        iconLabel.setBackground(iconColorPanel);
        main.add(iconLabel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        JPanel buttonPlay = new JPanel();
        buttonPlay.setOpaque(true);
        buttonPlay.setBackground(playColorPanel);
        buttonPlay.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton playButton = new JButton("JUGAR");
        playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        playButton.setBackground(colorplayButton);
        playButton.setForeground(Color.black);
        playButton.setFont(buttonFont);
        playButton.setFocusPainted(false);
        Dimension buttonPlaySize = new Dimension(200, 50);
        playButton.setPreferredSize(buttonPlaySize);
        buttonPlay.add(playButton);
        JPanel buttonssecond = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonssecond.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonssecond.setOpaque(true);
        buttonssecond.setBackground(secondColorPanel);
        Dimension buttonSecondSize = new Dimension(150, 40);
        JButton leaderboardButton = new JButton("Ver Marcador");
        leaderboardButton.setPreferredSize(buttonSecondSize);
        leaderboardButton.setBackground(colorsecondButton);
        leaderboardButton.setForeground(Color.black);
        leaderboardButton.setFont(buttonFont);
        leaderboardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        leaderboardButton.addActionListener(e -> {
            String[] columnNames = { "Usuario", "Score" };
            JPanel panel = new JPanel();
            JScrollPane tableScrollPane = ReusableMethods.createTableAdmin(columnNames, panel, true);
            panel.setLayout(new BorderLayout());
            panel.add(tableScrollPane, BorderLayout.CENTER);
            JOptionPane.showMessageDialog(mainFrame, panel, "Marcador De Jugadores",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        JButton exitButton = new JButton("Salir");
        exitButton.setPreferredSize(buttonSecondSize);
        exitButton.setBackground(colorsecondButton);
        exitButton.setForeground(Color.black);
        exitButton.setFont(buttonFont);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JButton accessAdmin = new JButton("Acceso Admin");
        accessAdmin.setPreferredSize(buttonSecondSize);
        accessAdmin.setBackground(colorsecondButton);
        accessAdmin.setForeground(Color.black);
        accessAdmin.setFont(buttonFont);
        accessAdmin.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        accessAdmin.addActionListener(e -> {
            ReusableMethods.setContentPane(LoginScreen.loginPanel(), mainFrame);
        });
        buttonssecond.add(leaderboardButton);
        buttonssecond.add(accessAdmin);
        buttonssecond.add(exitButton);
        southPanel.add(buttonPlay);
        southPanel.add(buttonssecond);
        main.add(southPanel, BorderLayout.SOUTH);
        mainFrame.add(main);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }
}