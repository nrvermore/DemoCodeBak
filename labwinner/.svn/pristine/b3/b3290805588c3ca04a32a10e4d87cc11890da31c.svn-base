package com.labwinner.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public abstract  class IRedisService<T> {

	
	 @Autowired
	    protected RedisTemplate<String, Object> redisTemplate;
	    
	     
	    
	    @Resource
	    protected HashOperations<String, String, T> hashOperations;
	    
	    @Resource
	    protected ValueOperations<String, Object> valueOperations;
	    
	     /**
	     * 存入redis中的key
	     */
	    protected abstract String getRedisKey();
	    
	     /**
	     * 添加
	     *
	     * @param key    key
	     * @param doamin 对象
	     * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
	     */
	    public void put(String key, T doamin, long expire) {
	        hashOperations.put(getRedisKey(), key, doamin);
	        if (expire != -1) {
	            redisTemplate.expire(getRedisKey(), expire, TimeUnit.DAYS);
	        }
	    }
	    
	    
	    public void putToken(String key, String tokenValue, long expire) {
	    	//valueOperations.set(key, tokenValue);
	        if (expire != -1) {
	        	valueOperations.set(key, tokenValue, expire, TimeUnit.DAYS);
	        }else{
	        	valueOperations.set(key, tokenValue);
	        }
	       // redisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
	    }
	    public void putExpire(String key, String tokenValue, long expire) {
	    	//valueOperations.set(key, tokenValue);
	        if (expire != -1) {
	        	valueOperations.set(key, tokenValue, expire, TimeUnit.DAYS);
	        }
	       // redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	    }
	    
	    /**
	     * 删除
	     *
	     * @param key 传入key的名称
	     */
	    public void remove(String key) {
	        hashOperations.delete(getRedisKey(), key);
	    }
	    
	    /**
	     * 查询
	     *
	     * @param key 查询的key
	     * @return
	     */
	    public String getToken(String key) {
	        return (String) valueOperations.get(key);
	    }
	    
	    public List<String> getAllToken() {
	    	List<String> res=new ArrayList<String>();
	    	Set<String> ss=hashOperations.keys(getRedisKey());
	    	Iterator<String> it = ss.iterator();  
	    	while (it.hasNext()) {  
	    	  String str = it.next();  
	    	  String value=(String) valueOperations.get(str);
	    	  res.add(value);
	    	}  
	    	return res; 
	    }
	    
	    /**
	     * 查询
	     *
	     * @param key 查询的key
	     * @return
	     */
	    public T get(String key) {
	        return hashOperations.get(getRedisKey(), key);
	    }
	    
	    /**
	     * 获取当前redis库下所有对象
	     * @return
	     */
	    public List<T> getAll() {
	        return hashOperations.values(getRedisKey());
	    }
	    
	    /**
	     * 查询查询当前redis库下所有key
	     *
	     * @return
	     */
	    public Set<String> getKeys() {
	        return hashOperations.keys(getRedisKey());
	    }
	    
	    /**
	     * 判断key是否存在redis中
	     *
	     * @param key 传入key的名称
	     * @return
	     */
	    public boolean isKeyExists(String key) {
	        return hashOperations.hasKey(getRedisKey(), key);
	    }
	    
	    /**
	     * 查询当前key下缓存数量
	     *
	     * @return
	     */
	    public long count() {
	        return hashOperations.size(getRedisKey());
	    }
	    
	    /**
	     * 清空redis
	     */
	    public void empty() {
	        Set<String> set = hashOperations.keys(getRedisKey());
	        set.stream().forEach(key -> hashOperations.delete(getRedisKey(), key));
	    }

}
