// Write a Java program to manage 10 integers using TreeSet<Integer>. The program
// should store numbers in sorted order, display them, find the smallest and largest,
// remove a number specified by the user, and demonstrate NavigableSet methods:
// lower(), higher(), ceiling(), floor(), pollFirst(), and pollLast().

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

// Comparator class
class NameComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}

public class C3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // TreeSet with custom Comparator
        TreeSet<Student> set = new TreeSet<>(new NameComparator());

        int choice;
        do {
            System.out.println("\n1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search by Roll Number");
            System.out.println("4. Delete by Roll Number");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter roll no: ");
                    int r = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String n = sc.nextLine();
                    set.add(new Student(r, n));
                    break;

                case 2:
                    System.out.println("\nStudents (sorted by name):");
                    for (Student s : set)
                        System.out.println(s);
                    break;

                case 3:
                    System.out.print("Enter roll no to search: ");
                    int sr = sc.nextInt();
                    boolean found = false;
                    for (Student s : set) {
                        if (s.rollNo == sr) {
                            System.out.println("Found: " + s);
                            found = true;
                            break;
                        }
                    }
                    if (!found)
                        System.out.println("Student not found");
                    break;

                case 4:
                    System.out.print("Enter roll no to delete: ");
                    int dr = sc.nextInt();
                    Iterator<Student> it = set.iterator();
                    boolean removed = false;
                    while (it.hasNext()) {
                        if (it.next().rollNo == dr) {
                            it.remove();
                            removed = true;
                            System.out.println("Student deleted");
                            break;
                        }
                    }
                    if (!removed)
                        System.out.println("Student not found");
                    break;
            }
        } while (choice != 0);
    }
}
