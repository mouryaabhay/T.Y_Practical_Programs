// Write a Java program to create a List of integers, remove duplicates using Set, and display unique values.

import java.util.*;

public class A5 {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(10);
        list.add(30);
        list.add(20);

        // Remove duplicates using Set
        Set<Integer> uniqueSet = new HashSet<>(list);

        // Display unique values
        System.out.println("Unique values:");
        for (int i : uniqueSet)
            System.out.println(i);
    }
}
