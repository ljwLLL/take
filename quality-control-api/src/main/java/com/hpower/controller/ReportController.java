package com.hpower.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.*;
import com.hpower.service.SReportService;
import com.hpower.validator.groups.PageGroup;
import com.hpower.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.groups.Default;

/**
 * @Classname 报表分析控制层
 * @Description TODO
 * @Date 2020/3/27 3:17 下午
 * @Created yangyang.jiang
 */

@RestController
@RequestMapping("/api/report")
@Api(value = "报表上传控制层", description = "报表上传控制层")
public class ReportController {

    @Autowired
    private SReportService reportService;

    //==========================================月度报表相关接口=====================================

    @PostMapping("/selectHospitalMonthByPage")
    @ApiOperation(value = "分页查询医院月度报表信息", notes = "分页查询医院月度报表信息")
    public R<Page<HospitalMonthVo>> selectHospitalMonthByPage(@Validated({PageGroup.class, Default.class}) @RequestBody HospitalMonthParam param) {
        return reportService.selectHospitalMonthByPage(param);
    }

    @GetMapping("/importHospitalMonth")
    @ApiOperation(value = "导出医院月度报表信息", notes = "导出医院月度报表信息")
    public void importHospitalMonth(HospitalMonthParam param, HttpServletRequest request, HttpServletResponse response) {
        reportService.importHospitalMonth(param, request, response);
    }

    @PostMapping("/addHospitalMonth")
    @ApiOperation(value = "新增医院月度报表", notes = "新增医院月度报表")
    public R<Object> addHospitalMonth(@Valid @RequestBody MonthParam param) {
        return reportService.addHospitalMonth(param);
    }

    @PostMapping("/writeHospitalMonth")
    @ApiOperation(value = "填写医院月度报表信息", notes = "填写医院月度报表信息")
    public R<Object> writeHospitalMonth(@Valid @RequestBody HospitalMonthAddParam param) {
        return reportService.writeHospitalMonth(param);
    }

    @PostMapping("/updateHospitalMonth")
    @ApiOperation(value = "修改医院月度报表信息", notes = "修改医院月度报表信息")
    public R<Object> updateHospitalMonth(@Valid @RequestBody HospitalMonthAddParam param) {
        return reportService.updateHospitalMonth(param);
    }

    @PostMapping("/verifyHospitalMonth")
    @ApiOperation(value = "审核医院月度报表", notes = "审核医院月度报表")
    public R<Object> verifyHospitalMonth(@Valid @RequestBody VerifyMonthParam param) {
        return reportService.verifyHospitalMonth(param);
    }

    @PostMapping("/deleteHospitalMonth")
    @ApiOperation(value = "删除医院月度报表信息", notes = "删除医院月度报表信息")
    public R<Object> deleteHospitalMonth(@Valid @RequestBody IdParam param) {
        return reportService.deleteHospitalMonth(param);
    }

    @PostMapping("/selectHospitalMonthById")
    @ApiOperation(value = "根据id查询医院月报信息", notes = "根据id查询医院月报信息")
    public R<HospitalMonthOneVo> selectHospitalMonthById(@Valid @RequestBody IdParam param) {
        return reportService.selectHospitalMonthById(param);
    }

    @PostMapping("/selectMonthRejectById")
    @ApiOperation(value = "根据id查询月度报表驳回信息", notes = "根据id查询月度报表驳回信息")
    public R<MonthRejectVo> selectMonthRejectById(@Valid @RequestBody IdParam param) {
        return reportService.selectMonthRejectById(param);
    }


    //==========================================年度报表相关接口======================================

    @PostMapping("/selectHospitalYearByPage")
    @ApiOperation(value = "分页查询年度报表信息", notes = "分页查询年度报表信息")
    public R<Page<HospitalYearVo>> selectHospitalYearByPage(@Validated({PageGroup.class, Default.class}) @RequestBody HospitalYearParam param) {
        return reportService.selectHospitalYearByPage(param);
    }

    @GetMapping("/importHospitalYear")
    @ApiOperation(value = "导出医院年度报表信息", notes = "导出医院年度报表信息")
    public void importHospitalYear(HospitalYearParam param, HttpServletRequest request, HttpServletResponse response) {
        reportService.importHospitalYear(param, request, response);
    }

    @PostMapping("/addHospitalYear")
    @ApiOperation(value = "新增医院年度报表", notes = "新增医院年度报表")
    public R<Object> addHospitalYear(@Valid @RequestBody YearParam param) {
        return reportService.addHospitalYear(param);
    }

    @PostMapping("/writeHospitalYear")
    @ApiOperation(value = "填写年度报表信息", notes = "填写年度报表信息")
    public R<Object> writeHospitalYear(@Valid @RequestBody HospitalYearAddParam param) {
        return reportService.writeHospitalYear(param);
    }

    @PostMapping("/selectHospitalYearById")
    @ApiOperation(value = "根据id查询年份报表信息", notes = "根据id查询年份报表信息")
    public R<HospitalYearOneVo> selectHospitalYearById(@Valid @RequestBody IdParam param) {
        return reportService.selectHospitalYearById(param);
    }

    @PostMapping("/updateHospitalYear")
    @ApiOperation(value = "修改年度报表信息", notes = "修改年度报表信息")
    public R<Object> updateHospitalYear(@Valid @RequestBody HospitalYearAddParam param) {
        return reportService.updateHospitalYear(param);
    }

    @PostMapping("/verifyHospitalYear")
    @ApiOperation(value = "审核医院年度报表", notes = "审核医院年度报表")
    public R<Object> verifyHospitalYear(@Valid @RequestBody VerifyYearParam param) {
        return reportService.verifyHospitalYear(param);
    }

    @PostMapping("/deleteHospitalYear")
    @ApiOperation(value = "删除医院年度报表信息", notes = "删除医院年度报表信息")
    public R<Object> deleteHospitalYear(@Valid @RequestBody IdParam param) {
        return reportService.deleteHospitalYear(param);
    }

    @PostMapping("/selectYearRejectById")
    @ApiOperation(value = "根据id查询年度报表驳回信息", notes = "根据id查询年度报表驳回信息")
    public R<MonthRejectVo> selectYearRejectById(@Valid @RequestBody IdParam param) {
        return reportService.selectYearRejectById(param);
    }
}
