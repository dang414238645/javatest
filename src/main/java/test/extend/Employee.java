package test.extend;

public class Employee {
	
	private String name;
	
	private int year;
	
	private int month;
	
	private int day;
	
	public Employee() {
		super();
	}

	public Employee(String name, int year, int month, int day, double salary) {
		super();
		this.name = name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	private double salary;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
