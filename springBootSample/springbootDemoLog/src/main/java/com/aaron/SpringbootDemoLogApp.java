package com.aaron;

import com.aaron.bean.Order;
import com.aaron.service.OrderService;
import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * https://github.com/mouzt/mzt-biz-log
 */
@RestController
@SpringBootApplication
@EnableLogRecord(tenant = "com.aaron.*")
public class SpringbootDemoLogApp {

    @Resource
    private OrderService orderService;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/createOrder")
    String createOrder() throws Exception {
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setProductName("围棋1");
        order.setPurchaseName("Aaron.Qiu");
        try {
            orderService.createOrder(order,"aaron");
        } catch (Exception e) {

        }
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoLogApp.class, args);
    }

}
