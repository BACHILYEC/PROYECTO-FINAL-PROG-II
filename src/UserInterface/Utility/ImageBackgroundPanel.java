package UserInterface.Utility;

import javax.swing.*;
import java.awt.*;

public class ImageBackgroundPanel extends JPanel {

    private Image backgroundImage;

    public ImageBackgroundPanel(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
