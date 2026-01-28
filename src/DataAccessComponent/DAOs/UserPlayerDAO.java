package DataAccessComponent.DAOs;

import DataAccessComponent.DTOs.UserPlayerDTO;
import DataAccessComponent.Helpers.DataHelperSQLite;
import DataAccessComponent.Interfaces.IDAO;
import Infrastructure.AppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserPlayerDAO extends DataHelperSQLite implements IDAO<UserPlayerDTO> {

    @Override
    public List<UserPlayerDTO> readBy(String name) throws AppException {
        throw new AppException("Método readBy no implementado para UserPlayerDAO", null, getClass(), "readBy");
    }

    @Override
    public List<UserPlayerDTO> readAllstatus(boolean status) throws AppException {
        String query = "SELECT idPlayer, idUserType, Name, Score, Status, ModificateDate, CreationDate FROM UserPlayer";
        if (status) {
            query += " WHERE Status = 'Activo'";
        }
        List<UserPlayerDTO> players = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserPlayerDTO player = new UserPlayerDTO();
                player.setIdPlayer(rs.getInt("idPlayer"));
                player.setIdUserType(rs.getInt("idUserType"));
                player.setName(rs.getString("Name"));
                player.setScore(rs.getInt("Score"));
                player.setStatus(rs.getString("Status"));
                player.setModificateDate(rs.getString("ModificateDate"));
                player.setCreationDate(rs.getString("CreationDate"));
                players.add(player);
            }
            rs.close();
            pstmt.close();
            // conn.close();
        } catch (Exception e) {
            throw new AppException("No se pudo leer los jugadores", e, getClass(), "readAllstatus");
        }
        return players;
    }

    @Override
    public boolean create(UserPlayerDTO entity) throws AppException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "INSERT INTO UserPlayer (IdUserType, Name, Score, CreationDate, ModificateDate) "
                + "VALUES (?, ?, ?, ?, ?);";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdUserType());
            pstmt.setString(2, entity.getName());
            pstmt.setInt(3, entity.getScore());
            pstmt.setString(4, dtf.format(now).toString());
            pstmt.setString(5, dtf.format(now).toString());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo crear el jugador: " + entity.getName(), e, getClass(), "create");
        }
    }

    @Override
    public boolean update(UserPlayerDTO entity) throws AppException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String query = "UPDATE UserPlayer SET Name = ?, Score = ?, ModificateDate = ? "
                + "WHERE idPlayer = ?;";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getName());
            pstmt.setInt(2, entity.getScore());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getIdPlayer());

            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo actualizar el jugador con id: " + entity.getIdPlayer(), e, getClass(),
                    "update");
        }
    }

    @Override
    public boolean changestatus(int id, Boolean status) throws AppException {
        String query = "UPDATE UserPlayer SET Status = ? WHERE idPlayer = ?";
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
            throw new AppException("No se pudo cambiar el estado del jugador con id: " + id, e, getClass(),
                    "changestatus");
        }
    }

    @Override
    public Integer getMaxReg() throws AppException {
        String query = "SELECT COUNT(*) TotalReg FROM UserPlayer WHERE Status = 'Activo'";
        try {
            Connection conn = openConnection();
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt("TotalReg");
            }
        } catch (SQLException e) {
            throw new AppException("No se pudo obtener el número máximo de registros de jugadores", e, getClass(),
                    "getMaxReg");
        }
        return 0;
    }

    public UserPlayerDTO readByName(String username) throws AppException {
        UserPlayerDTO userPlayerDTO = null;

        String query = "SELECT idPlayer, idUserType, Name, Score, CreationDate, ModificateDate FROM UserPlayer WHERE Name = ?;";

        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userPlayerDTO = new UserPlayerDTO(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
            throw new AppException("No se pudo leer el jugador por nombre: " + username, e, getClass(), "readByName");
        }

        return userPlayerDTO;
    }

    public UserPlayerDTO readById(int id) throws AppException {
        UserPlayerDTO userPlayerDTO = null;

        String query = "SELECT idPlayer, idUserType, Name, Score, Status, CreationDate, ModificateDate FROM UserPlayer WHERE idPlayer = ?;";

        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userPlayerDTO = new UserPlayerDTO();
                userPlayerDTO.setIdPlayer(rs.getInt("idPlayer"));
                userPlayerDTO.setIdUserType(rs.getInt("idUserType"));
                userPlayerDTO.setName(rs.getString("Name"));
                userPlayerDTO.setScore(rs.getInt("Score"));
                userPlayerDTO.setStatus(rs.getString("Status"));
                userPlayerDTO.setCreationDate(rs.getString("CreationDate"));
                userPlayerDTO.setModificateDate(rs.getString("ModificateDate"));
            }
        } catch (Exception e) {
            throw new AppException("No se pudo leer el jugador con id: " + id, e, getClass(), "readById");
        }
        return userPlayerDTO;
    }

    public UserPlayerDTO searchByName(String username) throws AppException {
        UserPlayerDTO userPlayerDTO = null;

        String query = "SELECT Name FROM UserPlayer WHERE Name = ?;";

        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userPlayerDTO = new UserPlayerDTO(
                        rs.getString(1));
            }
        } catch (Exception e) {
            throw new AppException("No se pudo buscar el jugador: " + username, e, getClass(), "readByName");
        }

        return userPlayerDTO;
    }

}
