package edu.umb.cs681.hw11;

public class ConcurrentSingletonRunnable implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(ConcurrentSingleton.getInstance());
		System.out.println(ConcurrentSingleton.getInstance());
		
	}
	
	public static void main(String[] args) {
		new Thread(new ConcurrentSingletonRunnable()).start(); 
		
		
	}
	
	

}
