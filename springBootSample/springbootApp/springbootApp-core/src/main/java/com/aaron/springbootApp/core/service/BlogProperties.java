package com.aaron.springbootApp.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/12/3.
 */
@Component
public class BlogProperties {

    @Value("${com.aaron.blog.value}")
    private String value;
    @Value("${com.aaron.blog.number}")
    private Integer number;
    @Value("${com.aaron.blog.bignumber}")
    private Long bignumber;
    @Value("${com.aaron.blog.test1}")
    private Integer test1;
    @Value("${com.aaron.blog.test2}")
    private Integer test2;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getBignumber() {
        return bignumber;
    }

    public void setBignumber(Long bignumber) {
        this.bignumber = bignumber;
    }

    public Integer getTest1() {
        return test1;
    }

    public void setTest1(Integer test1) {
        this.test1 = test1;
    }

    public Integer getTest2() {
        return test2;
    }

    public void setTest2(Integer test2) {
        this.test2 = test2;
    }

    @Override
    public String toString() {
        return "BlogProperties{" +
                "value='" + value + '\'' +
                ", number=" + number +
                ", bignumber=" + bignumber +
                ", test1=" + test1 +
                ", test2=" + test2 +
                '}';
    }
}
