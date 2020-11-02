package edu.umb.cs681.hw1;

@FunctionalInterface
public interface Observer {
	public abstract void update(Observable obs,Object arg);

}
