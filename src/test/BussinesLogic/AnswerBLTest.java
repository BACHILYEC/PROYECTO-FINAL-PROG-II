package test.BussinesLogic;

import org.junit.Test;

import BusinessLogic.AnswerBL;

import static org.junit.Assert.*;

import java.util.List;

import DataAccessComponent.DAOs.AnswerDAO;
import DataAccessComponent.DTOs.AnswerDTO;
import Infrastructure.AppException;

public class AnswerBLTest {

    @Test
    public void readOption_parametrosValidos_retornaLista() throws AppException {
        AnswerBL bl = new AnswerBL(new AnswerDAO());
        List<AnswerDTO> resultado = bl.readOption(1, true);
        assertNotNull(resultado);
    }

    @Test
    public void readCorrectAns_preguntaValida_retornaRespuesta() throws AppException {
        AnswerBL bl = new AnswerBL(new AnswerDAO());
        String respuesta = bl.readCorrectAns(1);
        assertNotNull(respuesta);
    }
}
