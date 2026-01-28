package BusinessLogic;

import DataAccessComponent.DAOs.QuestionDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import DataAccessComponent.DTOs.QuestionDTO;
import Infrastructure.AppException;

public class QuestionBL {

    private QuestionDTO QuestionDTO;
    private QuestionDAO QuestionDAO = new QuestionDAO();
    

    public QuestionDTO readByQuestion(Integer id) throws AppException {
        QuestionDTO = QuestionDAO.readByQuestion(id);
        return QuestionDTO;
    }

    public List<QuestionDTO> readAllQuestion() throws AppException {
        return QuestionDAO.readAllQuestion();
    }

    public List<QuestionDTO> getQuestionsForGame() throws AppException {
        try {
            List<QuestionDTO> selectedQuestions = new ArrayList<>();
            Random random = new Random();
            int totalRecords = QuestionDAO.getMaxReg();

            int goal = Math.min(totalRecords, 5);

            while (selectedQuestions.size() < goal) {
                int idRandom = random.nextInt(totalRecords) + 1;
                QuestionDTO question = QuestionDAO.readByQuestion(idRandom);

                if (question != null && !isAlreadyInList(selectedQuestions, question)) {
                    selectedQuestions.add(question);
                }
            }
            return selectedQuestions;
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("No se pudieron obtener las preguntas para el juego", e, QuestionBL.class, "getQuestionsForGame");
        }
    }

    private boolean isAlreadyInList(List<QuestionDTO> QuestionList, QuestionDTO newQuestion) throws AppException {
        for (QuestionDTO question : QuestionList) {
            if (question.getIdQuestion().equals(newQuestion.getIdQuestion())) {
                return true;
            }
        }
        return false;
    }

}
