package UserInterface.Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import DataAccessComponent.DAOs.UserAdminDAO;
import DataAccessComponent.DTOs.UserAdminDTO;
import UserInterface.Utility.AppConfig;
import UserInterface.Utility.ImageBackgroundPanel;

public class LoginScreen {
    private static JComponent[] components;
    private static int currentIndex = 0;

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
        centerPanel.add(Textmain);
        centerPanel.add(keyboard);
        centerPanel.add(buttonPanel);
        centerPanel.add(buttonPanelBack);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        components = new JComponent[] { usernameField, passwordField, loginButton,
                GoToBack };
        setupKeyBindings(keyboard);
        return mainPanel;
    }

    private static void setupKeyBindings(JPanel panel) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "nextComponent");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "nextComponent");
        actionMap.put("nextComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % components.length;
                focusComponent(currentIndex);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("UP"), "prevComponent");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "prevComponent");
        actionMap.put("prevComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex - 1 + components.length) % components.length;
                focusComponent(currentIndex);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "activateComponent");
        actionMap.put("activateComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (components[currentIndex] instanceof JButton) {
                    ((JButton) components[currentIndex]).doClick();
                } else {

                    currentIndex = (currentIndex + 1) % components.length;
                    focusComponent(currentIndex);
                }
            }
        });
    }

    private static void focusComponent(int index) {

        for (JComponent component : components) {
            if (component instanceof JButton) {
                component.setBorder(BorderFactory.createEmptyBorder());
            }
        }

        components[index].requestFocusInWindow();

        if (components[index] instanceof JButton) {
            components[index].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        }
    }
}