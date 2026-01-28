package BusinessLogic;

import java.util.List;

import DataAccessComponent.DAOs.UserAdminDAO;
import DataAccessComponent.DTOs.UserAdminDTO;
import Infrastructure.AppException;

public class UserAdminBL {

    private UserAdminDAO userAdminDAO;

    public UserAdminBL() {
        this.userAdminDAO = new UserAdminDAO();
    }

    public UserAdminBL(UserAdminDAO userAdminDAO) {
        this.userAdminDAO = userAdminDAO;
    }

    public Boolean login(String username, String password) throws AppException {
        try {
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
            throw new AppException(
                    "Error al validar las credenciales del administrador",
                    e,
                    UserAdminBL.class,
                    "login");
        }
    }

    public Boolean create(UserAdminDTO user) throws AppException {
        return userAdminDAO.create(user);
    }

    public Boolean update(UserAdminDTO user) throws AppException {
        return userAdminDAO.update(user);
    }

    public Boolean changeStatus(int id, Boolean status) throws AppException {
        return userAdminDAO.changeStatus(id, status);
    }

    public List<UserAdminDTO> readAllStatus(boolean status) throws AppException {
        return userAdminDAO.readAllStatus(status);
    }

    public Integer getMaxRow() throws AppException {
        return userAdminDAO.getMaxReg();
    }

    public UserAdminDTO searchByName(String username) throws AppException {
        return userAdminDAO.readByName(username);
    }
}
