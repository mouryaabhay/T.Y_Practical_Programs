// Question 1: Write a program to connect to a database and display a success message.
import java.sql.*;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        try {
            // 1. Load Driver (optional for JDBC 4.0+)
            Class.forName("org.postgresql.Driver");

            // 2. Create Connection
            Connection con = DriverManager.getConnection(url, user, password);

            // 3. Display success message
            System.out.println("Connected to database successfully!");
            System.out.println("Database: PostgreSQL");
            System.out.println("URL: " + url);

            // 4. Close connection
            con.close();
            System.out.println("Connection closed successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            System.out.println("Error: " + e.getMessage());
        }
    }
}