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
public class RemoteServerExample {

	public static void run() throws Exception {
		Logger log = LoggerFactory.getLogger(RemoteServerExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sche = sf.getScheduler();

		// /////////////////////////////////////////////////////////////////
		// ///////////////////////////////////////////////////////////////////
		
        sche.start();
        try {
			Thread.sleep(600000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
        sche.shutdown(true);
        SchedulerMetaData metaData = sche.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	}

	public static void main(String[] args) {

		RemoteServerExample simpleExample = new RemoteServerExample();
		try {
			simpleExample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
