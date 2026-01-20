package UserInterface.Screen;

import java.awt.*;
import javax.swing.*;

public class LoginScreen {
    public static JPanel loginPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel tittle = new JLabel("Liminalis", SwingConstants.CENTER);
        Font tittlefont = new Font("Comic Sans MS", Font.BOLD, 36);
        Color tittleColorPanel = new Color(173, 160, 219);
        Color LoginColor = new Color(199, 186, 212);
        tittle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tittle.setOpaque(true);
        tittle.setBackground(tittleColorPanel);
        tittle.setForeground(Color.WHITE);
        tittle.setFont(tittlefont);
        JPanel Textmain = new JPanel();
        Textmain.setLayout(new BoxLayout(Textmain, BoxLayout.Y_AXIS));
        JTextField usernameField = new JTextField(10);
        JPanel userLogin = new JPanel(new FlowLayout());
        userLogin.add(new JLabel("Username:"));
        userLogin.add(usernameField);
        JPasswordField passwordField = new JPasswordField(10);
        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.add(new JLabel("Password:"));
        passwordPanel.add(passwordField);
        Textmain.add(userLogin);
        Textmain.add(passwordPanel);
        mainPanel.add(tittle, BorderLayout.NORTH);
        mainPanel.add(Textmain, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        Font buttonFont = new Font("Comic Sans MS", Font.PLAIN, 18);
        loginButton.setOpaque(true);
        loginButton.setBackground(LoginColor);
        loginButton.setFont(buttonFont);
        buttonPanel.add(loginButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        return mainPanel;
    }
}
