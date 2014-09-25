package test;

import java.util.HashMap;
import java.util.Map;

/**
 * map数据结构：数组加链表：链表在这里是解决hash冲突的一种方法
 * @author changpengfei
 *
 */
public class MapTest {
	
	String key;
	Object value;
	
	public static void main(String[] args) {
		MapTest[] test=new MapTest[10];
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("a", 10);
		System.out.println(map.get("a"));
	}

}
