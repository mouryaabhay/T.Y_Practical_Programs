// 2) Write a java program to Create a thread by implementing the Runnable interface that prints a message 5 times.

class NumberRunnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": Iteration " + i);
        }
    }
}

public class A2 {
    public static void main(String[] args) {
        Thread t = new Thread(new NumberRunnable());
        t.start();
    }
}