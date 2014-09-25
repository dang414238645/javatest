package test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;

import test.extend.Employee;
import test.extend.Manager;
import test.extend.Person;





public class Test<T> {
	public static void main(String [] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
//		System.out.println("Hello , World!");
		Manager boss=new Manager("Carl Cracher",80000,1987,12,15,10000);
		Employee[] staff=new Employee[3];
		staff[0] =boss;
		staff[1]=new Employee("Harry Hacker",80000,1987,12,15);
		staff[2]=new Employee("tony Tester",80000,1987,12,15);
		for(Employee e:staff)
			System.out.println(e.getName()+" "+e.getSalary());
//		Manager ttManager=(Manager)staff[1];
//		有继承关系的返回true
		System.out.println(staff[1] instanceof Manager);
//		有继承关系的返回false
		System.out.println(staff[1].getClass() == Employee.class);
//		抽象类不可以实例化
		Person person=new Person("haha") {
			
			@Override
			public String getDescription() {
				// this指向person的子类
				return this.getName();
			}
		};
		
		System.out.println(person.getDescription());
		
		
		
		/**
		 * hashcode
		 */
		String s ="OK";
		StringBuffer sb	=new StringBuffer(s);
		System.out.println(s.hashCode()+" "+sb.hashCode());
		String t =new String("OK");
		StringBuffer tb =new StringBuffer(t);
		System.out.println(t.hashCode()+" "+tb.hashCode());
		
		/**
		 * 泛型
		 */
		ArrayList list=new ArrayList();
		
		
		/**
		 * 反射
		 */
//		Employee employee=new Employee();
//		employee.setName("tt");
//		System.out.print(Employee.class.getDeclaredField("name").get(employee));
		Map<String,Object> map=new HashMap<String, Object>();
		ObjectMapper mapper=new ObjectMapper();
		mapper.convertValue(boss, map.getClass());
		BeanUtils.populate(boss, map);
	}

}
