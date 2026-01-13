package BusinessLogic;

import DataAccessComponent.DAOs.UserPlayerDAO;
import DataAccessComponent.DTOs.UserPlayerDTO;


public class UserPlayerBL {

    public Boolean create(String username) throws Exception {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        
        if (exists(username)) {
            return false;
        }
        
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
        return false;
    }
    
    public Boolean exists(String username) throws Exception {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        try {
            UserPlayerDTO player = userPlayerDAO.readByName(username);
            return player != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean Update(String username, Integer score) throws Exception {
        UserPlayerDAO userPlayerDAO = new UserPlayerDAO();
        UserPlayerDTO userPlayerDTO = new UserPlayerDTO();

        if (exists(username)) {
            userPlayerDTO.setName(username);
            userPlayerDTO.setScore(score);
            try {
                if (userPlayerDAO.update(userPlayerDTO)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        return false;
    }


}



