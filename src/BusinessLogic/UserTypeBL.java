package BusinessLogic;

import DataAccessComponent.DAOs.UserTypeDAO;
import DataAccessComponent.DTOs.UserTypeDTO;
import java.util.List;

public class UserTypeBL {

    public List<UserTypeDTO> GetAll(Boolean status) throws Exception {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.readAllstatus(status);
    }

    public Boolean create(UserTypeDTO user) throws Exception {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        if (userTypeDAO.create(user)) {
            return true;
        }
        return false;
    }

    public Boolean Update(UserTypeDTO user) throws Exception {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        if (userTypeDAO.update(user)) {
            return true;
        }
        return false;
    }

    public Boolean ChangeStatus(int id, Boolean status) throws Exception {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        if (userTypeDAO.changestatus(id, status)) {
            return true;
        }
        return false;
    }

    public Integer GetMaxRow() throws Exception {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.getMaxReg();
    }
}
