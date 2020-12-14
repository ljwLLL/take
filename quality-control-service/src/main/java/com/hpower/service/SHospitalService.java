package com.hpower.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.EnabledParam;
import com.hpower.param.HospitalAddParam;
import com.hpower.param.HospitalParam;
import com.hpower.param.IdParam;
import com.hpower.vo.HospitalOneVo;
import com.hpower.vo.HospitalVo;
import com.hpower.vo.R;

/**
 * @Classname 医院服务接口
 * @Description TODO
 * @Date 2020/3/30 10:50 上午
 * @Created yangyang.jiang
 */
public interface SHospitalService {

    /**
     * 分页查询医院信息
     *
     * @param param 查询入参对象
     * @return 返回查询结果对象
     */
    R<Page<HospitalVo>> selectHospitalListByPage(HospitalParam param);

    /**
     * 新增医院信息
     *
     * @param param 新增医院入参对象
     * @return 返回结果信息
     */
    R<Object> addHospital(HospitalAddParam param);

    /**
     * 根据id查询医院信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<HospitalOneVo> selectHospitalById(IdParam param);

    /**
     * 修改医院信息
     *
     * @param param 医院修改入参对象
     * @return 返回结果信息
     */
    R<Object> updateHospital(HospitalAddParam param);

    /**
     * 删除医院信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> deleteHospital(IdParam param);

    /**
     * 启用禁用医院信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> enabled(EnabledParam param);
}
