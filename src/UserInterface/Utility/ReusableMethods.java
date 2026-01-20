package UserInterface.Utility;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DataAccessComponent.DAOs.UserAdminDAO;
import DataAccessComponent.DTOs.UserAdminDTO;

public class ReusableMethods {
    public static JScrollPane createTableAdmin(String[] columnNames, JPanel panel, Boolean status) {

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable data = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel columnModel = data.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(data);
        model.setRowCount(0);
        UserAdminDAO dao = new UserAdminDAO();
        try {
            for (UserAdminDTO dto : dao.readAllstatus(status)) {
                String[] row = { dto.getUserName(), dto.getLastLogin(), dto.getIdAdministrator().toString() };
                model.addRow(row);
            }
        } catch (

        Exception ex) {
            JOptionPane.showMessageDialog(panel, "Error al obtener datos: " + ex.getMessage());
        }
        return scrollPane;
    }

    public static JButton Button_Exit(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            System.exit(0);
        });
        button.setMaximumSize(size);
        return button;
    }
}