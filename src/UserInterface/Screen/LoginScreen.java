package UserInterface.Screen;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import BusinessLogic.UserAdminBL;
import DataAccessComponent.DTOs.UserAdminDTO;
import UserInterface.Utility.StyleConfig;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;

public class LoginScreen {
    static JComponent[][] components = new JComponent[7][10];

    public static JPanel loginPanel() {
        ImageBackgroundPanel mainPanel = new ImageBackgroundPanel(
                ReusableMethods.getImageBackground());
        JPanel tittle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = StyleConfig.titleConfig();
        tittle.setOpaque(false);
        tittle.add(label);
        Font login = new Font("Comic Sans MS", Font.BOLD, 18);
        JTextField usernameField = new JTextField(10);
        JPanel userLogin = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        userLogin.setOpaque(false);
        userLogin.setBorder(BorderFactory.createEmptyBorder(100, 3, 10, 70));
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(login);
        userLogin.add(usernameLabel);
        userLogin.add(usernameField);
        JPasswordField passwordField = new JPasswordField(10);
        JToggleButton showPasswordButton = new JToggleButton("Ver");
        showPasswordButton.setPreferredSize(new Dimension(50, 23));
        showPasswordButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        showPasswordButton.setBackground(StyleConfig.buttonSecondary());
        showPasswordButton.addActionListener(e -> {
            if (showPasswordButton.isSelected()) {
                passwordField.setEchoChar((char) 0);
                showPasswordButton.setText("Ocultar");
            } else {
                passwordField.setEchoChar('•');
                showPasswordButton.setText("Ver");
            }
        });
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        passwordPanel.setOpaque(false);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(login);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        passwordPanel.add(showPasswordButton);
        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));
        credentialsPanel.setOpaque(false);
        credentialsPanel.add(Box.createVerticalGlue());
        credentialsPanel.add(userLogin);
        credentialsPanel.add(Box.createVerticalGlue());
        credentialsPanel.add(passwordPanel);
        mainPanel.add(tittle);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        JButton loginButton = StyleConfig.createButton("Login", StyleConfig.buttonPrimary(), 200, 50);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(loginButton);
        loginButton.addActionListener(a -> {
            UserAdminBL BL = new UserAdminBL();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            try {
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor complete todos los campos.");
                    return;
                } else {
                    try {
                        UserAdminDTO dto = BL.searchByName(username);
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
        buttonPanelBack.setOpaque(false);
        JButton GoToBack = StyleConfig.createButton("Regresar", StyleConfig.buttonSecondary(), 150, 40);
        buttonPanelBack.add(GoToBack);
        GoToBack.addActionListener(e -> {
            MainFrame.setContentPane(MainMenu.gameMenu());
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        ArrayList<JTextField> input = new ArrayList<>();
        input.add(usernameField);
        input.add(passwordField);
        JPanel keyboard = ScreenKeyboard.keyboard(input);
        centerPanel.add(credentialsPanel);
        centerPanel.add(keyboard);
        centerPanel.add(buttonPanel);
        centerPanel.add(buttonPanelBack);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        for (int i = 0; i <= 9; i++) {
            components[0][i] = showPasswordButton;
        }
        JButton[][] buttons = ScreenKeyboard.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                components[i + 1][j] = buttons[i][j];
            }
        }

        for (int i = 0; i <= 9; i++) {
            components[5][i] = loginButton;
            components[6][i] = GoToBack;
        }
        ControllerDualsense controller = new ControllerDualsense();
        controller.setupKeyBindings(mainPanel, components);
        controller.focusComponent(controller.getCurrentIndexX(), controller.getCurrentIndexY(),
                components);
        return mainPanel;

    }

}