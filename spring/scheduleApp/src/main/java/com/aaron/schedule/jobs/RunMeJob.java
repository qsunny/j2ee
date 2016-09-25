package com.aaron.schedule.jobs;

import com.aaron.schedule.tasks.RunMeTask;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/9/25.
 */
@Component
public class RunMeJob extends QuartzJobBean {

    private RunMeTask runMeTask;

    private int timeout;

    /**
     * Setter called after the ExampleJob is instantiated
     * with the value from the JobDetailFactoryBean (5)
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setRunMeTask(RunMeTask runMeTask) {
        this.runMeTask = runMeTask;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("timeout===>" +timeout);
        System.out.println(this.runMeTask);
        this.runMeTask.printMe();
        System.out.println("===========task execute end===========");
    }
}
