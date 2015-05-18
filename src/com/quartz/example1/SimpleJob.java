
package com.quartz.example1;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
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
 * @JAVA路径 com.quartz.example1.HelloQuartz
 */
public class SimpleJob implements Job{
	private static Logger log = LoggerFactory.getLogger(SimpleJob.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JobKey jobKey=arg0.getJobDetail().getKey();
		log.info("simple says"+jobKey+" execute at "+new Date());
	}

}
