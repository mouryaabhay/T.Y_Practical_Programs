// Question 2: Create a table Student(id, name, marks) using JDBC and insert some records.
import java.sql.*;

public class A2 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");

            Statement stmt = con.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Student(id INT PRIMARY KEY, name VARCHAR(100) NOT NULL, marks DECIMAL(5,2))");

            int count = stmt.executeUpdate("INSERT INTO Student VALUES (101, 'John Doe', 85.50), (102, 'Jane Smith', 92.75), (103, 'Bob Johnson', 78.25), (104, 'Alice Brown', 88.00)");
            System.out.println(count + " records inserted");

            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

            System.out.println("\nID\tName\t\tMarks");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getDouble("marks"));
            }

            rs.close();
            stmt.close();
            con.close();
            System.out.println("\nConnection closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
