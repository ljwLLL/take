package com.hpower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.dto.LoginUser;
import com.hpower.entity.*;
import com.hpower.enums.PermissionEnum;
import com.hpower.errorcode.ApiErrorCode;
import com.hpower.mapper.SHospitalMapper;
import com.hpower.param.*;
import com.hpower.service.*;
import com.hpower.support.BaseSupport;
import com.hpower.util.Convert;
import com.hpower.util.StringUtils;
import com.hpower.vo.HospitalOneVo;
import com.hpower.vo.HospitalVo;
import com.hpower.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname 医院服务实现类
 * @Description TODO
 * @Date 2020/3/30 10:50 上午
 * @Created yangyang.jiang
 */
@Service
@Slf4j
public class SHospitalServiceImpl extends BaseSupport implements SHospitalService {

    @Autowired
    private SHospitalMapper hospitalMapper;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private SysRegionService sysRegionService;

    @Autowired
    private HospitalMonthService hospitalMonthService;

    @Autowired
    private HospitalYearService hospitalYearService;

    @Autowired
    private QualityUserService qualityUserService;

    /**
     * 分页查询医院信息
     *
     * @param param 查询入参对象
     * @return 返回查询结果对象
     */
    @Override
    public R<Page<HospitalVo>> selectHospitalListByPage(HospitalParam param) {
        Page<HospitalVo> page = new Page<>(param.getCurrent(), param.getSize());
        LoginUser loginUser = getLoginUser();
        System.out.println(loginUser);
        if (StringUtils.isNotNull(loginUser.getPermissionType())) {
            //根据质控中心查询医院信息
            if (loginUser.getPermissionType().equals(PermissionEnum.ALL.getValue())) {
                param.setType(HospitalParam.TYPE_ALL);
            } else {
                if (StringUtils.isNotEmpty(loginUser.getHospitalIds())) {
                    param.setType(HospitalParam.TYPE_ONLY);
                    param.setList(Arrays.asList(Convert.toLongArray(loginUser.getHospitalIds())));
                }
            }
            List<HospitalVo> list = hospitalMapper.selectHospitalListByPage(page, param);
            page.setRecords(list);
        }
        return R.ok(page);
    }


    /**
     * 新增医院信息
     *
     * @param param 新增医院入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> addHospital(HospitalAddParam param) {
        LoginUser loginUser = getLoginUser();
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(param, hospital);
        hospital.setCreateTime(LocalDateTime.now());
        hospital.setCreator(loginUser.getId());
        hospital.setCreatorName(loginUser.getName());
        hospital.setQualityId(loginUser.getQualityId());
        //获取省份信息
        SysRegion province = sysRegionService.getById(param.getProvinceCode());
        hospital.setProvinceName(province.getRegionName());
        //获取城市信息
        SysRegion city = sysRegionService.getById(param.getCityCode());
        hospital.setCityName(city.getRegionName());
        //获取城市信息
        SysRegion area = sysRegionService.getById(param.getAreaCode());
        hospital.setAreaName(area.getRegionName());
        hospitalService.save(hospital);
        return R.success();
    }


    /**
     * 根据id查询医院信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<HospitalOneVo> selectHospitalById(IdParam param) {
        Hospital hospital = hospitalService.getById(param.getId());
        if (hospital == null) {
            return R.failed(ApiErrorCode.HOSPITAL_NOT_EXISTS);
        }
        HospitalOneVo hospitalVo = new HospitalOneVo();
        BeanUtils.copyProperties(hospital, hospitalVo);
        hospitalVo.setHospitalCode(hospital.getCode());
        hospitalVo.setHospitalName(hospital.getName());
        return R.ok(hospitalVo);
    }


    /**
     * 修改医院信息
     *
     * @param param 医院修改入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> updateHospital(HospitalAddParam param) {
        LoginUser loginUser = getLoginUser();
        Hospital hospital = hospitalService.getById(param.getId());
        if (hospital == null) {
            return R.failed(ApiErrorCode.HOSPITAL_NOT_EXISTS);
        }
        hospital.setCode(param.getCode());
        if (!hospital.getName().equals(param.getName())) {
            hospital.setName(param.getName());
            List<HospitalMonth> monthList = hospitalMonthService.list(new QueryWrapper<HospitalMonth>().eq(HospitalMonth.HOSPITAL_ID, hospital.getId()));
            monthList.forEach(month -> {
                month.setHospitalName(param.getName());
                hospitalMonthService.updateById(month);
            });
            List<HospitalYear> yearList = hospitalYearService.list(new QueryWrapper<HospitalYear>().eq(HospitalYear.HOSPITAL_ID, hospital.getId()));
            yearList.forEach(year->{
                year.setHospitalName(param.getName());
                hospitalYearService.updateById(year);
            });
        }
        hospital.setLevel(param.getLevel());
        if (!hospital.getProvinceCode().equals(param.getProvinceCode())) {
            //获取省份信息
            SysRegion province = sysRegionService.getById(param.getProvinceCode());
            hospital.setProvinceName(province.getRegionName());
            hospital.setProvinceCode(province.getId());
        }
        if (!hospital.getCityCode().equals(param.getCityCode())) {
            //获取城市信息
            SysRegion city = sysRegionService.getById(param.getCityCode());
            hospital.setCityName(city.getRegionName());
            hospital.setCityCode(city.getId());
        }
        if (!hospital.getAreaCode().equals(param.getCityCode())) {
            //获取城市信息
            SysRegion area = sysRegionService.getById(param.getAreaCode());
            hospital.setAreaName(area.getRegionName());
            hospital.setAreaCode(area.getId());
        }
        hospital.setUpdater(loginUser.getId());
        hospital.setUpdaterName(loginUser.getName());
        hospital.setUpdateTime(LocalDateTime.now());
        hospitalService.updateById(hospital);
        return R.success();
    }

    /**
     * 删除医院信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> deleteHospital(IdParam param) {
        //判断医院是否存在
        Hospital hospital = hospitalService.getById(param.getId());
        if (hospital == null) {
            return R.failed(ApiErrorCode.HOSPITAL_NOT_EXISTS);
        }
        List<HospitalMonth> hospitalMonthList = hospitalMonthService.list(new QueryWrapper<HospitalMonth>().eq(HospitalMonth.HOSPITAL_ID, param.getId()));
        List<HospitalYear> hospitalYearList = hospitalYearService.list(new QueryWrapper<HospitalYear>().eq(HospitalYear.HOSPITAL_ID, param.getId()));
        List<QualityUser> hospitalUserList = qualityUserService.list(new QueryWrapper<QualityUser>().eq(QualityUser.HOSPITAL_ID, param.getId()));
        if (hospitalMonthList.size() > 0 || hospitalYearList.size() > 0 || hospitalUserList.size() > 0) {
            return R.failed(ApiErrorCode.HOSPITAL_DATA_EXISTS);
        }
        hospitalService.removeById(param.getId());
        return R.success();
    }


    /**
     * 启用禁用医院信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> enabled(EnabledParam param) {
        LoginUser loginUser = getLoginUser();
        //判断医院是否存在
        Hospital hospital = hospitalService.getById(param.getId());
        if (hospital == null) {
            return R.failed(ApiErrorCode.HOSPITAL_NOT_EXISTS);
        }
        hospital.setUpdateTime(LocalDateTime.now());
        hospital.setUpdater(loginUser.getId());
        hospital.setUpdaterName(loginUser.getName());
        hospital.setEnabled(param.getEnabled());
        hospitalService.updateById(hospital);
        return R.success();
    }
}
