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

import DataAccessComponent.DAOs.UserPlayerDAO;
import DataAccessComponent.DTOs.UserPlayerDTO;
import UserInterface.Utility.AppConfig;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;

public class ScreenAdmin {
    private static JButton[] buttons;
    private static int currentIndex = 0;

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
        JButton[] mainButtons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton boton = AppConfig.createButton(buttonLabels[i], AppConfig.ButtonPrimary(), 200, 50);
            boton.setMaximumSize(new Dimension(200, 75));
            boton.setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonPanel.add(boton);
            mainButtons[i] = boton;

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

        buttons = new JButton[mainButtons.length + 2];
        System.arraycopy(mainButtons, 0, buttons, 0, mainButtons.length);
        buttons[mainButtons.length] = Exit;
        buttons[mainButtons.length + 1] = GoToBack;

        setupKeyBindings(panel);

        highlightButton(currentIndex);

        return panel;
    }

    private static void setupKeyBindings(JPanel panel) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "nextButton");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "nextButton");
        actionMap.put("nextButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % buttons.length;
                highlightButton(currentIndex);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("UP"), "prevButton");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "prevButton");
        actionMap.put("prevButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex - 1 + buttons.length) % buttons.length;
                highlightButton(currentIndex);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "clickButton");
        actionMap.put("clickButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttons[currentIndex].doClick();
            }
        });
    }

    private static void highlightButton(int index) {

        for (JButton button : buttons) {
            button.setBorder(BorderFactory.createEmptyBorder());
        }

        buttons[index].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        buttons[index].requestFocusInWindow();
    }
}