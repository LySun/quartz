package com.quartz.example12;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.Trigger;
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
public class RemoteClientExample {

	public static void run() throws Exception {
		Logger log = LoggerFactory.getLogger(RemoteClientExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sche = sf.getScheduler();

		// /////////////////////////////////////////////////////////////////
		// ///////////////////////////////////////////////////////////////////
		JobDetail job = JobBuilder.newJob(SimpleJob.class)
				.withIdentity("remotelyAddedjob", "group1").build();

		JobDataMap data = job.getJobDataMap();
		data.put("msg", "your remotely added job has added");

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("remotelyAddedtrigger", "group1")
				.forJob(job.getKey())
				.withSchedule(CronScheduleBuilder.cronSchedule("/5 * * ? * *"))
				.build();
        sche.scheduleJob(job, trigger);
        log.info("Remote job scheduled.");
	}

	public static void main(String[] args) {

		RemoteClientExample simpleExample = new RemoteClientExample();
		try {
			simpleExample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
