package edu.umb.cs681.hw14;

public class MainMutliThread {
	
	public static void main(String[] args) {
		ThreadSafeBankAccount account = new ThreadSafeBankAccount();
		DepositRunnable depositMoney= new DepositRunnable(account);
		WithdrawRunnable withdrawMoney = new WithdrawRunnable(account);
		
		Thread td1  = new Thread(depositMoney);
		Thread tw1  = new Thread(withdrawMoney);
		Thread td2  = new Thread(depositMoney);
		Thread tw2  = new Thread(withdrawMoney);
		Thread td3  = new Thread(depositMoney);
		Thread tw3  = new Thread(withdrawMoney);
		Thread td4  = new Thread(depositMoney);
		Thread tw4  = new Thread(withdrawMoney);
		
		td1.start();
		tw1.start();
		td2.start();
		tw2.start();
		td3.start();
		tw3.start();
		td4.start();
		tw4.start();
		
		depositMoney.setDone();
		withdrawMoney.setDone();
		
		td1.interrupt();
		tw1.interrupt();
		td2.interrupt();
		tw2.interrupt();
		td3.interrupt();
		tw3.interrupt();
		td4.interrupt();
		tw4.interrupt();
		
		try {
			td1.join();
			td2.join();
			td3.join();
			td4.join();
			

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
