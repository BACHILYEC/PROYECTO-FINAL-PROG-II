
package DataAccessComponent.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DataAccessComponent.DTOs.CategoryDTO;

import DataAccessComponent.Helpers.DataHelperSQLite;
import DataAccessComponent.Interfaces.IDAO;
import Infrastructure.AppException;

public class CategoryDAO extends DataHelperSQLite implements IDAO<CategoryDTO> {

    @Override
    public List<CategoryDTO> readBy(String name) throws AppException {
        throw new AppException("Método readBy no implementado para CategoryDAO", null, getClass(), "readBy");
    }

    @Override
    public List<CategoryDTO> readAllstatus(boolean status) throws AppException {
        String query = "SELECT idCategory, CategoryName, Description FROM Category";
        if (status) {
            query += " WHERE Status = 'ctive';";
        }
        List<CategoryDTO> categories = new ArrayList<>();
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CategoryDTO category = new CategoryDTO(rs.getInt(1), rs.getString(2), rs.getString(3));
                categories.add(category);
            }
        } catch (Exception e) {
            throw new AppException("No se pudo leer las categorías", e, getClass(), "readAllstatus");
        }
        return categories;
    }

    @Override
    public boolean create(CategoryDTO entity) throws AppException {
        String query = "INSERT INTO Category (Name, Description) "
                + "VALUES (?, ?);";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getDescription());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo crear la categoría: " + entity.getName(), e, getClass(), "create");
        }
    }

    @Override
    public boolean update(CategoryDTO entity) throws AppException {
        String query = "UPDATE Category SET Name = ?, Description = ? WHERE idCategory = ?;";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getDescription());
            pstmt.setInt(3, entity.getIdCategory());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new AppException("No se pudo actualizar la categoría con id: " + entity.getIdCategory(), e, getClass(), "update");
        }
    }

    @Override
    public boolean changestatus(int id, Boolean status) throws AppException {
        String query = "UPDATE Category SET Status = ? WHERE idCategory = ?;";
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
            throw new AppException("No se pudo cambiar el estado de la categoría con id: " + id, e, getClass(), "changestatus");
        }
    }

    @Override
    public Integer getMaxReg() throws AppException {
        String query = "SELECT COUNT(*) TotalReg FROM Category WHERE Status = 'Activo';";
        try {
            Connection conn = openConnection();
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt("TotalReg");
            }
        } catch (SQLException e) {
            throw new AppException("No se pudo obtener el número máximo de registros de categorías activas", e, getClass(), "getMaxReg");
        }
        return 0;
    }

}
