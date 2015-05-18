package com.quartz.example10;

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

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		jobkey = context.getJobDetail().getKey();
		
		log.info("Excuting job: " + jobkey + " executing at " + new Date()+" fire by: "+context.getTrigger().getKey());
		
		if(context.getMergedJobDataMap().size()>0){
	    	 Set<String>  keys=	context.getMergedJobDataMap().keySet();
			 for (String key : keys) {
				String value= context.getMergedJobDataMap().getString(key);
				log.info(" jobdatamap entry: "+key+" = "+value);
			}
			 context.setResult("hello");
		}
		
		
	}
   
}
