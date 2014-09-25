package test;

public class TestType {

	public static void main(String[] args) {
		"12".getBytes();
		System.out.println(Integer.valueOf(new Byte("12".getBytes()[0]).toString(), 16));
		System.out.println(Integer.valueOf(new Byte("12".getBytes()[1]).toString(), 16));
		System.out.println("1".getBytes());
		System.out.println("12".getBytes());
		System.out.println(Integer.parseInt("00",16));
		System.out.println((char)97);
	}
}
