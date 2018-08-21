package com.aaron.springbootlettuce.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * redis相关操作
 * @author aaron.qiu
 * @Since:2018-08-21
 * @Copyright:Copyright (c) 2018 ~ 2019 版权所有
 */
@Service
public class RedisService {
	
	private Logger log = LoggerFactory.getLogger(RedisService.class);

	@Autowired
	private RedisTemplate redisTemplate ;
	@Autowired
	private StringRedisTemplate stringRedisTemplate ;
	
	/**|
	 * 把对象根据key保存到Redis中
	 * @param key 对象key
	 * @param t 对象值
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
	 * 把对象根据key保存到Redis中
	 * @param key 对象key
	 * @param value 对象值
	 * @param ttl 对象在redis中的生命周期
	 * @param timeUnit 时间单位
	 */
	public void putRedisString(String key,String value,int ttl,TimeUnit timeUnit) {
		ValueOperations<String, String> valueOperate = stringRedisTemplate.opsForValue();
		if(StringUtils.isNotBlank(key)) {
			valueOperate.set(key, value, ttl, timeUnit);
		}
	}

	public String getRedisString(String key) {
		ValueOperations<String, String> valueOperate = stringRedisTemplate.opsForValue();
		String value = valueOperate.get(key);
		return value;
	}


		/**
         * 根据key从redis中获取对象值
         * @param key 对象key
         * @return
         */
	public String get(String key) {
		if(StringUtils.isNotBlank(key)) {
			Object objValue = redisTemplate.opsForValue().get(key);
			if(null!=objValue) {
				return objValue.toString();
			}
		}
		return "";
	}

	/**
	 * 删除缓存<br>
	 * 根据key精确匹配删除
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	public void remove(String... key){
		if(key!=null && key.length > 0){
			if(key.length == 1){
				redisTemplate.delete(key[0]);
			}else{
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

}
