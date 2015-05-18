package com.quartz.example4;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b> </b>
 * <p>
 * 功能:
 * </p>
 * 
 * @作者 张涛
 * @创建日期 2013-12-24
 * @项目名称 quartzDemo
 * @JAVA路径 com.quartz.example1.SimpleExample
 */
public class JobStateExample {
	public static void run() throws Exception {
		Logger log = LoggerFactory.getLogger(JobStateExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sche = sf.getScheduler();

		Date runtime = DateBuilder.nextGivenSecondDate(null, 10);
///////////////////////////////////////////////////////////////////
		JobDetail job = JobBuilder.newJob(ColorJob.class)
				.withIdentity("job1", "group1").build();

		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
				.newTrigger()
				.startAt(runtime)
				.withIdentity("trigger1", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(10).withRepeatCount(4))
				.build();
		job.getJobDataMap().put(ColorJob.FAVOURATE_COLOR, "Green");
		job.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);
  
		Date scheduleTime = sche.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + scheduleTime
				+ " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000L + " seconds");
////////////////////////////////////////////////////////////////////
		JobDetail job1 = JobBuilder.newJob(ColorJob.class)
				.withIdentity("job2", "group1").build();

		SimpleTrigger trigger1 = (SimpleTrigger) TriggerBuilder
				.newTrigger()
				.startAt(runtime)
				.withIdentity("trigger2", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(10).withRepeatCount(4))
				.build();
		job1.getJobDataMap().put(ColorJob.FAVOURATE_COLOR, "Red");
		job1.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

		Date scheduleTime1 = sche.scheduleJob(job1, trigger1);
		log.info(job.getKey() + " will run at: " + scheduleTime1
				+ " and repeat: " + trigger1.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000L + " seconds");
		
		
		
		

		sche.start();

		try {
			Thread.sleep(65000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sche.shutdown();
	}

	public static void main(String[] args) {
		JobStateExample simpleExample = new JobStateExample();
		try {
			simpleExample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
