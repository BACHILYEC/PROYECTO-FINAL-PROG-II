package test.BussinesLogic;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import DataAccessComponent.DTOs.QuestionDTO;
import Infrastructure.AppException;
import BusinessLogic.QuestionBL;

public class QuestionBLTest {

    @Test
    public void readByQuestion_idValido_retornaPregunta() throws AppException {
        QuestionBL bl = new QuestionBL();

        QuestionDTO result = bl.readByQuestion(1);

        assertNotNull(result);
    }

    @Test
    public void readAllQuestion_existenDatos_retornaLista() throws AppException {
        QuestionBL bl = new QuestionBL();

        // Act
        List<QuestionDTO> list = bl.readAllQuestion();

        // Assert
        assertNotNull(list);
    }

    @Test
    public void getQuestionsForGame_retornaLista() throws AppException {
        QuestionBL bl = new QuestionBL();

        // Act
        List<QuestionDTO> result = bl.getQuestionsForGame();

        // Assert
        assertNotNull(result);
        assertTrue(result.size() <= 5);
    }
}
