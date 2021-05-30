package com.aaron.springbootDemoMp.web.controller.rest;

import com.aaron.springbootDemoMp.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(tags = "测试模块")
public class TestController extends BaseController {

    @RequestMapping("/")
    @ApiOperation("测试用例")
    String home() {
        return "Hello World!";
    }
}
