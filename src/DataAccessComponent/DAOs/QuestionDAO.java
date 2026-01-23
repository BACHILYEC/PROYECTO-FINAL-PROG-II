package DataAccessComponent.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import DataAccessComponent.Helpers.DataHelperSQLite;
import DataAccessComponent.Interfaces.IDAO;
import DataAccessComponent.DTOs.QuestionDTO;
import Infrastructure.AppException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuestionDAO extends DataHelperSQLite implements IDAO<QuestionDTO> {

    @Override
    public List<QuestionDTO> readAllstatus(boolean status) throws Exception {

        String query = "SELECT idQuestion, idCategory, Question, CreationDate, ModificateDate FROM Question";
        if (status) {
            query += " WHERE Status = 'Activo';";
        }
        List<QuestionDTO> question = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                QuestionDTO questionDTO = new QuestionDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5));
                question.add(questionDTO);
            }
        } catch (Exception e) {
            throw new AppException("No se pudo leer las preguntas", e, getClass(), "readAllstatus");
        }
        return question;
    }

    @Override
    public boolean create(QuestionDTO entity) throws Exception {

        String query = "INSERT INTO Question (idQuestion, Question) "
                + "VALUES (?, ?);";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdQuestion());
            pstmt.setString(2, entity.getQuestion());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo crear la pregunta: " + entity.getQuestion(), e, getClass(), "create");
        }
    }

    @Override
    public boolean update(QuestionDTO entity) throws Exception {

        String query = "UPDATE Question SET Question = ? "
                + "WHERE idQuestion = ?;";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdQuestion());
            pstmt.setString(2, entity.getQuestion());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo actualizar la pregunta con id: " + entity.getIdQuestion(), e, getClass(), "update");
        }
    }

    @Override
    public boolean changestatus(int id, Boolean status) throws Exception {

        String query = "UPDATE Question SET Status = ? WHERE idQuestion = ?;";
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
            throw new AppException("No se pudo cambiar el estado de la pregunta con id: " + id, e, getClass(), "changestatus");
        }
    }

    @Override
    public Integer getMaxReg() throws Exception {

        String query = "SELECT COUNT(*) TotalReg FROM Question WHERE Status = 'Activo';";
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

    @Override
    public List<QuestionDTO> readBy(String name) throws Exception {
        throw new AppException("Método readBy no implementado para QuestionDAO", null, getClass(), "readBy");
    }

    public List<QuestionDTO> readAllQuestion(Integer id) throws Exception {

        String query = "SELECT Question FROM Question WHERE idCategory = " + id.toString() + ";";

        List<QuestionDTO> question = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                QuestionDTO questionDTO = new QuestionDTO(rs.getString(1));
                question.add(questionDTO);
            }
        } catch (Exception e) {
            throw new AppException("No se pudo leer las preguntas de la categoría con id: " + id, e, getClass(), "readAllQuestion");
        }
        return question;
    }

    public QuestionDTO readByQuestion(Integer id) throws Exception {

        String query = "SELECT idQuestion, idCategory, Question FROM Question WHERE idQuestion = " + id.toString()
                + ";";

        QuestionDTO questionDTO = new QuestionDTO();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                questionDTO = new QuestionDTO(rs.getInt(1), rs.getInt(2), rs.getString(3));
            }
        } catch (Exception e) {
            throw new AppException("No se pudo leer la pregunta con id: " + id, e, getClass(), "readByQuestion");
        }
        return questionDTO;
    }
}
