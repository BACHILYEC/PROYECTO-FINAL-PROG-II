package UserInterface.Screen;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.ReusableMethods;

public class ScreenAdmin {
    public static JPanel MenuAdmin() {
        JPanel panel = new JPanel();
        Color tittleColorPanel = new Color(173, 160, 219);
        Color PanelButton = new Color(199, 186, 212);
        Font tittlefont = new Font("Comic Sans MS", Font.BOLD, 36);
        Font exitButtFont = new Font("Comic Sans MS", Font.BOLD, 18);
        Font buttonFont = new Font("Comic Sans MS", Font.PLAIN, 16);
        panel.setLayout(new BorderLayout());
        JLabel tittle = new JLabel("Liminalis", SwingConstants.CENTER);
        tittle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tittle.setOpaque(true);
        tittle.setBackground(tittleColorPanel);
        tittle.setForeground(Color.BLACK);
        tittle.setFont(tittlefont);
        panel.add(tittle, BorderLayout.NORTH);
        ImageBackgroundPanel buttonPanel = new ImageBackgroundPanel(
                "src\\UserInterface\\Resources\\ImagenBackGroundLogin.png");
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
        Dimension boton_size = new Dimension(250, 30);
        JButton Exit = ReusableMethods.Button_Exit("Salir", boton_size);
        JButton GoToBack = new JButton("Regresar");
        GoToBack.addActionListener(e -> {
            MainMenu.gameMenu();
        });
        String[] buttonLabels = { "Tabla De Jugadores", "Agregar Jugador", "Modificar Jugador", "Buscar Jugador" };
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton boton = new JButton(buttonLabels[i]);
            boton.setAlignmentX(Component.LEFT_ALIGNMENT);
            boton.setMaximumSize(boton_size);
            boton.setFocusPainted(false);
            boton.setFont(buttonFont);
            boton.setBackground(Color.WHITE);
            buttonPanel.add(boton);
            if (i - 1 < buttonLabels.length) {
                buttonPanel.add(Box.createRigidArea(new Dimension(20, 70)));
            }
            String index = buttonLabels[i];
            // boton.addActionListener(e -> {
            // switch (index) {
            // case "Mostrar Datos": {
            // ReusableMethods.setContentPane(ScreenData.showData(frame), frame);
            // break;
            // }
            // case "Crear Datos": {
            // ReusableMethods.setContentPane(ScreenData.createData(frame), frame);
            // break;
            // }
            // case "Actualizar Datos": {
            // ReusableMethods.setContentPane(ScreenData.updateData(frame), frame);
            // break;
            // }
            // case "Buscar Datos": {
            // ReusableMethods.setContentPane(ScreenData.searchData(frame), frame);
            // break;
            // }
            // }
            // });
        }
        JPanel buttonsaux = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsaux.setFont(exitButtFont);
        buttonsaux.setBackground(PanelButton);
        buttonsaux.add(Exit);
        buttonsaux.add(GoToBack);
        panel.add(buttonsaux, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        return panel;
    }
}