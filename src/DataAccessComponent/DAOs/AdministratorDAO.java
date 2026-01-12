package DataAccessComponent.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import DataAccessComponent.Helpers.DataHelperSQLite;
import DataAccessComponent.Interfaces.IDAO;
import DataAccessComponent.DTOs.AdministratorDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdministratorDAO extends DataHelperSQLite implements IDAO<AdministratorDTO> {

    @Override
    public List<AdministratorDTO> readBy(String name) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'readBy'");
    }

    @Override
    public List<AdministratorDTO> readAllstatus(boolean status) throws Exception {
        String query = "SELECT idAdmin, UserName, LastLogin FROM Administrator";
        if (status) {
            query += " WHERE Status = 'Activo';";
        }
        List<AdministratorDTO> administrators = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AdministratorDTO admin = new AdministratorDTO(rs.getInt(1), rs.getString(2), rs.getString(3));
                administrators.add(admin);
            }
        } catch (Exception e) {

            throw new UnsupportedOperationException("Unimplemented method 'readAllstatus'");
        }
        return administrators;
    }

    @Override
    public boolean create(AdministratorDTO entity) throws Exception {
        String query = "INSERT INTO Administrator (IdUserType, UserName, Password) "
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
            throw new UnsupportedOperationException("Unimplemented method 'create'");
        }
    }

    @Override
    public boolean update(AdministratorDTO entity) throws Exception {
        String query = "UPDATE Administrator SET UserName = ?, Password = ? "
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
            throw new UnsupportedOperationException("Unimplemented method 'update'");
        }
    }

    @Override
    public boolean changestatus(int id, Boolean status) throws Exception {
        String query = "UPDATE Administrator SET Status = ? WHERE idAdmin = ?;";
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
        String query = "SELECT COUNT(*) TotalReg FROM Administrator WHERE Status = 'Activo';";
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
