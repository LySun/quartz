package com.quartz.example8;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import org.quartz.impl.calendar.AnnualCalendar;
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
public class CalendarExample {
	public static void run() throws Exception {
		Logger log = LoggerFactory.getLogger(CalendarExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sche = sf.getScheduler();

		
		// /////////////////////////////////////////////////////////////////
		
		AnnualCalendar holidays=new AnnualCalendar();
   
		Calendar fouthofjuly=new GregorianCalendar(2005,6,4);
		holidays.setDayExcluded(fouthofjuly,true);
		
		Calendar hollewoon=new GregorianCalendar(2005,9,31);
		holidays.setDayExcluded(hollewoon,true);
		
		Calendar chrismas=new GregorianCalendar(2005,11,25);
		holidays.setDayExcluded(chrismas,true);
		
		sche.addCalendar("holidays", holidays, false, false);
		
		Date rundate=DateBuilder.dateOf(0, 0, 10,31,10);
		
		JobDetail job = JobBuilder.newJob(SimpleJob.class)
				.withIdentity("job1", "group1").build();

		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
				.newTrigger()
				.startAt(rundate)
				.withIdentity("trigger1", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInHours(1).repeatForever())
				.modifiedByCalendar("holidays")				
				.build();

		Date scheduleTime = sche.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + scheduleTime
				+ " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000L + " seconds");
		/////////////////////////////////////////////////////////////////////

		   sche.start();
		 
			try {
				Thread.sleep(30000L);
			 } catch (Exception e) {
			}
			
	    	sche.shutdown();
		   SchedulerMetaData metaData = sche.getMetaData();
		   log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	   }

	public static void main(String[] args) {
		CalendarExample simpleExample = new CalendarExample();
		try {
			simpleExample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
