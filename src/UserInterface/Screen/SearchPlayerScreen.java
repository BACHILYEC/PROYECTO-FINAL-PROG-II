package UserInterface.Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import DataAccessComponent.DAOs.UserPlayerDAO;
import DataAccessComponent.DTOs.UserPlayerDTO;
import UserInterface.Utility.StyleConfig;
import UserInterface.Utility.ImageBackgroundPanel;

public class SearchPlayerScreen {
    private static JComponent[][] buttons;
    private static int currentIndex = 0;

    public static JPanel searchPlayerPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel tittle = StyleConfig.tittleConfig();
        mainPanel.add(tittle, BorderLayout.NORTH);

        ImageBackgroundPanel centerPanel = new ImageBackgroundPanel(
                "src\\UserInterface\\Resources\\ImagenBackGroundLogin.png");
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));

        JLabel searchLabel = new JLabel("Seleccione el método de búsqueda:");
        searchLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        searchLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(searchLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JButton searchByNameButton = StyleConfig.createButton("Buscar por Nombre", StyleConfig.ButtonPrimary(), 250, 50);
        searchByNameButton.setMaximumSize(new Dimension(250, 50));
        searchByNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchByNameButton.addActionListener(e -> searchByName(mainPanel));
        centerPanel.add(searchByNameButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton searchByIdButton = StyleConfig.createButton("Buscar por ID", StyleConfig.ButtonPrimary(), 250, 50);
        searchByIdButton.setMaximumSize(new Dimension(250, 50));
        searchByIdButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchByIdButton.addActionListener(e -> searchById(mainPanel));
        centerPanel.add(searchByIdButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(StyleConfig.ButtonSecondaryPanel());
        JButton backButton = StyleConfig.createButton("Regresar", StyleConfig.ButtonSecondary(), 150, 40);
        backButton.addActionListener(e -> {
            MainFrame.setContentPane(ScreenAdmin.MenuAdmin());
        });
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        buttons = new JComponent[][] { { searchByNameButton }, { searchByIdButton }, { backButton } };
        ControllerDualsense ControllerDualsense = new ControllerDualsense();
        ControllerDualsense.setupKeyBindings(mainPanel, buttons);

        ControllerDualsense.focusComponent(ControllerDualsense.getCurrentIndexX(),
                ControllerDualsense.getCurrentIndexY(),
                buttons);

        return mainPanel;
    }

    private static void searchByName(JPanel parentPanel) {
        try {
            String username = JOptionPane.showInputDialog(parentPanel, "Ingrese el nombre del jugador:");

            if (username == null || username.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        parentPanel,
                        "Nombre inválido. Por favor ingrese un nombre válido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            UserPlayerDAO playerDAO = new UserPlayerDAO();
            UserPlayerDTO playerDTO = playerDAO.readByName(username);

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
            String idInput = JOptionPane.showInputDialog(parentPanel, "Ingrese el ID del jugador:");

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

            UserPlayerDAO playerDAO = new UserPlayerDAO();
            UserPlayerDTO playerDTO = playerDAO.readById(playerId);

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