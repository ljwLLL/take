package com.hpower.test;

import com.hpower.runner.QualityControlApplicationRunner;
import com.hpower.service.SCommonService;
import com.hpower.service.impl.SCommonServiceImpl;
import com.hpower.service.impl.SLogServiceImpl;
import com.hpower.service.impl.SysFileServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname SCommonServiceTest
 * @Description TODO
 * @Date 2020/4/3 2:04 下午
 * @Created yangyang.jiang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SCommonServiceTest {

    @Autowired
    private SCommonService sCommonService;

    /**
     * 定时器触发生成医院月度
     */
    @Test
    public void createHospitalMonthTest(){
        sCommonService.createHospitalMonth();
    }
}