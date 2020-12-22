package edu.umb.cs681.hw17;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;



public class AccessCounter {

	ConcurrentHashMap <java.nio.file.Path, AtomicInteger> hashMap = new ConcurrentHashMap <java.nio.file.Path, AtomicInteger>();
	private static ReentrantLock Staticlock = new ReentrantLock();
	private static AccessCounter instance = null;
	
	private AccessCounter() {};

	public static AccessCounter getInstance() {
		Staticlock.lock();
		try {
			if (instance == null) {
				instance = new AccessCounter();
			}
			return instance;
		} finally {
			Staticlock.unlock();
		}
	}

	public void increment(Path p) {
		hashMap.compute(p, (java.nio.file.Path k, AtomicInteger v) -> {
			if(v == null) {
				System.out.println(Thread.currentThread().getName() + "---> Increment : "+ p + " -- "  + 1);
				return new AtomicInteger(1);
			} else {
				System.out.println(Thread.currentThread().getName() + "---> Increment : " + p + " -- " + (v.get()+1));
				return new AtomicInteger(v.incrementAndGet());
			}
		});
	}

	public int getCount(Path p) {
		return hashMap.compute(p, (java.nio.file.Path k, AtomicInteger v) -> {
			if(v == null) {
				System.out.println(Thread.currentThread().getName() + "--> " + p + " count " + 0);
				return new AtomicInteger(0);
			} else {
				System.out.println(Thread.currentThread().getName() + "--> " + p + " count " + v.get());
				return v;
			}
		}).get();
		
	}
}
