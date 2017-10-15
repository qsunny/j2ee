package com.aaron.springbootApp.core.executor.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 任务Handler的一个Demo（Bean模式）
 * 开发步骤：
 * 1、继承 “IJobHandler” ；
 * 2、装配到Spring，例如加 “@Service” 注解；
 * 3、加 “@JobHander” 注解，注解value值为新增任务生成的JobKey的值;多个JobKey用逗号分割;
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 * https://mp.weixin.qq.com/s/UFWXvv5fwjUO5RmfRF5m7A
 * cron express:0 0/1 * * * ?    0 0/1 * * * ? *
 * @author xuxueli 2015-12-19 19:43:36
 */
@JobHander(value="helloJobHandler")
@Service
public class HelloJobHandler extends IJobHandler {
    private static final Logger logger = LoggerFactory.getLogger(HelloJobHandler.class);

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        logger.info("=======hello xxl job=======params="+strings);
        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }
}
