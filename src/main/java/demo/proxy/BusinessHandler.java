package demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BusinessHandler implements InvocationHandler {
	
	private Object object = null;
	 
	public BusinessHandler(Object object) {
	   this.object = object;
	}
	 
	public Object invoke(Object proxy, Method method, Object[] args)
	    throws Throwable {
	   System.out.println("do something before method");
	   Object ret = method.invoke(this.object, args);
	   System.out.println("do something after method");
	   return ret;
	 
	}

}
