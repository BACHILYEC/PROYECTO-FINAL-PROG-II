package UserInterface.Screen;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DataAccessComponent.DAOs.UserPlayerDAO;
import DataAccessComponent.DTOs.UserPlayerDTO;
import UserInterface.Utility.AppConfig;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;

public class ScreenAdmin {
    public static JPanel MenuAdmin() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel tittle = AppConfig.tittleConfig();
        panel.add(tittle, BorderLayout.NORTH);
        ImageBackgroundPanel buttonPanel = new ImageBackgroundPanel(
                "src\\UserInterface\\Resources\\ImagenBackGroundLogin.png");
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
        JButton Exit = AppConfig.createButton("Salir", AppConfig.ButtonSecondary(), 150, 40);
        Exit.addActionListener(e -> {
            System.exit(0);
        });
        JButton GoToBack = AppConfig.createButton("Regresar", AppConfig.ButtonSecondary(), 150, 40);
        GoToBack.addActionListener(e -> {
            MainFrame.setContentPane(MainMenu.gameMenu());
        });
        String[] buttonLabels = { "Tabla De Jugadores", "Agregar Jugador", "Modificar Jugador", "Buscar Jugador" };
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton boton = AppConfig.createButton(buttonLabels[i], AppConfig.ButtonPrimary(), 200, 50);
            boton.setMaximumSize(new Dimension(200, 75));
            boton.setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonPanel.add(boton);
            if (i - 1 < buttonLabels.length) {
                buttonPanel.add(Box.createRigidArea(new Dimension(20, 70)));
            }
            String index = buttonLabels[i];
            boton.addActionListener(e -> {
                switch (index) {
                    case "Tabla De Jugadores": {
                        String[] columnNames = { "Usuario", "Score", "Status", "Modification Date", "Creation Date" };
                        JPanel pan = new JPanel();
                        JScrollPane tableScrollPane = ReusableMethods.createTableUser(columnNames, pan, true);
                        pan.setLayout(new BorderLayout());
                        pan.add(tableScrollPane, BorderLayout.CENTER);
                        JOptionPane.showMessageDialog(panel, pan, "Tabla De Jugadores",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case "Agregar Jugador": {
                        MainFrame.setContentPane(CreatePlayer.createPlayerPanel());
                        break;
                    }
                    // Angela case "Modificar Jugador": {
                    // ReusableMethods.setContentPane(ScreenData.updateData(frame), frame);
                    // break;
                    // }
                    case "Buscar Jugador": {
                        String[] opciones = { "Buscar por Nombre", "Buscar por ID" };
                        int seleccion = JOptionPane.showOptionDialog(
                                null,
                                "Seleccione el método de búsqueda:",
                                "Buscar Jugador",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opciones,
                                opciones[0]);

                        if (seleccion == 0) {
                            try {
                                String username = JOptionPane.showInputDialog("Ingrese el nombre del jugador:");

                                if (username == null || username.trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Nombre inválido. Por favor ingrese un nombre válido.",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }

                                UserPlayerDAO playerDAO = new UserPlayerDAO();
                                UserPlayerDTO playerDTO = playerDAO.readByName(username);

                                if (playerDTO != null) {
                                    String mensaje = "¡Jugador encontrado!\n\n" +
                                            "ID: " + playerDTO.getIdPlayer() + "\n" +
                                            "Nombre: " + playerDTO.getName() + "\n" +
                                            "Score: " + playerDTO.getScore();

                                    JOptionPane.showMessageDialog(
                                            null,
                                            mensaje,
                                            "Jugador Encontrado",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "No se encontró ningún jugador con el nombre: " + username,
                                            "Jugador No Encontrado",
                                            JOptionPane.WARNING_MESSAGE);
                                }

                            } catch (Exception err) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Error al buscar el jugador: " + err.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                err.printStackTrace();
                            }
                        }

                        else if (seleccion == 1) {
                            try {
                                String idInput = JOptionPane.showInputDialog("Ingrese el ID del jugador:");

                                if (idInput == null || idInput.trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "ID inválido. Por favor ingrese un ID válido.",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }

                                int playerId;
                                try {
                                    playerId = Integer.parseInt(idInput.trim());
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "El ID debe ser un número válido.",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }

                                UserPlayerDAO playerDAO = new UserPlayerDAO();
                                UserPlayerDTO playerDTO = playerDAO.readById(playerId);

                                if (playerDTO != null) {
                                    String mensaje = "¡Jugador encontrado!\n\n" +
                                            "ID: " + playerDTO.getIdPlayer() + "\n" +
                                            "Nombre: " + playerDTO.getName() + "\n" +
                                            "Score: " + playerDTO.getScore();

                                    JOptionPane.showMessageDialog(
                                            null,
                                            mensaje,
                                            "Jugador Encontrado",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "No se encontró ningún jugador con el ID: " + playerId,
                                            "Jugador No Encontrado",
                                            JOptionPane.WARNING_MESSAGE);
                                }

                            } catch (Exception err) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Error al buscar el jugador: " + err.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                err.printStackTrace();
                            }
                        }
                        break;
                    }
                }
            });
        }
        JPanel buttonsaux = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsaux.setBackground(AppConfig.ButtonSecondaryPanel());
        buttonsaux.add(Exit);
        buttonsaux.add(GoToBack);
        panel.add(buttonsaux, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        return panel;
    }
}