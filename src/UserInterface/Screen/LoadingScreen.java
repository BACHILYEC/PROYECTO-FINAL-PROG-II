package UserInterface.Screen;

import javax.swing.*;

import UserInterface.Utility.StyleConfig;

import java.awt.*;

public class LoadingScreen {
    public static JPanel loadingPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel loadingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel informationPanel = new JPanel(new GridLayout(2, 1));
        JLabel titleLabel = StyleConfig.tittleConfig();
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(informationPanel, BorderLayout.CENTER);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setForeground(StyleConfig.ButtonSecondary());
        progressBar.setPreferredSize(new Dimension(800, 50));
        loadingPanel.add(progressBar);
        mainPanel.add(loadingPanel, BorderLayout.SOUTH);
        Timer loading = new Timer(50, null);
        loading.addActionListener(e -> {
            int value = progressBar.getValue();
            if (value < 100) {
                progressBar.setValue(value + 1);
            } else {
                loading.stop();
                MainFrame.setContentPane(MainMenu.gameMenu());
            }
        });
        loading.start();
        return mainPanel;
    }
}
