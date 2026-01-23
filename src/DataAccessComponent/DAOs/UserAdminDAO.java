package DataAccessComponent.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import DataAccessComponent.Helpers.DataHelperSQLite;
import DataAccessComponent.Interfaces.IDAO;
import DataAccessComponent.DTOs.UserAdminDTO;
import Infrastructure.AppException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAdminDAO extends DataHelperSQLite implements IDAO<UserAdminDTO> {

    @Override
    public List<UserAdminDTO> readBy(String name) throws Exception {
        throw new AppException("Método readBy no implementado para UserAdminDAO", null, getClass(), "readBy");
    }

    public UserAdminDTO readByName(String name) throws Exception {
        String query = "SELECT Password FROM UserAdmin WHERE UserName = '" + name + "';";
        UserAdminDTO admin = new UserAdminDTO();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                admin = new UserAdminDTO(rs.getString(1));
            }
            // conn.close();
        } catch (Exception e) {
            throw new AppException("No se pudo leer el usuario administrador: " + name, e, getClass(), "readByName");
        }
        return admin;
    }

    @Override
    public List<UserAdminDTO> readAllstatus(boolean status) throws Exception {
        String query = "SELECT idAdmin, UserName, Password, LastLogin FROM UserAdmin";
        if (status) {
            query += " WHERE Status = 'Activo';";
        }
        List<UserAdminDTO> administrators = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserAdminDTO admin = new UserAdminDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                administrators.add(admin);
            }
        } catch (Exception e) {
            throw new AppException("No se pudo leer los administradores", e, getClass(), "readAllstatus");
        }
        return administrators;
    }

    @Override
    public boolean create(UserAdminDTO entity) throws Exception {
        String query = "INSERT INTO UserAdmin (IdUserType, UserName, Password) "
                + "VALUES (?, ?, ?);";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdUserType());
            pstmt.setString(2, entity.getUserName());
            pstmt.setString(3, entity.getPassword());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo crear el usuario administrador: " + entity.getUserName(), e, getClass(), "create");
        }
    }

    @Override
    public boolean update(UserAdminDTO entity) throws Exception {
        String query = "UPDATE UserAdmin SET UserName = ?, Password = ? "
                + "WHERE idAdmin = ?;";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getUserName());
            pstmt.setString(2, entity.getPassword());
            pstmt.setInt(3, entity.getIdAdministrator());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo actualizar el usuario administrador con id: " + entity.getIdAdministrator(), e, getClass(), "update");
        }
    }

    @Override
    public boolean changestatus(int id, Boolean status) throws Exception {
        String query = "UPDATE UserAdmin SET Status = ? WHERE idAdmin = ?;";
        String sta;
        if (status) {
            sta = "Activo";
        } else {
            sta = "Inactivo";
        }
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, sta);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo cambiar el estado del usuario administrador con id: " + id, e, getClass(), "changestatus");
        }
    }

    @Override
    public Integer getMaxReg() throws Exception {
        String query = "SELECT COUNT(*) TotalReg FROM UserAdmin WHERE Status = 'Activo';";
        try {
            Connection conn = openConnection();
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt("TotalReg");
            }
        } catch (SQLException e) {
            throw new AppException("No se pudo obtener el número máximo de registros de administradores", e, getClass(), "getMaxReg");
        }
        return 0;
    }
}
