package com.quartz.example6;

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
public class JobExceptionExample {
	public static void run() throws Exception {
		Logger log = LoggerFactory.getLogger(JobExceptionExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sche = sf.getScheduler();

		Date runtime = DateBuilder.nextGivenSecondDate(null, 15);
///////////////////////////////////////////////////////////////////
		JobDetail job = JobBuilder.newJob(BadJob1.class)
				.withIdentity("badjob1", "group1")
				.usingJobData("denominator",0)
				.build();

		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
				.newTrigger()
				.startAt(runtime)
				.withIdentity("trigger1", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(10).repeatForever())
				.build();
	
  
		Date scheduleTime = sche.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + scheduleTime
				+ " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000L + " seconds");
////////////////////////////////////////////////////////////////////
		 job = JobBuilder.newJob(BadJob1.class)
				.withIdentity("badjob2", "group1").build();

		 trigger = (SimpleTrigger) TriggerBuilder
				.newTrigger()
				.startAt(runtime)
				.withIdentity("trigger2", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(5).repeatForever())
				.build();

		Date scheduleTime1 = sche.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + scheduleTime1
				+ " and repeat: " + trigger.getRepeatCount() + " times, every "
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
		JobExceptionExample simpleExample = new JobExceptionExample();
		try {
			simpleExample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
