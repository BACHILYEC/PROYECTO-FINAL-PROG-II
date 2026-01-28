package test.BussinesLogic;

import org.junit.Test;

import BusinessLogic.UserPlayerBL;

import static org.junit.Assert.*;

import java.util.List;

import DataAccessComponent.DTOs.UserPlayerDTO;
import Infrastructure.AppException;

public class UserPlayerBLTest {

    @Test
    public void exists_usuarioInexistente_retornaFalse() throws AppException {
        UserPlayerBL bl = new UserPlayerBL();
        Boolean result = bl.exists("usuario_que_no_existe");
        assertFalse(result);
    }

    @Test
    public void getAllPlayers_retornaLista() throws AppException {
        UserPlayerBL bl = new UserPlayerBL();
        List<UserPlayerDTO> list = bl.getAllPlayers();
        assertNotNull(list);
    }

    @Test
    public void getAllActivePlayers_activos_retornaLista() throws AppException {
        UserPlayerBL bl = new UserPlayerBL();
        List<UserPlayerDTO> list = bl.getAllActivePlayers(true);
        assertNotNull(list);
    }
}
