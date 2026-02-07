// 1) Write a Java program to simulate a bank account where multiple threads perform
// deposit and withdrawal operations concurrently. Ensure thread safety using synchronization.

class BankAccount {
	private int balance;

	public BankAccount(int initialBalance) {
		this.balance = initialBalance;
	}2

	public synchronized void deposit(int amount) {
		balance += amount;
		System.out.println(Thread.currentThread().getName() + " deposited " + amount + ", Balance: " + balance);
	}

	public synchronized void withdraw(int amount) {
		if (balance >= amount) {
			balance -= amount;
			System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", Balance: " + balance);
		} else {
			System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount + " but insufficient balance. Balance: " + balance);
		}
	}

	public int getBalance() {
		return balance;
	}
}

class DepositThread extends Thread {
	private BankAccount account;
	private int amount;

	public DepositThread(BankAccount account, int amount) {
		this.account = account;
		this.amount = amount;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			account.deposit(amount);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class WithdrawThread extends Thread {
	private BankAccount account;
	private int amount;

	public WithdrawThread(BankAccount account, int amount) {
		this.account = account;
		this.amount = amount;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			account.withdraw(amount);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class C1 {
	public static void main(String[] args) {
		BankAccount account = new BankAccount(1000);

		Thread t1 = new DepositThread(account, 200);
		Thread t2 = new WithdrawThread(account, 150);
		Thread t3 = new DepositThread(account, 100);
		Thread t4 = new WithdrawThread(account, 300);

		t1.setName("Depositor  1: ");
		t2.setName("Withdrawer 1: ");
		t3.setName("Depositor  2: ");
		t4.setName("Withdrawer 2: ");

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		// try {
		// 	t1.join();
		// 	t2.join();
		// 	t3.join();
		// 	t4.join();
		// } catch (InterruptedException e) {
		// 	e.printStackTrace();
		// }

		System.out.println("Final Balance: " + account.getBalance());
	}
}

