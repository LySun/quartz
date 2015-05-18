package com.quartz.example10;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.impl.matchers.KeyMatcher;
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
public class PluginExample {
	public static void run() throws Exception {
		Logger log = LoggerFactory.getLogger(PluginExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sche = sf.getScheduler();

		
		// /////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////

		   sche.start();
		 
			try {
				Thread.sleep(300000L);
			 } catch (Exception e) {
			}
			
	    	sche.shutdown();
		   SchedulerMetaData metaData = sche.getMetaData();
		   log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	   }

	public static void main(String[] args) {
		PluginExample simpleExample = new PluginExample();
		try {
			simpleExample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
