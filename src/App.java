<<<<<<< HEAD

public class App {
    public static void main(String[] args) throws Exception {
=======
import DataAccessComponent.AdministratorDAO;
import DataAccessComponent.PlayerDAO;
import DataAccessComponent.DTO.AdministratorDTO;
import DataAccessComponent.DTO.PlayerDTO;

public class App {
    public static void main(String[] args) throws Exception {

        PlayerDAO player = new PlayerDAO();
        // PlayerDTO newPlayer = new PlayerDTO(1,1, "4x4", 100);
        // player.create(newPlayer);

        for(PlayerDTO p : player.readAllstatus(true)){
            System.out.println(p.toString());
        }


>>>>>>> 409865ba2e11b96bd26a0f35097c14754615be79
    }
}