package test.extend;

public abstract class Person {
	
	public String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person(String aName){
		name=aName;
	}
	
	public abstract String getDescription();

	public void sayHi(String string) {
		System.out.print(string);
	}
	
	
}
