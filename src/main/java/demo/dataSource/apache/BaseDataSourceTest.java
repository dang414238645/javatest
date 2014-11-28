package demo.dataSource.apache;

import static org.junit.Assert.*;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

public class BaseDataSourceTest {
	BasicDataSource ds=null;
	@Before
	public void setUp() throws Exception {
		ds = new BasicDataSource();
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
	    ds.setUrl("jdbc:mysql://172.17.16.65:3306/um_dev?characterEncoding=utf-8&&zeroDateTimeBehavior=convertToNull");
	    ds.setUsername("um_dev");
	    ds.setPassword("um_dev");
	    ds.setInitialSize(50);
	    ds.setMaxActive(100);
	    ds.setMaxIdle(30);
	    ds.setMaxWait(10000);
	}

	@Test
	public void test() {
		
	}

}
