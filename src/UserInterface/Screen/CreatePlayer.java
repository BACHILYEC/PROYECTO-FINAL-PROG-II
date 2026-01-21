package UserInterface.Screen;

import java.awt.*;
import javax.swing.*;
import UserInterface.Utility.AppConfig;
import UserInterface.Utility.ImageBackgroundPanel;

public class CreatePlayer {
    public static JPanel createPlayerPanel() {
        Font login = new Font("Comic Sans MS", Font.BOLD, 18);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel tittle = AppConfig.tittleConfig();
        panel.add(tittle, BorderLayout.NORTH);
        JPanel getName = new JPanel(new BorderLayout());
        ImageBackgroundPanel namePanel = new ImageBackgroundPanel(
                "src\\UserInterface\\Resources\\ImagenBackGroundLogin.png");
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        textPanel.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
        textPanel.setOpaque(false);
        JLabel nameLabel = new JLabel("Nombre de Jugador:");
        nameLabel.setFont(login);
        JTextField nameField = new JTextField(10);
        textPanel.add(nameLabel);
        textPanel.add(nameField);
        namePanel.add(textPanel);
        getName.add(namePanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton create = AppConfig.createButton("Crear", AppConfig.ButtonPrimary(), 200, 50);
        JPanel createPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        createPanel.setBackground(AppConfig.ButtonPrimaryPanel());
        createPanel.add(create);
        JButton goBack = AppConfig.createButton("Regresar", AppConfig.ButtonSecondary(), 150, 40);
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        backPanel.setBackground(AppConfig.ButtonSecondaryPanel());
        backPanel.add(goBack);
        buttonPanel.add(createPanel);
        buttonPanel.add(backPanel);
        panel.add(getName, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

}
