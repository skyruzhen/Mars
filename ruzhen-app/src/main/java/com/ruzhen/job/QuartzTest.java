package com.ruzhen.job;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 〈一句话功能简述〉<br>
 * 〈quartz测试〉
 *
 * @author lizhen
 * @create 2018/9/25
 * @since 1.0.0
 */
public class QuartzTest {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //开始
            scheduler.start();

            JobDetail job = newJob(Job1Demo.class).withIdentity("job1", "group1").build();

            Date runTime = evenMinuteDate(new Date());

            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startAt(runTime)
                    .build();
            scheduler.scheduleJob(job, trigger);

            Thread.sleep(1000L);

            //结束
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}