package UserInterface.Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import UserInterface.Utility.AppConfig;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;

public class ScreenAdmin {
    private static JComponent[][] buttons = new JComponent[5][2];

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
            buttons[i][0] = boton;
            if (i < buttonLabels.length - 1) {
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
                        MainFrame.setContentPane(SearchPlayerScreen.searchPlayerPanel());
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
        buttons[4][0] = Exit;
        buttons[4][1] = GoToBack;
        ControllerDualsense ControllerDualsense = new ControllerDualsense();
        ControllerDualsense.setupKeyBindings(panel, buttons);

        ControllerDualsense.focusComponent(ControllerDualsense.getCurrentIndexX(),
                ControllerDualsense.getCurrentIndexY(),
                buttons);

        return panel;
    }

}