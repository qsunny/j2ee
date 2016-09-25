package com.aaron.config;

import com.aaron.schedule.jobs.RunMeJob;
import com.aaron.schedule.tasks.RunMeTask;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/25.
 */
@Configuration
@ComponentScan(basePackages = {"com.aaron.schedule.*"})
public class ScheduleConfig {

    @Autowired
    private RunMeJob runMeJob;
    @Autowired
    private RunMeTask runMeTask;

    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        methodInvokingJobDetailFactoryBean.setTargetObject(runMeTask);
        methodInvokingJobDetailFactoryBean.setTargetMethod("printMe");
        methodInvokingJobDetailFactoryBean.setArguments(new Object[]{"methodInvoking"});
        methodInvokingJobDetailFactoryBean.setConcurrent(false);
        return methodInvokingJobDetailFactoryBean;
    }

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(RunMeJob.class);
        jobDetail.setDurability(true);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("timeout",5);
        map.put("runMeTask",runMeTask);
        jobDetail.setJobDataAsMap(map);
        return jobDetail;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean stb = new SimpleTriggerFactoryBean();
        stb.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        //5 seconds
        stb.setStartDelay(5*1000);
        // repeat every 10 seconds
        stb.setRepeatInterval(10*1000);
        return stb;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
        cronTrigger.setJobDetail( this.jobDetailFactoryBean().getObject());
        cronTrigger.setCronExpression("*/3 * * * * ?");
        return cronTrigger;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        Trigger[] triggerArr = new Trigger[]{cronTriggerFactoryBean().getObject(), simpleTriggerFactoryBean().getObject()};
        schedulerFactoryBean.setTriggers( triggerArr);
        return schedulerFactoryBean;
    }


}
