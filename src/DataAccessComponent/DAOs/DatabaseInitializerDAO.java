package DataAccessComponent.DAOs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

import DataAccessComponent.Helpers.DataHelperSQLite;
import Infrastructure.AppException;

public class DatabaseInitializerDAO extends DataHelperSQLite {
    public static void initializeDDL() throws AppException {
        try {
            Path dbPath = getDatabasePath();
            Path dbDir = dbPath.getParent();

            if (!Files.exists(dbDir)) {
                Files.createDirectories(dbDir);

                Path scriptPath = Paths.get(
                        System.getProperty("user.dir"), "app",
                        "Storage",
                        "Scripts",
                        "01DDL.txt");
                if (!Files.exists(scriptPath)) {

                    throw new AppException(
                            "No se encontró el archivo de scripts: " + scriptPath.toString());
                }
                String[] query = Files.readString(scriptPath).split(";");
                for (int j = 0; j < query.length; j++) {
                    try {
                        Connection conn = openConnection();
                        Statement sts = conn.createStatement();
                        sts.executeUpdate(query[j]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Path scriptPath1 = Paths.get(
                        System.getProperty("user.dir"), "app",
                        "Storage",
                        "Scripts",
                        "DML.txt");
                if (!Files.exists(scriptPath1)) {

                    throw new AppException(
                            "No se encontró el archivo de scripts: " + scriptPath1.toString());
                }
                String[] query1 = Files.readString(scriptPath1).split(";");
                for (int j = 0; j < query1.length; j++) {
                    try {
                        Connection conn = openConnection();
                        Statement sts = conn.createStatement();
                        sts.executeUpdate(query1[j]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            throw new AppException(
                    "Error inicializando la base de datos",
                    e,
                    DatabaseInitializerDAO.class,
                    "initialize");
        }
    }
}
