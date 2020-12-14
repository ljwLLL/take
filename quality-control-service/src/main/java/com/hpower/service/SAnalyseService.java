package com.hpower.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.*;
import com.hpower.vo.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Classname 统计分析接口服务层
 * @Description TODO
 * @Date 2020/3/27 3:22 下午
 * @Created yangyang.jiang
 */
public interface SAnalyseService {

    /**
     * 分页查询月度报表统计
     *
     * @param param 入参对象
     * @return 返回结果集合对象
     */
    R<Page<AnalyseMonthVo>> selectAnalyseMonthByPage(AnalyseMonthParam param);


    /**
     * 根据医院id分页查询月度报表信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Page<AnalyseMonthDetailVo>> selectAnalyseMonthDetailByPage(AnalyseMonthDetailParam param);

    /**
     * 根据月度id查询月度报表明细
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<AnalyseMonthOneVo> selectAnalyseMonth(AnalyseOneParam param);

    /**
     * 查找浙江省的月度报表历史记录
     * @param param 分页
     * @return
     */
    R<Page<AnalyseMonthHistoryVO>> selectAllMonthByPage(AnalyseMonthAllParam param);


/**************************************************年度数据*************************************************************************8*/


    /**
     * 返回年度页面的分页数据
     * @param analyseParam 记载可能有的医院名称, 参数可为空
     * @return 返回年度页面的数据, 多一个累计上报 / 医院名称 / 最近上报时间 / 上报人 / 手机号 / 所属科室 / 职称
     */
    R<Page<AnalyseYearVO>> selectYearAnalyse(AnalyseParam analyseParam);


    /**
     * 根据年度中每个医院的id,查询信息,查询的信息包括 年度总结的信息+历史记录
     * @param idParam 医院自带的id信息
     * @return 返回历史记录加最近的一条年度总结
     */
    R<AnalyseYearLookOneVO> lookYearAnalyseHospital(IdParam idParam);

    /**
     *  根据id查询某一条记录，年度的数据
     * @param idParam 年度数据的id t_hospital_year表的id
     * @return 某一年的记录
     */
    R<AnalyseYearLookOneVO> lookHistory(IdParam idParam);

    /**
     * 根据医院id查询这个医院的上报历史记录
     * @param analyseParam 医院id
     * @return 返回医院年度报表历史记录
     */
    R<Page<AnalyseYearHistoryVO>> yearHistory(AnalyseParam analyseParam);
}
