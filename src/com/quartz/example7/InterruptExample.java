package com.quartz.example7;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b> </b>
 * <p>
 * ����:
 * </p>
 * 
 * @���� ����
 * @�������� 2013-12-24
 * @��Ŀ���� quartzDemo
 * @JAVA·�� com.quartz.example1.SimpleExample
 */
public class InterruptExample {
	public static void run() throws Exception {
		Logger log = LoggerFactory.getLogger(InterruptExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sche = sf.getScheduler();

		Date runtime = DateBuilder.nextGivenSecondDate(null, 15);
		// /////////////////////////////////////////////////////////////////
		JobDetail job = JobBuilder.newJob(DumInterruptTableJob.class)
				.withIdentity("interruptjob1", "group1").build();

		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
				.newTrigger()
				.startAt(runtime)
				.withIdentity("trigger1", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(5).repeatForever())
				.build();

		Date scheduleTime = sche.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + scheduleTime
				+ " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000L + " seconds");
		// //////////////////////////////////////////////////////////////////

		sche.start();
		log.info("ÿ7����ѭ��һ�ι���");
		for (int i = 0; i < 10; ++i) {
			try {
				Thread.sleep(7000L);
				sche.interrupt(job.getKey());
			} catch (Exception e) {
			}
			
		  }
	    	sche.shutdown();
		 SchedulerMetaData metaData = sche.getMetaData();
		  log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	   }

	public static void main(String[] args) {
		InterruptExample simpleExample = new InterruptExample();
		try {
			simpleExample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
