package demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


interface Inter{
	public void doSomething(String str);
}
class RealObject implements Inter{

	public void doSomething(String str) {
		System.out.println("dosomething "+str);
	}
	
}
class SelectorMethod implements InvocationHandler{
	public Object object;
	
	public SelectorMethod(Object object) {
		this.object = object;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before");
		//尼玛，下边的object必须指定
		method.invoke(this.object, args);
		
		System.out.println("after");
		return null;
	}
	
}
public class MyTest {

	public static void main(String args[]){
		RealObject realObject=new RealObject();
		Inter inter=(Inter)Proxy.newProxyInstance(realObject.getClass().getClassLoader(), 
				realObject.getClass().getInterfaces(), 
				new SelectorMethod(realObject));
		inter.doSomething("nimei");
		}
}
