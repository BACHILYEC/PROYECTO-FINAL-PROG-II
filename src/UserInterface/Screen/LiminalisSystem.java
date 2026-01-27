package UserInterface.Screen;

public class LiminalisSystem {
    public static void StartGame() {
        try {
            MainFrame.Frame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
