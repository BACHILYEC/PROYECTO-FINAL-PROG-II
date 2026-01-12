package DataAccessComponent.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import DataAccessComponent.Helpers.DataHelperSQLite;
import DataAccessComponent.Interfaces.IDAO;
import DataAccessComponent.DTOs.PlayerDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerDAO extends DataHelperSQLite implements IDAO<PlayerDTO> {

    @Override
    public List<PlayerDTO> readBy(String name) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'readBy'");
    }

    @Override
    public List<PlayerDTO> readAllstatus(boolean status) throws Exception {
        String query = "SELECT idPlayer, Name, Score FROM Player";
        if (status) {
            query += " WHERE Status = 'Activo';";
        }
        List<PlayerDTO> players = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PlayerDTO player = new PlayerDTO(rs.getInt(1), rs.getString(2), rs.getInt(3));
                players.add(player);
            }
        } catch (Exception e) {

            throw new UnsupportedOperationException("Unimplemented method 'readAllstatus'");
        }
        return players;
    }

    @Override
    public boolean create(PlayerDTO entity) throws Exception {
        String query = "INSERT INTO Player (IdUserType, Name, Score) "
                + "VALUES (?, ?, ?);";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdUserType());
            pstmt.setString(2, entity.getName());
            pstmt.setInt(3, entity.getScore());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'create'");
        }
    }

    @Override
    public boolean update(PlayerDTO entity) throws Exception {
        String query = "UPDATE Player SET Name = ?, Score = ? "
                + "WHERE idPlayer = ?;";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getName());
            pstmt.setInt(2, entity.getScore());
            pstmt.setInt(3, entity.getIdPlayer());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'update'");
        }
    }

    @Override
    public boolean changestatus(int id, Boolean status) throws Exception {
        String query = "UPDATE Player SET Status = ? WHERE idPlayer = ?;";
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
        String query = "SELECT COUNT(*) TotalReg FROM Player WHERE Status = 'Activo';";
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
