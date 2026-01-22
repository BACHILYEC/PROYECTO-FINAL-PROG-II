package UserInterface.Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;

import DataAccessComponent.DAOs.UserAdminDAO;
import DataAccessComponent.DTOs.UserAdminDTO;
import UserInterface.Utility.AppConfig;
import UserInterface.Utility.ImageBackgroundPanel;

public class LoginScreen {
    private static int currentIndexX = 0;
    private static int currentIndexY = 0;
    static JComponent[][] components = new JComponent[6][10];

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
        ArrayList<JTextField> input = new ArrayList<>();
        input.add(usernameField);
        input.add(passwordField);
        JPanel keyboard = ScreenKeyboard.keyboard(input);
        centerPanel.add(Textmain);
        centerPanel.add(keyboard);
        centerPanel.add(buttonPanel);
        centerPanel.add(buttonPanelBack);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JButton[][] buttons = ScreenKeyboard.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                components[i][j] = buttons[i][j];
                System.out.println(components[i][j] + "\n");
            }

        }
        System.out.println(components.length + components[0].length + "\n");
        for (int i = 0; i <= 9; i++) {
            components[4][i] = loginButton;
            components[5][i] = GoToBack;
        }
        setupKeyBindings(mainPanel);
        focusComponent(currentIndexY, currentIndexX);
        return mainPanel;

    }

    private static void setupKeyBindings(JPanel panel) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "prevComponent");
        actionMap.put("prevComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndexY = (currentIndexY - 1 + components[currentIndexX].length)
                        % components[currentIndexX].length;
                focusComponent(currentIndexX, currentIndexY);

            }
        });

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "RightComponent");
        actionMap.put("RightComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndexY = (currentIndexY + 1)
                        % components[currentIndexX].length;
                focusComponent(currentIndexX, currentIndexY);

            }
        });
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "DownComponent");
        actionMap.put("DownComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndexX = (currentIndexX + 1)
                        % components.length;
                focusComponent(currentIndexX, currentIndexY);

            }
        });

        inputMap.put(KeyStroke.getKeyStroke("UP"), "UpComponent");
        actionMap.put("UpComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndexX = (currentIndexX - 1 + components.length)
                        % components.length;
                focusComponent(currentIndexX, currentIndexY);

            }
        });

        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "activateComponent");
        actionMap.put("activateComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (components[currentIndexX][currentIndexY] instanceof JButton) {
                    ((JButton) components[currentIndexX][currentIndexY]).doClick();
                } else {

                    currentIndexX = (currentIndexX + 1) % components.length;
                    focusComponent(currentIndexX, currentIndexY);
                }
            }
        });
    }

    private static void focusComponent(int indexX, int indexY) {

        for (JComponent[] componentRow : components) {
            for (JComponent component : componentRow) {
                if (component instanceof JButton) {
                    component.setBorder(BorderFactory.createEmptyBorder());
                }
            }
        }

        components[indexX][indexY].requestFocusInWindow();

        if (components[indexX][indexY] instanceof JButton) {
            components[indexX][indexY].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        }
    }
}