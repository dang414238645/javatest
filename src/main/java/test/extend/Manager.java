package test.extend;

public class Manager extends Employee {
	
	private double bonus;
	
	

	public Manager() {
		super();
	}

	public Manager(String name, int year, int month, int day, double salary,double bonus) {
		//默认先调用父类的构造方法，如果试图改变这种顺序会编译不通过
		super(name, year, month, day, salary);
		this.bonus=bonus;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * 重写父类中的方法，并调用父类中的方法
	 */
	public double getSalary(){
		double baseSalary=super.getSalary();
		return baseSalary+bonus;
	}
	
}
