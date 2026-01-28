package DataAccessComponent.DAOs;

import java.util.ArrayList;
import java.util.List;

import DataAccessComponent.Helpers.DataHelperSQLite;
import DataAccessComponent.Interfaces.IDAO;
import DataAccessComponent.DTOs.UserTypeDTO;
import Infrastructure.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTypeDAO extends DataHelperSQLite implements IDAO<UserTypeDTO> {

    @Override
    public List<UserTypeDTO> readBy(String name) throws AppException {
        throw new AppException("Método readBy no implementado para UserTypeDAO", null, getClass(), "readBy");
    }

    @Override
    public List<UserTypeDTO> readAllStatus(boolean status) throws AppException {
        String query = "SELECT IdUserType, Name, Description, CreationDate, ModificateDate FROM UserType";
        if (status) {
            query += " WHERE Status = 'Activo';";
        }
        List<UserTypeDTO> userTypes = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserTypeDTO userType = new UserTypeDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5));
                userTypes.add(userType);
            }
        } catch (Exception e) {
            throw new AppException(
                    "No se pudo leer los tipos de usuario con estado: " + (status ? "Activo" : "Inactivo"), e,
                    getClass(), "readAllStatus");
        }
        return userTypes;
    }

    @Override
    public boolean create(UserTypeDTO entity) throws AppException {
        String query = "INSERT INTO UserType (Name, Description) "
                + "VALUES (?, ?);";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getDescription());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo crear el tipo de usuario: " + entity.getName(), e, getClass(), "create");
        }
    }

    @Override
    public boolean update(UserTypeDTO entity) throws AppException {
        String query = "UPDATE UserType SET Name = ?, Description = ?, ModificateDate = ? WHERE IdUserType = ?;";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getDescription());
            pstmt.setString(3, getDateTimeNow());
            pstmt.setInt(4, entity.getIdUserType());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo actualizar el tipo de usuario con id: " + entity.getIdUserType(), e,
                    getClass(), "update");
        }
    }

    @Override
    public boolean changeStatus(int id, Boolean status) throws AppException {
        String query = "UPDATE UserType SET Status = ? WHERE IdUserType = ?;";
        String statusString;
        if (status) {
            statusString = "Activo";
        } else {
            statusString = "Inactivo";
        }
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, statusString);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo cambiar el estado del tipo de usuario con id: " + id, e, getClass(),
                    "changeStatus");
        }
    }

    @Override
    public Integer getMaxReg() throws AppException {
        String query = "SELECT COUNT(*) TotalReg FROM UserType" +
                " WHERE Estado = 'Activo';";
        try {
            Connection conn = openConnection();
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt("TotalReg");
            }
        } catch (SQLException e) {
            throw new AppException("No se pudo obtener el número máximo de registros de tipos de usuario activos", e,
                    getClass(), "getMaxReg");
        }
        return 0;
    }
}
