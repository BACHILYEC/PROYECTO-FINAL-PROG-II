package DataAccessComponent.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import DataAccessComponent.Helpers.DataHelperSQLite;
import DataAccessComponent.Interfaces.IDAO;
import DataAccessComponent.DTOs.UserPlayerDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserPlayerDAO extends DataHelperSQLite implements IDAO<UserPlayerDTO> {

    @Override
    public List<UserPlayerDTO> readBy(String name) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'readBy'");
    }

    @Override
    public List<UserPlayerDTO> readAllstatus(boolean status) throws Exception {
        String query = "SELECT idPlayer, Name, Score FROM Player";
        if (status) {
            query += " WHERE Status = 'Activo';";
        }
        List<UserPlayerDTO> players = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserPlayerDTO player = new UserPlayerDTO(rs.getInt(1), rs.getString(2), rs.getInt(3));
                players.add(player);
            }
        } catch (Exception e) {

            throw new UnsupportedOperationException("Unimplemented method 'readAllstatus'");
        }
        return players;
    }

    @Override
    public boolean create(UserPlayerDTO entity) throws Exception {
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
    public boolean update(UserPlayerDTO entity) throws Exception {
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

    public UserPlayerDTO readByName(String username) throws Exception {
        UserPlayerDTO userPlayerDTO = null;
        String query = "SELECT * FROM UserPlayer WHERE name = ?";
        try (java.sql.Connection conn = getConnection();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userPlayerDTO = new UserPlayerDTO();
                    userPlayerDTO.setIdUserPlayer(rs.getInt("idUserPlayer"));
                    userPlayerDTO.setIdUserType(rs.getInt("idUserType"));
                    userPlayerDTO.setName(rs.getString("name"));
                    userPlayerDTO.setScore(rs.getInt("score"));
                }
            }
        }
        return userPlayerDTO;
    }

}
