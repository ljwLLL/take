package com.hpower.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.entity.HospitalMonth;
import com.hpower.entity.HospitalYear;
import com.hpower.param.HospitalMonthParam;
import com.hpower.param.HospitalYearParam;
import com.hpower.vo.HospitalMonthVo;
import com.hpower.vo.HospitalYearVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname 报表mapper类
 * @Description TODO
 * @Date 2020/3/30 4:29 下午
 * @Created yangyang.jiang
 */
@Component
public interface SReportMapper {

    /**
     * 分页查询医院月度报表信息
     *
     * @param page  分页对象
     * @param param 查询对象
     * @return 返回结果信息
     */
    List<HospitalMonthVo> selectHospitalMonthByPage(Page<HospitalMonthVo> page, @Param("param") HospitalMonthParam param);


    /**
     * 分页查询医院年度报表信息
     *
     * @param page  分页对象
     * @param param 查询对象
     * @return 返回结果信息
     */
    List<HospitalYearVo> selectHospitalYearByPage(Page<HospitalYearVo> page, @Param("param") HospitalYearParam param);

    /**
     * 根据条件查询所有月度报表信息
     *
     * @param param 入参对象
     * @return 返回月度报表信息
     */
    List<HospitalMonthVo> selectHospitalMonthList(@Param("param") HospitalMonthParam param);

    /**
     * 根据条件查询所有年度报表信息
     *
     * @param param 入参对象
     * @return 返回月度报表信息
     */
    List<HospitalYearVo> selectHospitalYearList(@Param("param") HospitalYearParam param);

    /**
     * 删除结构指标
     * @param monthId
     */
    void deleteHospitalMonthStructureDetail(Long monthId);

    /**
     * 删除过程指标
     * @param monthId
     */
    void deleteHospitalMonthProcessDetail(Long monthId);

    /**
     * 删除结果指标
     * @param monthId
     */
    void deleteHospitalMonthResultDetail(Long monthId);

    /**
     * 根据月度和质控中心id查询月度报表
     * @param queryMonth
     * @param qualityId
     * @return
     */
    List<HospitalMonth> queryHospitalMonthByDate(@Param("month") String queryMonth, @Param("id") Long qualityId);


    /**
     * 根据年度和质控中心id查询年度报表
     * @param queryYear
     * @param qualityId
     * @return
     */
    List<HospitalYear> queryHospitalYearByDate(@Param("year") String queryYear, @Param("id") Long qualityId);
}
