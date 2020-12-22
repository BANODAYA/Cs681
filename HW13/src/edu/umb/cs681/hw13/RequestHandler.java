package edu.umb.cs681.hw13;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
	
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	
	public void setDone() {
		lock.lock();
		try {
			done = true;
		}
		finally {
			lock.unlock();
		}
	}

	public void run() {
		
		String[] thread = {"AccessCounter.class", "RequestHandler.class",  "a.html",  "b.html", "c.html", "d.html", "e.html", "f.html"};
		AccessCounter ac = AccessCounter.getInstance();
		
		while (true) {			
			lock.lock();
			try {
				if(done) {
	    			System.out.println("Thread is Terminated");
	    			break;
	    		}
				
				Random random = new Random();
				
				int num = random.nextInt(thread.length);
				Path path = FileSystems.getDefault().getPath("..", thread[num]);				
				
				ac.increment(path);
				System.out.println(thread[num] + " -----------> " + ac.getCount(path));
			}
			finally {
				lock.unlock();
			}
			
			try {
				Thread.sleep(300);
			}
			catch(InterruptedException e) {
				System.out.println(e);
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		
		RequestHandler rh1  = new RequestHandler();
		RequestHandler rh2  = new RequestHandler();
		RequestHandler rh3  = new RequestHandler();
		RequestHandler rh4  = new RequestHandler();
		RequestHandler rh5  = new RequestHandler();
		RequestHandler rh6  = new RequestHandler();
		RequestHandler rh7  = new RequestHandler();
		RequestHandler rh8  = new RequestHandler();
		RequestHandler rh9  = new RequestHandler();		
		RequestHandler rh10 = new RequestHandler();
		RequestHandler rh11= new RequestHandler();
		RequestHandler rh12= new RequestHandler();
		RequestHandler rh13= new RequestHandler();
		RequestHandler rh14 = new RequestHandler();
		RequestHandler rh15 = new RequestHandler();
		RequestHandler rh16= new RequestHandler();
		RequestHandler rh17= new RequestHandler();
		RequestHandler rh18 = new RequestHandler();
		RequestHandler rh19 = new RequestHandler();
		RequestHandler rh20 = new RequestHandler();
		
		Thread thread1  = new Thread(rh1);
		Thread thread2  = new Thread(rh2);
		Thread thread3  = new Thread(rh3);
		Thread thread4  = new Thread(rh4);
		Thread thread5  = new Thread(rh5);
		Thread thread6  = new Thread(rh6);
		Thread thread7  = new Thread(rh7);
		Thread thread8  = new Thread(rh8);
		Thread thread9  = new Thread(rh9);
		Thread thread10 = new Thread(rh10);
		Thread thread11 = new Thread(rh11);
		Thread thread12 = new Thread(rh12);
		Thread thread13 = new Thread(rh13);
		Thread thread14 = new Thread(rh14);
		Thread thread15 = new Thread(rh15);
		Thread thread16 = new Thread(rh16);
		Thread thread17 = new Thread(rh17);
		Thread thread18 = new Thread(rh18);
		Thread thread19 = new Thread(rh19);
		Thread thread20 = new Thread(rh20);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();
		thread11.start();
		thread12.start();
		thread13.start();
		thread14.start();
		thread15.start();
		thread16.start();
		thread17.start();
		thread18.start();
		thread19.start();
		thread20.start();
		
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		rh1.setDone();
		rh2.setDone();
		rh3.setDone();
		rh4.setDone();
		rh5.setDone();
		rh6.setDone();
		rh7.setDone();
		rh8.setDone();
		rh9.setDone();
		rh10.setDone();
		rh11.setDone();
		rh12.setDone();
		rh13.setDone();
		rh14.setDone();
		rh15.setDone();
		rh16.setDone();
		rh17.setDone();
		rh18.setDone();
		rh19.setDone();
		rh20.setDone();
		
		thread1.interrupt();
		thread2.interrupt();
		thread3.interrupt();
		thread4.interrupt();
		thread5.interrupt();
		thread6.interrupt();
		thread7.interrupt();
		thread8.interrupt();
		thread9.interrupt();
		thread10.interrupt();
		thread11.interrupt();
		thread12.interrupt();
		thread13.interrupt();
		thread14.interrupt();
		thread15.interrupt();
		thread16.interrupt();
		thread17.interrupt();
		thread18.interrupt();
		thread19.interrupt();
		thread20.interrupt();
		
		
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
			thread6.join();
			thread7.join();
			thread8.join();
			thread9.join();
			thread10.join();
			thread11.join();
			thread12.join();
			thread13.join();
			thread14.join();
			thread15.join();
			thread16.join();
			thread17.join();
			thread18.join();
			thread19.join();
			thread20.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}   		
	}	
}
