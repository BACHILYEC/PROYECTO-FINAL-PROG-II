package UserInterface.Screen;

import java.awt.*;

import javax.swing.*;

import DataAccessComponent.DAOs.UserAdminDAO;
import DataAccessComponent.DTOs.UserAdminDTO;
import UserInterface.Utility.ImageBackgroundPanel;

public class LoginScreen {
    public static JPanel loginPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel tittle = new JLabel("Liminalis", SwingConstants.CENTER);
        Font tittlefont = new Font("Comic Sans MS", Font.BOLD, 36);
        Font login = new Font("Comic Sans MS", Font.BOLD, 18);
        Color tittleColorPanel = new Color(173, 160, 219);
        Color PanelButton = new Color(156, 130, 189);
        tittle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tittle.setOpaque(true);
        tittle.setBackground(tittleColorPanel);
        tittle.setForeground(Color.BLACK);
        tittle.setFont(tittlefont);
        JPanel Textmain = new JPanel(new BorderLayout());
        ImageBackgroundPanel GetCredencials = new ImageBackgroundPanel(
                "src\\UserInterface\\Resources\\ImagenBackGroundLogin.png");
        JTextField usernameField = new JTextField(10);
        JPanel userLogin = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        userLogin.setOpaque(false);
        userLogin.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(login);
        userLogin.add(usernameLabel);
        userLogin.add(usernameField);
        JPasswordField passwordField = new JPasswordField(10);
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        passwordPanel.setOpaque(false);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(login);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        GetCredencials.add(Box.createVerticalGlue());
        GetCredencials.add(userLogin);
        GetCredencials.add(Box.createVerticalGlue());
        GetCredencials.add(passwordPanel);
        Textmain.add(GetCredencials, BorderLayout.CENTER);
        mainPanel.add(tittle, BorderLayout.NORTH);
        mainPanel.add(Textmain, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton loginButton = new JButton("Login");
        loginButton.setFocusPainted(false);
        Font buttonFont = new Font("Comic Sans MS", Font.PLAIN, 18);
        loginButton.setOpaque(true);
        loginButton.setBackground(Color.WHITE);
        loginButton.setFont(buttonFont);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 50, 10));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(PanelButton);
        buttonPanel.add(loginButton);
        loginButton.addActionListener(a -> {
            UserAdminDAO dao = new UserAdminDAO();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            try {
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor complete todos los campos.");
                    return;
                } else {
                    try {
                        UserAdminDTO dto = dao.readByName(username);
                        if (dto.getPassword().equals(password)) {
                            JOptionPane.showMessageDialog(mainPanel, "Acceso Permitido");
                            MainFrame.setContentPane(ScreenAdmin.MenuAdmin());
                            return;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(mainPanel, "Usuario o contraseña incorrectos.");
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mainPanel, "Error: " + e.getMessage());
            }
        });
        JButton GoToBack = new JButton("Regresar");
        GoToBack.setOpaque(true);
        GoToBack.setBackground(Color.WHITE);
        GoToBack.setFont(buttonFont);
        GoToBack.addActionListener(e -> {
            MainMenu.gameMenu();
        });
        buttonPanel.add(GoToBack);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        return mainPanel;
    }
}
