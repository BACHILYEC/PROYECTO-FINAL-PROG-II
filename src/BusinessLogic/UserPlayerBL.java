package BusinessLogic;

import java.util.List;

import DataAccessComponent.DAOs.UserPlayerDAO;
import DataAccessComponent.DTOs.UserPlayerDTO;

public class UserPlayerBL {

    public static Boolean create(String username, int score) throws Exception {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();

        if (exists(username)) {
            int id = getIdByUsername(username);
            Update(username, score, id);
        } else {

            UserPlayerDTO userPlayerDTO = new UserPlayerDTO();
            userPlayerDTO.setIdUserType(1);
            userPlayerDTO.setName(username);
            userPlayerDTO.setScore(0);
            try {
                if (userPlayerDAO.create(userPlayerDTO)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Boolean exists(String username) throws Exception {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        try {
            UserPlayerDTO player = userPlayerDAO.readByName(username);
            return player != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getIdByUsername(String username) throws Exception {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        try {
            UserPlayerDTO player = userPlayerDAO.readByName(username);
            return player.getIdPlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<UserPlayerDTO> getAllActivePlayers() throws Exception {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        try {
            return userPlayerDAO.readAllstatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Boolean Update(String username, Integer score, int id) throws Exception {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        UserPlayerDTO userPlayerDTO = new UserPlayerDTO();
        userPlayerDTO.setName(username);
        userPlayerDTO.setScore(score);
        userPlayerDTO.setIdPlayer(id);
        try {
            if (userPlayerDAO.update(userPlayerDTO)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
