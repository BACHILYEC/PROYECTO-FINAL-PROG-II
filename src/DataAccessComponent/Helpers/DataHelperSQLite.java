package DataAccessComponent.Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Path;
import java.nio.file.Paths;

import Infrastructure.AppException;

public abstract class DataHelperSQLite {

    private static Connection conn = null;

    private static final String APP_NAME = "Liminalis";

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();

    protected DataHelperSQLite() {
    }

    protected static synchronized Connection openConnection() throws AppException {
        try {
            if (conn == null) {
                Path dbPath = getDatabasePath();
                conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath.toString());
            }
        } catch (SQLException e) {
            throw new AppException(
                    "No se pudo establecer conexión con la base de datos",
                    e,
                    DataHelperSQLite.class,
                    "openConnection");
        }
        return conn;
    }

    protected static Path getDatabasePath() {
        String appData = System.getenv("APPDATA");
        return Paths.get(
                appData,
                APP_NAME,
                "Storage",
                "Database",
                "triv.sqlite");
    }

    protected String getDataTimeNow() {
        return dtf.format(now);
    }
}
