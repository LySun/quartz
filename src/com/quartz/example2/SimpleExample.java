
package com.quartz.example2;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>  </b>
 * <p>
 *     功能:
 * </p>
 * @作者  张涛
 * @创建日期 2013-12-24
 * @项目名称 quartzDemo
 * @JAVA路径 com.quartz.example1.SimpleExample
 */
public class SimpleExample {
	public static void run() throws  Exception{
		Logger log = LoggerFactory.getLogger(SimpleExample.class);
		SchedulerFactory sf= new StdSchedulerFactory();
		Scheduler sche=sf.getScheduler();
		
		Date runtime=DateBuilder.evenMinuteDate(new Date());
		
		JobDetail job=JobBuilder.newJob(HelloQuartz.class).withIdentity("job1", "group1").build();
		
		Trigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runtime).build();
		
		sche.scheduleJob(job, trigger);
		
		log.info(job.getKey()+"run at "+ runtime);
		 
		sche.start();
		
		try {
			Thread.sleep(65000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sche.shutdown();
	}
  public static void main(String[] args) {
	 SimpleExample simpleExample=new SimpleExample();
	 try {
		simpleExample.run();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
