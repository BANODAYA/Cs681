package edu.umb.cs681.hw12;

public class MainRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Customer c = new Customer(new Address("6 cypress lane", "Nashua", "NH", 03063));
		System.out.println("Current Address: " + c.getAddress());
		c.setAddress(c.getAddress().change("7 Mass ave", "Boston", "MA", 02123));
		System.out.println(" New Address: "+ c.getAddress());
		
		c.setAddress(new Address("124 Summer St", "Boston", "MA", 02164));
        System.out.println(" New Address again changed to "+ c.getAddress());
		
	}
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new MainRunnable());
		
		
		thread1.start();

		
		try {
			thread1.join();

		}catch (InterruptedException err) {
			err.printStackTrace();
		}
		
		
		
		
	}

}
