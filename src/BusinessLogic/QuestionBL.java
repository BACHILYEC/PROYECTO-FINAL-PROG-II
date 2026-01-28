package BusinessLogic;

import DataAccessComponent.DAOs.QuestionDAO;
import DataAccessComponent.DTOs.QuestionDTO;
import Infrastructure.AppException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionBL {

    private QuestionDAO questionDAO;
    private Random random;

    public QuestionBL() {
        this.questionDAO = new QuestionDAO();
        this.random = new Random();
    }

    public QuestionBL(QuestionDAO questionDAO, Random random) {
        this.questionDAO = questionDAO;
        this.random = random;
    }

    public QuestionDTO readByQuestion(Integer id) throws AppException {
        return questionDAO.readByQuestion(id);
    }

    public List<QuestionDTO> readAllQuestion() throws AppException {
        return questionDAO.readAllQuestion();
    }

    public List<QuestionDTO> getQuestionsForGame() throws AppException {
        try {
            List<QuestionDTO> selectedQuestions = new ArrayList<>();
            int totalRecords = questionDAO.getMaxReg();
            int goal = Math.min(totalRecords, 5);

            while (selectedQuestions.size() < goal) {
                int idRandom = random.nextInt(totalRecords) + 1;
                QuestionDTO question = questionDAO.readByQuestion(idRandom);

                if (question != null && !isAlreadyInList(selectedQuestions, question)) {
                    selectedQuestions.add(question);
                }
            }
            return selectedQuestions;
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException(
                    "No se pudieron obtener las preguntas para el juego",
                    e,
                    QuestionBL.class,
                    "getQuestionsForGame");
        }
    }

    private boolean isAlreadyInList(List<QuestionDTO> list, QuestionDTO newQuestion) {
        for (QuestionDTO q : list) {
            if (q.getIdQuestion().equals(newQuestion.getIdQuestion())) {
                return true;
            }
        }
        return false;
    }
}
