package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DepositRunnable implements Runnable {
	
	private ThreadSafeBankAccount TSBA = null;
	public AtomicBoolean done = new AtomicBoolean(false);
	private ReentrantLock lock = new ReentrantLock();
	
	
	public DepositRunnable(ThreadSafeBankAccount TSBA) {
		this.TSBA = TSBA;
	}
	public void setDone() {
		done.getAndSet(true);
	}
	
	@Override
	public void run() {
		
		while (true) {
			lock.lock();
			try {
			
			if(done.get()) {
				System.out.println(Thread.currentThread().getName() +"---> Money is deposited successfully");
    			break;
			}
			}finally {
				lock.unlock();
			}
			
			System.out.println(Thread.currentThread().getName() +"---> Money is being deposited");
			TSBA.deposit(879);
			
			try {
				Thread.sleep(5000);
			}catch(InterruptedException e) {
				System.out.println(e);
				continue;
			}
		}
	}
}
