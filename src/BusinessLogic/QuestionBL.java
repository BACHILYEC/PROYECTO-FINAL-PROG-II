package BusinessLogic;

import DataAccessComponent.DAOs.QuestionDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import DataAccessComponent.DTOs.QuestionDTO;

public class QuestionBL {

    private QuestionDTO QuestionDTO;
    private QuestionDAO QuestionDAO = new QuestionDAO();
    

    public QuestionDTO readByQuestion(Integer id) throws Exception {

        QuestionDTO = QuestionDAO.readByQuestion(id);

        return QuestionDTO;
    }

    public List<QuestionDTO> readAllQuestion() throws Exception {
        return QuestionDAO.readAllQuestion();
    }

    public List<QuestionDTO> getQuestionsForGame() throws Exception {
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
    }

    private boolean isAlreadyInList(List<QuestionDTO> QuestionList, QuestionDTO newQuestion) {
        for (QuestionDTO question : QuestionList) {
            if (question.getIdQuestion().equals(newQuestion.getIdQuestion())) {
                return true;
            }
        }
        return false;
    }

}
