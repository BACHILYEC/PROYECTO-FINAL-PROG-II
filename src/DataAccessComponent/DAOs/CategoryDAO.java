
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

public class CategoryDAO extends DataHelperSQLite implements IDAO<CategoryDTO> {

    @Override
    public List<CategoryDTO> readBy(String name) throws Exception {

        throw new UnsupportedOperationException("Unimplemented method 'readBy'");
    }

    @Override
    public List<CategoryDTO> readAllstatus(boolean status) throws Exception {
        String query = "SELECT idCategory, CategoryName, Description FROM Category";
        if (status) {
            query += " WHERE Status = 'Active';";
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

            throw new UnsupportedOperationException("Unimplemented method 'readAllstatus'");
        }
        return categories;
    }

    @Override
    public boolean create(CategoryDTO entity) throws Exception {
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
            throw new UnsupportedOperationException("Unimplemented method 'create'");
        }
    }

    @Override
    public boolean update(CategoryDTO entity) throws Exception {
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
            throw new UnsupportedOperationException("Unimplemented method 'update'");
        }
    }

    @Override
    public boolean changestatus(int id, Boolean status) throws Exception {
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
            throw new UnsupportedOperationException("Unimplemented method 'changestatus'");
        }
    }

    @Override
    public Integer getMaxReg() throws Exception {
        String query = "SELECT COUNT(*) TotalReg FROM Category WHERE Status = 'Activo';";
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
