// Write a Java program to create a Map<String, Integer> with some key-value pairs.
// Iterate and display the entries using both an Iterator and a for-each loop

import java.util.*;

public class A3 {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);

        System.out.println("Using for-each loop:");
        for (Map.Entry<String, Integer> e : map.entrySet())
            System.out.println(e.getKey() + " = " + e.getValue());

        System.out.println("Using Iterator:");
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> e = it.next();
            System.out.println(e.getKey() + " = " + e.getValue());
        }
    }
}
