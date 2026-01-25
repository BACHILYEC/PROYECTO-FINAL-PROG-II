package UserInterface.Screen;

import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;
import UserInterface.Utility.StyleConfig;
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
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel tittle = StyleConfig.tittleConfig();
        panel.add(tittle, BorderLayout.NORTH);
        ImageBackgroundPanel buttonPanel = new ImageBackgroundPanel(
                "/UserInterface/Resources/ImagenBackGroundLogin.png");
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
        JButton Exit = StyleConfig.createButton("Salir", StyleConfig.ButtonSecondary(), 150, 40);
        Exit.addActionListener(e -> {
            System.exit(0);
        });
        JButton GoToBack = StyleConfig.createButton("Regresar", StyleConfig.ButtonSecondary(), 150, 40);
        GoToBack.addActionListener(e -> {
            MainFrame.setContentPane(MainMenu.gameMenu());
        });
        String[] buttonLabels = { "Tabla De Jugadores", "Modificar Jugador", "Buscar Jugador" };

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton boton = StyleConfig.createButton(buttonLabels[i], StyleConfig.ButtonPrimary(), 200, 50);
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
                switch (index) {
                    case "Tabla De Jugadores": {
                        String[] columnNames = { "Player Id", "Usuario", "Score", "Status", "Creation Date",
                                "Modificate Date" };
                        JPanel pan = new JPanel();
                        JScrollPane tableScrollPane = ReusableMethods.createTableUser(columnNames, pan, true);
                        pan.setLayout(new BorderLayout());
                        pan.add(tableScrollPane, BorderLayout.CENTER);
                        JOptionPane.showMessageDialog(panel, pan, "Tabla De Jugadores",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case "Modificar Jugador": {
                        MainFrame.setContentPane(UpdatePlayerScreen.updatePlayerPanel());
                        break;
                    }
                    case "Buscar Jugador": {
                        MainFrame.setContentPane(SearchPlayerScreen.searchPlayerPanel());
                        break;
                    }
                }
            });
        }
        JPanel buttonsaux = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsaux.setBackground(StyleConfig.ButtonSecondaryPanel());
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