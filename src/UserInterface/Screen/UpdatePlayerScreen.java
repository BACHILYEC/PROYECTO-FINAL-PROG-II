package UserInterface.Screen;

import DataAccessComponent.DAOs.UserPlayerDAO;
import DataAccessComponent.DTOs.UserPlayerDTO;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;
import UserInterface.Utility.StyleConfig;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UpdatePlayerScreen {
    private static JTextField txtName;
    private static JTextField txtScore;
    private static JComboBox<String> cmbStatus;
    private static JTable table;
    private static UserPlayerDTO selectedPlayer = new UserPlayerDTO();
    private static JComponent[][] components = new JComponent[6][10];
    private static ArrayList<JTextField> inputFields = new ArrayList<>();


    public static JPanel updatePlayerPanel() {

        ImageBackgroundPanel centerPanel = new ImageBackgroundPanel(
                ReusableMethods.getImageBackground());
        JLabel title = StyleConfig.tittleConfig();
        centerPanel.add(title, BorderLayout.NORTH);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel tableSection = createTableSection();
        centerPanel.add(tableSection);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel formSection = createFormSection();
        centerPanel.add(formSection);
        JPanel keyboard = ScreenKeyboard.keyboard(inputFields);
        JButton[][] buttons = ScreenKeyboard.getButtons();
          for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                components[i+1][j] = buttons[i][j];
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
        table.setFocusable(false);
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
        JLabel lblNameLabel = new JLabel("Nombre:");
        lblNameLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblNameLabel, gbc);

        gbc.gridx = 1;
        txtName = new JTextField(20);
        inputFields.add(txtName);
        txtName.setFocusable(false);
        fieldsPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblScoreLabel = new JLabel("Score:");
        lblScoreLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblScoreLabel, gbc);

        gbc.gridx = 1;
        txtScore = new JTextField(20);
        inputFields.add(txtScore);
        txtScore.setFocusable(false);
        fieldsPanel.add(txtScore, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblStatusLabel = new JLabel("Status:");
        lblStatusLabel.setForeground(Color.BLACK);
        fieldsPanel.add(lblStatusLabel, gbc);

        gbc.gridx = 1;
        cmbStatus = new JComboBox<>(new String[] { "Activo", "Inactivo" });


        
     for(int i =0; i< 10; i++) {
         components[0][i]=cmbStatus;
     }
        cmbStatus.setFocusable(false);
        fieldsPanel.add(cmbStatus, gbc);
        panel.add(fieldsPanel);

        return panel;
    }

    private static JPanel createButtonsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setOpaque(false);

        JButton btnUpdate = StyleConfig.createButton("Actualizar", StyleConfig.ButtonPrimary(), 150, 40);
        btnUpdate.addActionListener(e -> updatePlayer());

        JButton btnCancel = StyleConfig.createButton("Cancelar", StyleConfig.ButtonSecondary(), 150, 40);
        btnCancel.addActionListener(e -> clearForm());

        JButton btnBack = StyleConfig.createButton("Regresar", StyleConfig.ButtonSecondary(), 150, 40);
        btnBack.addActionListener(e -> MainFrame.setContentPane(ScreenAdmin.MenuAdmin()));
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
        cmbStatus.setSelectedIndex(0);
        selectedPlayer = null;
        table.clearSelection();
    }
}
