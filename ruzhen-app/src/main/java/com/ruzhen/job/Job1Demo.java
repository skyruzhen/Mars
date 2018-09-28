package com.ruzhen.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试代码〉
 *
 * @author lizhen
 * @create 2018/9/25
 * @since 1.0.0
 */
public class Job1Demo extends QuartzJobBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(Job1Demo.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info(new Date()+" hello, 我是任务1");

    }
}