package UserInterface.Screen;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

public class ControllerDualsense {

    int currentIndexX = 0;
    int currentIndexY = 0;

    public int getCurrentIndexX() {
        return currentIndexX;
    }

    public void setCurrentIndexX(int currentIndexX) {
        this.currentIndexX = currentIndexX;
    }

    public int getCurrentIndexY() {
        return currentIndexY;
    }

    public void setCurrentIndexY(int currentIndexY) {
        this.currentIndexY = currentIndexY;
    }

    public void setupKeyBindings(JPanel panel, JComponent[][] components) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "prevComponent");
        actionMap.put("prevComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentIndexY((getCurrentIndexY() - 1 + components[getCurrentIndexX()].length)
                        % components[getCurrentIndexX()].length);
                focusComponent(getCurrentIndexX(), getCurrentIndexY(), components);

            }

        });

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "RightComponent");
        actionMap.put("RightComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentIndexY((getCurrentIndexY() + 1)
                        % components[getCurrentIndexX()].length);
                focusComponent(getCurrentIndexX(), getCurrentIndexY(), components);

            }
        });
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "DownComponent");
        actionMap.put("DownComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentIndexX((getCurrentIndexX() + 1)
                        % components.length);
                focusComponent(getCurrentIndexX(), getCurrentIndexY(), components);

            }
        });

        inputMap.put(KeyStroke.getKeyStroke("UP"), "UpComponent");
        actionMap.put("UpComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentIndexX((getCurrentIndexX() - 1 + components.length)
                        % components.length);
                focusComponent(getCurrentIndexX(), getCurrentIndexY(), components);

            }
        });

        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "activateComponent");
        actionMap.put("activateComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (components[getCurrentIndexX()][getCurrentIndexY()] instanceof JButton) {
                    ((JButton) components[getCurrentIndexX()][getCurrentIndexY()]).doClick();
                } else if (components[getCurrentIndexX()][getCurrentIndexY()] instanceof JToggleButton) {
                    ((JToggleButton) components[getCurrentIndexX()][getCurrentIndexY()]).doClick();
                } else if(components[getCurrentIndexX()][getCurrentIndexY()] instanceof JComboBox){
                    @SuppressWarnings("unchecked")
                    JComboBox<String> comboBox = (JComboBox<String>) components[getCurrentIndexX()][getCurrentIndexY()];
                    int selectedIndex = comboBox.getSelectedIndex();
                    int itemCount = comboBox.getItemCount();
                    comboBox.setSelectedIndex((selectedIndex + 1) % itemCount);
                }              
                else {

                    setCurrentIndexX((getCurrentIndexX() + 1) % components.length);
                    focusComponent(getCurrentIndexX(), getCurrentIndexY(), components);
                }
            }
        });
    }

    public void focusComponent(int indexX, int indexY, JComponent[][] components) {

        for (JComponent[] componentRow : components) {
            for (JComponent component : componentRow) {
                if (component instanceof JButton || component instanceof JToggleButton|| component instanceof JComboBox) {
                    component.setBorder(BorderFactory.createEmptyBorder());
                }
            }
        }

        if (components[indexX][indexY] instanceof JButton || components[indexX][indexY] instanceof JToggleButton|| components[indexX][indexY] instanceof JComboBox) {
            components[indexX][indexY].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        }
    }
}
