// 1) Write a Java program by extending the Thread class to print numbers using two threads: one custom thread and the main thread.
// Use Thread.yield() to demonstrate its impact on thread scheduling and observe interleaved output.

class CustomThread extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Custom Thread: " + i);
            Thread.yield(); // Give chance to other threads
        }
    }
}

public class B1 {
    public static void main(String[] args) {
        CustomThread t = new CustomThread();
        t.start();

        for (int i = 1; i <= 5; i++) {
            System.out.println("Main Thread: " + i);
            Thread.yield(); // Give chance to other threads
        }
    }
}