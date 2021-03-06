package demo.threadtest.t21_4;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class SleepBlocked implements Runnable{

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("interrupt");
		}
		System.out.println("Exiting SleepBlocked.run()");
	}
	
}

class IOBlocked implements Runnable{
	private InputStream in;
	
	public IOBlocked(InputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		System.out.println("waiting for read()");
		try {
			in.read();
		} catch (IOException e) {
			if(Thread.currentThread().isInterrupted())
				System.out.println("interrupted form blocked I/O");
			else {
				throw new RuntimeException(e);
			}
		}
		System.out.println("Exiting IOBlocked.run()");
	}
	
}

class SynchronizedBlocked implements Runnable{

	@Override
	public void run() {
		System.out.println("trying to call f()");
		f();
		System.out.println("Exiting SynchronizedBlocked.run()");
	}

	private synchronized void f() {
		while(true)
			Thread.yield();
	}

	public SynchronizedBlocked() {
		new Thread(){
			public void run() {
				f();
			}
		}.start();;
	}
	
}


public class Interrupting {
	private static ExecutorService exec=Executors.newCachedThreadPool();
	static void test(Runnable r) throws InterruptedException{
		Future<?> f=exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("interruption "+r.getClass().getName());
		f.cancel(true);
		System.out.println("interrupt sent to "+r.getClass().getName());
	}
	public static void main(String args[]) throws InterruptedException{
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0);
	}

}
