package UserInterface.Screen;

import BusinessLogic.UserPlayerBL;
import DataAccessComponent.DAOs.UserPlayerDAO;
import DataAccessComponent.DTOs.UserPlayerDTO;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;
import UserInterface.Utility.StyleConfig;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UpdatePlayerScreen {
    private static JTextField txtName;
    private static JTextField txtScore;
    private static JLabel lblIdPlayer;
    private static JComboBox<String> cmbStatus;
    private static JLabel lblCreationDate;
    private static JLabel lblModificateDate;
    private static JTable table;
    private static UserPlayerDTO selectedPlayer = null;

    public static JPanel updatePlayerPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel title = StyleConfig.tittleConfig();
        mainPanel.add(title, BorderLayout.NORTH);

        ImageBackgroundPanel centerPanel = new ImageBackgroundPanel(
                ReusableMethods.getImageBackground());
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel tableSection = createTableSection();
        centerPanel.add(tableSection);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel formSection = createFormSection();
        centerPanel.add(formSection);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = createButtonsPanel();
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private static JPanel createTableSection() {
        JPanel panel = new JPanel(new BorderLayout());
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

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedPlayer();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 150));
        panel.add(scrollPane, BorderLayout.CENTER);

        loadTableData(model);

        return panel;
    }

    private static void loadTableData(DefaultTableModel model) {
        model.setRowCount(0);
        UserPlayerDAO dao = new UserPlayerDAO();
        try {
            for (UserPlayerDTO dto : dao.readAllstatus(true)) {
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

            for (UserPlayerDTO dto : dao.readAllstatus(false)) {
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

    private static void loadSelectedPlayer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idPlayer = (Integer) table.getValueAt(selectedRow, 0);
            try {
                selectedPlayer = UserPlayerBL.readById(idPlayer);
                if (selectedPlayer != null) {
                    lblIdPlayer.setText(selectedPlayer.getIdPlayer().toString());
                    txtName.setText(selectedPlayer.getName());
                    txtScore.setText(selectedPlayer.getScore().toString());
                    cmbStatus.setSelectedItem(
                            selectedPlayer.getStatus() != null ? selectedPlayer.getStatus() : "Activo");
                    lblCreationDate.setText(selectedPlayer.getCreationDate());
                    lblModificateDate.setText(
                            selectedPlayer.getModificateDate() != null ? selectedPlayer.getModificateDate() : "N/A");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Error al cargar el jugador: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
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
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblIdLabel = new JLabel("ID:");
        lblIdLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblIdLabel, gbc);

        gbc.gridx = 1;
        lblIdPlayer = new JLabel("-");
        lblIdPlayer.setForeground(Color.BLACK);
        lblIdPlayer.setPreferredSize(new Dimension(200, 25));
        fieldsPanel.add(lblIdPlayer, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblNameLabel = new JLabel("Nombre:");
        lblNameLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblNameLabel, gbc);

        gbc.gridx = 1;
        txtName = new JTextField(20);
        fieldsPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblScoreLabel = new JLabel("Score:");
        lblScoreLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblScoreLabel, gbc);

        gbc.gridx = 1;
        txtScore = new JTextField(20);
        fieldsPanel.add(txtScore, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblStatusLabel = new JLabel("Status:");
        lblStatusLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblStatusLabel, gbc);

        gbc.gridx = 1;
        cmbStatus = new JComboBox<>(new String[] { "Activo", "Inactivo" });
        fieldsPanel.add(cmbStatus, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel lblCreationLabel = new JLabel("Fecha Creación:");
        lblCreationLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblCreationLabel, gbc);

        gbc.gridx = 1;
        lblCreationDate = new JLabel("-");
        lblCreationDate.setForeground(Color.BLACK);
        fieldsPanel.add(lblCreationDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel lblModificateLabel = new JLabel("Fecha Modificación:");
        lblModificateLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblModificateLabel, gbc);

        gbc.gridx = 1;
        lblModificateDate = new JLabel("-");
        lblModificateDate.setForeground(Color.BLACK);
        fieldsPanel.add(lblModificateDate, gbc);

        panel.add(fieldsPanel);

        return panel;
    }

    private static JPanel createButtonsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnUpdate = StyleConfig.createButton("Actualizar", StyleConfig.ButtonPrimary(), 150, 40);
        btnUpdate.addActionListener(e -> updatePlayer());

        JButton btnCancel = StyleConfig.createButton("Cancelar", StyleConfig.ButtonSecondary(), 150, 40);
        btnCancel.addActionListener(e -> clearForm());

        JButton btnBack = StyleConfig.createButton("Regresar", StyleConfig.ButtonSecondary(), 150, 40);
        btnBack.addActionListener(e -> MainFrame.setContentPane(ScreenAdmin.MenuAdmin()));

        panel.add(btnUpdate);
        panel.add(btnCancel);
        panel.add(btnBack);

        return panel;
    }

    private static void updatePlayer() {
        if (selectedPlayer == null) {
            JOptionPane.showMessageDialog(null,
                    "Por favor seleccione un jugador de la tabla",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = txtName.getText().trim();
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

        String scoreText = txtScore.getText().trim();
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

        selectedPlayer.setName(name);
        selectedPlayer.setScore(score);

        UserPlayerDAO dao = new UserPlayerDAO();
        try {
            boolean success = dao.update(selectedPlayer);

            String selectedStatus = (String) cmbStatus.getSelectedItem();
            boolean isActive = selectedStatus.equals("Activo");
            dao.changestatus(selectedPlayer.getIdPlayer(), isActive);

            if (success) {
                JOptionPane.showMessageDialog(null,
                        "Jugador actualizado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                DefaultTableModel model = (DefaultTableModel) table.getModel();
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
        txtName.setText("");
        txtScore.setText("");
        lblIdPlayer.setText("-");
        cmbStatus.setSelectedIndex(0);
        lblCreationDate.setText("-");
        lblModificateDate.setText("-");
        selectedPlayer = null;
        table.clearSelection();
    }
}
