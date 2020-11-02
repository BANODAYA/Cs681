package edu.umb.cs681.hw1;
//import java.util.Observable;

public class MyExampleObservable extends Observable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DJIAQuoteObservable DQ = new DJIAQuoteObservable();
		StockQuoteObservable SQ = new StockQuoteObservable();
		
		
		DQ.addObserver((Observable o, Object obj) -> {
			System.out.println("DJIA Observer has been added and notified");
		});
		DQ.changeQuote("QWERTY", 211);
		DQ.addObserver((Observable o, Object obj) -> {
			System.out.println("DJIA Observer has been added and notified");
		});
		DQ.changeQuote("ASDFGH", 99);
		
		
		
		SQ.addObserver((Observable o, Object obj) -> {
			System.out.println("STOCK Observer has been added and notified");
		});
		SQ.changeQuote("This", 50);
		SQ.addObserver((Observable o, Object obj) -> {
			System.out.println("STOCK Observer has been added and notified");
		});
		SQ.changeQuote("That", 100);

	

	}

}
