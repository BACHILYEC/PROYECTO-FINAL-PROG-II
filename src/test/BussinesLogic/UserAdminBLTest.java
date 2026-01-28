package test.BussinesLogic;

import org.junit.Test;

import BusinessLogic.UserAdminBL;

import static org.junit.Assert.*;

import java.util.List;

import DataAccessComponent.DTOs.UserAdminDTO;
import Infrastructure.AppException;

public class UserAdminBLTest {

    @Test
    public void login_credencialesInvalidas_retornaFalse() throws AppException {
        UserAdminBL bl = new UserAdminBL();
        Boolean result = bl.login("no_existe", "incorrecta");
        assertFalse(result);
    }

    @Test
    public void readAllStatus_activos_retornaLista() throws AppException {
        UserAdminBL bl = new UserAdminBL();
        List<UserAdminDTO> list = bl.readAllStatus(true);
        assertNotNull(list);
    }

    @Test
    public void getMaxRow_retornaValor() throws AppException {
        UserAdminBL bl = new UserAdminBL();
        Integer max = bl.getMaxRow();
        assertNotNull(max);
    }
}
