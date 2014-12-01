package demo.threadtest.t21_3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable{
	private IntGenerator intGenerator;
	private final int id;	
	
	public EvenChecker(IntGenerator intGenerator, int id) {
		super();
		this.intGenerator = intGenerator;
		this.id = id;
	}

	public void run() {
		while (!intGenerator.isCanceled()) {
			int val=intGenerator.nextLock();
			if(val%2!=0){
				System.out.println(val+" is not even!");
				intGenerator.cancel();
			}
		}
	}
	
	public static void test(IntGenerator gp, int count){
		System.out.println("press ctrl+c to exit");
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<count;i++){
			exec.execute(new EvenChecker(gp, count));
		}
		exec.shutdown();
	}
	
	public static void test(IntGenerator gp){
		test(gp, 50);
	}

}
