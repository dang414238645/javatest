package demo.proxy;

import java.lang.reflect.Proxy;

public class Client {
	public static void main(String[] args) {
		   // 元对象(被代理对象)
		   ManagerImpl managerImpl = new ManagerImpl();
		 
		   // 业务代理类
		   BusinessHandler securityHandler = new BusinessHandler(managerImpl);
		 
		   // 获得代理类($Proxy0 extends Proxy implements Manager)的实例.
		   Manager managerProxy = (Manager) Proxy.newProxyInstance(managerImpl
		     .getClass().getClassLoader(), managerImpl.getClass()
		     .getInterfaces(), securityHandler);
		 
		   managerProxy.modify();
		}
}
