// Write a Java program to manage 10 integers using TreeSet<Integer>. The program
// should store numbers in sorted order, display them, find the smallest and largest,
// remove a number specified by the user, and demonstrate NavigableSet methods:
// lower(), higher(), ceiling(), floor(), pollFirst(), and pollLast().

import java.util.*;

public class TreeSetDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create TreeSet
        TreeSet<Integer> numbers = new TreeSet<>();

        // Input 10 integers
        System.out.println("Enter 10 integers:");
        for (int i = 0; i < 10; i++) {
            numbers.add(sc.nextInt());
        }

        // Display elements (sorted automatically)
        System.out.println("\nNumbers in sorted order:");
        System.out.println(numbers);

        // Smallest and largest elements
        System.out.println("\nSmallest element: " + numbers.first());
        System.out.println("Largest element: " + numbers.last());

        // Remove a number specified by the user
        System.out.print("\nEnter a number to remove: ");
        int removeNum = sc.nextInt();
        if (numbers.remove(removeNum)) {
            System.out.println(removeNum + " removed successfully.");
        } else {
            System.out.println(removeNum + " not found in the set.");
        }

        System.out.println("Updated set: " + numbers);

        // Demonstrate NavigableSet methods
        System.out.print("\nEnter a reference number for navigation methods: ");
        int ref = sc.nextInt();

        System.out.println("Lower than " + ref + ": " + numbers.lower(ref));
        System.out.println("Higher than " + ref + ": " + numbers.higher(ref));
        System.out.println("Ceiling of " + ref + ": " + numbers.ceiling(ref));
        System.out.println("Floor of " + ref + ": " + numbers.floor(ref));

        // pollFirst() and pollLast()
        System.out.println("\nPolling first element: " + numbers.pollFirst());
        System.out.println("Polling last element: " + numbers.pollLast());

        System.out.println("Final set after polling: " + numbers);

        sc.close();
    }
}
