package com.aaron.springbootDemo.core.executor.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * 示例任务
 * @author Aaron.Qiu
 * @date 2019-01-15
 */
@JobHander(value="exampleJobHandler")
@Service
public class ExampleJobHandler extends IJobHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExampleJobHandler.class);


    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        ReturnT<String> returnT = new ReturnT<>();
        returnT.setCode(ReturnT.SUCCESS_CODE);
        returnT.setMsg("示例任务执行成功");
        logger.info("示例任务开始,params="+ Arrays.toString(params));
        try {
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("示例任务执行出错",e);
            returnT.setCode(ReturnT.FAIL_CODE);
            returnT.setMsg("示例任务执行出错,"+e.getMessage());

        }
        logger.info("示例任务执行结束");
        return returnT;
    }
}
