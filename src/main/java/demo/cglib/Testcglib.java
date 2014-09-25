package demo.cglib;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


public class Testcglib {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProxy1() throws Exception {

	       final Person p1 = new Person();    

	       Enhancer en = new Enhancer();
	       en.setSuperclass(Person.class);       

	       en.setCallback(new MethodInterceptor() {

			public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.err.println("Before...");
				Object o = method.invoke(p1,args);
				System.err.println("After...");
				return o;
			}

	       });

	       Person p = (Person) en.create();   //通过create方法返回Person类的代理

	       System.err.println(p.getClass());//被代理的对象

	       p.sayHi("Hello");

	    }
	
	
	@Test

    public void testProxy2() throws Exception {

       final Dog dog = new Dog();             //声明被代理对象

       Enhancer en = new Enhancer();          //声明CGLIB增强类

       en.setSuperclass(IAnimal.class);       //设置接口类，也可以设置成dog实现类，会影响create返回的对象

       en.setCallback(new MethodInterceptor() {

           public Object intercept(Object target, Method method,

                  Object[] args, MethodProxy proxy) throws Throwable {

              System.err.println("Before...");

              Object o = method.invoke(dog, args);

              System.err.println("After...");

              return o;

           }

       });

       //Dog dog2 = (Dog) en.create();//必须转型为接口,否则抛出ClassCastException

       IAnimal dog2 = (IAnimal)en.create();

       dog2.eat();

    }

}
