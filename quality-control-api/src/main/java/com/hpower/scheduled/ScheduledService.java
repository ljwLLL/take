package com.hpower.scheduled;

import com.hpower.service.SCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Classname 定时任务服务类
 * @Description TODO
 * @Date 2020/3/30 1:01 下午
 * @Created yangyang.jiang
 */
@Component
public class ScheduledService {

    @Autowired
    private SCommonService commonService;

    /**
     * 每月一号生成医院报表
     */
    //@Scheduled(cron = " 0 0 0 1 * ? ")
    public void createHospitalMonth() {
        commonService.createHospitalMonth();
    }


    /**
     * 每年一号生成医院报表
     */
    //@Scheduled(cron = " 0 0 0 1 1 ? ")
    public void createHospitalYear() {
        commonService.createHospitalYear();
    }
}
