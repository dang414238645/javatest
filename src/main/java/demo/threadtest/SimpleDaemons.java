package demo.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SimpleDaemons implements Runnable{

	public static void main(String[] args) throws InterruptedException {
		final Thread thread=new Thread(new SimpleDaemons());
		ExecutorService eService=Executors.newCachedThreadPool();
		eService.execute(thread);
		thread.join(5000);
		Future<?> tt=eService.submit(new Runnable() {
			@Override
			public void run() {
				while (true) {
					thread.interrupt();
//					thread.stop();
					System.out.println("---------------------interrupt---------------------");
				}
			}
		});
		eService.shutdown();
	}

	public void run() {
		while (true) {
			System.out.println("nimei");
		}
	}

}
