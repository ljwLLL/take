package com.hpower.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.dto.DataPanelDto;
import com.hpower.entity.Hospital;
import com.hpower.param.AnalyseMonthDetailParam;
import com.hpower.param.AnalyseMonthParam;
import com.hpower.param.AnalyseOneParam;
import com.hpower.param.AnalyseParam;
import com.hpower.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname 报表统计mapper接口
 * @Description TODO
 * @Date 2020/4/2 3:12 下午
 * @Created yangyang.jiang
 */
@Component
public interface SAnalyseMapper {

    /**
     * 分页查询月度报表统计信息
     *
     * @param page  分页入参对象
     * @param param 入参对象
     * @return 返回集合信息
     */
    List<AnalyseMonthVo> selectAnalyseMonthByPage(Page<AnalyseMonthVo> page, @Param("param") AnalyseMonthParam param);


    Integer selectCount(@Param("param") AnalyseMonthParam param);

    /**
     * 查询医院id查询月度报表信息
     *
     * @param page  分页对象
     * @param param 查询对象
     * @return 返回结果信息
     */
    List<AnalyseMonthDetailVo> selectAnalyseMonthDetailByPage(Page<AnalyseMonthDetailVo> page, @Param("param") AnalyseMonthDetailParam param);

    /**
     * 查询单个月度报表信息
     *
     * @param param 查询对象
     * @return 返回结果信息
     */
    AnalyseMonthOneVo selectAnalyseMonth(@Param("param") AnalyseOneParam param);

    /**
     * 查询所有月度报表总和
     *
     * @param param 查询对象
     * @return 返回结果信息
     */
    AnalyseMonthOneVo selectAllAnalyseMonth(@Param("param") AnalyseOneParam param);


    /**
     * 查询所有医院年份总结,累计报告,最近上报时间等信息
     *
     * @param page  分页信息数据
     * @param param 携带医院参数
     * @return
     */
    List<AnalyseYearVO> selectAnalyseYear(Page<AnalyseYearVO> page, @Param("param") AnalyseParam param);

    /**
     * 返回年度信息中的历史记录
     *
     * @param page
     * @param param 携带医院参数
     * @return 返回这个医院年度的历史记录
     */
    List<AnalyseYearHistoryVO> selectAnalyseYearHistory(Page<AnalyseYearHistoryVO> page, @Param("param") AnalyseParam param);

    /**
     * 查找浙江省的月度报表的历史记录
     *
     * @param page 分页数据
     * @return
     */
    List<AnalyseMonthHistoryVO> selectAllMonthHistory(Page<AnalyseMonthHistoryVO> page);

}
