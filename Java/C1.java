// Write a Java program to accept a string, store the frequency of each character in a
// TreeMap, and display the characters in sorted order along with their counts.

import java.util.*;

public class C1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        TreeMap<Character, Integer> freqMap = new TreeMap<>();

        // Count frequency of each character
        for (char c : str.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Display characters in sorted order
        System.out.println("\nCharacter frequencies:");
        for (Map.Entry<Character, Integer> e : freqMap.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }
}
