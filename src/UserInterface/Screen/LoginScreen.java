package UserInterface.Screen;

import java.awt.*;

import javax.swing.*;

import DataAccessComponent.DAOs.UserAdminDAO;
import DataAccessComponent.DTOs.UserAdminDTO;
import UserInterface.Utility.AppConfig;
import UserInterface.Utility.ImageBackgroundPanel;

public class LoginScreen {
    public static JPanel loginPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel tittle = AppConfig.tittleConfig();
        Font login = new Font("Comic Sans MS", Font.BOLD, 18);
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
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(login);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        GetCredencials.add(Box.createVerticalGlue());
        GetCredencials.add(userLogin);
        GetCredencials.add(Box.createVerticalGlue());
        GetCredencials.add(passwordPanel);
        Textmain.add(GetCredencials, BorderLayout.CENTER);
        mainPanel.add(tittle, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(AppConfig.ButtonPrimaryPanel());
        JButton loginButton = AppConfig.createButton("Login", AppConfig.ButtonPrimary(), 200, 50);
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

                            MainFrame.setContentPane(ScreenAdmin.MenuAdmin());
                            return;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(mainPanel, "Usuario o contraseña incorrectos.");
                        e.printStackTrace();
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mainPanel, "Error: " + e.getMessage());
            }
        });
        JPanel buttonPanelBack = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanelBack.setBackground(AppConfig.ButtonSecondaryPanel());
        JButton GoToBack = AppConfig.createButton("Regresar", AppConfig.ButtonSecondary(), 150, 40);
        buttonPanelBack.add(GoToBack);
        GoToBack.addActionListener(e -> {
            MainFrame.setContentPane(MainMenu.gameMenu());
        });
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JPanel keyboard = new ScreenKeyboard().keyboard(usernameField);
        Textmain.add(keyboard, BorderLayout.SOUTH);
        centerPanel.add(Textmain);
        centerPanel.add(buttonPanel);
        centerPanel.add(buttonPanelBack);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        return mainPanel;
    }
}
