// Question 6: Design a menu-driven Java application using JDBC to manage student records stored in a database.
// The program should allow the user to insert, display, search, update, and delete student information
// using JDBC connectivity.
import java.sql.*;
import java.util.Scanner;

public class B3 {

    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection con;
    private static Scanner sc = new Scanner(System.in);

    private static void displayMenu() {
        System.out.println("\nSTUDENT MANAGEMENT SYSTEM");
        System.out.println("==============================");
        System.out.println("1. Insert Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("==============================");
    }

    private static void createTableIfNotExists() throws SQLException {
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS Student (" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "marks DECIMAL(5,2))";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    private static void insertStudent() throws SQLException {
        System.out.println("\nINSERT STUDENT RECORD");
        System.out.println("----------------------");

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        String sql = "INSERT INTO Student VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setDouble(3, marks);

        int result = pstmt.executeUpdate();
        if (result > 0) {
            System.out.println("Student record inserted successfully!");
        } else {
            System.out.println("Failed to insert student record!");
        }
        pstmt.close();
    }

    private static void displayAllStudents() throws SQLException {
        System.out.println("\nALL STUDENT RECORDS");
        System.out.println("==================================");
        System.out.printf("%-5s | %-20s | %-6s%n", "ID", "Name", "Marks");
        System.out.println("==================================");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Student ORDER BY id");

        boolean hasRecords = false;
        while (rs.next()) {
            hasRecords = true;
            System.out.printf("%-5d | %-20s | %-6.2f%n",
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("marks"));
        }

        if (!hasRecords) {
            System.out.println("No student records found!");
        }
        System.out.println("==================================");
        System.out.println("Total records: " + getRowCount());

        rs.close();
        stmt.close();
    }

    private static int getRowCount() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Student");
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        stmt.close();
        return count;
    }

    private static void searchStudent() throws SQLException {
        System.out.println("\nSEARCH STUDENT");
        System.out.println("----------------");

        System.out.print("Enter Student ID to search: ");
        int id = sc.nextInt();

        String sql = "SELECT * FROM Student WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            System.out.println("\nStudent Found:");
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Marks: " + rs.getDouble("marks"));
        } else {
            System.out.println("Student with ID " + id + " not found!");
        }

        rs.close();
        pstmt.close();
    }

    private static void updateStudent() throws SQLException {
        System.out.println("\nUPDATE STUDENT");
        System.out.println("----------------");

        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        // Check if student exists
        if (!studentExists(id)) {
            System.out.println("Student with ID " + id + " not found!");
            return;
        }

        System.out.println("What do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Marks");
        System.out.println("3. Both");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        String sql;
        PreparedStatement pstmt;

        switch (choice) {
            case 1:
                System.out.print("Enter new Name: ");
                String newName = sc.nextLine();
                sql = "UPDATE Student SET name = ? WHERE id = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, newName);
                pstmt.setInt(2, id);
                break;
            case 2:
                System.out.print("Enter new Marks: ");
                double newMarks = sc.nextDouble();
                sql = "UPDATE Student SET marks = ? WHERE id = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setDouble(1, newMarks);
                pstmt.setInt(2, id);
                break;
            case 3:
                System.out.print("Enter new Name: ");
                String name = sc.nextLine();
                System.out.print("Enter new Marks: ");
                double marks = sc.nextDouble();
                sql = "UPDATE Student SET name = ?, marks = ? WHERE id = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setDouble(2, marks);
                pstmt.setInt(3, id);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        int result = pstmt.executeUpdate();
        if (result > 0) {
            System.out.println("Student record updated successfully!");
        } else {
            System.out.println("Failed to update student record!");
        }
        pstmt.close();
    }

    private static void deleteStudent() throws SQLException {
        System.out.println("\nDELETE STUDENT");
        System.out.println("----------------");

        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        // Check if student exists
        if (!studentExists(id)) {
            System.out.println("Student with ID " + id + " not found!");
            return;
        }

        System.out.print("Are you sure you want to delete this record? (y/n): ");
        String confirm = sc.next();

        if (confirm.equalsIgnoreCase("y")) {
            String sql = "DELETE FROM Student WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Student record deleted successfully!");
            } else {
                System.out.println("Failed to delete student record!");
            }
            pstmt.close();
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static boolean studentExists(int id) throws SQLException {
        String sql = "SELECT id FROM Student WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        boolean exists = rs.next();
        rs.close();
        pstmt.close();
        return exists;
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database successfully!");

            // Create table if not exists
            createTableIfNotExists();

            int choice;
            do {
                displayMenu();
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        insertStudent();
                        break;
                    case 2:
                        displayAllStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        System.out.println("Thank you for using Student Management System!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 6);

            con.close();
            System.out.println("Connection closed");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}