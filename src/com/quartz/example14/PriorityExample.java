package com.quartz.example14;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriorityExample
{
  public void run()
    throws Exception
  {
    Logger log = LoggerFactory.getLogger(PriorityExample.class);

    log.info("------- Initializing ----------------------");

    SchedulerFactory sf = new StdSchedulerFactory("com/quartz/example14/quartz_priority.properties");
    Scheduler sched = sf.getScheduler();

    log.info("------- Initialization Complete -----------");

    log.info("------- Scheduling Jobs -------------------");

    JobDetail job = JobBuilder.newJob(TriggerEchoJob.class).withIdentity("TriggerEchoJob").build();

    Date startTime = DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND);

    Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("Priority1Trigger5SecondRepeat").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInSeconds(5)).withPriority(1).forJob(job).build();

    Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("Priority5Trigger10SecondRepeat").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInSeconds(10)).forJob(job).build();

    Trigger trigger3 = TriggerBuilder.newTrigger().withIdentity("Priority10Trigger15SecondRepeat").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInSeconds(15)).withPriority(10).forJob(job).build();

    sched.scheduleJob(job, trigger1);
    sched.scheduleJob(trigger2);
    sched.scheduleJob(trigger3);

    sched.start();
    log.info("------- Started Scheduler -----------------");

    log.info("------- Waiting 30 seconds... -------------");
    try {
      Thread.sleep(30000L);
    }
    catch (Exception e)
    {
    }

    log.info("------- Shutting Down ---------------------");
    sched.shutdown(true);
    log.info("------- Shutdown Complete -----------------");
  }

  public static void main(String[] args) throws Exception {
    PriorityExample example = new PriorityExample();
    example.run();
  }
}