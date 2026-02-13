// Question 8: Display all column names and table metadata using DatabaseMetaData and ResultSetMetaData.
import java.sql.*;

public class C2 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database successfully!");
            System.out.println("=".repeat(80));

            // PART 1: Display Database Metadata
            displayDatabaseMetadata(con);

            // PART 2: Display All Tables and Their Column Metadata
            displayAllTablesMetadata(con);

            con.close();
            System.out.println("\nConnection closed successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void displayDatabaseMetadata(Connection con) throws SQLException {
        DatabaseMetaData dbmd = con.getMetaData();

        System.out.println("\nDATABASE METADATA");
        System.out.println("=".repeat(80));

        // Basic Database Information
        System.out.println("Database Product:");
        System.out.println("   - Name: " + dbmd.getDatabaseProductName());
        System.out.println("   - Version: " + dbmd.getDatabaseProductVersion());
        System.out.println("   - Major Version: " + dbmd.getDatabaseMajorVersion());
        System.out.println("   - Minor Version: " + dbmd.getDatabaseMinorVersion());

        System.out.println("\nJDBC Driver:");
        System.out.println("   - Driver Name: " + dbmd.getDriverName());
        System.out.println("   - Driver Version: " + dbmd.getDriverVersion());
        System.out.println("   - JDBC Major Version: " + dbmd.getJDBCMajorVersion());
        System.out.println("   - JDBC Minor Version: " + dbmd.getJDBCMinorVersion());

        System.out.println("\nConnection Information:");
        System.out.println("   - URL: " + dbmd.getURL());
        System.out.println("   - User Name: " + dbmd.getUserName());

        System.out.println("\nDatabase Capabilities:");
        System.out.println("   - Supports Transactions: " + dbmd.supportsTransactions());
        System.out.println("   - Supports Batch Updates: " + dbmd.supportsBatchUpdates());
        System.out.println("   - Supports Stored Procedures: " + dbmd.supportsStoredProcedures());
        System.out.println("   - Supports Outer Joins: " + dbmd.supportsOuterJoins());
        System.out.println("   - Supports Full Outer Joins: " + dbmd.supportsFullOuterJoins());
        System.out.println("   - Supports Limited Outer Joins: " + dbmd.supportsLimitedOuterJoins());
        System.out.println("   - Max Connections: " + dbmd.getMaxConnections());
        System.out.println("   - Max Table Name Length: " + dbmd.getMaxTableNameLength());
        System.out.println("   - Max Columns in Table: " + dbmd.getMaxColumnsInTable());
        System.out.println("   - Max Row Size: " + dbmd.getMaxRowSize() + " bytes");

        System.out.println("\nSQL Keywords:");
        System.out.println("   - SQL Keywords: " + dbmd.getSQLKeywords());
        System.out.println("   - Numeric Functions: " + dbmd.getNumericFunctions());
        System.out.println("   - String Functions: " + dbmd.getStringFunctions());
        System.out.println("   - System Functions: " + dbmd.getSystemFunctions());
        System.out.println("   - Time/Date Functions: " + dbmd.getTimeDateFunctions());
    }

    private static void displayAllTablesMetadata(Connection con) throws SQLException {
        DatabaseMetaData dbmd = con.getMetaData();

        System.out.println("\n\nTABLE AND COLUMN METADATA");
        System.out.println("=".repeat(80));

        // Get all tables in the database
        String[] types = {"TABLE"};
        ResultSet tables = dbmd.getTables(null, null, "%", types);

        boolean hasTables = false;
        while (tables.next()) {
            hasTables = true;
            String tableName = tables.getString("TABLE_NAME");
            String tableSchema = tables.getString("TABLE_SCHEM");
            String tableType = tables.getString("TABLE_TYPE");
            String remarks = tables.getString("REMARKS");

            System.out.println("\nTABLE: " + tableName);
            System.out.println("   Schema: " + tableSchema);
            System.out.println("   Type: " + tableType);
            if (remarks != null && !remarks.isEmpty()) {
                System.out.println("   Remarks: " + remarks);
            }
            System.out.println("   " + "-".repeat(60));

            // Display column metadata for this table using ResultSetMetaData
            displayColumnMetadataWithResultSet(con, tableName);

            // Display column metadata using DatabaseMetaData
            displayColumnMetadataWithDatabaseMetaData(dbmd, tableName);
        }

        if (!hasTables) {
            System.out.println("No tables found in the database!");
        }
        tables.close();
    }

    private static void displayColumnMetadataWithResultSet(Connection con, String tableName) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 0");
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println("\n   COLUMN METADATA (ResultSetMetaData):");
            System.out.println("   " + "┌" + "─".repeat(78) + "┐");
            System.out.printf("   │ %-3s │ %-20s │ %-15s │ %-10s │ %-10s │%n",
                "No", "Column Name", "Data Type", "Size", "Nullable");
            System.out.println("   ├" + "─".repeat(3) + "┼" + "─".repeat(22) + "┼" +
                             "─".repeat(17) + "┼" + "─".repeat(12) + "┼" + "─".repeat(12) + "┤");

            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String nullable = rsmd.isNullable(i) == ResultSetMetaData.columnNullable ? "YES" :
                                 (rsmd.isNullable(i) == ResultSetMetaData.columnNoNulls ? "NO" : "UNKNOWN");

                System.out.printf("   │ %-3d │ %-20s │ %-15s │ %-10d │ %-10s │%n",
                    i,
                    truncate(rsmd.getColumnName(i), 20),
                    truncate(rsmd.getColumnTypeName(i), 15),
                    rsmd.getColumnDisplaySize(i),
                    nullable);
            }
            System.out.println("   └" + "─".repeat(3) + "┴" + "─".repeat(22) + "┴" +
                             "─".repeat(17) + "┴" + "─".repeat(12) + "┴" + "─".repeat(12) + "┘");

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("   Error getting column metadata for " + tableName + ": " + e.getMessage());
        }
    }

    private static void displayColumnMetadataWithDatabaseMetaData(DatabaseMetaData dbmd, String tableName) {
        try {
            ResultSet columns = dbmd.getColumns(null, null, tableName, "%");

            System.out.println("\n   COLUMN METADATA (DatabaseMetaData):");
            System.out.println("   " + "┌" + "─".repeat(78) + "┐");
            System.out.printf("   │ %-20s │ %-15s │ %-10s │ %-10s │ %-8s │%n",
                "Column Name", "Data Type", "Size", "Nullable", "Default");
            System.out.println("   ├" + "─".repeat(22) + "┼" + "─".repeat(17) + "┼" +
                             "─".repeat(12) + "┼" + "─".repeat(12) + "┼" + "─".repeat(10) + "┤");

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                int size = columns.getInt("COLUMN_SIZE");
                String nullable = columns.getString("IS_NULLABLE");
                String defaultValue = columns.getString("COLUMN_DEF");

                if (defaultValue == null) defaultValue = "NULL";

                System.out.printf("   │ %-20s │ %-15s │ %-10d │ %-10s │ %-8s │%n",
                    truncate(columnName, 20),
                    truncate(dataType, 15),
                    size,
                    nullable,
                    truncate(defaultValue, 8));
            }
            System.out.println("   └" + "─".repeat(22) + "┴" + "─".repeat(17) + "┴" +
                             "─".repeat(12) + "┴" + "─".repeat(12) + "┴" + "─".repeat(10) + "┘");

            columns.close();

        } catch (SQLException e) {
            System.out.println("   Error getting column metadata from DatabaseMetaData: " + e.getMessage());
        }
    }

    private static String truncate(String str, int length) {
        if (str == null) return "NULL";
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }
}