package UserInterface.Screen;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import BusinessLogic.UserPlayerBL;
import DataAccessComponent.DTOs.UserPlayerDTO;
import Infrastructure.AppException;
import UserInterface.Utility.StyleConfig;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;

public class SearchPlayerScreen {
        private static JComponent[][] components;
        private static JTextField searchNameTextField;
        private static JTextField searchIdTextField;

        public static JPanel searchPlayerPanel() {

                ImageBackgroundPanel centerPanel = new ImageBackgroundPanel(ReusableMethods.getImageBackground());
                centerPanel.add(Box.createVerticalGlue());
                JLabel tittle = StyleConfig.titleConfig();
                JPanel tittlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                tittlePanel.setOpaque(false);
                tittlePanel.add(tittle);
                centerPanel.add(tittlePanel);

                Font labelFont = new Font("Comic Sans MS", Font.BOLD, 18);

                JPanel searchByNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
                searchByNamePanel.setOpaque(false);
                searchByNamePanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
                JLabel nameLabel = new JLabel("Buscar por Nombre:");
                nameLabel.setFont(labelFont);
                searchNameTextField = new JTextField(15);
                searchByNamePanel.add(nameLabel);
                searchByNamePanel.add(searchNameTextField);
                centerPanel.add(searchByNamePanel);
                centerPanel.add(Box.createVerticalGlue());

                JPanel searchByIdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
                searchByIdPanel.setOpaque(false);
                searchByIdPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
                JLabel idLabel = new JLabel("Buscar por ID:");
                idLabel.setFont(labelFont);
                searchIdTextField = new JTextField(15);
                searchByIdPanel.add(idLabel);
                searchByIdPanel.add(searchIdTextField);
                centerPanel.add(searchByIdPanel);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
                buttonPanel.setOpaque(false);

                JButton searchByNameButton = StyleConfig.createButton("Buscar por Nombre", StyleConfig.buttonPrimary(),
                                200, 50);

                searchByNameButton.addActionListener(e -> searchByName(centerPanel));
                buttonPanel.add(searchByNameButton);

                JButton searchByIdButton = StyleConfig.createButton("Buscar por ID", StyleConfig.buttonPrimary(), 200,
                                50);
                searchByIdButton.addActionListener(e -> searchById(centerPanel));
                buttonPanel.add(searchByIdButton);

                JButton GoToBack = StyleConfig.createButton("Regresar", StyleConfig.buttonSecondary(), 150, 40);
                GoToBack.addActionListener(e -> {
                        try {
                                MainFrame.setContentPane(ScreenAdmin.MenuAdmin());
                        } catch (Exception ex) {
                                throw new RuntimeException(new AppException("Error al regresar", ex,
                                                SearchPlayerScreen.class, "searchPlayerPanel"));
                        }
                });
                buttonPanel.add(GoToBack);

                ArrayList<JTextField> input = new ArrayList<>();
                input.add(searchNameTextField);
                input.add(searchIdTextField);
                JPanel keyboard = ScreenKeyboard.keyboard(input);

                GoToBack.addActionListener(e -> {
                        MainFrame.setContentPane(ScreenAdmin.MenuAdmin());
                });

                JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
                backPanel.setOpaque(false);
                backPanel.add(GoToBack);

                JPanel fullCenterPanel = new JPanel();
                fullCenterPanel.setLayout(new BoxLayout(fullCenterPanel, BoxLayout.Y_AXIS));
                fullCenterPanel.setOpaque(false);
                // fullCenterPanel.add(centerPanel);
                fullCenterPanel.add(keyboard);
                fullCenterPanel.add(buttonPanel);
                fullCenterPanel.add(backPanel);

                centerPanel.add(fullCenterPanel, BorderLayout.CENTER);

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
                controller.setupKeyBindings(centerPanel, components);
                controller.focusComponent(controller.getCurrentIndexX(), controller.getCurrentIndexY(), components);

                return centerPanel;
        }

        private static void searchByName(JPanel parentPanel) {
                try {
                        String username = searchNameTextField.getText();

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

                                searchNameTextField.setText("");
                                searchIdTextField.setText("");
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
                        String idInput = searchIdTextField.getText();

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

                        UserPlayerDTO playerDTO = UserPlayerBL.readById(playerId);

                        if (playerDTO != null) {
                                String mensaje = "¡Jugador encontrado!\n\n" +
                                                "ID: " + playerDTO.getIdPlayer() + "\n" +
                                                "Nombre: " + playerDTO.getName() + "\n" +
                                                "Score: " + playerDTO.getScore();
                                searchNameTextField.setText("");

                                JOptionPane.showMessageDialog(
                                                parentPanel,
                                                mensaje,
                                                "Jugador Encontrado",
                                                JOptionPane.INFORMATION_MESSAGE);

                                searchIdTextField.setText("");
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