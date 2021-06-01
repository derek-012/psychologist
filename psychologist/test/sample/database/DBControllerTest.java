package sample.database;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class DBControllerTest {
    @Test
    void testProperties() {
        Properties props = new Properties();
        try (InputStream inputStream = Files.newInputStream(Path.of("src/sample/configs/database.properties"))) {
            props.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error");
        }

        if (!props.isEmpty()) {
            System.out.println("URL = " + props.getProperty("url") + " (length = " + props.getProperty("url").length() + ")");
            System.out.println("username = " + props.getProperty("username") + " (length = " + props.getProperty("username").length() + ")");
            System.out.println("password = " + props.getProperty("password") + " (length = " + props.getProperty("password").length() + ")");
        }
    }
}