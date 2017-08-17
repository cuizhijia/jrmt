package com.swift.jrmt.cache.service;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.swift.jrmt.common.utils.CollectionUtil;

/**
 * √Ë ˆ£∫
 * @author Administrator
 * @version 3.0
 * 
 */
@Service("cacheOperator")
public class CacheOperator  {

	@Resource
	private JedisConnectionFactory jedisConnectionFactory;
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> valueOperations;
	
//	@Resource(name = "redisTemplate")
//	private ListOperations<String, Long> listOperations;
//	
//	@Resource(name = "redisTemplate")
//	private SetOperations<String, String> setOperations;
//	
//	@Resource(name = "redisTemplate")
//	private HashOperations<String, String, Object> hashOperations;
//	
//	@Resource(name = "redisTemplate")
//	private ValueOperations<String, Object> valueOperations;
	
	
	/* 
	 * @see org.howsun.redis.CacheOperator#expire(java.lang.String, int)
	 */
	
	public Boolean expire(String key, int second){
		return redisTemplate.expire(key, second, TimeUnit.SECONDS);
	}
	
	
	/* 
	 * @see org.howsun.redis.CacheOperator#expireAt(java.lang.String, java.util.Date)
	 */
	
	public Boolean expireAt(String key, Date date){
		return redisTemplate.expireAt(key, date);
	}
	

	/* 
	 * @see org.howsun.redis.CacheOperator#exist(java.lang.String)
	 */
	
	public Boolean exist(String key){
		return redisTemplate.hasKey(key);
	}
	

	/* 
	 * @see org.howsun.redis.CacheOperator#typeOf(java.lang.String)
	 */
	
	public DataType typeOf(String key){
		return redisTemplate.type(key);
	}
	
	
	/* 
	 * @see org.howsun.redis.CacheOperator#delete(java.lang.String)
	 */
	
	public void delete(String key){
		redisTemplate.delete(key);
	}
	
	/* 
	 * @see org.howsun.redis.CacheOperator#deletes(java.lang.String)
	 */
	
	public void deletes(String pattern) {
		Set<String> keys = getKeys(pattern);
		if(CollectionUtil.notEmpty(keys)){
			for(String key : keys){
				redisTemplate.delete(key);
			}
		}
	}
	
	/* 
	 * @see org.howsun.redis.CacheOperator#getKeys(java.lang.String)
	 */
	
	public Set<String> getKeys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	/* 
	 * @see org.howsun.redis.CacheOperator#flushAll()
	 */
	
	public void flushAll(){
		try {
			jedisConnectionFactory.getConnection().flushDb();
		}
		catch (Exception e) {
		}
	}
	
	/* 
	 * @see org.howsun.redis.CacheOperator#setString(java.lang.String, java.lang.String, int)
	 */
	
	public void setString(String key, String value, int second) {
		stringRedisTemplate.boundValueOps(key).set(value, second, TimeUnit.SECONDS);
	}
	
	/* 
	 * @see org.howsun.redis.CacheOperator#getString(java.lang.String)
	 */
	
	public String getString(String key) {
		return stringRedisTemplate.boundValueOps(key).get();
	}
	
	/* 
	 * @see org.howsun.redis.CacheOperator#setObject(java.lang.String, java.lang.Object, int)
	 */
	
	public void setObject(String key, Object object, long second) {
		valueOperations.boundValueOps(key).set(object, second, TimeUnit.SECONDS);
	}

	/* 
	 * @see org.howsun.redis.CacheOperator#getObject(java.lang.String)
	 */
	
	public Object getObject(String key) {
		return valueOperations.boundValueOps(key).get();
	}
	

	@SuppressWarnings("unchecked")
	
	public <T> T getObject(String key, Class<T> t) {
		Object obj = getObject(key);
		return obj == null ? null : (T)obj;
	}


	/* 
	 * @see org.howsun.redis.CacheOperator#getJedisConnectionFactory()
	 */
	
	public JedisConnectionFactory getJedisConnectionFactory() {
		return jedisConnectionFactory;
	}


	/* 
	 * @see org.howsun.redis.CacheOperator#getRedisTemplate()
	 */
	
	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}


	/* 
	 * @see org.howsun.redis.CacheOperator#getStringRedisTemplate()
	 */
	
	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}



	protected byte[] rawKey(String key){
		byte[] result = null;
		try {
			result = key.getBytes("UTF-8");
		}
		catch (Exception e) {
		}
		return result;
	}
}
