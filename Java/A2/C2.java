// 2) Write a Java program to simulate an office print management system where multiple
// employees send print requests simultaneously. Each print request should be treated as a task
// and each printer should act as a thread. Use a fixed-size thread pool of three threads to
// efficiently process all print jobs by reusing the available printers.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class PrintJob implements Runnable {
	private String employeeName;
	private int pages;

	public PrintJob(String employeeName, int pages) {
		this.employeeName = employeeName;
		this.pages = pages;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " is printing " + pages + " pages for " + employeeName);
		try {
			Thread.sleep(pages * 100); // Simulate time taken to print
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " finished printing for " + employeeName);
	}
}

public class C2 {
	public static void main(String[] args) {
		ExecutorService printerPool = Executors.newFixedThreadPool(3);

		// Simulate multiple employees sending print requests
		PrintJob[] jobs = {
			new PrintJob("Alice", 5),
			new PrintJob("Bob", 3),
			new PrintJob("Charlie", 7),
			new PrintJob("Diana", 2),
			new PrintJob("Eve", 4),
			new PrintJob("Frank", 6)
		};

		for (PrintJob job : jobs) {
			printerPool.execute(job);
		}

		printerPool.shutdown();
	}
}

