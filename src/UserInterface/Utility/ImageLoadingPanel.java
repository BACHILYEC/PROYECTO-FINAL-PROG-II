package UserInterface.Utility;

import java.net.URL;

import javax.swing.*;
import java.awt.*;

public class ImageLoadingPanel extends JPanel {
    private Image backgroundImage;

    public ImageLoadingPanel(URL imageURL) {
        if (imageURL == null) {
            throw new RuntimeException("No se encontró la imagen: " + imageURL);
        }

        this.backgroundImage = new ImageIcon(imageURL).getImage();
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
