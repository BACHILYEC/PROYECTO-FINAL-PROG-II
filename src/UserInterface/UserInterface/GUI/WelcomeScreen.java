package UserInterface.GUI;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import UserInterface.WelcomeStyle;

public abstract class WelcomeScreen {

    private static JFrame cmFrmSplash;
    private static JProgressBar cmPrbLoading;
    private static ImageIcon cmIconImage;
    private static JLabel cmLblSplash;

    public static void show(){

        ImageIcon cmOriginalIcon = new ImageIcon(WelcomeStyle.cmURL_SPLASH);
        Image cmImage = cmOriginalIcon.getImage(); 
        Image cmNuevaImagen = cmImage.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        
        cmIconImage  = new ImageIcon(cmNuevaImagen);
        
        cmLblSplash  = new JLabel(cmIconImage);
        cmPrbLoading = new JProgressBar(0, 100);

        cmPrbLoading.setStringPainted(true);

        cmFrmSplash = new JFrame();
        cmFrmSplash.setUndecorated(true);

        cmFrmSplash.getContentPane().add(cmLblSplash, BorderLayout.CENTER);
        cmFrmSplash.add(cmPrbLoading, BorderLayout.SOUTH);
        
        cmFrmSplash.setSize(cmIconImage.getIconWidth(), cmIconImage.getIconHeight());
        cmFrmSplash.setLocationRelativeTo(null); 

        cmFrmSplash.setVisible(true);

        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(10); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cmPrbLoading.setValue(i);
        }

        cmFrmSplash.setVisible(false);
        cmFrmSplash.dispose();
        

    }

    
}


