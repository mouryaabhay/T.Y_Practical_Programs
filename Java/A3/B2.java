// Question 5: Write a Java program using JDBC to retrieve and display database information
// such as database product name, database version, driver name, and user name
// using the DatabaseMetaData interface.
import java.sql.*;

public class DatabaseMetaDataDemo {
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

            // 1. Database Product Name
            System.out.println("Database Product Name    : " + dbMetaData.getDatabaseProductName());

            // 2. Database Product Version
            System.out.println("Database Product Version : " + dbMetaData.getDatabaseProductVersion());

            // 3. Driver Name
            System.out.println("Driver Name              : " + dbMetaData.getDriverName());

            // 4. Driver Version
            System.out.println("Driver Version           : " + dbMetaData.getDriverVersion());

            // 5. User Name
            System.out.println("User Name                : " + dbMetaData.getUserName());

            // Additional Information
            System.out.println("\nADDITIONAL INFORMATION");
            System.out.println("========================================");
            System.out.println("JDBC Major Version       : " + dbMetaData.getJDBCMajorVersion());
            System.out.println("JDBC Minor Version       : " + dbMetaData.getJDBCMinorVersion());
            System.out.println("URL                      : " + dbMetaData.getURL());
            System.out.println("Max Connections          : " + dbMetaData.getMaxConnections());
            System.out.println("Supports Transactions    : " + dbMetaData.supportsTransactions());
            System.out.println("Supports Stored Procedures: " + dbMetaData.supportsStoredProcedures());
            System.out.println("Supports Batch Updates   : " + dbMetaData.supportsBatchUpdates());

            con.close();
            System.out.println("\nConnection closed");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}