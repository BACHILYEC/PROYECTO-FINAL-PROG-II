package DataAccessComponent;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DataAccessComponent.DTO.UserTypeDTO;

public class UserTypeDAO extends DataHelperSQLite implements IDAO<UserTypeDTO> {

    @Override
    public List<UserTypeDTO> readBy(String name) throws Exception {
        return null;
    }

    @Override
    public List<UserTypeDTO> readAllstatus(boolean status) throws Exception {
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
            throw new UnsupportedOperationException("Unimplemented method 'readAllstatus'");
        }
        return userTypes;
    }

    @Override
    public boolean create(UserTypeDTO entity) throws Exception {
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
            throw new UnsupportedOperationException("Unimplemented method 'create'");
        }
    }

    @Override
    public boolean update(UserTypeDTO entity) throws Exception {
        String query = "UPDATE UserType SET Name = ?, Description = ?, ModificateDate = ? WHERE IdUserType = ?;";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getDescription());
            pstmt.setString(3, getDataTimeNow());
            pstmt.setInt(4, entity.getIdUserType());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'update'");
        }
    }

    @Override
    public boolean changestatus(int id, Boolean status) throws Exception {
        String query = "UPDATE UserType SET Status = ? WHERE IdUserType = ?;";
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
            throw new UnsupportedOperationException("Unimplemented method 'changestatus'");
        }
    }

    @Override
    public Integer getMaxReg() throws Exception {
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
        }
        return 0;
    }
}
