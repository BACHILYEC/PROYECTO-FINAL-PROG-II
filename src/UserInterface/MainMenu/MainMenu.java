package UserInterface.MainMenu;

import java.awt.*;
import javax.swing.*;

import DataAccessComponent.DAOs.UserAdminDAO;
import DataAccessComponent.DTOs.UserAdminDTO;

public class MainMenu {
    public static void gameMenu() {
        JFrame main = new JFrame("Liminalis");
        main.setLayout(new BorderLayout());
        Font tittlefont = new Font("Times New Roman", Font.BOLD, 24);
        Font buttonfont = new Font("Arial", Font.PLAIN, 18);
        JLabel tittle = new JLabel("Liminalis", SwingConstants.CENTER);
        tittle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tittle.setFont(tittlefont);
        ImageIcon icon = new ImageIcon("src\\UserInterface\\Resources\\GameIcon.png");
        Image iconGame = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        icon = new ImageIcon(iconGame);
        main.add(new JLabel(icon), BorderLayout.CENTER);
        main.add(tittle, BorderLayout.NORTH);
        JPanel buttons = new JPanel();
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton playButton = new JButton("JUGAR");
        playButton.setBackground(Color.pink);
        playButton.setForeground(Color.white);
        playButton.setBorder(BorderFactory.createEtchedBorder());
        playButton.setFont(buttonfont);
        Dimension buttonPlaySize = new Dimension(200, 50);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setMaximumSize(buttonPlaySize);
        buttons.add(playButton);
        JPanel buttonssecond = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonssecond.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Dimension buttonSecondSize = new Dimension(150, 40);
        JButton leaderboardButton = new JButton("Ver Marcador");
        leaderboardButton.setMaximumSize(buttonSecondSize);
        JButton exitButton = new JButton("Salir");
        exitButton.setMaximumSize(buttonSecondSize);
        JButton accessAdmin = new JButton("Acceso Admin");
        accessAdmin.setMaximumSize(buttonSecondSize);
        buttonssecond.add(leaderboardButton);
        buttonssecond.add(accessAdmin);
        buttonssecond.add(exitButton);
        buttons.add(buttonssecond);
        main.add(buttons, BorderLayout.SOUTH);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(700, 700);
        main.setLocationRelativeTo(null);
        main.setVisible(true);

    }
}