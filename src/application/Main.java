package application;

import database.DB;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Connection conn;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "insert into seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Leonardo");
            st.setString(2, "leonardo@email.com");
            st.setDate(3, new Date(1999, 04, 25));
            st.setDouble(4, 3800.00);
            st.setInt(5, 4);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done, Id = " + id);
                }
            } else {
                System.out.println("No rows affected");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DB.closeConnection();
            DB.closeStatement(st);
        }
    }
}
