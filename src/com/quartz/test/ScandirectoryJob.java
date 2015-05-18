
package com.quartz.test;

import java.io.File;
import java.io.FileFilter;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <b>  </b>
 * <p>
 *     功能:
 * </p>
 * @作者  张涛
 * @创建日期 2013-12-24
 * @项目名称 quartzDemo
 * @JAVA路径 com.quartz.test.TestQuartz
 */
public class ScandirectoryJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail=context.getJobDetail();
		String jobname=jobDetail.getDescription();
		JobDataMap dataMap=jobDetail.getJobDataMap();
		String dirname=dataMap.getString("scandir");
		if (dirname==null) {
			throw new JobExecutionException("directory not configured");
		}
		File dirfile=new File(dirname);
		if(!dirfile.exists()){
			throw new JobExecutionException("invial dir	"+dirname);
		}
		FileFilter  filter=new FileextensionFileFilter(".xml");
		 File[] files=       dirfile.listFiles(filter);    
		if(files==null||files.length==0) return;
		int size=files.length;
		for (int i = 0; i < size; i++) {
			File file = files[i];
			File afile=file.getAbsoluteFile();
			long filelength=file.length();
			String 	msg=afile+"-size"+filelength;
			System.out.println(msg);
		}
		 
		 
		 
	}

}
