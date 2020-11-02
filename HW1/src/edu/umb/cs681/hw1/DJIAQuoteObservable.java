package edu.umb.cs681.hw1;

public class DJIAQuoteObservable extends Observable{
	private float quoteObs;
	
	public void changeQuote(String t,float q) {
        this.quoteObs = q;
        setChanged();
        notifyObservers(new DJIAEvent(q));
    }

}
