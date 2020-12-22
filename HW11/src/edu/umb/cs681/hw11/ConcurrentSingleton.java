package edu.umb.cs681.hw11;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {
	private ConcurrentSingleton() {
		System.out.println("ConcurrentSingleton enter");
	};
	
	private static AtomicReference<ConcurrentSingleton> instance = 
			new AtomicReference<>(null);
	
	public static ConcurrentSingleton getInstance() {
		ConcurrentSingleton cs = new ConcurrentSingleton();
		if (instance.get() == null) {
			instance.set(cs);
//			System.out.println("instance of "+ instance);
		}
		return instance.get();
	}

}
