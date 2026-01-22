package UserInterface.Screen;

import javax.swing.*;

import java.awt.event.ActionEvent;
import UserInterface.Utility.AppConfig;

import java.awt.*;

public class ScreenKeyboard {
    private String[] keys = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L", "ENTER",
            "Z", "X", "C", "V", "B", "N", "M", "<--", "ESPACIO", "Mayus"
    };
    private static JButton[] buttons = new JButton[40];
    private static int currentIndex = 0;
    private Boolean mayus = false;

    public JPanel keyboard(JTextField inputKey) {
        // JPanel mainPanel = new JPanel(new BorderLayout());
        // JPanel CenterPanel = new JPanel(new BorderLayout());
        JPanel panelKeyboard = new JPanel(new GridLayout(4, 10, 5, 5));
        panelKeyboard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        final String[] key = { "" };
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals("ESPACIO")) {
                JButton button = AppConfig.createButton("ESPACIO", AppConfig.ButtonPrimary(), 40, 40);
                button.addActionListener(e -> {
                    key[0] += " ";
                    inputKey.setText(key[0]);
                });
                panelKeyboard.add(button);
                buttons[i] = button;
            } else if (keys[i].equals("ENTER")) {
                JButton button = AppConfig.createButton("ENTER", AppConfig.ButtonPrimary(), 40, 40);
                panelKeyboard.add(button);
                buttons[i] = button;
                button.addActionListener(e -> {
                    return;
                });
            } else if (keys[i].equals("Mayus")) {
                JButton button = AppConfig.createButton("Mayus", AppConfig.ButtonPrimary(), 40, 40);
                panelKeyboard.add(button);
                buttons[i] = button;
                button.addActionListener(e -> {
                    mayus = !mayus;
                });
            } else if (keys[i].equals("<--")) {
                JButton button = AppConfig.createButton("<--", AppConfig.ButtonPrimary(), 40, 40);
                panelKeyboard.add(button);
                buttons[i] = button;
                button.addActionListener(e -> {
                    if (key[0].length() > 0) {
                        key[0] = key[0].substring(0, key[0].length() - 1);
                        inputKey.setText(key[0]);
                    }
                });
            } else {
                JButton button = AppConfig.createButton(keys[i], AppConfig.keyboardButtons(), 40, 40);
                panelKeyboard.add(button);
                final int Letter = i;
                buttons[i] = button;
                button.addActionListener(e -> {
                    if (mayus) {
                        keys[Letter] = keys[Letter].toUpperCase();
                        key[0] += keys[Letter];
                        inputKey.setText(key[0]);
                    } else {
                        keys[Letter] = keys[Letter].toLowerCase();
                        key[0] += keys[Letter];
                        inputKey.setText(key[0]);
                    }
                });
            }
        }
        // CenterPanel.add(panelKeyboard, BorderLayout.SOUTH);
        // mainPanel.add(CenterPanel, BorderLayout.CENTER);

        // setupKeyBindings(mainPanel);

        // return mainPanel;
        return panelKeyboard;
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
