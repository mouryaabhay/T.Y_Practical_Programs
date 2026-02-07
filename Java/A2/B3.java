// 3) Write a Java program to demonstrate the Producer–Consumer problem using
// wait() and notify() methods for inter-thread communication.
// Note: Create a shared sr accessed by two threads:
// a Producer thread that produces data and a Consumer thread that consumes the data.

class SharedResource {
    private int data;
    private boolean available = false;

    // Producer method
    public synchronized void produce(int value) {
        while (available) {
            try {
                wait(); // wait if data already present
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        data = value;
        System.out.println("Produced: " + data);
        available = true;
        notify();
    }

    // Consumer method
    public synchronized void consume() {
        while(!available) {
            try {
                wait(); // wait if no data available
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println("Consumed: " + data);
        available = false;
        notify(); // notify producer
    }
}

class Producer extends Thread {
    private SharedResource sr;

    Producer(SharedResource sr) {
        this.sr = sr;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            sr.produce(i);
        }
    }
}

class Consumer extends Thread {
    private SharedResource sr;

    Consumer(SharedResource sr) {
        this.sr = sr;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            sr.consume();
        }
    }
}

public class B3 {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sr = new SharedResource();

        Producer producer = new Producer(sr);
        Consumer consumer = new Consumer(sr);

        producer.start();
        consumer.start();
    }
}