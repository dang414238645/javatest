package demo.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.crypto.Data;

import org.quartz.SimpleTrigger;

import sun.util.calendar.CalendarDate;

/**
 * @Description: 测试类
 *
 * @ClassName: QuartzTest
 * @Copyright: Copyright (c) 2014
 *
 */
public class QuartzTest {
	public static void main(String[] args) {
		try {
			String job_nameb = "并行job";
			System.out.println("【系统启动】开始(每1秒输出一次)...");  
			//并行执行job
			QuartzManager.addJob(job_nameb, QuartzJob.class, "0/1 * * * * ?");  
			String job_namec = "串行job";
			System.out.println("【系统启动】开始(每1秒输出一次)...");  
			//串行执行job
			QuartzManager.addJob(job_namec, QuartzCJob.class, "0/1 * * * * ?");  
			
			
			
			
			
			String job_name = "动态任务调度";
			Thread.sleep(5000);  
			System.out.println("【修改时间】开始(每2秒输出一次)...");  
			QuartzManager.modifyJobTime(job_name, "10/2 * * * * ?");  
			Thread.sleep(6000);  
			System.out.println("【移除定时】开始...");  
			QuartzManager.removeJob(job_name);  
			System.out.println("【移除定时】成功");  
			
			System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");  
			QuartzManager.addJob(job_name, QuartzJob.class, "*/10 * * * * ?");  
			Thread.sleep(60000);  
			System.out.println("【移除定时】开始...");  
			QuartzManager.removeJob(job_name);  
			System.out.println("【移除定时】成功");

			//20秒后执行
//			String job_name = "动态任务调度";
//			Date date=new Date();
//			Calendar calendar=Calendar.getInstance();
//			calendar.setTimeInMillis(date.getTime()+20000);
//			String cronExpress =calendar.get(Calendar.SECOND)+" "+calendar.get(Calendar.MINUTE)+" "+calendar.get(Calendar.HOUR_OF_DAY)
//					+" " +calendar.get(Calendar.DAY_OF_MONTH)+" "+(calendar.get(Calendar.MONTH)+1)+" "+"?"+" "+calendar.get(Calendar.YEAR);
//			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
//			
//			QuartzManager.addJob(job_name, QuartzJob.class, cronExpress);
//			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
