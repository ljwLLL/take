package com.hpower.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hpower.dto.LoginLogDto;
import com.hpower.entity.SysLoginLog;
import com.hpower.service.SLogService;
import com.hpower.service.SysLoginLogService;
import com.hpower.support.BaseSupport;
import lombok.extern.slf4j.Slf4j;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Classname SLogServiceImpl
 * @Description TODO
 * @Date 2020/3/27 8:42 上午
 * @Created yangyang.jiang
 */
@Service
@Slf4j
public class SLogServiceImpl extends BaseSupport implements SLogService {

    @Autowired
    private SysLoginLogService loginLogService;

    private UserAgentAnalyzer uaa = UserAgentAnalyzer
            .newBuilder()
            .withoutCache()
            .hideMatcherLoadStats()
            .build();

    /**
     * 保存登录日志和退出登录日志接口
     *
     * @param loginLogDto 入参对象
     */
    @Override
    @Transactional
    public void saveLoginLog(LoginLogDto loginLogDto) {
        if (loginLogDto.getType().equals(LoginLogDto.LOGIN)) {
            SysLoginLog loginLog = loginLogService.getOne(new QueryWrapper<SysLoginLog>().eq(SysLoginLog.SESSION_ID, loginLogDto.getSessionId()));
            if (loginLog == null) {
                UserAgent userAgent = uaa.parse(loginLogDto.getUserAgent());
                SysLoginLog sysLoginLog = new SysLoginLog();
                BeanUtils.copyProperties(loginLogDto, sysLoginLog);
                sysLoginLog.setDeviceClass(userAgent.getValue(UserAgent.DEVICE_CLASS))
                        .setDeviceName(userAgent.getValue(UserAgent.DEVICE_NAME))
                        .setDeviceBrand(userAgent.getValue(UserAgent.DEVICE_BRAND))
                        .setOs(userAgent.getValue(UserAgent.OPERATING_SYSTEM_NAME_VERSION))
                        .setBrowser(userAgent.getValue(UserAgent.AGENT_NAME_VERSION))
                        .setCreator(loginLogDto.getUserId())
                        .setCreateTime(LocalDateTime.now())
                        .setCreatorName(loginLogDto.getUserName());
                loginLogService.save(sysLoginLog);
            }
        } else {
            SysLoginLog loginLog = loginLogService.getOne(new QueryWrapper<SysLoginLog>().eq(SysLoginLog.SESSION_ID, loginLogDto.getSessionId()));
            loginLog.setLoginoutTime(loginLog.getLoginoutTime());
            loginLog.setUpdater(loginLogDto.getUserId());
            loginLog.setUpdaterName(loginLogDto.getUserName());
            loginLog.setUpdateTime(LocalDateTime.now());
            loginLogService.updateById(loginLog);
        }
    }
}
