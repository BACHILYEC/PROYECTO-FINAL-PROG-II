package UserInterface.Screen;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {
    public static JFrame frame;

    public static void Frame() {
        frame = new JFrame("Liminalis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(GameScreen.game());
        frame.setVisible(true);
    }

    public static void setContentPane(JPanel panel) {
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }
}
