package demo.cglib;

public class Person {
	public String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public void sayHi(String string) {
		System.out.print(string);
	}
}
