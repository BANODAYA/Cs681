package edu.umb.cs681.hw15;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionControl {
	
	private int visitors = 0;
	private ReentrantLock lock = new ReentrantLock();
	
	private Condition sufficientCondition = lock.newCondition();
	private Condition LimitCondition = lock.newCondition();
	
	public void enter() {
		
		lock.lock();
				
		try {
			
			System.out.println(Thread.currentThread().getName() + "--->  Current Visitors	: " + visitors);
			
			while (visitors >= 7) {
				try {
					System.out.println(Thread.currentThread().getName() + "---> SO many visitors, Visitors exceeded");
					sufficientCondition.await();
				} catch (InterruptedException e) {
					return;
				}
			}
			
			visitors++;
			System.out.println(Thread.currentThread().getName() + "---> New Set of  Current Visitors	: " + visitors);
			LimitCondition.signalAll();
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			lock.unlock();
		}
	}
	
	public void exit() {
		
		lock.lock();
		
		try {
			System.out.println(Thread.currentThread().getName() + "--->  Current Visitors	: " + visitors);
			
			while (visitors <= 0) {
				try {
					System.out.println(Thread.currentThread().getName() + "---> Visitors are done.");
					LimitCondition.await();
				} catch (InterruptedException e) {
					return;
				}
			}
			
			visitors--;
			System.out.println(Thread.currentThread().getName() + "---> New set of Current Visitors	: " + visitors);
			sufficientCondition.signalAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public int visitorsCount() {
		lock.lock();
		try {
			return visitors;
		}
		finally {
			lock.unlock();
		}
	}
}
