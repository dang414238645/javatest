package demo.hbase;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;

public class HbaseTest {
	public static void main(String[] args) {
	       ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans-hbase.xml");
	       HbaseTemplate htemplate=(HbaseTemplate) context.getBean("htemplate");
	       //	       环境变量配置 HADOOP_HOME 下面为临时解决方法  hadoop2.2window上需要文件 d://nfs/bin/winutils.exe
	       System.setProperty("hadoop.home.dir", "d://nfs");
	       Object tObject=htemplate.get("stl_bu", "1-1ZC-3635", new RowMapper<String>(){
			public String mapRow(Result result, int arg1) throws Exception {
				String string="";
				for(KeyValue kv :result.raw()){
                    String key = new String(kv.getQualifier());
                    String value = new String(kv.getValue());
                    Map<String ,String> map=new HashMap<String ,String>();
                    string+=value;
                    System.out.println(key +"= "+Bytes.toString(key.getBytes()));
                    System.out.println(value +"= "+Bytes.toString(value.getBytes()));
                }
                return string;
			}
	       });
	       System.out.println(tObject);

	}
}
