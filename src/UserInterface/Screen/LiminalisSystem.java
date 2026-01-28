package UserInterface.Screen;

public class LiminalisSystem {
    public static void startGame() {
        try {
            MainFrame.createFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
