package com.quartz.example3;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
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
public class SimpleTriggerExample {
	public static void run() throws Exception {
		Logger log = LoggerFactory.getLogger(SimpleTriggerExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sche = sf.getScheduler();

		Date runtime = DateBuilder.nextGivenSecondDate(null, 50);

		JobDetail job = JobBuilder.newJob(SimpleJob.class)
				.withIdentity("job1", "group1").build();

		CronTrigger trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("trigger1", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
				.build();

		Date date = sche.scheduleJob(job, trigger);

		log.info(job.getKey() + "run at " + date + "and repeat on expression"
				+ trigger.getCronExpression());

		job = JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group1")
				.build();

		trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("trigger2", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("15 0/2 * * * ?"))
				.build();

		date = sche.scheduleJob(job, trigger);

		log.info(job.getKey() + "run at " + date + "and repeat on expression"
				+ trigger.getCronExpression());
		job = JobBuilder.newJob(SimpleJob.class).withIdentity("job3", "group1")
				.build();

		trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("trigger3", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
				.build();

		date = sche.scheduleJob(job, trigger);

		log.info(job.getKey() + "run at " + date + "and repeat on expression"
				+ trigger.getCronExpression());
		job = JobBuilder.newJob(SimpleJob.class).withIdentity("job4", "group1")
				.build();

		trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("trigger4", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0 0/3 17-23 * * ?"))
				.build();

		date = sche.scheduleJob(job, trigger);

		log.info(job.getKey() + "run at " + date + "and repeat on expression"
				+ trigger.getCronExpression());
		job = JobBuilder.newJob(SimpleJob.class).withIdentity("job5", "group1")
				.build();

		trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("trigger5", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0 0 10am 1,15 * ?"))
				.build();

		date = sche.scheduleJob(job, trigger);

		log.info(job.getKey() + "run at " + date + "and repeat on expression"
				+ trigger.getCronExpression());
		job = JobBuilder.newJob(SimpleJob.class).withIdentity("job6", "group1")
				.build();

		trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("trigger6", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0,30 * * ? * MON-FRI"))
				.build();

		date = sche.scheduleJob(job, trigger);
		
		log.info(job.getKey() + "run at " + date + "and repeat on expression"
				+ trigger.getCronExpression());
		job = JobBuilder.newJob(SimpleJob.class).withIdentity("job7", "group1")
				.build();

		trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("trigger7", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0,30 * * ? * SAT,SUN"))
				.build();

		date = sche.scheduleJob(job, trigger);
		sche.start();

		try {
			Thread.sleep(65000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sche.shutdown();
	}

	public static void main(String[] args) {
		SimpleTriggerExample simpleExample = new SimpleTriggerExample();
		try {
			simpleExample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
