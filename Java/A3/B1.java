// Question 4: Use PreparedStatement to insert data into the student table using user input.
import java.sql.*;
import java.util.Scanner;

public class B1 {
    private static void displayAllStudents(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Student ORDER BY id");

        System.out.println("\nCurrent Student Records:");
        System.out.println("ID\tName\t\t\tMarks");
        System.out.println("--------------------------------------");

        while (rs.next()) {
            System.out.printf("%d\t%-20s\t%.2f%n",
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("marks"));
        }

        rs.close();
        stmt.close();
    }
    
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");

            // Prepare SQL query with parameters
            String sql = "INSERT INTO Student (id, name, marks) VALUES (?, ?, ?)";

            // Create PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(sql);

            System.out.println("\nINSERT NEW STUDENT RECORD");
            System.out.println("=============================");

            // Get user input
            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();

            // Set parameters
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, marks);

            // Execute insert
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student record inserted successfully!");
            } else {
                System.out.println("Failed to insert student record");
            }

            // Display current records
            displayAllStudents(con);

            // Close resources
            pstmt.close();
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                System.out.println("Error: Student ID already exists!");
            } else {
                System.out.println("Database error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}