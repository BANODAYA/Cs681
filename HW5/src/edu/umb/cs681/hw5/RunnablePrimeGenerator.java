package edu.umb.cs681.hw5;

import java.util.ArrayList;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable{
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void run() {
		generatePrimes();
	}
	
	public static void main(String[] args) {
		//single thread
		System.out.println("Running with single thread");
		RunnablePrimeGenerator generator = new RunnablePrimeGenerator(1L, 2000000L);
		Thread thread = new Thread(generator);
		
		
		try {
			thread.start();
			thread.join();
		} catch (InterruptedException e) {
		}

		generator.getPrimes().forEach((Long p) -> System.out.print(p + ", "));
		ArrayList<Long> primeList = new ArrayList<> ();
		generator.getPrimes().forEach(x -> primeList.add(x));
		System.out.println("\nTotal number of primes in the list is = " + primeList.size());
		
		System.out.println("---------------------------------------------------");
		
		//Double thread
		System.out.println("Running with Double thread");
		RunnablePrimeGenerator generator1 = new RunnablePrimeGenerator(1L, 1000000L);
		RunnablePrimeGenerator generator2 = new RunnablePrimeGenerator(1000000L, 2000000L);
		Thread thread1 = new Thread(generator1);
		Thread thread2 = new Thread(generator2);

		
		
		try {
			thread1.start();
			thread2.start();
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
		}

		

		ArrayList<Long> primeList1 = new ArrayList<> ();
		generator.getPrimes().forEach(x -> primeList1.add(x));
		System.out.println("\nTotal number of primes in the list is = " + primeList1.size());
		
		
		
	}
	

}
