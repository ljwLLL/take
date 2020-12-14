package com.hpower.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.*;
import com.hpower.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname 报表上传接口服务层
 * @Description TODO
 * @Date 2020/3/27 3:22 下午
 * @Created yangyang.jiang
 */
public interface SReportService {

    /**
     * 分页查询月度报表信息
     *
     * @param param 查询入参对象
     * @return 返回分页封装对象
     */
    R<Page<HospitalMonthVo>> selectHospitalMonthByPage(HospitalMonthParam param);

    /**
     * 填写报表
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> writeHospitalMonth(HospitalMonthAddParam param);


    /**
     * 审核医院报表
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> verifyHospitalMonth(VerifyMonthParam param);

    /**
     * 删除医院报表信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> deleteHospitalMonth(IdParam param);

    /**
     * 修改医院月度报表
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> updateHospitalMonth(HospitalMonthAddParam param);

    /**
     * 根据id查询医院月报信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<HospitalMonthOneVo> selectHospitalMonthById(IdParam param);


    /**
     * 新增医院月度报表
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> addHospitalMonth(MonthParam param);

    /**
     * 查看驳回备注信息
     *
     * @param param 入参对象
     * @return 返回驳回备注信息
     */
    R<MonthRejectVo> selectMonthRejectById(IdParam param);

    /**
     * 分页查询年度报表信息
     *
     * @param param 入参对象
     * @return 返回年度报表信息
     */
    R<Page<HospitalYearVo>> selectHospitalYearByPage(HospitalYearParam param);

    /**
     * 填写年度报表信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> writeHospitalYear(HospitalYearAddParam param);

    /**
     * 根据id插叙医院年份报表信息
     *
     * @param param 入参对象
     * @return 返回年份报表信息
     */
    R<HospitalYearOneVo> selectHospitalYearById(IdParam param);

    /**
     * 修改年份报表信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> updateHospitalYear(HospitalYearAddParam param);

    /**
     * 审核医院年度报表
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> verifyHospitalYear(VerifyYearParam param);

    /**
     * 查询年度报表驳回信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<MonthRejectVo> selectYearRejectById(IdParam param);

    /**
     * 新增医院月度报表
     *
     * @param param 年度报表入参对象
     * @return 返回结果信息
     */
    R<Object> addHospitalYear(YearParam param);

    /**
     * 删除医院年度报表信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> deleteHospitalYear(IdParam param);

    /**
     * 导出月度报表信息
     *
     * @param param 入参对象
     * @return 返回数据流
     */
    void importHospitalMonth(HospitalMonthParam param, HttpServletRequest request, HttpServletResponse response);

    /**
     * 导出年度报表信息
     *
     * @param param 入参对象
     * @return 返回数据流
     */
    void importHospitalYear(HospitalYearParam param, HttpServletRequest request, HttpServletResponse response);
}
