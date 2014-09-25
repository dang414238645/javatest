package test;

public class IntegerTest {

	public static void main(String[] args) {
		Integer a=new Integer(1);
		Integer b=new Integer(1);
		//拆箱时调用intValue方法
		int c=new Integer(1);
		int d=new Integer(1);
		int e=129;
		int f=129;
		//装箱时调用valueOf方法
		Integer g=129;
		Integer h=129;
		double i=100000000l;
		double j=100000000l;
		System.out.println(a==b);
		System.out.println(c==d);
		System.out.println(e==f);
		System.out.println(i==j);
	}
}
