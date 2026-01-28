package UserInterface.Screen;

import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;
import UserInterface.Utility.StyleConfig;
import Infrastructure.AppException;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScreenAdmin {
    private static JComponent[][] buttons = new JComponent[4][2];

    public static JPanel MenuAdmin() {
        ImageBackgroundPanel panel = new ImageBackgroundPanel(
                ReusableMethods.getImageBackground());
        panel.setLayout(new BorderLayout());
        JLabel tittle = StyleConfig.titleConfig();
        panel.add(tittle, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
        buttonPanel.setOpaque(false);
        JButton Exit = StyleConfig.createButton("Salir", StyleConfig.buttonSecondary(), 150, 40);
        Exit.addActionListener(e -> {
            System.exit(0);
        });
        JButton GoToBack = StyleConfig.createButton("Regresar", StyleConfig.buttonSecondary(), 150, 40);
        GoToBack.addActionListener(e -> {
            MainFrame.setContentPane(MainMenu.gameMenu());
        });
        String[] buttonLabels = { "Tabla De Jugadores", "Modificar Jugador", "Buscar Jugador" };

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton boton = StyleConfig.createButton(buttonLabels[i], StyleConfig.buttonPrimary(), 200, 50);
            boton.setMaximumSize(new Dimension(200, 75));
            boton.setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonPanel.add(boton);
            buttons[i][0] = boton;
            buttons[i][1] = boton;
            if (i < buttonLabels.length - 1) {
                buttonPanel.add(Box.createRigidArea(new Dimension(20, 70)));
            }
            String index = buttonLabels[i];
            boton.addActionListener(e -> {
                try {
                    switch (index) {
                        case "Tabla De Jugadores": {
                            try {
                                String[] columnNames = { "IdJugador", "Usuario", "Puntuación", "Estado",
                                        "Fecha de Creación",
                                        "Fecha de Modificación" };
                                JPanel pan = new JPanel();
                                JScrollPane tableScrollPane = ReusableMethods.createTableUser(columnNames, pan, true);
                                pan.setLayout(new BorderLayout());
                                pan.add(tableScrollPane, BorderLayout.CENTER);
                                JOptionPane.showMessageDialog(panel, pan, "Tabla De Jugadores",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } catch (Exception ex) {
                                throw new AppException("Error al cargar tabla de jugadores", ex, ScreenAdmin.class,
                                        "MenuAdmin");
                            }
                            break;
                        }
                        case "Modificar Jugador": {
                            try {
                                MainFrame.setContentPane(UpdatePlayerScreen.updatePlayerPanel());
                            } catch (Exception ex) {
                                throw new AppException("Error al cargar pantalla de modificación", ex,
                                        ScreenAdmin.class, "MenuAdmin");
                            }
                            break;
                        }
                        case "Buscar Jugador": {
                            try {
                                MainFrame.setContentPane(SearchPlayerScreen.searchPlayerPanel());
                            } catch (Exception ex) {
                                throw new AppException("Error al cargar pantalla de búsqueda", ex, ScreenAdmin.class,
                                        "MenuAdmin");
                            }
                            break;
                        }
                    }
                } catch (AppException appEx) {
                    JOptionPane.showMessageDialog(panel, "Error: " + appEx.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
        }
        JPanel buttonsaux = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsaux.setOpaque(false);
        buttonsaux.add(Exit);
        buttonsaux.add(GoToBack);
        panel.add(buttonsaux, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        buttons[3][0] = Exit;
        buttons[3][1] = GoToBack;
        ControllerDualsense ControllerDualsense = new ControllerDualsense();
        ControllerDualsense.setupKeyBindings(panel, buttons);

        ControllerDualsense.focusComponent(ControllerDualsense.getCurrentIndexX(),
                ControllerDualsense.getCurrentIndexY(),
                buttons);

        return panel;
    }

}