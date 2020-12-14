package com.hpower.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.DataYearParam;
import com.hpower.param.PatientDetailParam;
import com.hpower.param.PatientParam;
import com.hpower.param.ProvinceHospitalParam;
import com.hpower.vo.*;

import java.util.List;

/**
 * @Classname 数据总览服务接口层
 * @Description TODO
 * @Date 2020/3/27 3:23 下午
 * @Created yangyang.jiang
 */
public interface SDataPanelService {

    /**
     * 查询全省医院分布
     *
     * @param param 查询入参对象
     * @return 返回全省分布对象集合
     */
    R<List<ProvinceHospitalVo>> selectHospital(ProvinceHospitalParam param);

    /**
     * 全省上报情况
     *
     * @return 返回结果信息
     */
    R<ReportListVo> selectReportList();

    /**
     * 住院患者营养风险筛查率
     *
     * @param param 查询入参对象
     * @return 返回结果信息
     */
    R<List<DataMonthVo>> selectPatient(PatientParam param);

    /**
     * 根据医院查询月平均明细信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Page<DataMonthElseVo>> selectPatientDetail(PatientDetailParam param);

    /**
     * 查询年度信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<DataYearVo> selectYearList(DataYearParam param);
}
