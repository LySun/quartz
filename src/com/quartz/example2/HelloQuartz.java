
package com.quartz.example2;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>  </b>
 * <p>
 *     ����:
 * </p>
 * @����  ����
 * @�������� 2013-12-24
 * @��Ŀ���� quartzDemo
 * @JAVA·�� com.quartz.example1.HelloQuartz
 */
public class HelloQuartz implements Job{
	private static Logger log = LoggerFactory.getLogger(HelloQuartz.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info("hello world"+new Date());
	}

}
