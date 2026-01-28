package Infrastructure;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import Infrastructure.Tools.CMD;

public class AppConfig {
    private static final Properties props = new Properties();

    private static final String APP_PROPERTIES = "src/app.properties";
    private static final String KEY_FILE_LOG = "df.logFile";
    private static final String KEY_DB_NAME = "db.File";

    public static final String getDatabase() {
        return getProperty(KEY_DB_NAME);
    }

    public static final String getLogFile() throws IOException {
        String relativePath = getProperty(KEY_FILE_LOG);

        Path path = Paths.get(System.getenv("APPDATA") + "\\Liminalis\\" + relativePath);
        Path dirPath = path.getParent();

        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        if (relativePath != null) {
            return path.toString();
        }
        return path.toString();
    }

    // AppMSGs
    public static final String MSG_DEFAULT_ERROR = "Caracoles, ocurrio un error inesperado! Contacta al administrador";
    public static final String MSG_DEFAULT_CLASS = "undefined";
    public static final String MSG_DEFAULT_METHOD = "undefined";

    static {
        try (InputStream appProperties = new FileInputStream(APP_PROPERTIES)) {
            props.load(appProperties);
        } catch (IOException e) {
            CMD.printlnError("ERROR al cargar ⚠️ " + e.getMessage());
        }
    }

    private AppConfig() {
    }

    static String getProperty(String key) {
        String path = props.getProperty(key);
        CMD.println("AppConfig ⚠️ " + APP_PROPERTIES + "." + key + " : " + path);
        if (path != null)
            return path;
        else
            CMD.printlnError("ERROR ⚠️ " + APP_PROPERTIES + "." + key + " : " + path);
        return null;
    }

    static URL getAppResource(String key) {
        String path = getProperty(key);
        if (path != null)
            return AppConfig.class.getResource(path);
        else
            CMD.printlnError("ERROR ⚠️ getAppResource : " + APP_PROPERTIES + "." + key + " : " + path);
        return null;
    }

}
