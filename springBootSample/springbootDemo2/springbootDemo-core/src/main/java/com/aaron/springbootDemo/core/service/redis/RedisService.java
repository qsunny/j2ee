package com.aaron.springbootDemo.core.service.redis;

import com.aaron.springbootDemo.utils.CommUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * redis相关操作
 * @author aaron.qiu
 * @Since:2015-04-12
 * @Copyright:Copyright (c) 2015 ~ 2020 版权所有
 */
@Service
public class RedisService {
	
	private static final Logger log = LoggerFactory.getLogger(RedisService.class);

	/**
	 * 默认过期时长，单位：秒
	 */
	public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

	@Resource(name="redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

	/**
	 * 不设置过期时长
	 */
	public static final long NOT_EXPIRE = -1;
	
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
	public boolean existsKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
	 *
	 * @param oldKey
	 * @param newKey
	 */
	public void renameKey(String oldKey, String newKey) {
		redisTemplate.rename(oldKey, newKey);
	}

	/**
	 * newKey不存在时才重命名
	 *
	 * @param oldKey
	 * @param newKey
	 * @return 修改成功返回true
	 */
	public boolean renameKeyNotExist(String oldKey, String newKey) {
		return redisTemplate.renameIfAbsent(oldKey, newKey);
	}

	/**
	 * 删除key
	 *
	 * @param key
	 */
	public void deleteKey(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 删除多个key
	 *
	 * @param keys
	 */
	public void deleteKey(String... keys) {
		Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
		redisTemplate.delete(kSet);
	}

	/**
	 * 删除Key的集合
	 *
	 * @param keys
	 */
	public void deleteKey(Collection<String> keys) {
		Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
		redisTemplate.delete(kSet);
	}

	/**
	 * 设置key的生命周期
	 *
	 * @param key
	 * @param time
	 * @param timeUnit
	 */
	public void expireKey(String key, long time, TimeUnit timeUnit) {
		redisTemplate.expire(key, time, timeUnit);
	}

	/**
	 * 指定key在指定的日期过期
	 *
	 * @param key
	 * @param date
	 */
	public void expireKeyAt(String key, Date date) {
		redisTemplate.expireAt(key, date);
	}

	/**
	 * 查询key的生命周期
	 *
	 * @param key
	 * @param timeUnit
	 * @return
	 */
	public long getKeyExpire(String key, TimeUnit timeUnit) {
		return redisTemplate.getExpire(key, timeUnit);
	}

	/**
	 * 将key设置为永久有效
	 *
	 * @param key
	 */
	public void persistKey(String key) {
		redisTemplate.persist(key);
	}

}
