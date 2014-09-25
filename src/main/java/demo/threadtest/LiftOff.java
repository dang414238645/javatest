package demo.threadtest;

public class LiftOff implements Runnable {
	
	protected int countDown=10;
	private static int taskCount=0;
	private final int id =taskCount++;
	

	public LiftOff() {
		super();
	}


	public LiftOff(int countDown) {
		super();
		this.countDown = countDown;
	}
	
	public String status(){
		return "#"+id+"("+countDown+"------"+(countDown>0?"countDown":"LiftOff")+")";
	}

	public void run() {
		while(countDown-->0){
			System.out.println(status());
			Thread.yield();
		}
		
		while(true){
			System.out.println("--------死循环-------");
			Thread.yield();
		}
		
	}

}
