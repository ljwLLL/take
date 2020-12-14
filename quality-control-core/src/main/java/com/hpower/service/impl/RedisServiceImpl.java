package com.hpower.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hpower.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @author yangyang.jiang
 * @date 2020/03/27
 * @since 1.0
 */
@Service
public class RedisServiceImpl implements RedisService {
    public static final int EXPIRED_TIME = 54000;

    private RedisTemplate redisTemplate;

    private String namespace;

    public RedisTemplate getRedisTemplate() {
        return this.redisTemplate;
    }

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(stringSerializer);
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setStringSerializer(stringSerializer);
        redisTemplate.afterPropertiesSet();
        this.redisTemplate = redisTemplate;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    private String queryKey(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("Redis key is null!");
        }
        return key;
    }

    @Override
    public void set(String key, String value) {
        key = this.queryKey(key);
        ValueOperations ops = this.redisTemplate.opsForValue();
        ops.set(key, value);
    }

    @Override
    public void set(String key, String value, long expired) {
        key = this.queryKey(key);
        ValueOperations ops = this.redisTemplate.opsForValue();
        ops.set(key, value, expired, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Object is null!");
        }
        key = this.queryKey(key);
        ValueOperations ops = this.redisTemplate.opsForValue();
        ops.set(key, JSON.toJSONString(value));
    }

    @Override
    public void set(String key, Object value, long expired) {
        if (value == null) {
            throw new IllegalArgumentException("Object is null!");
        }
        key = this.queryKey(key);
        ValueOperations ops = this.redisTemplate.opsForValue();
        ops.set(key, JSON.toJSONString(value), expired, TimeUnit.SECONDS);
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        key = this.queryKey(key);
        ValueOperations ops = this.redisTemplate.opsForValue();
        return JSONObject.parseObject((String) ops.get(key), clazz);
    }

    @Override
    public String get(String key) {
        key = this.queryKey(key);
        ValueOperations ops = this.redisTemplate.opsForValue();
        return (String) ops.get(key);
    }

    @Override
    public boolean containsKeyPattern(String key) {
        key = this.queryKey(key);
        Set keys = this.redisTemplate.keys(key + "*");
        return !CollectionUtils.isEmpty(keys);
    }

    @Override
    public boolean hasKey(String key) {
        key = this.queryKey(key);
        return this.redisTemplate.hasKey(key).booleanValue();
    }

    @Override
    public long getExpire(String key) {
        key = this.queryKey(key);
        return this.redisTemplate.getExpire(key).longValue();
    }

    @Override
    public void del(String key) {
        key = this.queryKey(key);
        this.redisTemplate.delete(key);
    }
}
