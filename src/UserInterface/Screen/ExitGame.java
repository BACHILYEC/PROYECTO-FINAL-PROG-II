package UserInterface.Screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import UserInterface.Utility.StyleConfig;

public class ExitGame {

    
    public static JPanel confirmExitPanel(JPanel previousPanel) {
    JPanel confirmPanel = new JPanel(new BorderLayout());
    confirmPanel.setBackground(StyleConfig.ButtonSecondaryPanel());
    confirmPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    JLabel titleLabel = new JLabel("¿Estás seguro de que quieres salir?");
    titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    titleLabel.setHorizontalAlignment(JLabel.CENTER);
    titleLabel.setForeground(new Color(60, 60, 60));
    titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
    confirmPanel.add(titleLabel, BorderLayout.NORTH);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
    buttonPanel.setOpaque(false);

    JButton yesButton = StyleConfig.createButton("Si", StyleConfig.ButtonPrimary(), 100, 50);
    yesButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
    yesButton.addActionListener(e -> {
        MainFrame.setContentPane(MainMenu.gameMenu());
    });

    JButton noButton = StyleConfig.createButton("No", StyleConfig.ButtonSecondary(), 100, 50);
    noButton.addActionListener(e -> {
        MainFrame.setContentPane(previousPanel);
    });

    JComponent[][] buttons = new JComponent[][] { { yesButton, noButton } };
            ControllerDualsense ControllerDualsense = new ControllerDualsense();
            ControllerDualsense.setupKeyBindings(confirmPanel, buttons);
            ControllerDualsense.focusComponent(ControllerDualsense.getCurrentIndexX(),
                    ControllerDualsense.getCurrentIndexY(), buttons);

    buttonPanel.add(yesButton);
    buttonPanel.add(noButton);
    confirmPanel.add(buttonPanel, BorderLayout.CENTER);

    return confirmPanel;
}

}
