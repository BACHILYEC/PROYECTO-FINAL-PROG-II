package UserInterface.Screen;

import DataAccessComponent.DAOs.UserPlayerDAO;
import DataAccessComponent.DTOs.UserPlayerDTO;
import Infrastructure.AppException;
import Infrastructure.AppMSG;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;
import UserInterface.Utility.StyleConfig;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UpdatePlayerScreen {
    private static JTextField nameTextField;
    private static JTextField scoreTextField;
    private static JTextField idTextField;
    private static JComboBox<String> statusCombo;
    private static JTable playerTable;
    private static UserPlayerDTO selectedPlayer = new UserPlayerDTO();
    private static JComponent[][] components = new JComponent[6][10];
    private static ArrayList<JTextField> inputFields = new ArrayList<>();

    public static JPanel updatePlayerPanel() {

        ImageBackgroundPanel centerPanel = new ImageBackgroundPanel(
                ReusableMethods.getImageBackground());
        JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));
        title.setMaximumSize(new Dimension(600, 120));
        title.setOpaque(false);
        JLabel tittle = StyleConfig.titleConfig();
        tittle.setFont(new Font("Cooper Black", Font.BOLD, 30));
        title.add(tittle);
        centerPanel.add(title, BorderLayout.NORTH);

        JPanel tableSection = createTableSection();
        centerPanel.add(tableSection);
        tableSection.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel formSection = createFormSection();
        formSection.setMaximumSize(new Dimension(600, 50));
        centerPanel.add(formSection);
        JPanel keyboard = ScreenKeyboard.keyboard(inputFields);
        keyboard.setPreferredSize(new Dimension(600, 150));
        JButton[][] buttons = ScreenKeyboard.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                components[i + 1][j] = buttons[i][j];
            }
        }
        centerPanel.add(keyboard);

        JPanel buttonsPanel = createButtonsPanel();
        centerPanel.add(buttonsPanel, BorderLayout.SOUTH);
        ControllerDualsense ControllerDualsense = new ControllerDualsense();
        ControllerDualsense.setupKeyBindings(centerPanel, components);
        ControllerDualsense.focusComponent(ControllerDualsense.getCurrentIndexX(),
                ControllerDualsense.getCurrentIndexY(),
                components);
        return centerPanel;
    }

    private static JPanel createTableSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(600, 150));
        panel.setOpaque(false);

        JLabel tableLabel = new JLabel("Seleccione un jugador:");
        tableLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tableLabel.setForeground(Color.WHITE);
        panel.add(tableLabel, BorderLayout.NORTH);

        String[] columnNames = { "ID", "Usuario", "Score", "Status", "Fecha Creación", "Fecha Modificacion" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        playerTable = new JTable(model);
        playerTable.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(playerTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        loadTableData(model);
        return panel;
    }

    private static void loadTableData(DefaultTableModel model) {
        model.setRowCount(0);
        UserPlayerDAO dao = new UserPlayerDAO();
        try {
            for (UserPlayerDTO dto : dao.readAllStatus(true)) {
                Object[] row = {
                        dto.getIdPlayer(),
                        dto.getName(),
                        dto.getScore(),
                        dto.getStatus(),
                        dto.getCreationDate(),
                        dto.getModificateDate()
                };
                model.addRow(row);
            }

            for (UserPlayerDTO dto : dao.readAllStatus(false)) {
                if ("Inactivo".equals(dto.getStatus())) {
                    Object[] row = {
                            dto.getIdPlayer(),
                            dto.getName(),
                            dto.getScore(),
                            dto.getStatus(),
                            dto.getCreationDate(),
                            dto.getModificateDate()
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al cargar datos: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static JPanel createFormSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        JLabel formLabel = new JLabel("Datos del Jugador");
        formLabel.setFont(new Font("Arial", Font.BOLD, 16));
        formLabel.setForeground(Color.BLACK);
        formLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(formLabel);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel id = new JLabel("ID:");
        id.setForeground(Color.BLACK);
        fieldsPanel.add(id, gbc);

        gbc.gridx = 1;
        idTextField = new JTextField(10);
        idTextField.setFocusable(false);
        fieldsPanel.add(idTextField, gbc);
        inputFields.add(idTextField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblNameLabel = new JLabel("Nombre:");
        lblNameLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblNameLabel, gbc);

        gbc.gridx = 1;
        nameTextField = new JTextField(10);
        inputFields.add(nameTextField);
        nameTextField.setFocusable(false);
        fieldsPanel.add(nameTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel lblScoreLabel = new JLabel("Score:");
        lblScoreLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblScoreLabel, gbc);

        gbc.gridx = 3;
        scoreTextField = new JTextField(10);
        inputFields.add(scoreTextField);
        scoreTextField.setFocusable(false);
        fieldsPanel.add(scoreTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        JLabel lblStatusLabel = new JLabel("Status:");
        lblStatusLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblStatusLabel, gbc);

        gbc.gridx = 3;
        statusCombo = new JComboBox<>(new String[] { "Activo", "Inactivo" });

        for (int i = 0; i < 10; i++) {
            components[0][i] = statusCombo;
        }
        statusCombo.setFocusable(false);
        fieldsPanel.add(statusCombo, gbc);
        panel.add(fieldsPanel);

        return panel;
    }

    private static JPanel createButtonsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setOpaque(false);

        JButton btnUpdate = StyleConfig.createButton("Actualizar", StyleConfig.buttonPrimary(), 150, 40);
        btnUpdate.addActionListener(e -> updatePlayer());

        JButton btnCancel = StyleConfig.createButton("Cancelar", StyleConfig.buttonSecondary(), 150, 40);
        btnCancel.addActionListener(e -> clearForm());

        JButton btnBack = StyleConfig.createButton("Regresar", StyleConfig.buttonSecondary(), 150, 40);
        btnBack.addActionListener(e -> {
            try {
                MainFrame.setContentPane(ScreenAdmin.MenuAdmin());
            } catch (Exception ex) {
                throw new RuntimeException(
                        new AppException("Error al regresar", ex, UpdatePlayerScreen.class, "createButtonsPanel"));
            }
        });
        components[5][0] = btnUpdate;
        components[5][1] = btnCancel;
        components[5][2] = btnBack;
        components[5][3] = btnUpdate;
        components[5][4] = btnCancel;
        components[5][5] = btnBack;
        components[5][6] = btnUpdate;
        components[5][7] = btnCancel;
        components[5][8] = btnBack;
        components[5][9] = btnBack;
        panel.add(btnUpdate);
        panel.add(btnCancel);
        panel.add(btnBack);
        return panel;
    }

    private static void updatePlayer() {

        int id = Integer.parseInt(idTextField.getText());
        try {
            if (id < 2) {
                throw new AppException("ID no puede ser negativo");
            }
        } catch (AppException e) {
            AppException e2 = new AppException(
                    e.getMessage(),
                    e,
                    UpdatePlayerScreen.class,
                    "updatePlayer");
            AppMSG.showError(e2.getMessage());
        }

        String name = nameTextField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El nombre no puede estar vacío",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (name.length() < 3) {
            JOptionPane.showMessageDialog(null,
                    "El nombre debe tener al menos 3 caracteres",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String scoreText = scoreTextField.getText().trim();
        if (scoreText.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El score no puede estar vacío",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int score;
        try {
            score = Integer.parseInt(scoreText);
            if (score < 0) {
                JOptionPane.showMessageDialog(null,
                        "El score debe ser un número positivo",
                        "Error de Validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                    "El score debe ser un número entero válido",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        selectedPlayer.setIdPlayer(id);
        selectedPlayer.setName(name);
        selectedPlayer.setScore(score);

        UserPlayerDAO dao = new UserPlayerDAO();
        try {
            boolean success = dao.update(selectedPlayer);

            String selectedStatus = (String) statusCombo.getSelectedItem();
            boolean isActive = selectedStatus.equals("Activo");
            dao.changeStatus(selectedPlayer.getIdPlayer(), isActive);

            if (success) {
                JOptionPane.showMessageDialog(null,
                        "Jugador actualizado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                DefaultTableModel model = (DefaultTableModel) playerTable.getModel();
                loadTableData(model);

                clearForm();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar el jugador: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void clearForm() {
        idTextField.setText("");
        nameTextField.setText("");
        scoreTextField.setText("");
        statusCombo.setSelectedIndex(0);
        selectedPlayer = null;
        playerTable.clearSelection();
    }
}
