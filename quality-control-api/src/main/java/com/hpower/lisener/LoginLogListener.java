package com.hpower.lisener;

import com.hpower.constant.MessageQueueConstant;
import com.hpower.dto.LoginLogDto;
import com.hpower.service.SLogService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * listener监听队列消费信息
 *
 * @author yangyang.jiang
 * @date 2020/03/27
 * @since 1.0
 */
@Component
@RabbitListener(queues = MessageQueueConstant.LOGIN_LOG)
public class LoginLogListener {

    @Autowired
    private SLogService logService;

    @RabbitHandler
    public void receive(LoginLogDto loginLogDto) {
        logService.saveLoginLog(loginLogDto);
    }
}
