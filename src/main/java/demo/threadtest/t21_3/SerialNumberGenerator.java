package demo.threadtest.t21_3;

public class SerialNumberGenerator {
	private static volatile int serialNumber=0;
	public static int nextSerialNumber() {
		return serialNumber++;
	}

}
