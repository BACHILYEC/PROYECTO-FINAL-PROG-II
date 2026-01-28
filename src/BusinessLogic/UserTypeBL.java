package BusinessLogic;

import DataAccessComponent.DAOs.UserTypeDAO;
import DataAccessComponent.DTOs.UserTypeDTO;
import Infrastructure.AppException;
import java.util.List;

public class UserTypeBL {

    public List<UserTypeDTO> GetAll(Boolean status) throws AppException {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.readAllstatus(status);
    }

    public Boolean create(UserTypeDTO user) throws AppException {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.create(user);
    }

    public Boolean Update(UserTypeDTO user) throws AppException {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.update(user);
    }

    public Boolean ChangeStatus(int id, Boolean status) throws AppException {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.changestatus(id, status);
    }

    public Integer GetMaxRow() throws AppException {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.getMaxReg();
    }
}
