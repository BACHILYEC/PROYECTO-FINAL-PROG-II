package UserInterface.Screen;

import javax.swing.*;
import UserInterface.Utility.StyleConfig;

import java.awt.*;
import java.util.ArrayList;

public class ScreenKeyboard {

    static Boolean mayus = true;
    static Boolean textfield = true;
    static JButton[][] buttons = new JButton[4][10];

    public static JButton[][] getButtons() {
        return buttons;
    }

    public static void setButtons(JButton[][] buttons) {
        ScreenKeyboard.buttons = buttons;
    }

    public static JPanel keyboard(ArrayList<JTextField> input) {
        final String[][] keys = {
                { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" },
                { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P" },
                { "A", "S", "D", "F", "G", "H", "J", "K", "L", "ENTER" },
                { "Z", "X", "C", "V", "B", "N", "M", "<--", "ESPACIO", "Mayus" }
        };
        JPanel panelKeyboard = new JPanel(new GridLayout(4, 10, 5, 5));
        panelKeyboard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        final String[] key = { "" };
        final String[] temp = { "" };
        final JTextField[] inputKey = { input.get(0) };
        inputKey[0].setBackground(Color.LIGHT_GRAY);
        inputKey[0].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        for (int i = 0; i < keys.length; i++) {
            for (int j = 0; j < keys[i].length; j++) {
                if (keys[i][j].equals("ESPACIO")) {
                    JButton button = StyleConfig.createButton("ESPACIO", StyleConfig.ButtonPrimary(), 40, 40);
                    button.addActionListener(e -> {
                        key[0] += " ";
                        inputKey[0].setText(key[0]);
                    });
                    panelKeyboard.add(button);
                    buttons[i][j] = button;
                } else if (keys[i][j].equals("ENTER")) {
                    JButton button = StyleConfig.createButton("ENTER", StyleConfig.ButtonPrimary(), 40, 40);
                    panelKeyboard.add(button);
                    buttons[i][j] = button;
                    button.addActionListener(e -> {
                        inputKey[0].setBackground(Color.WHITE);
                        inputKey[0].setBorder(null);
                        if (textfield) {
                            inputKey[0] = input.get(1);
                            temp[0] = key[0];
                            key[0] = "";
                            textfield = false;
                        } else {
                            inputKey[0] = input.get(0);
                            key[0] = temp[0];
                            inputKey[0].setText(key[0]);
                            textfield = true;
                        }
                        inputKey[0].setBackground(Color.LIGHT_GRAY);
                        inputKey[0].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                    });
                } else if (keys[i][j].equals("Mayus")) {
                    JButton button = StyleConfig.createButton("Mayus", StyleConfig.ButtonPrimary(), 40, 40);
                    panelKeyboard.add(button);
                    buttons[i][j] = button;
                    button.addActionListener(e -> {
                        mayus = !mayus;
                    });
                } else if (keys[i][j].equals("<--")) {
                    JButton button = StyleConfig.createButton("<--", StyleConfig.ButtonPrimary(), 40, 40);
                    panelKeyboard.add(button);
                    buttons[i][j] = button;
                    button.addActionListener(e -> {
                        if (key[0].length() > 0) {
                            key[0] = key[0].substring(0, key[0].length() - 1);
                            inputKey[0].setText(key[0]);
                        }
                    });
                } else {
                    JButton button = StyleConfig.createButton(keys[i][j], StyleConfig.keyboardButtons(), 40, 40);
                    panelKeyboard.add(button);
                    final int Letter = i;
                    final int Index = j;
                    buttons[i][j] = button;
                    button.addActionListener(e -> {
                        if (mayus) {
                            keys[Letter][Index] = keys[Letter][Index].toUpperCase();
                            key[0] += keys[Letter][Index];
                            inputKey[0].setText(key[0]);
                        } else {
                            keys[Letter][Index] = keys[Letter][Index].toLowerCase();
                            key[0] += keys[Letter][Index];
                            inputKey[0].setText(key[0]);
                        }
                    });
                }
            }
        }
        return panelKeyboard;
    }
}
