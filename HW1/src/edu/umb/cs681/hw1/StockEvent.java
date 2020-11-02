package edu.umb.cs681.hw1;

public class StockEvent {
	private String ticker;
	private float quote;
	
	public StockEvent() {
		
	}
	
	public StockEvent(String T, float Q) {
		this.ticker = T;
		this.quote = Q;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public float getQuote() {
		return quote;
	}
}
