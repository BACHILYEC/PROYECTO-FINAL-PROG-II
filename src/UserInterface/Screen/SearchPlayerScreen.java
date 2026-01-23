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
    private static JTextField nameTextField;
    private static JTextField idTextField;

    public static JPanel searchPlayerPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel tittle =StyleConfig.tittleConfig();
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

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(StyleConfig.ButtonPrimaryPanel());
        
        JButton searchByNameButton =StyleConfig.createButton("Buscar por Nombre",StyleConfig.ButtonPrimary(), 200, 50);
        searchByNameButton.addActionListener(e -> searchByName(mainPanel));
        buttonPanel.add(searchByNameButton);

        JButton searchByIdButton =StyleConfig.createButton("Buscar por ID",StyleConfig.ButtonPrimary(), 200, 50);
        searchByIdButton.addActionListener(e -> searchById(mainPanel));
        buttonPanel.add(searchByIdButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

   
        JPanel buttonPanelBack = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanelBack.setBackground(StyleConfig.ButtonSecondaryPanel());
        JButton backButton =StyleConfig.createButton("Regresar",StyleConfig.ButtonSecondary(), 150, 40);
        backButton.addActionListener(e -> {
            MainFrame.setContentPane(ScreenAdmin.MenuAdmin());
        });
        buttonPanelBack.add(backButton);

      
        JPanel fullCenterPanel = new JPanel();
        fullCenterPanel.setLayout(new BoxLayout(fullCenterPanel, BoxLayout.Y_AXIS));
        fullCenterPanel.add(centerPanel);
        fullCenterPanel.add(buttonPanel);
        fullCenterPanel.add(buttonPanelBack);
        mainPanel.add(fullCenterPanel, BorderLayout.CENTER);

        buttons = new JComponent[][] { 
            { nameTextField }, 
            { idTextField },
            { searchByNameButton }, 
            { searchByIdButton }, 
            { backButton } 
        };
        
        ControllerDualsense controller = new ControllerDualsense();
        controller.setupKeyBindings(mainPanel, buttons);
        controller.focusComponent(controller.getCurrentIndexX(), controller.getCurrentIndexY(), buttons);

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

            UserPlayerDAO playerDAO = new UserPlayerDAO();
            UserPlayerDTO playerDTO = playerDAO.readByName(username.trim());

            if (playerDTO != null) {
                String mensaje = "¡Jugador encontrado!\n\n" +
                        "ID: " + playerDTO.getIdPlayer() + "\n" +
                        "Nombre: " + playerDTO.getName() + "\n" +
                        "Score: " + playerDTO.getScore();
                        idTextField.setText("");

                JOptionPane.showMessageDialog(
                        parentPanel,
                        mensaje,
                        "Jugador Encontrado",
                        JOptionPane.INFORMATION_MESSAGE);
                
                nameTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(
                        parentPanel,
                        "No se encontró ningún jugador con el nombre: " + username,
                        "Jugador No Encontrado",
                        JOptionPane.WARNING_MESSAGE);
                        idTextField.setText("");
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
        String idInput = idTextField.getText();
        try {
            

            if (idInput == null || idInput.trim().isEmpty()) {
                nameTextField.setText("");
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
                        nameTextField.setText("");
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