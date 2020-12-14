package com.hpower.service;

/**
 * @author yangyang.jiang
 * @date 2020/03/27
 * @since 1.0
 */
public interface RedisService {
    /**
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * @param key
     * @param value
     * @param expired 过期时间，单位秒
     */
    void set(String key, String value, long expired);

    /**
     * @param key
     * @param value 不能为null，使用json方式存储
     */
    void set(String key, Object value);

    /**
     * @param key
     * @param value   不能为null，使用json方式存储
     * @param expired 过期时间，单位秒
     */
    void set(String key, Object value, long expired);

    /**
     * @param key
     * @return
     */
    String get(String key);

    <T> T getObject(String key, Class<T> clazz);

    boolean containsKeyPattern(String keyPattern);

    boolean hasKey(String key);

    void del(String key);

    long getExpire(String key);
}
