package com.hpower.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.EnabledParam;
import com.hpower.param.HospitalAddParam;
import com.hpower.param.HospitalParam;
import com.hpower.param.IdParam;
import com.hpower.service.SHospitalService;
import com.hpower.validator.groups.AddGroup;
import com.hpower.validator.groups.PageGroup;
import com.hpower.validator.groups.UpdateGroup;
import com.hpower.vo.HospitalOneVo;
import com.hpower.vo.HospitalVo;
import com.hpower.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.groups.Default;

/**
 * @Classname 医院管理控制器
 * @Description TODO
 * @Date 2020/3/30 10:45 上午
 * @Created yangyang.jiang
 */
@RestController
@RequestMapping("/api/hospital")
@Api(value = "医院管理控制器", description = "医院管理控制器")
public class HospitalController {

    @Autowired
    private SHospitalService hospitalService;

    @PostMapping("/selectHospitalListByPage")
    @ApiOperation(value = "分页查询医院集合信息", notes = "分页查询医院集合信息")
    public R<Page<HospitalVo>> selectHospitalListByPage(@Validated({PageGroup.class, Default.class}) @RequestBody HospitalParam param) {
        return hospitalService.selectHospitalListByPage(param);
    }

    @PostMapping("/addHospital")
    @ApiOperation(value = "新增医院信息", notes = "新增医院信息")
    public R<Object> addHospital(@Validated({AddGroup.class, Default.class}) @RequestBody HospitalAddParam param) {
        return hospitalService.addHospital(param);
    }

    @PostMapping("/selectHospitalById")
    @ApiOperation(value = "根据id查询医院信息", notes = "根据id查询医院信息")
    public R<HospitalOneVo> selectHospitalById(@Valid @RequestBody IdParam param) {
        return hospitalService.selectHospitalById(param);
    }

    @PostMapping("/updateHospital")
    @ApiOperation(value = "修改医院信息", notes = "修改医院信息")
    public R<Object> updateHospital(@Validated({UpdateGroup.class, Default.class}) @RequestBody HospitalAddParam param) {
        return hospitalService.updateHospital(param);
    }

    @PostMapping("/deleteHospital")
    @ApiOperation(value = "删除医院信息", notes = "删除医院信息")
    public R<Object> deleteHospital(@Valid @RequestBody IdParam param) {
        return hospitalService.deleteHospital(param);
    }

    @PostMapping("/enabled")
    @ApiOperation(value = "启用禁用医院", notes = "启用禁用医院")
    public R<Object> enabled(@Valid @RequestBody EnabledParam param) {
        return hospitalService.enabled(param);
    }
}
