// Write a Java Program that accept n integer values store in List and do the following
// operations.
// 1) Display all elements of the list using an Iterator.
// 2) Count how many elements are Less than or equal to 20 & Greater than 20.
// 3) Remove all elements greater than 20 while iterating..
// 4) Find and display: a. Maximum value b. Minimum value (after removal)
// 5) Display the final updated list.

import java.util.*;

public class B1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++)
            list.add(sc.nextInt());

        // 1., 2., & 3. Display using Iterator, Count and remove (>20) together
        System.out.println("\nElements:");
        Iterator<Integer> it = list.iterator();
        int le20 = 0, gt20 = 0;
        it = list.iterator();
        while (it.hasNext()) {
            int x = it.next();
            System.out.println(x);
            if (x <= 20)
                le20++;
            else {
                gt20++;
                it.remove();
            }
        }

        System.out.println("\n<= 20 : " + le20);
        System.out.println("> 20  : " + gt20);

        // 4. Max & Min after removal
        int max = Collections.max(list);
        int min = Collections.min(list);

        System.out.println("\nMaximum: " + max);
        System.out.println("Minimum: " + min);

        // 5. Final updated list
        System.out.println("\nFinal List:");
        for (int x : list)
            System.out.println(x);
    }
}
