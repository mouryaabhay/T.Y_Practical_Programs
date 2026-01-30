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