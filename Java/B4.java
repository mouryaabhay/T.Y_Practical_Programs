// Write a menu-driven Java program to store integer elements in an ArrayList.
// The program should use methods of the Collections class to perform operations such as
// sorting the list, reversing the list, swapping two elements, finding the maximum and
// minimum values, searching for an element, and displaying the frequency of a given
// element.

import java.util.*;

public class B4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add element");
            System.out.println("2. Sort list");
            System.out.println("3. Reverse list");
            System.out.println("4. Swap two elements");
            System.out.println("5. Find Max & Min");
            System.out.println("6. Search element");
            System.out.println("7. Frequency of element");
            System.out.println("8. Display list");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter element: ");
                    list.add(sc.nextInt());
                    break;

                case 2:
                    Collections.sort(list);
                    System.out.println("List sorted");
                    break;

                case 3:
                    Collections.reverse(list);
                    System.out.println("List reversed");
                    break;

                case 4:
                    System.out.print("Enter two indexes: ");
                    int i = sc.nextInt();
                    int j = sc.nextInt();
                    Collections.swap(list, i, j);
                    System.out.println("Elements swapped");
                    break;

                case 5:
                    System.out.println("Max: " + Collections.max(list));
                    System.out.println("Min: " + Collections.min(list));
                    break;

                case 6:
                    System.out.print("Enter element to search: ");
                    int key = sc.nextInt();
                    System.out.println("Found at index: " + list.indexOf(key));
                    break;

                case 7:
                    System.out.print("Enter element: ");
                    int x = sc.nextInt();
                    System.out.println("Frequency: " +
                            Collections.frequency(list, x));
                    break;

                case 8:
                    System.out.println("List: " + list);
                    break;

                case 0:
                    System.out.println("Exited");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }
}
