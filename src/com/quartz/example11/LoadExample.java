package com.quartz.example11;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
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
public class LoadExample {
	public static int numberofjobs = 500;

	public LoadExample(int numberofjobs) {
		this.numberofjobs = numberofjobs;
	}

	public static void run() throws Exception {
		Logger log = LoggerFactory.getLogger(LoadExample.class);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sche = sf.getScheduler();

		// /////////////////////////////////////////////////////////////////
		// ///////////////////////////////////////////////////////////////////

		for (int i = 1; i <= numberofjobs; i++) {
			JobDetail job = JobBuilder.newJob(SimpleJob.class)
					.withIdentity("job" + i, "group1").build();

			long timedelay = (long) (Math.random() * 2500.0D);
			job.getJobDataMap().put("delaytime", timedelay);

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("trigger" + i, "group1")
					.startAt(
							DateBuilder.futureDate(10000 + i * 100,
									DateBuilder.IntervalUnit.MILLISECOND))
					.build();
			
			sche.scheduleJob(job, trigger);
			if (i%25==0) {
				log.info("sched"+i+"job");
			}

		}

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
		int numberofjobs =500;
		if(args.length==1){
			numberofjobs=Integer.parseInt(args[0]);
		}
		if(args.length>1){
			System.out.println("Usage java "+LoadExample.class.getName()+"# of jobs	");
			return; 	
		}
		LoadExample simpleExample = new LoadExample(numberofjobs);
		try {
			simpleExample.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
