class MyThread extends Thread {
    public MyThread(String name) {
        setName(name);
    }

    public void run() {
        System.out.println(getName() + " started. Priority: " + getPriority());
        try {
            Thread.sleep(1000); // Pause for 1 second
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(getName() + " finished.");
    }
}

public class B2 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Moe");
        MyThread t2 = new MyThread("Miny");
        MyThread t3 = new MyThread("Mini");
        MyThread t4 = new MyThread("Ini");

        t1.setPriority(Thread.MIN_PRIORITY); // 1
        t2.setPriority(Thread.NORM_PRIORITY); // 5
        t3.setPriority(8); // 8
        t4.setPriority(Thread.MAX_PRIORITY); // 10

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}