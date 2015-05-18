
package com.quartz.test;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

/**
 * <b>  </b>
 * <p>
 *     功能:
 * </p>
 * @作者  张涛
 * @创建日期 2013-12-24
 * @项目名称 quartzDemo
 * @JAVA路径 com.quartz.test.QueartTest
 */
public class QueartTest {

	/**
	 * <b> 功能 :  </b>
	 * @param args
	 * @作者  张涛
	 * @创建日期 2013-12-24
	 */
	public static void main(String[] args) {
        try {
            // Grab the Scheduler instance from the Factory 
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail job=new JobDetailImpl("newtest", Scheduler.DEFAULT_GROUP,ScandirectoryJob.class);
            Trigger trigger=new SimpleTriggerImpl("test", 5, 4000);
            
            scheduler.scheduleJob(job, trigger);
            // and start it off
            scheduler.start();
         
            
            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
	}

}
