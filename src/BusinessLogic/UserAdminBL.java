package BusinessLogic;

import java.util.List;

import DataAccessComponent.DAOs.UserAdminDAO;
import DataAccessComponent.DTOs.UserAdminDTO;
import Infrastructure.AppException;

public class UserAdminBL {

    public Boolean login(String username, String password) throws AppException {
        try {
            UserAdminDAO userAdminDAO = new UserAdminDAO();
            for (UserAdminDTO dto : userAdminDAO.readAllStatus(true)) {
                if (dto.getUserName().equals(username) && dto.getPassword().equals(password)) {
                    userAdminDAO.update(dto);
                    return true;
                }
            }
            return false;
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Error al validar las credenciales del administrador", e, UserAdminBL.class,
                    "login");
        }
    }

    public Boolean create(UserAdminDTO user) throws AppException {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        return userAdminDAO.create(user);
    }

    public Boolean update(UserAdminDTO user) throws AppException {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        return userAdminDAO.update(user);
    }

    public Boolean changeStatus(int id, Boolean status) throws AppException {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        return userAdminDAO.changeStatus(id, status);
    }

    public List<UserAdminDTO> readAllStatus(boolean status) throws AppException {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        return userAdminDAO.readAllStatus(status);
    }

    public Integer GetMaxRow() throws AppException {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        return userAdminDAO.getMaxReg();
    }

    public UserAdminDTO searchByName(String username) throws AppException {
        UserAdminDAO userAdminDAO = new UserAdminDAO();
        return userAdminDAO.readByName(username);
    }
}
