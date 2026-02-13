// Question 7: Write a Java program to fetch all employee records from a table.
// Use ResultSetMetaData to dynamically display column names and their data types along with the data.
import java.sql.*;

public class C1 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");

            // Create Employee table if not exists
            createEmployeeTable(con);

            // Insert sample employee data
            insertSampleEmployees(con);

            // Fetch and display employee records with metadata
            displayEmployeeRecordsWithMetadata(con);

            con.close();
            System.out.println("\nConnection closed");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createEmployeeTable(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS Employee (" +
                    "emp_id INT PRIMARY KEY, " +
                    "emp_name VARCHAR(100) NOT NULL, " +
                    "department VARCHAR(50), " +
                    "salary DECIMAL(10,2), " +
                    "joining_date DATE)";
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Employee table ready");
    }

    private static void insertSampleEmployees(Connection con) throws SQLException {
        // Check if table is empty
        Statement checkStmt = con.createStatement();
        ResultSet countRs = checkStmt.executeQuery("SELECT COUNT(*) FROM Employee");
        countRs.next();
        int count = countRs.getInt(1);
        countRs.close();
        checkStmt.close();

        if (count == 0) {
            String sql = "INSERT INTO Employee VALUES " +
                        "(1001, 'John Smith', 'IT', 75000.00, '2023-01-15'), " +
                        "(1002, 'Sarah Johnson', 'HR', 65000.00, '2023-02-20'), " +
                        "(1003, 'Mike Wilson', 'Finance', 70000.00, '2023-03-10'), " +
                        "(1004, 'Emily Davis', 'Marketing', 60000.00, '2023-04-05'), " +
                        "(1005, 'Robert Brown', 'IT', 80000.00, '2023-05-12')";

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Sample employee records inserted");
        }
    }

    private static void displayEmployeeRecordsWithMetadata(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

        // Get ResultSetMetaData
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        System.out.println("\nEMPLOYEE RECORDS WITH METADATA");
        System.out.println("==========================================");

        // Display Column Metadata
        System.out.println("COLUMN METADATA:");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-5s %-20s %-15s %-10s%n", "No.", "Column Name", "Data Type", "Size");
        System.out.println("--------------------------------------------------");

        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-5d %-20s %-15s %-10d%n",
                i,
                rsmd.getColumnName(i),
                rsmd.getColumnTypeName(i),
                rsmd.getColumnDisplaySize(i));
        }

        // Display Column Headers for Data
        System.out.println("\nEMPLOYEE DATA:");
        System.out.print("┌");
        for (int i = 1; i <= columnCount; i++) {
            for (int j = 0; j < 20; j++) System.out.print("─");
            if (i < columnCount) System.out.print("┬");
        }
        System.out.println("┐");

        // Print column headers
        System.out.print("│");
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf(" %-18s │", rsmd.getColumnName(i));
        }
        System.out.println();

        System.out.print("├");
        for (int i = 1; i <= columnCount; i++) {
            for (int j = 0; j < 20; j++) System.out.print("─");
            if (i < columnCount) System.out.print("┼");
        }
        System.out.println("┤");

        // Print data rows
        int rowCount = 0;
        while (rs.next()) {
            System.out.print("│");
            for (int i = 1; i <= columnCount; i++) {
                String value = rs.getString(i);
                if (value == null) value = "NULL";
                System.out.printf(" %-18s │", value.length() > 18 ? value.substring(0, 15) + "..." : value);
            }
            System.out.println();
            rowCount++;
        }

        System.out.print("└");
        for (int i = 1; i <= columnCount; i++) {
            for (int j = 0; j < 20; j++) System.out.print("─");
            if (i < columnCount) System.out.print("┴");
        }
        System.out.println("┘");

        System.out.println("\nTotal records displayed: " + rowCount);

        // Display additional metadata
        System.out.println("\nADDITIONAL COLUMN METADATA:");
        System.out.println("--------------------------------------------------");
        for (int i = 1; i <= columnCount; i++) {
            System.out.println("\nColumn: " + rsmd.getColumnName(i));
            System.out.println("  - Table Name: " + rsmd.getTableName(i));
            System.out.println("  - Schema Name: " + rsmd.getSchemaName(i));
            System.out.println("  - Class Name: " + rsmd.getColumnClassName(i));
            System.out.println("  - Nullable: " + (rsmd.isNullable(i) == ResultSetMetaData.columnNullable ? "YES" : "NO"));
            System.out.println("  - Auto Increment: " + (rsmd.isAutoIncrement(i) ? "YES" : "NO"));
            System.out.println("  - Currency: " + (rsmd.isCurrency(i) ? "YES" : "NO"));
            System.out.println("  - Read Only: " + (rsmd.isReadOnly(i) ? "YES" : "NO"));
            System.out.println("  - Writable: " + (rsmd.isWritable(i) ? "YES" : "NO"));
            System.out.println("  - Definite Writable: " + (rsmd.isDefinitelyWritable(i) ? "YES" : "NO"));
        }

        rs.close();
        stmt.close();
    }
}