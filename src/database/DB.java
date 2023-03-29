package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    private static final String DB_PROPERTIES_PATH = "db.properties";

    private static final Connection CONNECTION = null;

    public static Connection getConnection() {
        try {
            if (CONNECTION == null) {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                DriverManager.getConnection(url, properties);
            }
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
        return CONNECTION;
    }

    public static void closeConnection() {
        try {
            if (CONNECTION != null) {
                CONNECTION.close();
            }
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream dbProperties = new FileInputStream(DB_PROPERTIES_PATH)) {
            Properties properties = new Properties();
            properties.load(dbProperties);
            return properties;
        } catch (IOException exception) {
            throw new DbException(exception.getMessage());
        }
    }
}
