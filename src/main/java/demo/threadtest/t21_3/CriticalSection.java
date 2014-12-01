package demo.threadtest.t21_3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.hslf.model.textproperties.ParagraphFlagsTextProp;



class Pair{
	private int x,y;
	
	public Pair() {
		this(0,0);
	}
	
	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void incrementX(){
		x++;
	}
	
	public void incrementY(){
		y++;
	}

	@Override
	public String toString() {
		return "Pair [x=" + x + ", y=" + y + "]";
	}
	
	
	

public class PairValuesNotEqualException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PairValuesNotEqualException() {
		super("Pair values not equal: " +Pair.this);
	}
	
}
public void checkState(){
	if(x!=y){
		throw new PairValuesNotEqualException();
	}
}
}


abstract class PairManager{
	AtomicInteger checkCounter=new AtomicInteger(0);
	protected Pair p=new Pair();
	private List<Pair> storage= Collections.synchronizedList(new ArrayList<Pair>());
	public synchronized Pair getPair(){
		return new Pair(p.getX(),p.getY());
	}
	protected void store(Pair p){
		storage.add(p);
		try {
			TimeUnit.MICROSECONDS.sleep(50);
		} catch (InterruptedException e) {}
	}
	public abstract void increment();
	
}


class PairManager1 extends PairManager{

	@Override
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
	
}


class PairManager2 extends PairManager{

	@Override
	public void increment() {
		Pair temp;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			temp=getPair();
		}
		store(temp);
	}
	
}

class PairManipulator implements Runnable{

	private PairManager pm;
	
	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}

	public void run() {
		while(true){
			pm.increment();
		}
	}

	@Override
	public String toString() {
		return "PairManipulator [pm=" + pm.getPair() + ";checkCounter="+pm.checkCounter.get()+"]";
	}
}
class PairChecker implements Runnable{

	private PairManager pm;
	
	public PairChecker(PairManager pm) {
		this.pm = pm;
	}

	public void run() {
		while(true){
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState();
		}
	}

	@Override
	public String toString() {
		return "PairManipulator [pm=" + pm.getPair() + ";checkCounter="+pm.checkCounter.get()+"]";
	}
}
public class CriticalSection {
	static void testApproaches(PairManager p1,PairManager p2){
		ExecutorService exec=Executors.newCachedThreadPool();
		PairManipulator pm1=new PairManipulator(p1),
				pm2=new PairManipulator(p2);
		PairChecker pcheck1=new PairChecker(p1),
				pcheck2=new PairChecker(p2);
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pcheck1);
		exec.execute(pcheck2);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("pm1: " +pm1+"\npm2: "+pm2);
		}
		System.out.println("sleep interrupted");
		System.exit(0);
	}
	public static void main(String args[]){
		PairManager pman1=new PairManager1();
		PairManager pman2=new PairManager2();
		testApproaches(pman1, pman2);
	}
}
