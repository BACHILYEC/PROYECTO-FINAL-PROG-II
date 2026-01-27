
import DataAccessComponent.DAOs.DatabaseInitializerDAO;
import UserInterface.Screen.LiminalisSystem;

public class App {
    public static void main(String[] args) throws Exception {
        DatabaseInitializerDAO.initializeDDL();
        LiminalisSystem.StartGame();

    }
}