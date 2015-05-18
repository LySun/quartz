package com.quartz.example6;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
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
public class BadJob2 implements Job {

	private static Logger log = LoggerFactory.getLogger(BadJob2.class);
	public int 	caculation;


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JobKey jobKey = arg0.getJobDetail().getKey();
          
		log.info("---" + jobKey + " executing at " + new Date());
		try { int zere=0;
			  this.caculation=(4815/zere);
			 
		} catch (Exception e) {
			  log.info("--- Error in job!");
		      JobExecutionException e2 = new JobExecutionException(e);
		      e2.setUnscheduleAllTriggers(true);
		      throw e2;
		}
		log.info("---" + jobKey + " completed at " + new Date());
	}

}
