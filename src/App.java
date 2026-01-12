import DataAccessComponent.DAOs.AdministratorDAO;
import DataAccessComponent.DAOs.AnswerDAO;
import DataAccessComponent.DTOs.AdministratorDTO;
import DataAccessComponent.DTOs.AnswerDTO;

public class App {
    public static void main(String[] args) throws Exception {
        AnswerDAO dao = new AnswerDAO();
        for (AnswerDTO ans : dao.readAllstatus(true)) {
            System.out.println(ans.toString());
        }

        AdministratorDAO adminDao = new AdministratorDAO();
        for (AdministratorDTO admin : adminDao.readAllstatus(true)) {
            System.out.println(admin.toString());
        }
    }

}