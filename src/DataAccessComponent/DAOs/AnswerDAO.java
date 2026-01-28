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
import Infrastructure.AppException;

public class AnswerDAO extends DataHelperSQLite implements IDAO<AnswerDTO> {

    public boolean compareAnswer(AnswerDTO answer, AnswerDTO selectanswer) {
        if (answer.getAnswer().equals(selectanswer.getAnswer())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<AnswerDTO> readBy(String name) throws AppException {
        return null;
    }

    public String readCorrectAns(int idQuestion) throws AppException {
        String query = "SELECT Answer Status FROM Answer WHERE CorrectAns = '1'"
                + " AND idQuestion = '"
                + idQuestion + "';";
        AnswerDTO ans1 = new AnswerDTO();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ans1 = new AnswerDTO(rs.getString(1));
            }

        } catch (Exception e) {
            throw new AppException("No se encontró respuesta correcta para la pregunta ", e, getClass(),
                    "readCorrectAns()");
        }
        return ans1.getAnswer();
    }

    public List<AnswerDTO> readOptions(int question, Boolean status) throws AppException {
        String sta;
        if (status) {
            sta = "Activo";
        } else {
            sta = "Inactivo";
        }
        String query = "SELECT Answer FROM Answer WHERE idQuestion = '" + question + "' AND Status = '" + sta + "';";
        List<AnswerDTO> list = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AnswerDTO ans1 = new AnswerDTO(rs.getString(1));
                list.add(ans1);
            }

        } catch (Exception e) {
            throw new AppException("No se pudo leer las opciones", e, getClass(), "readOptions");
        }
        return list;
    }

    @Override
    public List<AnswerDTO> readAllStatus(boolean status) throws AppException {
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
            throw new AppException("No se pudo leer las respuestas", e, getClass(), "readAllStatus");
        }
        return list;
    }

    public List<AnswerDTO> readAllCorrectAnswers(boolean status, int correctAnswer) throws AppException {
        String query = "SELECT idAnswer, idQuestion, Answer, CorrectAns FROM Answer WHERE CorrectAns = '"
                + correctAnswer + "';";
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
            throw new AppException("No se encontraron respuestas correctas para la pregunta", e, getClass(),
                    "readAllcorrectanswers");
        }
        return list;
    }

    @Override
    public boolean create(AnswerDTO entity) throws AppException {
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
            throw new AppException("No se pudo crear la respuesta", e, getClass(), "create");
        }
    }

    @Override
    public boolean update(AnswerDTO entity) throws AppException {
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
            throw new AppException("No se pudo actualizar la respuesta", e, getClass(), "update");
        }
    }

    @Override
    public boolean changeStatus(int id, Boolean status) throws AppException {
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

            throw new AppException("No se pudo cambiar el estado de la respuesta", e, getClass(), "changeStatus");
        }
    }

    @Override
    public Integer getMaxReg() throws AppException {
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
            throw new AppException("No se pudo obtener el número máximo de registros", e, getClass(), "getMaxReg");
        }
        return 0;
    }

}
