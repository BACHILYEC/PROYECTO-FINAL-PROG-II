import DataAccessComponent.DAOs.UserAdminDAO;
import DataAccessComponent.DAOs.AnswerDAO;
import DataAccessComponent.DTOs.UserAdminDTO;
import DataAccessComponent.DTOs.AnswerDTO;

public class App {
    public static void main(String[] args) throws Exception {
        AnswerDAO dao = new AnswerDAO();
        System.out.println(dao.readCorrectAns(10));
    }

}