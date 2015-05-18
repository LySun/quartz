
package com.quartz.example9;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>  </b>
 * <p>
 *     功能:
 * </p>
 * @作者  张涛
 * @创建日期 2013-12-26
 * @项目名称 quartzDemo
 * @JAVA路径 com.quartz.example9.Job1Lisener
 */
public class Job1Lisener implements JobListener{
	private static Logger log = LoggerFactory.getLogger(Job1Lisener.class);
	@Override
	public String getName() {
		return "job1-to-job2";
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext arg0) {
        log.info("job1listener says: job execution was vetod");	
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext arg0) {
		 log.info("job1listener says: job about to be executed");	
	}

	@Override
	public void jobWasExecuted(JobExecutionContext arg0,
			JobExecutionException arg1) {
		 log.info("job1listener says: job was executed");	
		
		 JobDetail job2 = JobBuilder.newJob(SimpleJob2.class).withIdentity("jobe").build();
		 Trigger trigger = TriggerBuilder.newTrigger().withIdentity("job2trigger").startNow().build();
		 
		 try {
			arg0.getScheduler().scheduleJob(job2, trigger);
		} catch (Exception e) {
			log.warn("unable to schedule job2");
			e.printStackTrace();
		}
		 
	}

}
