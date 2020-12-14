package com.hpower.runner;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * springboot启动加载类初始化
 *
 * @author yangyang.jiang
 * @date 2020-03-23
 * @since 1.0
 */
public class QualityControlApplicationRunner implements ApplicationRunner, DisposableBean {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("init......");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy......");
    }
}
