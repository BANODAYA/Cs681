package edu.umb.cs681.hw1;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable {

	private List<Observer> observers = new LinkedList<Observer>();
	private boolean changes = false;
	
	public void addObserver(Observer o) {
		if (o==null) {
			System.out.println("can't add null observer");
		}
		observers.add(o);
	}
	public void deleteObserver(Observer o) {
		observers.remove(o);
	}
	
	protected void setChanged() {
        changes = true;
    }
	
	protected boolean hasChanged() {
        return changes;
    }
    
    protected void clearChanged() {
        changes = false;
    }
    public void notifyObservers() {
		notifyObservers(null);
	}
    
    public void notifyObservers(Object args) {
		
        if (hasChanged()) {
        	observers.forEach((Observer observer) -> observer.update(this, args));
            clearChanged();
        }
	}
    
    
	
}

