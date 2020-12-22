package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;



public class AccessCounter {

	public Map<java.nio.file.Path, Integer> hashMap = new HashMap<java.nio.file.Path, Integer>();
	public ReentrantLock nonStaticLock = new ReentrantLock();
	public static ReentrantLock Staticlock = new ReentrantLock();
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
		nonStaticLock.lock();
		try {
			if (hashMap.get(p) != null) {
				int c = hashMap.get(p);
				hashMap.put(p, c + 1);
			} else {
				hashMap.put(p, 1);
			}
		} finally {
			nonStaticLock.unlock();
		}
	}

	public int getCount(Path p) {
		nonStaticLock.lock();
		int score = 0;
		try {
			if (hashMap.get(p) != null) {
				return hashMap.get(p);
			}else {
				return score;
			}
		} finally {
			nonStaticLock.unlock();
		}
		
	}
}
