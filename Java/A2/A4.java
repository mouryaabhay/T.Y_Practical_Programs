// 4) Write a Java program to demonstrate the use of Thread.sleep() in a user-defined thread.
// Show how the main thread and the child thread execute concurrently with delayed output from the child thread.

class MyThread extends Thread {
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            System.out.println("Child Interrupted");
        }
    }
}

public class A4 {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        t.start();

        for (int i = 1; i <= 5; i++) {
            System.out.println("Main Thread: " + i);
            Thread.sleep(1000);
        }
    }
}
