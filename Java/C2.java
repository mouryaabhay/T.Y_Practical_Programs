// Write a Java program to manage Student records using TreeSet sorted by name (using
// Comparator). Implement a menu-driven program to add, display, search by roll number,
// and delete by roll number.

import java.util.*;

// Student class
class Student {
    int rollNo;
    String name;

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    public String toString() {
        return rollNo + " " + name;
    }
}

// Comparator class to sort Student by name
class NameComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}

public class C2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // TreeSet sorted by name using Comparator (NO lambda)
        TreeSet<Student> students = new TreeSet<>(new NameComparator());

        int choice;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search by Roll Number");
            System.out.println("4. Delete by Roll Number");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter roll number: ");
                    int roll = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    students.add(new Student(roll, name));
                    System.out.println("Student added.");
                    break;

                case 2:
                    System.out.println("\nStudents sorted by name:");
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    break;

                case 3:
                    System.out.print("Enter roll number to search: ");
                    int searchRoll = sc.nextInt();
                    boolean found = false;

                    for (Student s : students) {
                        if (s.rollNo == searchRoll) {
                            System.out.println("Found: " + s);
                            found = true;
                            break;
                        }
                    }

                    if (!found)
                        System.out.println("Student not found.");
                    break;

                case 4:
                    System.out.print("Enter roll number to delete: ");
                    int delRoll = sc.nextInt();

                    Iterator<Student> it = students.iterator();
                    boolean removed = false;

                    while (it.hasNext()) {
                        if (it.next().rollNo == delRoll) {
                            it.remove();
                            removed = true;
                            System.out.println("Student removed.");
                            break;
                        }
                    }

                    if (!removed)
                        System.out.println("Student not found.");
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);

        sc.close();
    }
}
