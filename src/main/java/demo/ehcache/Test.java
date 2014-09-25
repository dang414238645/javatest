package demo.ehcache;

import static org.junit.Assert.*;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Test {

	public static void main(String args[]){
		
		//会自动读取classpath下面的ehcache。xml文件
		Cache cache = CacheManager.getInstance().getCache("resetPasswordCountCache");
		cache.put(new Element("key", 1));
		cache.put(new Element("key", 1+1));
		Element element = cache.get("key");
		System.out.println(element.getValue());
		Cache cache1 = CacheManager.getInstance().getCache("oaTemplateCache");
		cache1.put(new Element("key", 1000));
		cache.removeAll();
		Element element1 = cache1.get("key");
		System.out.println(element1.getValue());
		element = cache.get("key");
//		System.out.println(element.getValue());
		
		
		
	}
}
