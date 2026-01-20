package UserInterface.Screen;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {
    public static JFrame frame;

    public static void Frame() {
        frame = new JFrame("Liminalis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(MainMenu.gameMenu());
        frame.setVisible(true);
    }

    public static void setContentPane(JPanel panel) {
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }
}
