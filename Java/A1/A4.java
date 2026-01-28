// Write a Java program to demonstrate basic operations on Stack. Perform push, pop on the Stack.

import java.util.Stack;

public class A4 {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        // Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Stack after push: " + stack);

        // Pop element
        int popped = stack.pop();
        System.out.println("Popped element: " + popped);

        System.out.println("Stack after pop: " + stack);
    }
}
