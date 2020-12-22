package edu.umb.cs681.hw18;


import java.util.Random;

public class MainMultiRunnable {
	
	public static void main(String[] args) {
		DJIAQuoteObservable djia = new DJIAQuoteObservable();
		StockQuoteObservable stock = new StockQuoteObservable();
		
		String quote1 = "QWERTY";
    	Float val1 = 8000.0f;
    	String code = "KLM";
        Float val2 = 300.0f;
        
        Random random = new Random();
        
        System.out.println(" DJIAQuoteObservable below");
        djia.addObserver((Observable o, Object obj) -> {
            Float f = ((DJIAEvent) obj).getDjia();
            System.out.println("---> Observer 1/DIJA event: " + f);
        });
        
        djia.addObserver((Observable o, Object obj) -> {
            Float f = ((DJIAEvent) obj).getDjia();
            System.out.println("---> Observer 2/DIJA event: " + f);
        });
        
        System.out.println("---> Observers current Count: " + djia.countObserver());

		System.out.println("---> Adding new DJIAQouote: " + quote1);
		djia.setQuote(val1);

		val1 = 6000.0f;
		System.out.println("---> QWERTY is changed here");
		djia.changeQuote(val1);
		System.out.println("-----------------------------------------------------");
		
		System.out.println(" StockQuoteObservable below");
		stock.addObserver((Observable o, Object obj) -> {
			String ticker = ((StockEvent) obj).getTicker();
			Float quote = ((StockEvent) obj).getQuote();
			System.out.println("---> Observer 1/Stock event: " + ticker + " -- " + quote);
		});

		stock.addObserver((Observable o, Object obj) -> {
			String ticker = ((StockEvent) obj).getTicker();
			Float quote = ((StockEvent) obj).getQuote();
			System.out.println("---> Observer 2/Stock event: " + ticker + " -- " + quote);
		});
		
		System.out.println("---> Observers current Count: " + stock.countObserver());

		System.out.println("---> Adding new Stock: " + code);
		stock.setQuote(code, val2);

		val2 = 3000.0f;
		System.out.println("---> KLM changed here");
		stock.changeQuote(code, val2);
		
		System.out.println("-----------------------------------------------------");
		System.out.println(" MultiThread below");
		
		Thread t1 = new Thread(() -> {
			djia.setQuote(random.nextFloat() * 100f + 14000f);
			djia.notifyObservers(new DJIAEvent(random.nextFloat() * 1000f + 14000f));
		});
		Thread t2 = new Thread(() -> {
			djia.setQuote(random.nextFloat() * 100f + 14000f);
			djia.notifyObservers(new DJIAEvent(random.nextFloat() * 1000f + 14000f));
		});
		
		t1.start();
		t2.start();
        
		Thread ts1 = new Thread(() -> {
			stock.setQuote("KLM", random.nextFloat() * 100f + 300f);
			stock.notifyObservers(new StockEvent("SQO", random.nextFloat() * 100f + 300f));
		});
		Thread ts2 = new Thread(() -> {
			stock.setQuote("KLM", random.nextFloat() * 100f + 300f);
			stock.notifyObservers(new StockEvent("SQO", random.nextFloat() * 100f + 300f));
		});
		
		ts1.start();
		ts2.start();
		
		try {
			t1.join();
			t2.join();
			

			ts1.join();
			ts2.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
    	
	}

}
