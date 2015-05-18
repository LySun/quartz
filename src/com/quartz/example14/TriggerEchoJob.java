package com.quartz.example14;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriggerEchoJob
  implements Job
{
  private static final Logger LOG = LoggerFactory.getLogger(TriggerEchoJob.class);

  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    LOG.info("TRIGGER: " + context.getTrigger().getKey());
  }
}