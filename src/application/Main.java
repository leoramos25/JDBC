package application;

import database.DB;
import database.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DB.getConnection();
            preparedStatement = connection
                    .prepareStatement("delete from department where Id = ?");

            preparedStatement.setInt(1, 4);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Done, rows affected " + rowsAffected);
        } catch (SQLException exception) {
            throw new DbIntegrityException(exception.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }
    }
}
