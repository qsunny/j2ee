package com.aaron.service;

import com.aaron.bean.Order;
import com.aaron.constants.SystemConstant;
import com.mzt.logapi.starter.annotation.LogRecordAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class OrderService {

    @LogRecordAnnotation(
            fail = "创建订单失败，失败原因：「{{#_errorMsg}}」",
            category = "MANAGER",
            detail = "{{#order.toString()}}",
            operator = "{{#currentUser}}",
            success = "{{#order.purchaseName}}下了一个订单,购买商品「{{#order.productName}}」,下单结果:{{#_ret}}",
            prefix = SystemConstant.LogRecordType.ORDER, bizNo = "{{#order.orderNo}}")
    public boolean createOrder(Order order,String currentUser) throws Exception {
        if(Objects.equals("围棋",order.getProductName()))
            throw new Exception("下单异常");
        log.info("【创建订单】orderNo={}", order.getOrderNo());
        // db insert order
        return true;
    }
}
