package DataAccessComponent.Helpers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Infrastructure.AppConfig;
import Infrastructure.AppException;

public abstract class DataHelperSQLite {
    private static final String DBPathConnection = AppConfig.getDATABASE();
    private static Connection conn = null;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();

    protected DataHelperSQLite() {
    }

    protected static synchronized Connection openConnection() throws AppException {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(DBPathConnection);
            }
        } catch (SQLException e) {
            throw new AppException("No se pudo establecer conexión con la base de datos: " + DBPathConnection, e,
                    DataHelperSQLite.class, "openConnection");
        }
        return conn;
    }

    protected String getDataTimeNow() {
        return dtf.format(now).toString();
    }

}