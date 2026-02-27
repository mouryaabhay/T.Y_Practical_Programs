// Question 6: Design a menu-driven Java application using JDBC to manage student records stored in a database.
// The program should allow the user to insert, display, search, update, and delete student information
// using JDBC connectivity.

import java.sql.*;
import java.util.Scanner;

public class B3 {
    String url = "jdbc:postgresql://localhost:5432/testdb";
    String user = "postgres";
    String password = "postgres";

    Connection con;
    Scanner sc = new Scanner(System.in);

    void displayMenu() {
        System.out.println("\nSTUDENT MANAGEMENT SYSTEM");
        System.out.println("1. Insert Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    void createTableIfNotExists() {
        Statement stmt = con.createStatement();
        stmt.executeUpdate(
            "CREATE TABLE IF NOT EXISTS Student (id INT PRIMARY KEY, name VARCHAR(100) NOT NULL, marks DECIMAL(5,2))"
        );
        stmt.close();
    }

    void insertStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        PreparedStatement pstmt = con.prepareStatement("INSERT INTO Student VALUES (?, ?, ?)");
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setDouble(3, marks);

        int result = pstmt.executeUpdate();
        System.out.println(result > 0 ? "Inserted Successfully!" : "Insert Failed!");

        pstmt.close();
    }

    void displayAllStudents() {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Student ORDER BY id");

        System.out.println("\nID\tName\t\tMarks");
        System.out.println("--------------------------------");

        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + "\t" +
                rs.getString("name") + "\t\t" +
                rs.getDouble("marks")
            );
        }

        rs.close();
        stmt.close();
    }

    void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = sc.nextInt();

        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Student WHERE id = ?");
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Marks: " + rs.getDouble("marks"));
        } else {
            System.out.println("Student Not Found!");
        }

        rs.close();
        pstmt.close();
    }

    void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Marks: ");
        double marks = sc.nextDouble();

        PreparedStatement pstmt = con.prepareStatement("UPDATE Student SET name=?, marks=? WHERE id=?");
        pstmt.setString(1, name);
        pstmt.setDouble(2, marks);
        pstmt.setInt(3, id);

        int result = pstmt.executeUpdate();
        System.out.println(result > 0 ? "Updated Successfully!" : "Student Not Found!");

        pstmt.close();
    }

    void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        PreparedStatement pstmt =
            con.prepareStatement("DELETE FROM Student WHERE id=?");
        pstmt.setInt(1, id);

        int result = pstmt.executeUpdate();
        System.out.println(result > 0 ? "Deleted Successfully!" : "Student Not Found!");

        pstmt.close();
    }

    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected Successfully!");

            createTableIfNotExists();

            int choice;

            do {
                displayMenu();
                choice = sc.nextInt();

                switch (choice) {
                    case 1: insertStudent(); break;
                    case 2: displayAllStudents(); break;
                    case 3: searchStudent(); break;
                    case 4: updateStudent(); break;
                    case 5: deleteStudent(); break;
                    case 6: System.out.println("Exiting..."); break;
                    default: System.out.println("Invalid Choice!");
                }
            } while (choice != 6);

            con.close();
            sc.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}