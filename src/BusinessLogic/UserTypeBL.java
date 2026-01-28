package BusinessLogic;

import DataAccessComponent.DAOs.UserTypeDAO;
import DataAccessComponent.DTOs.UserTypeDTO;
import Infrastructure.AppException;
import java.util.List;

public class UserTypeBL {

    private UserTypeDAO userTypeDAO;

    public UserTypeBL() {
        this.userTypeDAO = new UserTypeDAO();
    }

    public UserTypeBL(UserTypeDAO userTypeDAO) {
        this.userTypeDAO = userTypeDAO;
    }

    public List<UserTypeDTO> getAll(Boolean status) throws AppException {
        return userTypeDAO.readAllStatus(status);
    }

    public Boolean create(UserTypeDTO user) throws AppException {
        return userTypeDAO.create(user);
    }

    public Boolean update(UserTypeDTO user) throws AppException {
        return userTypeDAO.update(user);
    }

    public Boolean changeStatus(int id, Boolean status) throws AppException {
        return userTypeDAO.changeStatus(id, status);
    }

    public Integer getMaxRow() throws AppException {
        return userTypeDAO.getMaxReg();
    }
}
