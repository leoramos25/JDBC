package application;

import database.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            connection = DB.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from department");
            while (resultSet.next()) {
                System.out.printf("%d | %s%n", resultSet.getInt("Id"), resultSet.getString("Name"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
