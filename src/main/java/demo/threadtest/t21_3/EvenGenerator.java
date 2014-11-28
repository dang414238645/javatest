package demo.threadtest.t21_3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvenGenerator extends IntGenerator {
	private int currentEventValue=0;

	@Override
	public synchronized int next() {
		++currentEventValue;//danger point here!
		Thread.yield();
		++currentEventValue;
		return currentEventValue;
	}
	
	private Lock lock=new ReentrantLock();
	public int nextLock() {
		try {
			lock.lock();
			++currentEventValue;//danger point here!
			Thread.yield();
			++currentEventValue;
			return currentEventValue;
		} finally{
			lock.unlock();
		}
	}
	
	public static void main(String args[]){
		EvenChecker.test(new EvenGenerator());
	}

}
