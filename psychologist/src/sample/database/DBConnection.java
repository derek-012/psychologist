package sample.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    static Connection connection;
    static boolean connected;

    private static Properties getProps() {
        Properties props = new Properties();
        try (InputStream inputStream = Files.newInputStream(Path.of("src/sample/configs/database.properties"))) {
            props.load(inputStream);
        } catch (IOException e) {
            return null;
        }
        return props;
    }

    public static boolean connect() {
        Properties props = getProps();
        if (props != null && props.containsKey("url") && props.containsKey("username") && props.containsKey("password")) {
            try {
                connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), "");
                connected = true;
                return true;
            } catch (SQLException e) {
                System.out.println(e);
                connected = false;
                return false;
            }
        } else {
            System.out.println("Props = NULL");
            connected = false;
            return false;
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static boolean isActive() {
        return connected;
    }
}
