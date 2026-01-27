package BusinessLogic;

import java.util.List;

import DataAccessComponent.DAOs.AnswerDAO;
import DataAccessComponent.DTOs.AnswerDTO;
import Infrastructure.AppException;

public class AnswerBL {

    public List<AnswerDTO> readOption(int question, Boolean status) throws AppException {
        AnswerDAO answerDAO = new AnswerDAO();
        return answerDAO.readOptions(question, status);

    }

    public String readCorrectAns(int idQuestion) throws AppException{
        AnswerDAO answerDAO = new AnswerDAO();
        return answerDAO.readCorrectAns(idQuestion);
    }


}
