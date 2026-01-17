package BusinessLogic;

import DataAccessComponent.DAOs.AnswerDAO;
import DataAccessComponent.DTOs.AnswerDTO;

public class AnswerBL {

    public Integer processAnswerAndScore(AnswerDTO selectedAnswer) {
        if (selectedAnswer != null && selectedAnswer.getCorrectAnswer() == 1) {
            return 1;
        }
        return 0;
    }

    public Boolean create(AnswerDTO user) throws Exception {
        AnswerBL answerBL = new AnswerBL();
        if (answerBL.create(user)) {
            return true;
        }
        return false;
    }

    public Boolean Update(AnswerDTO user) throws Exception {
        AnswerBL answerBL = new AnswerBL();
        if (answerBL.Update(user)) {
            return true;
        }
        return false;
    }

    public Boolean ChangeStatus(int id, Boolean status) throws Exception {
        AnswerBL answerBL = new AnswerBL();
        if (answerBL.ChangeStatus(id, status)) {
            return true;
        }
        return false;
    }

    public Integer GetMaxRow() throws Exception {
        AnswerBL answerBL = new AnswerBL();
        return answerBL.GetMaxRow();
    }

}
