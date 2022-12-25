package config;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    public static java.sql.Connection getConnection() {
        try {
            Class.forName(Data.DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to find driver class :(");
        }

        try {
            return DriverManager.getConnection(Data.URL,
                    Data.USERNAME,
                    Data.PASSWORD);
        } catch (SQLException e) {
            System.out.println("Unable to receive connection :(");
            throw new RuntimeException(e);
        }
    }
}
