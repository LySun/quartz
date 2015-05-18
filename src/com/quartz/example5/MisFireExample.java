package com.quartz.example5;

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
public class MisFireExample {
	public static void run() throws Exception {
		Logger log = LoggerFactory.getLogger(MisFireExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sche = sf.getScheduler();

		Date runtime = DateBuilder.nextGivenSecondDate(null, 10);
///////////////////////////////////////////////////////////////////
		JobDetail job = JobBuilder.newJob(StatefuldumbJob.class)
				.withIdentity("job1", "group1")
				.usingJobData(StatefuldumbJob.EXECUTION_DELAY, 10000l)
				.build();

		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
				.newTrigger()
				.startAt(runtime)
				.withIdentity("trigger1", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(3).repeatForever())
				.build();
  
		Date scheduleTime = sche.scheduleJob(job, trigger);
//		log.info(job.getKey() + " will run at: " + scheduleTime
//				+ " and repeat: " + trigger.getRepeatCount() + " times, every "
//				+ trigger.getRepeatInterval() / 1000L + " seconds");
////////////////////////////////////////////////////////////////////
		 job = JobBuilder.newJob(StatefuldumbJob.class)
				.withIdentity("job2", "group1")
				.usingJobData(StatefuldumbJob.EXECUTION_DELAY, 10000L)
				.build();

		 trigger = (SimpleTrigger) TriggerBuilder
				.newTrigger()
				.startAt(runtime)
				.withIdentity("trigger2", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(3).repeatForever().
								withMisfireHandlingInstructionNowWithExistingCount())
								
				.build();

		Date scheduleTime1 = sche.scheduleJob(job, trigger);
//		log.info(job.getKey() + " will run at: " + scheduleTime1
//				+ " and repeat: " + trigger.getRepeatCount() + " times, every "
//				+ trigger.getRepeatInterval() / 1000L + " seconds");
		
		
		
		

		sche.start();

		try {
			Thread.sleep(60000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sche.shutdown();
	}

	public static void main(String[] args) {
		MisFireExample simpleExample = new MisFireExample();
		try {
			simpleExample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
