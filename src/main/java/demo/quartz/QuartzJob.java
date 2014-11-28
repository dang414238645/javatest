package demo.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * @Description: 任务执行类
 * StatefulJob 实现这个接口为串行
 * Job 实现这个接口为并行
 */
public class QuartzJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ arg0.getJobDetail().getName()+"★★★★★★★★★★★");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
