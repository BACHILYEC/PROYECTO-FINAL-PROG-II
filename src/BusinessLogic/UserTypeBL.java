package BusinessLogic;

import DataAccessComponent.DAOs.UserTypeDAO;
import DataAccessComponent.DTOs.UserTypeDTO;
import Infrastructure.AppException;
import java.util.List;

public class UserTypeBL {

    public List<UserTypeDTO> getAll(Boolean status) throws AppException {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.readAllStatus(status);
    }

    public Boolean create(UserTypeDTO user) throws AppException {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.create(user);
    }

    public Boolean update(UserTypeDTO user) throws AppException {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.update(user);
    }

    public Boolean changeStatus(int id, Boolean status) throws AppException {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.changeStatus(id, status);
    }

    public Integer getMaxRow() throws AppException {
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        return userTypeDAO.getMaxReg();
    }
}
