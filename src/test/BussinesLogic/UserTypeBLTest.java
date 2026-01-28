package test.BussinesLogic;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import DataAccessComponent.DTOs.UserTypeDTO;
import Infrastructure.AppException;
import BusinessLogic.UserTypeBL;

public class UserTypeBLTest {

    @Test
    public void getAll_activos_retornaLista() throws AppException {
        UserTypeBL bl = new UserTypeBL();
        List<UserTypeDTO> list = bl.getAll(true);
        assertNotNull(list);
    }

    @Test
    public void getMaxRow_retornaNumero() throws AppException {
        UserTypeBL bl = new UserTypeBL();
        Integer max = bl.getMaxRow();
        assertNotNull(max);
        assertTrue(max >= 0);
    }
}
