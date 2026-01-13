package BusinessLogic;

import DataAccessComponent.DAOs.UserAdminDAO;
import DataAccessComponent.DTOs.UserAdminDTO;

public class UserAdminBL {

    public Boolean Login(String username, String password) throws Exception {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        for (UserAdminDTO dto : userAdminDAO.readAllstatus(true)) {
            if (dto.getUserName().equals(username) && dto.getPassword().equals(password)) {
                userAdminDAO.update(dto);
                return true;
            }
        }
        return false;
    }

    public Boolean create(UserAdminDTO user) throws Exception {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        if (userAdminDAO.create(user)) {
            return true;
        }
        return false;
    }

    public Boolean Update(UserAdminDTO user) throws Exception {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        if (userAdminDAO.update(user)) {
            return true;
        }
        return false;
    }

    public Boolean ChangeStatus(int id, Boolean status) throws Exception {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        if (userAdminDAO.changestatus(id, status)) {
            return true;
        }
        return false;
    }

    public Integer GetMaxRow() throws Exception {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        return userAdminDAO.getMaxReg();
    }
}
