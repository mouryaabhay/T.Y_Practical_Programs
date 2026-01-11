// Write a java program to accept numbers form user and sort them (duplicate can
// allowed and sort it) user appropriate collection classes

import java.util.*;

public class A6 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        System.out.print("Enter how many numbers: ");
        int n = sc.nextInt();

        System.out.println("Enter numbers:");
        for (int i = 0; i < n; i++)
            list.add(sc.nextInt());

        // Sort the list
        Collections.sort(list);

        System.out.println("Sorted numbers:");
        for (int i : list)
            System.out.println(i);
    }
}
