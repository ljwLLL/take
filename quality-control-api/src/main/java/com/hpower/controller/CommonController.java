package com.hpower.controller;

import com.hpower.entity.SysFile;
import com.hpower.param.*;
import com.hpower.service.SCommonService;
import com.hpower.vo.DictTypeVo;
import com.hpower.vo.R;
import com.hpower.vo.ResultSelectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Classname 公共接口控制层
 * @Description TODO
 * @Date 2020/3/26 5:21 下午
 * @Created yangyang.jiang
 */
@RestController
@RequestMapping("/api/common")
@Api(value = "公共接口控制层", description = "公共接口控制层")
public class CommonController {

    @Autowired
    private SCommonService sCommonService;

    @PostMapping(value = "/uploadFile")
    @ApiOperation(value = "文件的上传", notes = "文件的上传")
    public R<List<SysFile>> uploadFile(
            @ApiParam(value = "文件类型", required = true) @RequestParam String typeCode,
            HttpServletRequest request
    ) {
        if (StringUtils.isEmpty(typeCode)) {
            return R.failed("文件类型不能为空");
        }
        return sCommonService.uploadFile(typeCode, request);
    }


    @PostMapping("/monthExport")
    @ApiOperation(value = "月度-表单导出", notes = "月度-表单导出")
    public R<Object> monthExport(@Valid @RequestBody HospitalMonthParam param) {
        return sCommonService.ExportMonth(param);
    }

    @PostMapping("/yearExport")
    @ApiOperation(value = "年度-表单导出", notes = "年度-表单导出")
    public R<Object> yearExport(@Valid @RequestBody HospitalYearParam param) {
        return sCommonService.ExportYear(param);
    }

    @GetMapping(value = "/downLoadFile")
    @ApiOperation(value = "文件的下载", notes = "文件的下载")
    public R<Object> downloadFile(@Valid @ModelAttribute DownLoadFileParam downLoadFile) {
        return sCommonService.downLoadFile(downLoadFile);
    }

    @PostMapping(value = "/selectNextList")
    @ApiOperation(value = "根据父级id查询省市区信息", notes = "根据父级id查询省市区信息")
    public R<List<ResultSelectVo>> selectNextList(@Valid @RequestBody RegionParam regionParam) {
        return sCommonService.selectNextList(regionParam);
    }

    @PostMapping(value = "/selectDictDataList")
    @ApiOperation(value = "查询所有字典项", notes = "查询所有字典项")
    public R<List<DictTypeVo>> selectDictDataList() {
        return sCommonService.selectDictDataList();
    }

    @PostMapping(value = "/selectHospitalList")
    @ApiOperation(value = "根据质控中心id查询医院信息", notes = "根据质控中心id查询医院信息")
    public R<List<ResultSelectVo>> selectHospitalList(@Valid @RequestBody HospitalListParam param) {
        return sCommonService.selectHospitalList(param);
    }

    @PostMapping(value = "/selectRoleList")
    @ApiOperation(value = "根据质控中心id查询角色信息", notes = "根据质控中心id查询角色信息")
    public R<List<ResultSelectVo>> selectRoleList() {
        return sCommonService.selectRoleList();
    }
}
