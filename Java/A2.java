// Write a Java Program to Create a HashSet and TressSet of integers display it by using foreach and iterator.

import java.util.HashSet;
import java.util.TreeSet;
import java.util.Iterator;

public class A2 {

    public static void main(String[] args) {

        // Create HashSet of integers
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(10);
        hashSet.add(50);
        hashSet.add(30);
        hashSet.add(20);
        hashSet.add(40);

        // Display HashSet using for-each loop
        System.out.println("HashSet elements using for-each:");
        for (int i : hashSet) {
            System.out.println(i);
        }

        // Display HashSet using Iterator
        System.out.println("\nHashSet elements using Iterator:");
        Iterator<Integer> hashIterator = hashSet.iterator();
        while (hashIterator.hasNext()) {
            System.out.println(hashIterator.next());
        }

        // Create TreeSet of integers
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(50);
        treeSet.add(30);
        treeSet.add(20);
        treeSet.add(40);

        // Display TreeSet using for-each loop
        System.out.println("\nTreeSet elements using for-each:");
        for (int i : treeSet) {
            System.out.println(i);
        }

        // Display TreeSet using Iterator
        System.out.println("\nTreeSet elements using Iterator:");
        Iterator<Integer> treeIterator = treeSet.iterator();
        while (treeIterator.hasNext()) {
            System.out.println(treeIterator.next());
        }
    }
}
