package com.hpower.config;

import com.hpower.constant.MessageQueueConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit初始化配置
 *
 * @author yangyang.jiang
 * @date 2020/03/27
 * @since 1.0
 */
@Configuration
public class RabbitConfig {
    /**
     * 初始化登录日志队列
     *
     * @return
     */
    @Bean
    public Queue initLoginLogQueue() {
        return new Queue(MessageQueueConstant.LOGIN_LOG);
    }


}
