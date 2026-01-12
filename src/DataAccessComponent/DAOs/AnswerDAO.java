package DataAccessComponent.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import DataAccessComponent.DTOs.AnswerDTO;

import DataAccessComponent.Helpers.DataHelperSQLite;
import DataAccessComponent.Interfaces.IDAO;

public class AnswerDAO extends DataHelperSQLite implements IDAO<AnswerDTO> {

    @Override
    public List<AnswerDTO> readBy(String name) throws Exception {
        return null;
    }

    public AnswerDTO readCorrectAns(int idQuestion) throws Exception {
        String query = "SELECT idAnswer, idQuestion, Answer, CorrectAns, Status FROM Answer WHERE CorrectAns = '1'"
                + " AND idQuestion = '"
                + idQuestion + "';";
        AnswerDTO ans1 = new AnswerDTO();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ans1 = new AnswerDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            }

        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'readBy'");
        }
        return ans1;
    }

    @Override
    public List<AnswerDTO> readAllstatus(boolean status) throws Exception {
        String sta;
        if (status) {
            sta = "Activo";
        } else {
            sta = "Inactivo";
        }
        String query = "SELECT idAnswer, idQuestion, Answer FROM Answer WHERE Status = '" + sta + "';";
        List<AnswerDTO> list = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AnswerDTO ans1 = new AnswerDTO(rs.getInt(1), rs.getInt(2), rs.getString(3));
                list.add(ans1);
            }

        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'readBy'");
        }
        return list;
    }

    public List<AnswerDTO> readAllcorrectanswers(boolean status, int CorrectAnswer) throws Exception {
        String query = "SELECT idAnswer, idQuestion, Answer, CorrectAns FROM Answer WHERE CorrectAns = '"
                + CorrectAnswer + "';";
        List<AnswerDTO> list = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AnswerDTO ans1 = new AnswerDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
                list.add(ans1);
            }

        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'readBy'");
        }
        return list;
    }

    @Override
    public boolean create(AnswerDTO entity) throws Exception {
        String query = "INSERT INTO Answer (idQuestion, Answer, CorrectAns) VALUES (?,?,?);";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdQuestion());
            pstmt.setString(2, entity.getAnswer());
            pstmt.setInt(3, entity.getCorrectAnswer());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'create'");
        }
    }

    @Override
    public boolean update(AnswerDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Answer SET idQuestion = ?, Answer = ?, CorrectAns = ?, ModificateDate = ? WHERE idAnswer = ?;";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdQuestion());
            pstmt.setString(2, entity.getAnswer());
            pstmt.setInt(3, entity.getCorrectAnswer());
            pstmt.setString(4, dtf.format(now).toString());
            pstmt.setInt(5, entity.getIdAnswer());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'update'");
        }
    }

    @Override
    public boolean changestatus(int id, Boolean status) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Answer SET Status = ?, ModificateDate = ? WHERE idAnswer '" + id + "';";
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
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {

            throw new UnsupportedOperationException("Unimplemented method 'changestatus'");
        }
    }

    @Override
    public Integer getMaxReg() throws Exception {
        String query = "SELECT COUNT(*) TotalReg FROM Answer" +
                " WHERE Status = 'Activo';";
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
