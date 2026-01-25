package UserInterface.Screen;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import Infrastructure.AppException;
import UserInterface.Utility.StyleConfig;
import UserInterface.Utility.ImageBackgroundPanel;

public class CreatePlayer {
    private static JComponent[][] components;
    public static JTextField nameField;

    public static JPanel createPlayerPanel() {

        Font login = new Font("Comic Sans MS", Font.BOLD, 18);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel tittle = StyleConfig.tittleConfig();
        panel.add(tittle, BorderLayout.NORTH);

        JPanel getName = new JPanel(new BorderLayout());
        ImageBackgroundPanel namePanel = new ImageBackgroundPanel(
                "/UserInterface/Resources/ImagenBackGroundLogin.png");
        namePanel.setLayout(new BorderLayout());

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        textPanel.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
        textPanel.setOpaque(false);
        JLabel nameLabel = new JLabel("Nombre de Jugador:");
        nameLabel.setFont(login);
        nameField = new JTextField(10);
        textPanel.add(nameLabel);
        textPanel.add(nameField);

        ArrayList<JTextField> input = new ArrayList<>();
        input.add(nameField);
        JPanel keyboard = ScreenKeyboard.keyboard(input);

        JPanel centerContent = new JPanel(new BorderLayout());
        centerContent.setOpaque(false);
        centerContent.add(textPanel, BorderLayout.NORTH);
        centerContent.add(keyboard, BorderLayout.CENTER);

        namePanel.add(centerContent, BorderLayout.NORTH);
        getName.add(namePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton create = StyleConfig.createButton("Jugar", StyleConfig.ButtonPrimary(), 200, 50);
        JPanel createPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        createPanel.setBackground(StyleConfig.ButtonPrimaryPanel());
        createPanel.add(create);
        create.addActionListener(e -> {
            try {
                if(nameField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(panel,"El nombre no puede estar vacio o tener espacios","Error",JOptionPane.ERROR_MESSAGE);
                    MainFrame.setContentPane(CreatePlayer.createPlayerPanel());
                }else{
                    MainFrame.setContentPane(GameScreen.game());
                }  
            } catch (AppException e1) {
                e1.printStackTrace();
            }
        });
        JButton goBack = StyleConfig.createButton("Regresar", StyleConfig.ButtonSecondary(), 150, 40);
        goBack.addActionListener(e -> {
            MainFrame.setContentPane(MainMenu.gameMenu());
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        backPanel.setBackground(StyleConfig.ButtonSecondaryPanel());
        backPanel.add(goBack);

        buttonPanel.add(createPanel);
        buttonPanel.add(backPanel);
        panel.add(getName, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JButton[][] buttons = ScreenKeyboard.getButtons();
        components = new JComponent[buttons.length + 2][buttons[0].length];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                components[i][j] = buttons[i][j];
            }
        }
        // asdasd
        for (int i = 0; i < buttons[0].length; i++) {
            components[buttons.length][i] = create;
            components[buttons.length + 1][i] = goBack;

        }

        ControllerDualsense controller = new ControllerDualsense();
        controller.setupKeyBindings(panel, components);
        controller.focusComponent(controller.getCurrentIndexX(), controller.getCurrentIndexY(), components);

        return panel;
    }
}