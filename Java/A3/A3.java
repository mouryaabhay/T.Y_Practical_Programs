// Question 3: Write a program to display all student records from the table using ResultSet
import java.sql.*;

public class A3 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");

            // Create Statement
            Statement stmt = con.createStatement();

            // Execute query
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

            // Display column headers
            System.out.println("\nALL STUDENT RECORDS");
            System.out.println("==================================");
            System.out.printf("%-5s | %-20s | %-6s%n", "ID", "Name", "Marks");
            System.out.println("==================================");

            // Check if table is empty
            if (!rs.next()) {
                System.out.println("No records found in Student table");
            } else {
                // process first row + remaining rows
                do {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double marks = rs.getDouble("marks");

                    System.out.printf("%-5d | %-20s | %-6.2f%n", id, name, marks);
                } while (rs.next());
            }

            System.out.println("==================================");

            // Close resources
            rs.close();
            stmt.close();
            con.close();
            System.out.println("Connection closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
