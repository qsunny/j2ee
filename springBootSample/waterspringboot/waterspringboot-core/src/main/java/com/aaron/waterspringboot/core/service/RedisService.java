package com.aaron.waterspringboot.core.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.cecport.tools.utils.CommUtils;


/**
 * redis相关操作
 * @author aaron.qiu
 * @Since:2015-04-12
 * @Copyright:Copyright (c) 2015 ~ 2016 版权所有
 */
@Service
public class RedisService {
	
	private Logger log = Logger.getLogger(RedisService.class);
	
	@Resource(name="redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;
	
	/**|
	 * 把对象根据key保存到Redis中
	 * @param key 对象key
	 * @param value 对象值
	 * @param ttl 对象在redis中的生命周期
	 * @param timeUnit 时间单位
	 */
	public void put(String key,String t) {
		this.put(key, t, 24, TimeUnit.HOURS);
	}
	
	/**|
	 * 把对象根据key保存到Redis中
	 * @param key 对象key
	 * @param value 对象值
	 * @param ttl 对象在redis中的生命周期
	 * @param timeUnit 时间单位
	 */
	public void put(String key,String value,int ttl,TimeUnit timeUnit) {
		if(StringUtils.isNotBlank(key)) {
			redisTemplate.opsForValue().set(key, value, ttl, timeUnit);
		}
	}
	
	/**
	 * 根据key从redis中获取对象值
	 * @param key 对象key
	 * @return
	 */
	public String get(String key) {
		if(StringUtils.isNotBlank(key)) {
			Object objValue = redisTemplate.opsForValue().get(key);
			if(!CommUtils.isNull(objValue)) {
				return CommUtils.toStr(objValue);
			}
		}
		return "";
	}
	
}
