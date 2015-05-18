package com.quartz.example5;

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
public class StatefuldumbJob implements Job {

	private static Logger log = LoggerFactory.getLogger(StatefuldumbJob.class);
	public static final String NUM_EXECUTIONS = "NumExecutions";
    public static final String EXECUTION_DELAY = "ExecutionDelay";

    
	public StatefuldumbJob() {
		
	}
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.err.println(context.getJobDetail().getKey()+"execute at "+new Date());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		 
		int executecount=0;
		if(dataMap.containsKey(NUM_EXECUTIONS)){
			executecount=dataMap.getInt(NUM_EXECUTIONS);
		}
		executecount++;
		dataMap.put(NUM_EXECUTIONS, executecount);
		
		long delay = 1000L;
		if(dataMap.containsKey(EXECUTION_DELAY)){
			delay=dataMap.getLong(EXECUTION_DELAY);
		}
		try {
			Thread.sleep(delay);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(context.getJobDetail().getKey()+"complete "+executecount);
	}

}
