// Question 2: Create a table Student(id, name, marks) using JDBC and insert some records.
import java.sql.*;

public class CreateStudentTable {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");

            // Create Statement
            Statement stmt = con.createStatement();

            // Create table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Student (" +
                                   "id INT PRIMARY KEY, " +
                                   "name VARCHAR(100) NOT NULL, " +
                                   "marks DECIMAL(5,2))";

            stmt.executeUpdate(createTableSQL);
            System.out.println("Student table created successfully");

            // Insert records
            String insertSQL1 = "INSERT INTO Student VALUES (101, 'John Doe', 85.50)";
            String insertSQL2 = "INSERT INTO Student VALUES (102, 'Jane Smith', 92.75)";
            String insertSQL3 = "INSERT INTO Student VALUES (103, 'Bob Johnson', 78.25)";
            String insertSQL4 = "INSERT INTO Student VALUES (104, 'Alice Brown', 88.00)";

            int count1 = stmt.executeUpdate(insertSQL1);
            int count2 = stmt.executeUpdate(insertSQL2);
            int count3 = stmt.executeUpdate(insertSQL3);
            int count4 = stmt.executeUpdate(insertSQL4);

            System.out.println("Inserted " + (count1 + count2 + count3 + count4) + " records");

            // Display records
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
            System.out.println("\nStudent Records:");
            System.out.println("ID\tName\t\tMarks");
            System.out.println("--------------------------------");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                 rs.getString("name") + "\t" +
                                 rs.getDouble("marks"));
            }

            // Close resources
            rs.close();
            stmt.close();
            con.close();
            System.out.println("\nConnection closed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}