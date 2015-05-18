package com.quartz.example11;

import java.util.Date;
import java.util.Set;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.UnableToInterruptJobException;
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
 * @JAVA路径 com.quartz.example1.HelloQuartz
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleJob implements Job {

	private static Logger log = LoggerFactory.getLogger(SimpleJob.class);
	private JobKey jobkey=null;
    public static final String DELAYTIME="delaytime";
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		jobkey = context.getJobDetail().getKey();
		
		log.info("Excuting job: " + jobkey + " executing at " + new Date());
		
		long delaytime = context.getJobDetail().getJobDataMap().getLong("delaytime");
		
		try {
			Thread.sleep(delaytime);
		} catch (Exception e) {
		}
		log.info("finish executing job "+ jobkey+"at"+ new Date());
		
		
		
				
		
		
	}
   
}
