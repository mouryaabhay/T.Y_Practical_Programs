// Create a Map to store employee IDs and their names. Display all key-value pairs using
// Map.Entry and Sort them according to employee IDs.

import java.util.*;

public class B3 {
    public static void main(String[] args) {

        // Create Map
        Map<Integer, String> empMap = new HashMap<>();
        empMap.put(103, "Ravi");
        empMap.put(101, "Amit");
        empMap.put(104, "Suresh");
        empMap.put(102, "Ankit");

        // Sort Map by Employee ID
        Map<Integer, String> sortedMap = new TreeMap<>(empMap);

        // Display using Map.Entry
        System.out.println("Employee ID and Names (Sorted):");
        for (Map.Entry<Integer, String> e : sortedMap.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}
