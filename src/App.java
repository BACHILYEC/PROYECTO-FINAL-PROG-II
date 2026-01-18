import DataAccessComponent.DAOs.AnswerDAO;
import DataAccessComponent.DTOs.AnswerDTO;

public class App {
    public static void main(String[] args) throws Exception {

        AnswerDAO dao = new AnswerDAO();
        for (AnswerDTO answer : dao.readOptions(48, true)) {
            System.out.println(answer.toString());
        }
    }
}