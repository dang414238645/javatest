package demo.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class Test {
	public static void main(String args[]) throws SchedulerException{
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		// 任务明细对象 设置名字 任务组等
		JobDetail jobDetail = new JobDetail();
		// 任务触发对象 间隔时间单位(分钟) 需要设置时间等触发参数
		SimpleTrigger trigger = new SimpleTrigger();
		// 添加到scheduler容器等待运行
		scheduler.scheduleJob(jobDetail, trigger);
		
	}
}
