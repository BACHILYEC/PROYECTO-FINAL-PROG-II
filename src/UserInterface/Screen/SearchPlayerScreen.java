package UserInterface.Screen;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import BusinessLogic.UserPlayerBL;
import DataAccessComponent.DTOs.UserPlayerDTO;
import UserInterface.Utility.StyleConfig;
import UserInterface.Utility.ImageBackgroundPanel;

public class SearchPlayerScreen {
        private static JComponent[][] components;
        private static JTextField nameTextField;
        private static JTextField idTextField;

        public static JPanel searchPlayerPanel() {
                JPanel mainPanel = new JPanel(new BorderLayout());
                JLabel tittle = StyleConfig.tittleConfig();
                mainPanel.add(tittle, BorderLayout.NORTH);

                ImageBackgroundPanel centerPanel = new ImageBackgroundPanel(
                                "src\\UserInterface\\Resources\\ImagenBackGroundLogin.png");
                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                centerPanel.add(Box.createVerticalGlue());

                Font labelFont = new Font("Comic Sans MS", Font.BOLD, 18);

                JPanel searchByNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
                searchByNamePanel.setOpaque(false);
                searchByNamePanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
                JLabel nameLabel = new JLabel("Buscar por Nombre:");
                nameLabel.setFont(labelFont);
                nameTextField = new JTextField(15);
                searchByNamePanel.add(nameLabel);
                searchByNamePanel.add(nameTextField);
                centerPanel.add(searchByNamePanel);

                centerPanel.add(Box.createVerticalGlue());

                JPanel searchByIdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
                searchByIdPanel.setOpaque(false);
                searchByIdPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
                JLabel idLabel = new JLabel("Buscar por ID:");
                idLabel.setFont(labelFont);
                idTextField = new JTextField(15);
                searchByIdPanel.add(idLabel);
                searchByIdPanel.add(idTextField);
                centerPanel.add(searchByIdPanel);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

                buttonPanel.setBackground(StyleConfig.ButtonPrimaryPanel());

                JButton searchByNameButton = StyleConfig.createButton("Buscar por Nombre", StyleConfig.ButtonPrimary(),
                                200, 50);

                buttonPanel.setBackground(StyleConfig.ButtonPrimaryPanel());

                searchByNameButton.addActionListener(e -> searchByName(mainPanel));
                buttonPanel.add(searchByNameButton);

                JButton searchByIdButton = StyleConfig.createButton("Buscar por ID", StyleConfig.ButtonPrimary(), 200,
                                50);
                searchByIdButton.addActionListener(e -> searchById(mainPanel));
                buttonPanel.add(searchByIdButton);

                JButton GoToBack = StyleConfig.createButton("Regresar", StyleConfig.ButtonSecondary(), 150, 40);
                        GoToBack.addActionListener(e -> {
                            MainFrame.setContentPane(ScreenAdmin.MenuAdmin());
                });
                buttonPanel.add(GoToBack);

                ArrayList<JTextField> input = new ArrayList<>();
                input.add(nameTextField);
                input.add(idTextField);
                JPanel keyboard = ScreenKeyboard.keyboard(input);

                JPanel fullCenterPanel = new JPanel();
                fullCenterPanel.setLayout(new BoxLayout(fullCenterPanel, BoxLayout.Y_AXIS));
                fullCenterPanel.add(centerPanel);
                fullCenterPanel.add(keyboard);
                fullCenterPanel.add(buttonPanel);
                fullCenterPanel.add(GoToBack);

                mainPanel.add(fullCenterPanel, BorderLayout.CENTER);

                JButton[][] buttons = ScreenKeyboard.getButtons();
                components = new JComponent[buttons.length + 3][buttons[0].length];

                for (int i = 0; i < buttons.length; i++) {
                        for (int j = 0; j < buttons[i].length; j++) {
                                components[i][j] = buttons[i][j];
                        }
                }

                for (int i = 0; i < buttons[0].length; i++) {
                        components[buttons.length][i] = searchByNameButton;
                        components[buttons.length + 1][i] = searchByIdButton;
                        components[buttons.length + 2][i] = GoToBack;
                }

                ControllerDualsense controller = new ControllerDualsense();
                controller.setupKeyBindings(mainPanel, components);
                controller.focusComponent(controller.getCurrentIndexX(), controller.getCurrentIndexY(), components);

                return mainPanel;
        }

        private static void searchByName(JPanel parentPanel) {
                try {
                        String username = nameTextField.getText();

                        if (username == null || username.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(
                                                parentPanel,
                                                "Nombre inválido. Por favor ingrese un nombre válido.",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        UserPlayerBL BL = new UserPlayerBL();
                        UserPlayerDTO playerDTO = BL.searchByName(username.trim());

                        if (playerDTO != null) {
                                String mensaje = "¡Jugador encontrado!\n\n" +
                                                "ID: " + playerDTO.getIdPlayer() + "\n" +
                                                "Nombre: " + playerDTO.getName() + "\n" +
                                                "Score: " + playerDTO.getScore();

                                JOptionPane.showMessageDialog(
                                                parentPanel,
                                                mensaje,
                                                "Jugador Encontrado",
                                                JOptionPane.INFORMATION_MESSAGE);

                                nameTextField.setText("");
                                idTextField.setText("");
                        } else {
                                JOptionPane.showMessageDialog(
                                                parentPanel,
                                                "No se encontró ningún jugador con el nombre: " + username,
                                                "Jugador No Encontrado",
                                                JOptionPane.WARNING_MESSAGE);
                        }

                } catch (Exception err) {
                        JOptionPane.showMessageDialog(
                                        parentPanel,
                                        "Error al buscar el jugador: " + err.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                        err.printStackTrace();
                }
        }

        private static void searchById(JPanel parentPanel) {
                try {
                        String idInput = idTextField.getText();

                        if (idInput == null || idInput.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(
                                                parentPanel,
                                                "ID inválido. Por favor ingrese un ID válido.",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        int playerId;
                        try {
                                playerId = Integer.parseInt(idInput.trim());
                        } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(
                                                parentPanel,
                                                "El ID debe ser un número válido.",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        UserPlayerBL BL = new UserPlayerBL();
                        UserPlayerDTO playerDTO = BL.readById(playerId);

                        if (playerDTO != null) {
                                String mensaje = "¡Jugador encontrado!\n\n" +
                                                "ID: " + playerDTO.getIdPlayer() + "\n" +
                                                "Nombre: " + playerDTO.getName() + "\n" +
                                                "Score: " + playerDTO.getScore();
                                nameTextField.setText("");

                                JOptionPane.showMessageDialog(
                                                parentPanel,
                                                mensaje,
                                                "Jugador Encontrado",
                                                JOptionPane.INFORMATION_MESSAGE);

                                idTextField.setText("");
                        } else {
                                JOptionPane.showMessageDialog(
                                                parentPanel,
                                                "No se encontró ningún jugador con el ID: " + playerId,
                                                "Jugador No Encontrado",
                                                JOptionPane.WARNING_MESSAGE);
                        }

                } catch (Exception err) {
                        JOptionPane.showMessageDialog(
                                        parentPanel,
                                        "Error al buscar el jugador: " + err.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                        err.printStackTrace();
                }
        }
}