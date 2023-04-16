package application;

import database.DB;

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
                    .prepareStatement("update seller set BaseSalary = BaseSalary + ? where (DepartmentId = ?)");

            preparedStatement.setDouble(1, 200.00);
            preparedStatement.setInt(2, 2);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Done, rows affected " + rowsAffected);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }
    }
}
