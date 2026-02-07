// 1) Write a java program to Create a thread by extending the Thread class that prints numbers from 1 to 10.

class NumberThread extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

public class A1 {
    public static void main(String[] args) {
        NumberThread t = new NumberThread();
        t.start();
    }
}