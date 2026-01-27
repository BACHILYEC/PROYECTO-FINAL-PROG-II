package UserInterface.Screen;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {
    public static JFrame frame;

    public static void Frame() {
        frame = new JFrame("Liminalis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(LoadingScreen.loadingPanel());
        frame.setVisible(true);
    }

    public static void setContentPane(JPanel panel) {
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }
}
