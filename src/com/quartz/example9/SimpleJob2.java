package com.quartz.example9;

import java.util.Date;

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
public class SimpleJob2 implements Job {

	private static Logger log = LoggerFactory.getLogger(SimpleJob2.class);
	private JobKey jobkey=null;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		jobkey = context.getJobDetail().getKey();
		log.info("SimpleJob2 says: " + jobkey + " executing at " + new Date());
	}
   
}
