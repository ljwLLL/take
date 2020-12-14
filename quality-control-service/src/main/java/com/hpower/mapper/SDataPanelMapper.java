package com.hpower.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.dto.DataPanelDto;
import com.hpower.entity.HospitalYear;
import com.hpower.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname 总览mapper接口
 * @Description TODO
 * @Date 2020/4/2 3:12 下午
 * @Created yangyang.jiang
 */
@Component
public interface SDataPanelMapper {

    /**
     * 根据指标选择查询数据
     *
     * @param dataPanelDto 入参对象
     * @return 返回集合信息
     */
    List<DataMonthDetailVo> selectPatient(@Param("dto") DataPanelDto dataPanelDto);


    /**
     * 分页查询月度指标明细信息
     *
     * @param page         分页对象
     * @param dataPanelDto 查询条件
     * @return 返回结果信息
     */
    List<DataMonthElseVo> selectPatientDetail(Page<DataMonthElseVo> page, @Param("dto") DataPanelDto dataPanelDto);

    /**
     * 按照条件查询年度报表信息
     *
     * @param dataPanelDto 入参查询对象
     * @return 返回结果信息
     */
    List<Integer> selectHospitalYearList(@Param("dto") DataPanelDto dataPanelDto);

    /**
     * 营养科专业人员和床位比分布统计
     *
     * @param dataPanelDto 入参对象
     * @return 返回结果信息
     */
    List<DataYearCommonDetailVo> selectUserBedList(@Param("dto")DataPanelDto dataPanelDto);

    /**
     * 不同等级医院中住院病人营养风险筛查率的分布
     *
     * @param dataPanelDto 入参对象
     * @return 返回结果信息
     */
    List<DataYearCommonDetailVo> selectPatientList(@Param("dto")DataPanelDto dataPanelDto);

    /**
     * 不同等级医院中有肠内营养配制室的比例
     *
     * @param dataPanelDto 入参对象
     * @return 返回结果信息
     */
    List<DataYearCommonDetailVo> selectEntericList(@Param("dto")DataPanelDto dataPanelDto);

    /**
     * 不同等级医院营养科是否有独立收费项目分布
     *
     * @param dataPanelDto 入参对象
     * @return 返回结果信息
     */
    List<DataYearCommonDetailVo> selectOwnList(@Param("dto")DataPanelDto dataPanelDto);
}
