package test;

import java.util.Set;
import java.util.TreeSet;

public class TestTreeSet {

	public static void main(String[] args) {
		Set<Object> set=new TreeSet<Object>();
		set.add(10);
		set.add(8);
		set.add(9);
		set.add(5);
		set.add(11);
		set.contains(5);
		set.contains(12);
		set.remove(5);
		System.out.println(set.size());

	}

}
