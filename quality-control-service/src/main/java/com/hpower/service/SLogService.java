package com.hpower.service;

import com.hpower.dto.LoginLogDto;

/**
 * @Classname log日志服务接口类
 * @Description TODO
 * @Date 2020/3/27 8:35 上午
 * @Created yangyang.jiang
 */
public interface SLogService {

    /**
     * 保存登录日志和退出登录日志接口
     *
     * @param loginLogDto 入参对象
     */
    void saveLoginLog(LoginLogDto loginLogDto);
}
