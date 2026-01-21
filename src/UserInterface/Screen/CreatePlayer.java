package UserInterface.Screen;

import java.awt.*;
import javax.swing.*;

public class CreatePlayer {
    public static JPanel createPlayerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel tittle = new JLabel("Crear Jugador", SwingConstants.CENTER);
        tittle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(tittle, BorderLayout.NORTH);
        JPanel getName = new JPanel();
        getName.setLayout(new BoxLayout(getName, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel("Nombre de Jugador:");
        JTextField nameField = new JTextField(15);
        JButton createButton = new JButton("Crear");
        getName.add(nameLabel);
        getName.add(nameField);
        getName.add(createButton);
        panel.add(getName, BorderLayout.CENTER);
        return panel;
    }

}
