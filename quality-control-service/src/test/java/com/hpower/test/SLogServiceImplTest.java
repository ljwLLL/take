package com.hpower.test;

import com.hpower.dto.LoginLogDto;
import com.hpower.service.SLogService;
import com.hpower.service.impl.SLogServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname SLogServiceImplTest
 * @Description TODO
 * @Date 2020/3/27 1:35 下午
 * @Created yangyang.jiang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SLogServiceImpl.class)
public class SLogServiceImplTest {

    @Autowired
    private SLogService sLogService;

    @Test
    public void saveLoginLog() {
        sLogService.saveLoginLog(LoginLogDto.builder().build());
    }
}