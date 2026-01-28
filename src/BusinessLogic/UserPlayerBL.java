package BusinessLogic;

import java.util.List;

import DataAccessComponent.DAOs.UserPlayerDAO;
import DataAccessComponent.DTOs.UserPlayerDTO;
import Infrastructure.AppException;

public class UserPlayerBL {

    public static Boolean create(String username, int score) throws AppException {
        try {
            UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
            if (exists(username)) {
                int id = getIdByUsername(username);
                return update(username, score, id);
            } else {
                UserPlayerDTO userPlayerDTO = new UserPlayerDTO();
                userPlayerDTO.setIdUserType(1);
                userPlayerDTO.setName(username);
                userPlayerDTO.setScore(score);
                return userPlayerDAO.create(userPlayerDTO);
            }
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("No se pudo crear el jugador: " + username, e, UserPlayerBL.class, "create");
        }
    }

    public static Boolean exists(String username) throws AppException {
        try {
            UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
            UserPlayerDTO player = userPlayerDAO.readByName(username);
            return player != null;
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("No se pudo verificar la existencia del jugador: " + username, e, UserPlayerBL.class,
                    "exists");
        }
    }

    public static int getIdByUsername(String username) throws AppException {
        try {
            UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
            UserPlayerDTO player = userPlayerDAO.readByName(username);
            if (player != null) {
                return player.getIdPlayer();
            }
            throw new AppException("Jugador no encontrado: " + username, null, UserPlayerBL.class, "getIdByUsername");
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Error al obtener el ID del jugador: " + username, e, UserPlayerBL.class,
                    "getIdByUsername");
        }
    }

    public static List<UserPlayerDTO> getAllActivePlayers(boolean status) throws AppException {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        return userPlayerDAO.readAllStatus(status);
    }

    public static Boolean update(String username, Integer score, int id) throws AppException {
        try {
            UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
            UserPlayerDTO userPlayerDTO = new UserPlayerDTO();
            userPlayerDTO.setName(username);
            userPlayerDTO.setScore(score);
            userPlayerDTO.setIdPlayer(id);
            return userPlayerDAO.update(userPlayerDTO);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("No se pudo actualizar el jugador: " + username, e, UserPlayerBL.class, "update");
        }
    }

    public static boolean updateAll(UserPlayerDTO user) throws AppException {
        try {
            UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
            return userPlayerDAO.update(user);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("No se pudo actualizar los datos del jugador", e, UserPlayerBL.class, "updateAll");
        }
    }

    public UserPlayerDTO searchByName(String username) throws AppException {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        return userPlayerDAO.readByName(username);
    }

    public static UserPlayerDTO readById(int id) throws AppException {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        return userPlayerDAO.readById(id);
    }

    public List<UserPlayerDTO> getAllPlayers() throws AppException {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        return userPlayerDAO.readAllStatus(true);
    }

}
