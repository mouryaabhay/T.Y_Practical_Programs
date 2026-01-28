// Write a Java program to store Employee objects in a TreeSet and automatically sort
// them alphabetically by name. Display the sorted list of employees. Use
// Comparable or Comparator for custom sorting.

import java.util.*;

class Employee implements Comparable<Employee> {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Sort by name alphabetically
    public int compareTo(Employee e) {
        return this.name.compareTo(e.name);
    }

    public String toString() {
        return id + " " + name;
    }
}

public class B2 {
    public static void main(String[] args) {

        TreeSet<Employee> set = new TreeSet<>();

        set.add(new Employee(1, "Ravi"));
        set.add(new Employee(2, "Ankit"));
        set.add(new Employee(3, "Suresh"));
        set.add(new Employee(4, "Amit"));

        System.out.println("Employees sorted by name:");
        for (Employee e : set)
            System.out.println(e);
    }
}
