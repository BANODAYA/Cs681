package edu.umb.cs681.hw1;

import java.util.HashMap;
import java.util.Map;


public class StockQuoteObservable  extends Observable{
	  private Map<String, Float> mappings = new HashMap<String, Float>();

	void changeQuote(String T, float Q) {
		mappings.put(T, Q);
		setChanged();
		notifyObservers(new StockEvent(T, Q));
	}
}
