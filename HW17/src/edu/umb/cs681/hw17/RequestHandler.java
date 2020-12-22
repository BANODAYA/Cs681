package edu.umb.cs681.hw17;


import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {

	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}

	public void run() {
		Random random = new Random();
		int randomHtml = random.nextInt(6);
		while (true) {
			lock.lock();
			try {
				if (done) {
					System.out.println(Thread.currentThread().getName() + " over");
					break;
				}
			} finally {
				lock.unlock();
			}
			java.nio.file.Path p = null;
			switch (randomHtml) {
			case 0:
				p = java.nio.file.Paths.get("a.html");
				break;
			case 1:
				p = java.nio.file.Paths.get("b.html");
				break;
			case 2:
				p = java.nio.file.Paths.get("b.html");
				break;
			case 3:
				p = java.nio.file.Paths.get("b.html");
				break;
			case 4:
				p = java.nio.file.Paths.get("b.html");
				break;
			case 5:
				p = java.nio.file.Paths.get("b.html");
				break;
			case 6:
				p = java.nio.file.Paths.get("b.html");
				break;

			default:
			}

			AccessCounter.getInstance().increment(p);
			AccessCounter.getInstance().getCount(p);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				System.out.println(e);
				continue;
			}
		}
	}

	public static void main(String[] args) {

		RequestHandler rh1 = new RequestHandler();
		RequestHandler rh2 = new RequestHandler();
		RequestHandler rh3 = new RequestHandler();
		RequestHandler rh4 = new RequestHandler();
		

		Thread t1 = new Thread(rh1);
		Thread t2 = new Thread(rh2);
		Thread t3 = new Thread(rh3);
		Thread t4 = new Thread(rh4);
		

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		rh1.setDone();
		rh2.setDone();
		rh3.setDone();
		rh4.setDone();
		

		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		t4.interrupt();
		

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}