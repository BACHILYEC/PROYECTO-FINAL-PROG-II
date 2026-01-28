package UserInterface.Screen;

import javax.swing.*;

import Infrastructure.AppException;
import UserInterface.Utility.ImageLoadingPanel;
import UserInterface.Utility.ReusableMethods;

import java.awt.*;

public class LoadingScreen {
    public static JPanel loadingPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        ImageLoadingPanel loadingPanel = new ImageLoadingPanel(ReusableMethods.getIconApp());
        loadingPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 175, 15));
        JProgressBar progressBar = new JProgressBar(0, 100);
        ReusableMethods.applyCleanProgressBarUI(progressBar);
        progressBar.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE, 2),
                        BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        progressBar.setBackground(new Color(204, 159, 250));
        progressBar.setForeground(Color.WHITE);
        progressBar.setPreferredSize(new Dimension(500, 30));
        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        progressPanel.setOpaque(false);
        progressPanel.add(progressBar);
        loadingPanel.add(progressPanel, BorderLayout.SOUTH);
        mainPanel.add(loadingPanel, BorderLayout.CENTER);
        Timer loading = new Timer(50, null);
        loading.addActionListener(e -> {
            int value = progressBar.getValue();
            if (value < 100) {
                progressBar.setValue(value + 1);
            } else {
                loading.stop();
                try {
                    MainFrame.setContentPane(MainMenu.gameMenu());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Error al cargar menú: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    new AppException("Error al cargar pantalla de menú principal", ex, LoadingScreen.class,
                            "loadingPanel()");
                }
            }
        });
        loading.start();
        return mainPanel;
    }
}
