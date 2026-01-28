package UserInterface.Utility;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import BusinessLogic.UserAdminBL;
import BusinessLogic.UserPlayerBL;
import DataAccessComponent.DTOs.UserAdminDTO;
import DataAccessComponent.DTOs.UserPlayerDTO;
import Infrastructure.AppException;

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
        UserAdminBL BL = new UserAdminBL();
        try {
            for (UserAdminDTO dto : BL.readAllStatus(status)) {
                String[] row = { dto.getUserName(), dto.getLastLogin(), dto.getIdAdministrator().toString() };
                model.addRow(row);
            }
        } catch (AppException appEx) {
            JOptionPane.showMessageDialog(panel, "Error al obtener datos de administradores: " + appEx.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Error al obtener datos: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            new AppException("Error al cargar tabla de administradores", ex, ReusableMethods.class,
                    "createTableAdmin()");
        }
        return scrollPane;
    }

    public static JScrollPane createTableUser(String[] columnNames, JPanel panel, Boolean status) {

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
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(data);
        model.setRowCount(0);

        try {
            UserPlayerBL UserPlayerBL = new UserPlayerBL();
            for (UserPlayerDTO dto : UserPlayerBL.getAllActivePlayers(status)) {
                String[] row = { dto.getIdPlayer().toString(), dto.getName(), dto.getScore().toString(),
                        dto.getStatus(), dto.getCreationDate(),
                        dto.getModificateDate() };
                model.addRow(row);
            }
        } catch (AppException appEx) {
            JOptionPane.showMessageDialog(panel, "Error al obtener datos de jugadores: " + appEx.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Error al obtener datos: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            new AppException("Error al cargar tabla de usuarios", ex, ReusableMethods.class, "createTableUser()");
        }
        return scrollPane;
    }

    public static JScrollPane createTablePlayer(String[] columnNames, JPanel panel, Boolean status) {

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable data = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel columnModel = data.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(data);
        model.setRowCount(0);

        try {
            UserPlayerBL bl = new UserPlayerBL();
            for (UserPlayerDTO dto : bl.getAllActivePlayers(status)) {
                String[] row = { dto.getIdPlayer().toString(), dto.getName(), dto.getScore().toString(),
                        dto.getModificateDate() };
                model.addRow(row);
            }
        } catch (AppException appEx) {
            JOptionPane.showMessageDialog(panel, "Error al obtener datos de jugadores: " + appEx.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Error al obtener datos: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            new AppException("Error al cargar tabla de jugadores", ex, ReusableMethods.class, "createTablePlayer()");
        }
        return scrollPane;
    }

    public static void setContentPane(JPanel panel, JFrame frame) {
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }

    public static JButton buttonExit(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            System.exit(0);
        });
        button.setMaximumSize(size);
        return button;
    }

    public static java.net.URL getImage(String relativePath) {
        java.net.URL url = ReusableMethods.class.getResource(relativePath);
        if (url == null) {
            System.err.println("WARNING: Resource not found: " + relativePath);
        }
        return url;
    }

    public static URL getImageBackground() {
        URL url = ReusableMethods.getImage("/UserInterface/Resources/BackGround.png");
        return url;
    }

    public static URL getTitle() {
        URL url = ReusableMethods.getImage("/UserInterface/Resources/icon.png");
        return url;
    }

    public static URL getQR() {
        URL url = ReusableMethods.getImage("/UserInterface/Resources/icon1.png");
        return url;
    }

    public static URL getIconApp() {
        URL url = ReusableMethods.getImage("/UserInterface/Resources/LoadingImage.png");
        return url;
    }

    public static void applyCleanProgressBarUI(JProgressBar progressBar) {
        progressBar.setUI(new BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Insets insets = progressBar.getInsets();
                int width = progressBar.getWidth() - insets.left - insets.right;
                int height = progressBar.getHeight() - insets.top - insets.bottom;

                int amountFull = getAmountFull(insets, width, height);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(progressBar.getForeground());
                g2.fillRect(
                        insets.left,
                        insets.top,
                        amountFull,
                        height);
                g2.dispose();
            }
        });
    }
}