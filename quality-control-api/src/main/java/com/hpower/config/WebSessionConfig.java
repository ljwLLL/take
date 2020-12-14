package com.hpower.config;

import com.hpower.resolver.CookieHeaderHttpSessionIdResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HttpSessionIdResolver;


//配置session过期时间
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3 * 24 * 60 * 60)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@Slf4j
public class WebSessionConfig {


    /**
     * session策略，这里配置的是自定义同时支持Cookie和Header方式（有提供Header，Cookie等方式）
     *
     * @return
     */
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return CookieHeaderHttpSessionIdResolver.token();
    }

}



