package UserInterface.Screen;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import BusinessLogic.UserPlayerBL;
import Infrastructure.AppException;
import UserInterface.Utility.StyleConfig;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;

public class CreatePlayer {
    private static JComponent[][] components;
    public static JTextField playerNameField;

    public static JPanel createPlayerPanel() {

        Font login = new Font("Comic Sans MS", Font.BOLD, 18);

        JLabel tittle = StyleConfig.titleConfig();
        JPanel label = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageBackgroundPanel namePanel = new ImageBackgroundPanel(
                ReusableMethods.getImageBackground());
        tittle.setOpaque(false);
        label.add(tittle);
        label.setOpaque(false);
        namePanel.add(label);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        textPanel.setBorder(BorderFactory.createEmptyBorder(125, 0, 0, 0));
        textPanel.setOpaque(false);
        JLabel nameLabel = new JLabel("Nombre de Jugador:");
        nameLabel.setFont(login);
        playerNameField = new JTextField(10);
        textPanel.add(nameLabel);
        textPanel.add(playerNameField);

        ArrayList<JTextField> input = new ArrayList<>();
        input.add(playerNameField);
        JPanel keyboard = ScreenKeyboard.keyboard(input);
        keyboard.setOpaque(false);
        JPanel centerContent = new JPanel(new BorderLayout());
        centerContent.setOpaque(false);
        centerContent.add(textPanel, BorderLayout.NORTH);

        namePanel.add(centerContent, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(keyboard);
        JButton create = StyleConfig.createButton("Jugar", StyleConfig.buttonPrimary(), 200, 50);
        JPanel createPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        createPanel.setOpaque(false);
        createPanel.add(create);
        create.addActionListener(e -> {
            try {
                if (playerNameField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(namePanel, "Nombre no Valido", "Error", JOptionPane.ERROR_MESSAGE);
                    MainFrame.setContentPane(CreatePlayer.createPlayerPanel());
                } else {
                    try {
                        UserPlayerBL playerBL = new UserPlayerBL();
                        playerBL.create(playerNameField.getText().trim(), 0);

                        MainFrame.setContentPane(GameScreen.game());
                    } catch (AppException appEx) {
                        throw new AppException("Error al crear jugador: " + appEx.getMessage(), appEx,
                                CreatePlayer.class, "createPlayerPanel");
                    }
                }
            } catch (AppException appEx) {
                JOptionPane.showMessageDialog(namePanel, appEx.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                appEx.printStackTrace();
            }
        });
        JButton goBack = StyleConfig.createButton("Regresar", StyleConfig.buttonSecondary(), 150, 40);
        goBack.addActionListener(e -> {
            MainFrame.setContentPane(MainMenu.gameMenu());
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        backPanel.setOpaque(false);
        backPanel.add(goBack);

        buttonPanel.add(createPanel);
        buttonPanel.add(backPanel);
        namePanel.add(buttonPanel);

        JButton[][] buttons = ScreenKeyboard.getButtons();
        components = new JComponent[buttons.length + 2][buttons[0].length];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                components[i][j] = buttons[i][j];
            }
        }
        for (int i = 0; i < buttons[0].length; i++) {
            components[buttons.length][i] = create;
            components[buttons.length + 1][i] = goBack;

        }

        ControllerDualsense controller = new ControllerDualsense();
        controller.setupKeyBindings(namePanel, components);
        controller.focusComponent(controller.getCurrentIndexX(), controller.getCurrentIndexY(), components);

        return namePanel;
    }
}