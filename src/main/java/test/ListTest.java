package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		String[]strings={"",""};
		List<String> list=new ArrayList<String>();
		list.add("hah");//第一次添加元素的时候，如果数组没初始化，会初始化数组，默认是十个
		list.add("nimei");
		list.add("mei");
		list.add("i");
		list.remove("nimei");
//		System.out.println(list.get(0));
		
		List<String> list2=new LinkedList<String>();
		list2.add("nimei");
		list2.add("mei");
		list2.add("i");
		list2.remove(1);
		System.out.println(list2.get(0));
	}

}
