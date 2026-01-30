class EvenOdd implements Runnable {
    private boolean isEven;

    EvenOdd(boolean isEven) {
        this.isEven = isEven;
    }

    public void run() {
        for (int i = 1; i <= 20; i++) {
            if (isEven && i % 2 == 0) {
                System.out.println("Even: " + i);
            }
            if (!isEven && i % 2 != 0) {
                System.out.println("Odd: " + i);
            }
        }
    }
}

public class A3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new EvenOdd(true));
        Thread t2  = new Thread(new EvenOdd(false));

        t1.start();
        t2.start();
    }
}
