package com.quartz.example4;

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
public class ColorJob implements Job {

	private static Logger log = LoggerFactory.getLogger(ColorJob.class);
	public static final String FAVOURATE_COLOR = "favorite_color";
	public static final String EXECUTION_COUNT = "execution_count";
	private int _counter = 1;
	public static int  change=2;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JobKey jobKey = arg0.getJobDetail().getKey();
		JobDataMap data = arg0.getJobDetail().getJobDataMap();
		String favouriteColor = data.getString(FAVOURATE_COLOR);
		int count = data.getInt(EXECUTION_COUNT);
          
		log.info("ColorJob: " + jobKey + " executing at " + new Date() + "\n"
				+ "  favorite color is " + favouriteColor + "\n"
				+ "  execution count (from job map) is " + count + "\n"
				+ "  execution count (from job member variable) is "
				+ this._counter);
		count++;
	    data.put(EXECUTION_COUNT, count);
	    System.out.println("job中的静态变量："+change);
         change++;
	    this._counter += 1;
	}

}
