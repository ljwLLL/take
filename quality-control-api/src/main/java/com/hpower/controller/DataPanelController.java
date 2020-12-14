package com.hpower.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.DataYearParam;
import com.hpower.param.PatientDetailParam;
import com.hpower.param.PatientParam;
import com.hpower.param.ProvinceHospitalParam;
import com.hpower.service.SDataPanelService;
import com.hpower.validator.groups.PageGroup;
import com.hpower.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.List;

/**
 * @Classname 数据总览控制层
 * @Description TODO
 * @Date 2020/3/27 3:16 下午
 * @Created yangyang.jiang
 */
@RestController
@RequestMapping("/api/panel")
@Api(value = "数据总览控制层", description = "数据总览控制层")
public class DataPanelController {

    @Autowired
    private SDataPanelService dataPanelService;


    @PostMapping("/selectHospital")
    @ApiOperation(value = "全省医院分布", notes = "全省医院分布")
    public R<List<ProvinceHospitalVo>> selectHospital(@Valid @RequestBody ProvinceHospitalParam param) {
        return dataPanelService.selectHospital(param);
    }

    @PostMapping("/selectReportList")
    @ApiOperation(value = "最新全省上报情况", notes = "最新全省上报情况")
    public R<ReportListVo> selectReportList() {
        return dataPanelService.selectReportList();
    }


    @PostMapping("/selectPatient")
    @ApiOperation(value = "月度指标", notes = "月度指标")
    public R<List<DataMonthVo>> selectPatient(@Valid @RequestBody PatientParam param) {
        return dataPanelService.selectPatient(param);
    }

    @PostMapping("/selectPatientDetail")
    @ApiOperation(value = "月度指标明细", notes = "月度指标明细")
    public R<Page<DataMonthElseVo>> selectPatientDetail(@Validated({PageGroup.class, Default.class}) @RequestBody PatientDetailParam param) {
        return dataPanelService.selectPatientDetail(param);
    }

    @PostMapping("/selectYearList")
    @ApiOperation(value = "年度指标", notes = "年度指标")
    public R<DataYearVo> selectYearList(@Valid @RequestBody DataYearParam param) {
        return dataPanelService.selectYearList(param);
    }
}
