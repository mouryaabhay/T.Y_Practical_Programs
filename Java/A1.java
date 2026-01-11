// Write a Java program to store 5 Strings in an ArrayList and display the list using
// iterator and foreach and perform the following operations: insert a string at a specific
// index, remove a given string, and check if a specific string exists in the list.

import java.util.ArrayList;
import java.util.Iterator;

public class A1 {

    public static void main(String[] args) {

        // Create ArrayList to store Strings
        ArrayList<String> list = new ArrayList<>();

        // Add 5 strings
        list.add("Java");
        list.add("Python");
        list.add("C");
        list.add("C++");
        list.add("JavaScript");

        // Display using Iterator
        System.out.println("Displaying list using Iterator:");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Display using for-each loop
        System.out.println("\nDisplaying list using for-each loop:");
        for (String s : list) {
            System.out.println(s);
        }

        // Insert a string at a specific index
        list.add(2, "Kotlin");
        System.out.println("\nAfter inserting 'Kotlin' at index 2:");
        System.out.println(list);

        // Remove a given string
        list.remove("C++");
        System.out.println("\nAfter removing 'C++':");
        System.out.println(list);

        // Check if a specific string exists
        String searchString = "Java";
        if (list.contains(searchString)) {
            System.out.println("\n'" + searchString + "' exists in the list.");
        } else {
            System.out.println("\n'" + searchString + "' does not exist in the list.");
        }
    }
}
