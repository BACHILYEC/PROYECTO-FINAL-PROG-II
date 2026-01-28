package UserInterface.Screen;

import javax.swing.*;
import UserInterface.Utility.StyleConfig;

import java.awt.*;
import java.util.ArrayList;

public class ScreenKeyboard {

    static Boolean isMayus = true;
    static Boolean isTextField = true;
    static JButton[][] buttons = new JButton[4][10];
    static int index = 0;

    public static JButton[][] getButtons() {
        return buttons;
    }

    public static void setButtons(JButton[][] buttons) {
        ScreenKeyboard.buttons = buttons;
    }

    static String[][] keysArray = new String[4][10];

    public static JPanel keyboard(ArrayList<JTextField> input) {
        JPanel panelKeyboard = new JPanel(new GridLayout(4, 10, 5, 5));
        panelKeyboard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelKeyboard.setOpaque(false);
        final String[] currentKey = { "" };
        final String[][] keysMayus = {
                { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" },
                { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P" },
                { "A", "S", "D", "F", "G", "H", "J", "K", "L", "ENTER" },
                { "Z", "X", "C", "V", "B", "N", "M", "<--", "ESPACIO", "Mayus" }
        };
        final String[][] keysMinusArray = {
                { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" },
                { "q", "w", "e", "r", "t", "y", "u", "i", "o", "p" },
                { "a", "s", "d", "f", "g", "h", "j", "k", "l", "ENTER" },
                { "z", "x", "c", "v", "b", "n", "m", "<--", "ESPACIO", "Mayus" }
        };
        keysArray = keysMayus;
        final JTextField[] inputKey = { input.get(0) };
        inputKey[0].setBorder(BorderFactory.createLineBorder(Color.black, 1));
        for (int i = 0; i < keysArray.length; i++) {
            for (int j = 0; j < keysArray[i].length; j++) {
                if (keysArray[i][j].equals("ESPACIO")) {
                    JButton button = StyleConfig.createButton("ESPACIO", StyleConfig.buttonPrimary(), 40, 40);
                    button.addActionListener(e -> {
                        currentKey[0] += " ";
                        inputKey[0].setText(currentKey[0]);
                    });
                    panelKeyboard.add(button);
                    buttons[i][j] = button;
                } else if (keysArray[i][j].equals("ENTER")) {
                    JButton button = StyleConfig.createButton("ENTER", StyleConfig.buttonPrimary(), 40, 40);
                    panelKeyboard.add(button);
                    buttons[i][j] = button;
                    final int[] inputIndex = { 0 };
                    button.addActionListener(e -> {
                        inputKey[0].setBorder(null);
                        inputIndex[0]++;
                        if (inputIndex[0] >= input.size()) {
                            inputIndex[0] = 0;
                        }
                        inputKey[0] = input.get(inputIndex[0]);
                        inputKey[0].setText("");
                        currentKey[0] = "";
                        inputKey[0].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    });
                } else if (keysArray[i][j].equals("Mayus")) {
                    JButton button = StyleConfig.createButton("Mayus", StyleConfig.buttonPrimary(), 40, 40);
                    panelKeyboard.add(button);
                    buttons[i][j] = button;
                    button.addActionListener(e -> {
                        if (isMayus) {
                            keysArray = keysMinusArray;
                            for (int r = 0; r < keysArray.length; r++) {
                                for (int c = 0; c < keysArray[r].length; c++) {
                                    if (!keysArray[r][c].equals("ENTER") && !keysArray[r][c].equals("<--")
                                            && !keysArray[r][c].equals("ESPACIO") && !keysArray[r][c].equals("Mayus")) {
                                        buttons[r][c].setText(keysArray[r][c]);
                                    }
                                }
                            }
                            panelKeyboard.revalidate();
                            panelKeyboard.repaint();
                        } else {
                            keysArray = keysMayus;
                            for (int r = 0; r < keysArray.length; r++) {
                                for (int c = 0; c < keysArray[r].length; c++) {
                                    if (!keysArray[r][c].equals("ENTER") && !keysArray[r][c].equals("<--")
                                            && !keysArray[r][c].equals("ESPACIO") && !keysArray[r][c].equals("Mayus")) {
                                        buttons[r][c].setText(keysArray[r][c]);
                                    }
                                }
                            }
                            panelKeyboard.revalidate();
                            panelKeyboard.repaint();
                        }
                        isMayus = !isMayus;
                    });
                } else if (keysArray[i][j].equals("<--")) {
                    JButton button = StyleConfig.createButton("<--", StyleConfig.buttonPrimary(), 40, 40);
                    panelKeyboard.add(button);
                    buttons[i][j] = button;
                    button.addActionListener(e -> {
                        if (currentKey[0].length() > 0) {
                            currentKey[0] = currentKey[0].substring(0, currentKey[0].length() - 1);
                            inputKey[0].setText(currentKey[0]);
                        }
                    });
                } else {
                    JButton button = StyleConfig.createButton(keysArray[i][j], StyleConfig.keyboardButtons(), 40, 40);
                    panelKeyboard.add(button);
                    final int letterIndex = i;
                    final int charIndex = j;
                    buttons[i][j] = button;
                    button.addActionListener(e -> {
                        if (isMayus) {
                            keysArray[letterIndex][charIndex] = keysArray[letterIndex][charIndex].toUpperCase();
                            currentKey[0] += keysArray[letterIndex][charIndex];
                            inputKey[0].setText(currentKey[0]);
                        } else {
                            keysArray[letterIndex][charIndex] = keysArray[letterIndex][charIndex].toLowerCase();
                            currentKey[0] += keysArray[letterIndex][charIndex];
                            inputKey[0].setText(currentKey[0]);
                        }
                    });
                }
            }
        }
        return panelKeyboard;
    }
}
