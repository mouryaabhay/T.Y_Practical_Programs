// Question 5: Write a Java program using JDBC to retrieve and display database information
// such as database product name, database version, driver name, and user name
// using the DatabaseMetaData interface.
import java.sql.*;

public class B2 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");

            // Get DatabaseMetaData object
            DatabaseMetaData dbMetaData = con.getMetaData();

            // Display Database Information
            System.out.println("\nDATABASE METADATA INFORMATION");
            System.out.println("========================================");
            System.out.println("Database Product Name    : " + dbMetaData.getDatabaseProductName());
            System.out.println("Database Product Version : " + dbMetaData.getDatabaseProductVersion());
            System.out.println("Driver Name              : " + dbMetaData.getDriverName());
            System.out.println("Driver Version           : " + dbMetaData.getDriverVersion());
            System.out.println("User Name                : " + dbMetaData.getUserName());

            con.close();
            System.out.println("\nConnection closed");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}