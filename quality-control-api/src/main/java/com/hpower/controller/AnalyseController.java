package com.hpower.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.*;
import com.hpower.service.SAnalyseService;
import com.hpower.validator.groups.PageGroup;
import com.hpower.vo.*;
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
import java.util.List;

/**
 * @Classname 报表分析控制器
 * @Description TODO
 * @Date 2020/3/27 3:18 下午
 * @Created yangyang.jiang
 */
@RestController
@RequestMapping("/api/analyse")
@Api(value = "报表分析控制器", description = "报表分析控制器")
public class AnalyseController {

    @Autowired
    private SAnalyseService analyseService;

    @PostMapping("/selectAnalyseMonthByPage")
    @ApiOperation(value = "分页查询月度报表统计", notes = "分页查询月度报表统计")
    public R<Page<AnalyseMonthVo>> selectAnalyseMonthByPage(@Validated({PageGroup.class, Default.class}) @RequestBody AnalyseMonthParam param) {
        return analyseService.selectAnalyseMonthByPage(param);
    }

    @PostMapping("/selectAnalyseMonthDetailByPage")
    @ApiOperation(value = "根据医院id分页查询月度报表信息", notes = "根据医院id分页查询月度报表信息")
    public R<Page<AnalyseMonthDetailVo>> selectAnalyseMonthDetailByPage(@Validated({PageGroup.class, Default.class}) @RequestBody AnalyseMonthDetailParam param) {
        return analyseService.selectAnalyseMonthDetailByPage(param);
    }

    @PostMapping("/selectAnalyseMonth")
    @ApiOperation(value = "根据月度id查询月度报表明细", notes = "根据月度id查询月度报表明细")
    public R<AnalyseMonthOneVo> selectAnalyseMonth(@Valid @RequestBody AnalyseOneParam param) {
        return analyseService.selectAnalyseMonth(param);
    }

    @PostMapping("/selectAllMonthByPage")
    @ApiOperation(value = "浙江省全部的上报记录统计",notes = "浙江省全部的上报记录统计")
    public R<Page<AnalyseMonthHistoryVO>> selectAllMonthByPage(@RequestBody AnalyseMonthAllParam param){
        return analyseService.selectAllMonthByPage(param);
    }

    /**************************************************年度数据**********************************************************/


    @PostMapping(value = "/selectYearAnalyseListByPage")
    @ApiOperation(value = "年度-分页数据", notes = "年度-分页数据")
    public R<Page<AnalyseYearVO>> selectYearAnalyse(@Validated({PageGroup.class, Default.class}) @RequestBody AnalyseParam analyseParam) {
        return analyseService.selectYearAnalyse(analyseParam);
    }


    @ApiOperation(value = "年度-（单个医院,根据医院id）", notes = "年度-查看数据（单个医院,根据医院id）")
    @PostMapping(value = "/lookYearAnalyseHospital")
    public R<AnalyseYearLookOneVO> lookYearAnalyseHospital(@Valid @RequestBody IdParam idParam) {
        return analyseService.lookYearAnalyseHospital(idParam);
    }

    @ApiOperation(value = "年度-返回历史记录,根据医院id", notes = "年度-返回历史记录,根据医院id")
    @PostMapping(value = "/yearHistory")
    public R<Page<AnalyseYearHistoryVO>> yearHistory(@Valid @RequestBody AnalyseParam analyseParam) {
        return analyseService.yearHistory(analyseParam);
    }

    @ApiOperation(value = "年度-单条数据（弹窗,根据年度报表的id查询）", notes = "年度-单条数据（弹窗，根据年度报表的id查询）")
    @PostMapping(value = "/lookHistory")
    public R<AnalyseYearLookOneVO> lookHistory(@Valid @RequestBody IdParam idParam) {
        return analyseService.lookHistory(idParam);
    }

}
