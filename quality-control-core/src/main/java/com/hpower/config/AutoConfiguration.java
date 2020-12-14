package com.hpower.config;

import com.hpower.runner.QualityControlApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"com.hpower.config"})
public class AutoConfiguration {

    /**
     * springboot启动之后会调用{@link QualityControlApplicationRunner#run(ApplicationArguments)}方法
     *
     * @param applicationContext
     * @return
     */
    @Bean
    public ApplicationRunner lisApplicationRunner(ApplicationContext applicationContext) {
        return new QualityControlApplicationRunner();
    }
}
