package com.hpower.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.HospitalParam;
import com.hpower.vo.HospitalVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname 医院mapper接口
 * @Description TODO
 * @Date 2020/3/30 10:52 上午
 * @Created yangyang.jiang
 */
@Component
public interface SHospitalMapper {

    /**
     * 分页查询医院信息
     *
     * @param page  分页对象
     * @param param 查询对象
     * @return hospitalVo集合
     */
    List<HospitalVo> selectHospitalListByPage(Page<HospitalVo> page, @Param("param") HospitalParam param);
}
