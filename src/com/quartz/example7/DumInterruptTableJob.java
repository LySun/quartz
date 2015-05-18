package com.quartz.example7;

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
 * ����:
 * </p>
 * 
 * @���� ����
 * @�������� 2013-12-24
 * @��Ŀ���� quartzDemo
 * @JAVA·�� com.quartz.example1.HelloQuartz
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DumInterruptTableJob implements InterruptableJob {

	private static Logger log = LoggerFactory.getLogger(DumInterruptTableJob.class);
	private JobKey jobkey=null;
    private boolean interrupt=false;
    

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		jobkey = context.getJobDetail().getKey();
		try {
			for (int i = 0; i < 4; i++) {
			   try {
				Thread.sleep(1000L);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			if(this.interrupt){
               log.info(jobkey+"--interrrupt...balling out");
               return;
			}
			}
		}finally{
			log.info(jobkey+" completed at "+new Date());
		}
	}
   public void interrupt() throws UnableToInterruptJobException{
	   log.info(this.jobkey+"--�����---");
       this.interrupt=true;	   
   }
}
