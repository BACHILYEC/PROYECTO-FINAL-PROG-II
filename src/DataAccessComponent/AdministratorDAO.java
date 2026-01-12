package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

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
        try{
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                AdministratorDTO admin = new AdministratorDTO();
                admin.setIdAdmin(rs.getInt("idAdmin"));
                admin.setUserName(rs.getString("UserName"));
                admin.setLastLogin(rs.getString("LastLogin"));
                administrators.add(admin);
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'readAllstatus'");
    }

    @Override
    public boolean create(AdministratorDTO entity) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public boolean update(AdministratorDTO entity) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean changestatus(int id, String status) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'changestatus'");
    }

    @Override
    public Integer getMaxReg() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'getMaxReg'");
    }

}
