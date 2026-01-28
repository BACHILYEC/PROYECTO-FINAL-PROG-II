package BusinessLogic;

import java.util.List;
import DataAccessComponent.DAOs.AnswerDAO;
import DataAccessComponent.DTOs.AnswerDTO;
import Infrastructure.AppException;

public class AnswerBL {

    private AnswerDAO answerDAO;

    public AnswerBL(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    public List<AnswerDTO> readOption(int question, Boolean status) throws AppException {
        return answerDAO.readOptions(question, status);
    }

    public String readCorrectAns(int idQuestion) throws AppException {
        return answerDAO.readCorrectAns(idQuestion);
    }
}
