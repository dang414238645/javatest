package demo.dataSource.apache;

import org.apache.commons.dbcp.BasicDataSource;

public class Test {
	
	public static void main(String args[]){
		BasicDataSource ds = new BasicDataSource();
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
	    ds.setUrl("jdbc:mysql://localhost:3306/cheng");
	    ds.setUsername("root");
	    ds.setPassword("root");
	    ds.setInitialSize(50);
	    ds.setMaxActive(100);
	    ds.setMaxIdle(30);
	    ds.setMaxWait(10000);
	}

}
