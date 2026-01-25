package UserInterface.Utility;

import javax.swing.*;
import java.awt.*;

import java.net.URL;

public class ImageBackgroundPanel extends JPanel {

    private Image backgroundImage;

    public ImageBackgroundPanel(String resourcePath) {

        URL imgURL = getClass().getResource(resourcePath);

        if (imgURL == null) {
            throw new RuntimeException("No se encontró la imagen: " + resourcePath);
        }

        this.backgroundImage = new ImageIcon(imgURL).getImage();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
