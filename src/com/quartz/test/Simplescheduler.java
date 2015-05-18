
package com.quartz.test;

import java.util.Date;

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
 * @JAVA路径 com.quartz.test.Simplescheduler
 */
public class Simplescheduler {
	public static void main(String[] args) {
		Simplescheduler simple = new Simplescheduler();       
         try
         {       
             // Create a Scheduler and schedule the Job       
            Scheduler scheduler = simple.createScheduler();       
             simple.scheduleJob(scheduler);       
    
             // Start the Scheduler running       
            scheduler.start();       
         System.out.println("Scheduler started at " + new Date());
        } catch (SchedulerException ex) {       
                   
        }   
	}
    public Scheduler createScheduler() throws SchedulerException{
         return StdSchedulerFactory.getDefaultScheduler();    	
    }
    
    private void scheduleJob(Scheduler  scheduler) throws SchedulerException{
    	 JobDetail jobDetail=new JobDetailImpl("ScanDirectory",Scheduler.DEFAULT_GROUP,ScandirectoryJob.class);
    	 jobDetail.getJobDataMap().put("scandir", "D:/Eclipse/eclipse");
          Trigger trigger=new SimpleTriggerImpl("scanTrigger",4,new Long("2000"));
          scheduler.scheduleJob(jobDetail, trigger);
    }
    
    
} 
