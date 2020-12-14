package com.hpower.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.dto.LoginUser;
import com.hpower.entity.*;
import com.hpower.enums.PermissionEnum;
import com.hpower.enums.ReportProcessEnum;
import com.hpower.enums.ReportStateEnum;
import com.hpower.enums.WhetherEnum;
import com.hpower.errorcode.ApiErrorCode;
import com.hpower.mapper.SReportMapper;
import com.hpower.param.*;
import com.hpower.service.*;
import com.hpower.support.BaseSupport;
import com.hpower.util.Convert;
import com.hpower.util.ExcelExportUtil;
import com.hpower.util.StringUtils;
import com.hpower.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hpower.errorcode.ApiErrorCode.*;

/**
 * @Classname 报表上传接口服务实现层
 * @Description TODO
 * @Date 2020/3/27 3:26 下午
 * @Created yangyang.jiang
 */
@Service
@Slf4j
public class SReportServiceImpl extends BaseSupport implements SReportService {

    @Value("${file.filePath}")
    String uploadFile;

    @Autowired
    private SReportMapper reportMapper;

    @Autowired
    private HospitalMonthService hospitalMonthService;

    @Autowired
    private HospitalYearService hospitalYearService;

    @Autowired
    private HospitalMonthStructureDetailService structureDetailService;

    @Autowired
    private HospitalMonthProcessDetailService processDetailService;

    @Autowired
    private HospitalMonthResultDetailService resultDetailService;

    @Autowired
    private HospitalMonthProcessService processService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalYearBaseDetailService baseDetailService;

    @Autowired
    private HospitalYearUserDetailService userDetailService;

    @Autowired
    private HospitalYearFunctionDetailService functionDetailService;

    @Autowired
    private HospitalYearProcessService hospitalYearProcessService;

    /**
     * 分页查询月度报表信息
     *
     * @param param 查询入参对象
     * @return 返回分页封装对象
     */
    @Override
    public R<Page<HospitalMonthVo>> selectHospitalMonthByPage(HospitalMonthParam param) {
        Page<HospitalMonthVo> page = new Page<>(param.getCurrent(), param.getSize());
        //获取当前用户信息
        LoginUser loginUser = getLoginUser();
        if (StringUtils.isNotEmpty(param.getStartTime())) {
            param.setStartTime(param.getStartTime() + " 00:00:00");
        }
        if (StringUtils.isNotEmpty(param.getEndTime())) {
            param.setEndTime(param.getEndTime() + " 23:59:59");
        }
        param.setQualityId(loginUser.getQualityId());
        List<HospitalMonthVo> list = new ArrayList<>();
        if (StringUtils.isNotNull(loginUser.getPermissionType())) {
            //根据质控中心查询医院信息
            if (loginUser.getPermissionType().equals(PermissionEnum.ALL.getValue())) {
                param.setType(HospitalMonthParam.TYPE_ALL);
            } else {
                if (StringUtils.isNotEmpty(loginUser.getHospitalIds())) {
                    param.setType(HospitalMonthParam.TYPE_ONLY);
                    param.setList(Arrays.asList(Convert.toLongArray(loginUser.getHospitalIds())));
                }
            }
            list = reportMapper.selectHospitalMonthByPage(page, param);
        }
        page.setRecords(list);
        return R.ok(page);
    }


    /**
     * 填写报表
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public synchronized R<Object> writeHospitalMonth(HospitalMonthAddParam param) {
        LoginUser loginUser = getLoginUser();
        //判断月度报表是否存在
        HospitalMonth hospitalMonth = hospitalMonthService.getById(param.getMonthId());
        if (hospitalMonth == null) {
            return R.failed(MONTH_NOT_EXISTS);
        }
        if (!hospitalMonth.getState().equals(ReportStateEnum.NO.getValue())) {
            return R.failed(MONTH_IS_COMMIT);
        }
        BeanUtils.copyProperties(param, hospitalMonth);
        hospitalMonth.setState(ReportStateEnum.WAIT.getValue());
        hospitalMonth.setUpdater(loginUser.getId());
        hospitalMonth.setUpdaterName(loginUser.getName());
        hospitalMonth.setUpdateTime(LocalDateTime.now());
        hospitalMonthService.updateById(hospitalMonth);
        StructureParam structureParam = param.getStructureParam();
        //新增月度报表结构指标
        HospitalMonthStructureDetail structureDetail = new HospitalMonthStructureDetail();
        BeanUtils.copyProperties(structureParam, structureDetail);
        structureDetail.setCreateTime(LocalDateTime.now());
        structureDetail.setCreator(loginUser.getId());
        structureDetail.setCreatorName(loginUser.getName());
        structureDetail.setMonthId(hospitalMonth.getId());
        structureDetailService.save(structureDetail);
        //新增月度过程指标
        HospitalMonthProcessDetail processDetail = new HospitalMonthProcessDetail();
        ProcessParam processParam = param.getProcessParam();
        BeanUtils.copyProperties(processParam, processDetail);
        processDetail.setCreateTime(LocalDateTime.now());
        processDetail.setCreator(loginUser.getId());
        processDetail.setCreatorName(loginUser.getName());
        processDetail.setMonthId(hospitalMonth.getId());
        processDetailService.save(processDetail);
        //新增结果指标
        HospitalMonthResultDetail resultDetail = new HospitalMonthResultDetail();
        ResultParam resultParam = param.getResultParam();
        BeanUtils.copyProperties(resultParam, resultDetail);
        resultDetail.setCreateTime(LocalDateTime.now());
        resultDetail.setCreator(loginUser.getId());
        resultDetail.setCreatorName(loginUser.getName());
        resultDetail.setMonthId(hospitalMonth.getId());
        resultDetailService.save(resultDetail);
        //新增报表流程信息
        HospitalMonthProcess process = new HospitalMonthProcess()
                .setName(loginUser.getName())
                .setState(ReportProcessEnum.WAIT.getValue())
                .setMonthId(hospitalMonth.getId())
                .setCreateTime(LocalDateTime.now())
                .setCreator(loginUser.getId())
                .setCreatorName(loginUser.getName())
                .setTime(LocalDateTime.now())
                .setUserId(loginUser.getId());
        processService.save(process);
        return R.success();
    }


    /**
     * 审核医院报表
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> verifyHospitalMonth(VerifyMonthParam param) {
        LoginUser loginUser = getLoginUser();
        if (param.getState().equals(WhetherEnum.NO.getValue())) {
            if (StringUtils.isEmpty(param.getRemark())) {
                return R.failed(REMARK_NOT_EXISTS);
            }
        }
        //判断月度报表是否存在
        HospitalMonth hospitalMonth = hospitalMonthService.getById(param.getMonthId());
        if (hospitalMonth == null) {
            return R.failed(MONTH_NOT_EXISTS);
        }
        //新增报表流程信息
        HospitalMonthProcess process = new HospitalMonthProcess()
                .setName(loginUser.getName())
                .setMonthId(hospitalMonth.getId())
                .setCreateTime(LocalDateTime.now())
                .setCreator(loginUser.getId())
                .setCreatorName(loginUser.getName())
                .setTime(LocalDateTime.now())
                .setUserId(loginUser.getId());
        if (param.getState().equals(WhetherEnum.NO.getValue())) {
            hospitalMonth.setState(ReportStateEnum.REJECT.getValue());
            process.setState(ReportProcessEnum.REJECT.getValue());
            process.setSuggest(param.getRemark());
        } else {
            hospitalMonth.setState(ReportStateEnum.FINISH.getValue());
            hospitalMonth.setTime(LocalDateTime.now());
            process.setState(ReportProcessEnum.FINISH.getValue());
        }
        hospitalMonth.setUpdateTime(LocalDateTime.now());
        hospitalMonth.setUpdaterName(loginUser.getName());
        hospitalMonth.setUpdater(loginUser.getId());
        hospitalMonthService.updateById(hospitalMonth);
        processService.save(process);
        return R.success();
    }

    /**
     * 删除医院报表信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> deleteHospitalMonth(IdParam param) {
        //判断月度报表是否存在
        HospitalMonth hospitalMonth = hospitalMonthService.getById(param.getId());
        if (hospitalMonth == null) {
            return R.failed(MONTH_NOT_EXISTS);
        }
        //报表已上传，不允许删除
        if (hospitalMonth.getState().equals(ReportStateEnum.FINISH.getValue())) {
            return R.failed(MONTH_IS_FINISH);
        }
        hospitalMonthService.removeById(param.getId());
        return R.success();
    }


    /**
     * 修改医院月度报表
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> updateHospitalMonth(HospitalMonthAddParam param) {
        LoginUser loginUser = getLoginUser();
        //判断月度报表是否存在
        HospitalMonth hospitalMonth = hospitalMonthService.getById(param.getMonthId());
        if (hospitalMonth == null) {
            return R.failed(MONTH_NOT_EXISTS);
        }
        /**
         * 1.报表已上传，不允许修改
         * 2.报表已驳回，修改报表状态
         */
        if (hospitalMonth.getState().equals(ReportStateEnum.FINISH.getValue())) {
            return R.failed(MONTH_FINISH);
        } else if (hospitalMonth.getState().equals(ReportStateEnum.REJECT.getValue())) {
            hospitalMonth.setState(ReportStateEnum.WAIT.getValue());
        }
        //修改报表信息
        BeanUtils.copyProperties(param, hospitalMonth);
        hospitalMonth.setUpdater(loginUser.getId());
        hospitalMonth.setUpdaterName(loginUser.getName());
        hospitalMonth.setUpdateTime(LocalDateTime.now());
        hospitalMonthService.updateById(hospitalMonth);
        //修改月度结构指标
        StructureParam structureParam = param.getStructureParam();
        HospitalMonthStructureDetail structureDetail = structureDetailService.getOne(new QueryWrapper<HospitalMonthStructureDetail>().eq(HospitalMonthStructureDetail.MONTH_ID, hospitalMonth.getId()));
        BeanUtils.copyProperties(structureParam, structureDetail);
        structureDetail.setUpdater(loginUser.getId());
        structureDetail.setUpdaterName(loginUser.getName());
        structureDetail.setUpdateTime(LocalDateTime.now());
        structureDetailService.updateById(structureDetail);
        //新增结果指标
        HospitalMonthResultDetail resultDetail = resultDetailService.getOne(new QueryWrapper<HospitalMonthResultDetail>().eq(HospitalMonthResultDetail.MONTH_ID, hospitalMonth.getId()));
        ResultParam resultParam = param.getResultParam();
        BeanUtils.copyProperties(resultParam, resultDetail);
        resultDetail.setUpdateTime(LocalDateTime.now());
        resultDetail.setUpdater(loginUser.getId());
        resultDetail.setUpdaterName(loginUser.getName());
        resultDetailService.updateById(resultDetail);
        //新增月度过程指标
        HospitalMonthProcessDetail processDetail = processDetailService.getOne(new QueryWrapper<HospitalMonthProcessDetail>().eq(HospitalMonthProcessDetail.MONTH_ID, hospitalMonth.getId()));
        ProcessParam processParam = param.getProcessParam();
        BeanUtils.copyProperties(processParam, processDetail);
        processDetail.setUpdateTime(LocalDateTime.now());
        processDetail.setUpdater(loginUser.getId());
        processDetail.setUpdaterName(loginUser.getName());
        processDetailService.updateById(processDetail);
        //新增月度报表流程信息
        HospitalMonthProcess process = new HospitalMonthProcess()
                .setState(ReportProcessEnum.UPDATE.getValue())
                .setCreateTime(LocalDateTime.now())
                .setCreator(loginUser.getId())
                .setCreatorName(loginUser.getName())
                .setMonthId(hospitalMonth.getId())
                .setTime(LocalDateTime.now())
                .setName(loginUser.getName())
                .setUserId(loginUser.getId());
        processService.save(process);
        return R.success();
    }

    /**
     * 根据id查询医院月报信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<HospitalMonthOneVo> selectHospitalMonthById(IdParam param) {
        //判断月度报表是否存在
        HospitalMonth hospitalMonth = hospitalMonthService.getById(param.getId());
        if (hospitalMonth == null) {
            return R.failed(MONTH_NOT_EXISTS);
        }
        HospitalMonthOneVo monthOneVo = new HospitalMonthOneVo();
        if (!hospitalMonth.getState().equals(ReportStateEnum.NO.getValue())) {
            //查询结果指标信息
            HospitalMonthStructureDetail structureDetail = structureDetailService.getOne(new QueryWrapper<HospitalMonthStructureDetail>().eq(HospitalMonthStructureDetail.MONTH_ID, param.getId()));
            StructureVo structureVo = new StructureVo();
            BeanUtils.copyProperties(structureDetail, structureVo);
            monthOneVo.setStructureVo(structureVo);
            //查询过程指标
            HospitalMonthProcessDetail processDetail = processDetailService.getOne(new QueryWrapper<HospitalMonthProcessDetail>().eq(HospitalMonthProcessDetail.MONTH_ID, param.getId()));
            ProcessVo processVo = new ProcessVo();
            BeanUtils.copyProperties(processDetail, processVo);
            monthOneVo.setProcessVo(processVo);
            //查询结果指标
            HospitalMonthResultDetail resultDetail = resultDetailService.getOne(new QueryWrapper<HospitalMonthResultDetail>().eq(HospitalMonthResultDetail.MONTH_ID, param.getId()));
            ResultVo resultVo = new ResultVo();
            BeanUtils.copyProperties(resultDetail, resultVo);
            monthOneVo.setResultVo(resultVo);
        }
        BeanUtils.copyProperties(hospitalMonth, monthOneVo);
        monthOneVo.setMonthId(hospitalMonth.getId());
        return R.ok(monthOneVo);
    }


    /**
     * 新增医院月度报表
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public synchronized R<Object> addHospitalMonth(MonthParam param) {
        LoginUser loginUser = getLoginUser();
        //判断当前月份该医院是否有记录
        HospitalMonth hospitalMonth = hospitalMonthService.getOne(new QueryWrapper<HospitalMonth>().eq(HospitalMonth.HOSPITAL_ID, param.getHospitalId()).eq(HospitalMonth.MONTH, param.getMonth()));
        if (hospitalMonth != null) {
            return R.failed(MONTH_IS_EXISTS);
        } else {
            //新增医院月度报表信息
            hospitalMonth = new HospitalMonth()
                    .setState(ReportStateEnum.NO.getValue())
                    .setCreateTime(LocalDateTime.now())
                    .setHospitalId(param.getHospitalId())
                    .setHospitalName(hospitalService.getById(param.getHospitalId()).getName())
                    .setMonth(param.getMonth())
                    .setCreator(loginUser.getId())
                    .setCreatorName(loginUser.getName());
            hospitalMonthService.save(hospitalMonth);
            //新增医院月度报表流程信息
            HospitalMonthProcess process = new HospitalMonthProcess()
                    .setUserId(loginUser.getId())
                    .setName(loginUser.getName())
                    .setTime(LocalDateTime.now())
                    .setCreatorName(loginUser.getName())
                    .setCreator(loginUser.getId())
                    .setMonthId(hospitalMonth.getId())
                    .setCreateTime(LocalDateTime.now())
                    .setState(ReportProcessEnum.MANUAL.getValue());
            processService.save(process);
        }
        return R.success();
    }


    /**
     * 查看驳回备注信息
     *
     * @param param 入参对象
     * @return 返回驳回备注信息
     */
    @Override
    public R<MonthRejectVo> selectMonthRejectById(IdParam param) {
        //判断月度报表是否存在
        HospitalMonth hospitalMonth = hospitalMonthService.getById(param.getId());
        if (hospitalMonth == null) {
            return R.failed(MONTH_NOT_EXISTS);
        }
        //获取驳回备注信息
        HospitalMonthProcess monthProcess = processService.getOne(new QueryWrapper<HospitalMonthProcess>().eq(HospitalMonthProcess.MONTH_ID, param.getId()).eq(HospitalMonthProcess.STATE, ReportProcessEnum.REJECT.getValue()).orderByDesc(HospitalMonthProcess.CREATE_TIME));
        MonthRejectVo rejectVo = new MonthRejectVo();
        rejectVo.setSuggest(monthProcess.getSuggest());
        return R.ok(rejectVo);
    }


    /**
     * 分页查询年度报表信息
     *
     * @param param 入参对象
     * @return 返回年度报表信息
     */
    @Override
    public R<Page<HospitalYearVo>> selectHospitalYearByPage(HospitalYearParam param) {
        Page<HospitalYearVo> page = new Page<>(param.getCurrent(), param.getSize());
        //获取当前用户信息
        LoginUser loginUser = getLoginUser();
        param.setQualityId(loginUser.getQualityId());
        List<HospitalYearVo> list = new ArrayList<>();
        if (StringUtils.isNotNull(loginUser.getPermissionType())) {
            //根据质控中心查询医院信息
            if (loginUser.getPermissionType().equals(PermissionEnum.ALL.getValue())) {
                param.setType(HospitalMonthParam.TYPE_ALL);
            } else {
                if (StringUtils.isNotEmpty(loginUser.getHospitalIds())) {
                    param.setType(HospitalMonthParam.TYPE_ONLY);
                    param.setList(Arrays.asList(Convert.toLongArray(loginUser.getHospitalIds())));
                }
            }
            list = reportMapper.selectHospitalYearByPage(page, param);
        }
        page.setRecords(list);
        return R.ok(page);
    }


    /**
     * 填写年度报表信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public synchronized R<Object> writeHospitalYear(HospitalYearAddParam param) {
        LoginUser loginUser = getLoginUser();
        //判断年份报表是否存在
        HospitalYear hospitalYear = hospitalYearService.getById(param.getYearId());
        if (hospitalYear == null) {
            return R.failed(YEAR_NOT_EXISTS);
        }
        if (!hospitalYear.getState().equals(ReportStateEnum.NO.getValue())) {
            return R.failed(YEAR_IS_COMMIT);
        }
        //修改年份报表信息
        hospitalYear.setUpdater(loginUser.getId());
        hospitalYear.setUpdaterName(loginUser.getName());
        hospitalYear.setUpdateTime(LocalDateTime.now());
        hospitalYear.setState(ReportStateEnum.WAIT.getValue());
        BeanUtils.copyProperties(param, hospitalYear);
        hospitalYearService.updateById(hospitalYear);
        //新增年份报表基础信息
        HospitalYearBaseDetail baseDetail = new HospitalYearBaseDetail();
        BeanUtils.copyProperties(param.getBaseParam(), baseDetail);
        baseDetail.setCreateTime(LocalDateTime.now());
        baseDetail.setCreator(loginUser.getId());
        baseDetail.setCreatorName(loginUser.getName());
        baseDetail.setYearId(param.getYearId());
        baseDetailService.save(baseDetail);
        //新增年份报表用户信息
        HospitalYearUserDetail userDetail = new HospitalYearUserDetail();
        BeanUtils.copyProperties(param.getUserParam(), userDetail);
        userDetail.setCreateTime(LocalDateTime.now());
        userDetail.setCreator(loginUser.getId());
        userDetail.setCreatorName(loginUser.getName());
        userDetail.setYearId(param.getYearId());
        userDetailService.save(userDetail);
        //新增年份功能信息
        HospitalYearFunctionDetail functionDetail = new HospitalYearFunctionDetail();
        BeanUtils.copyProperties(param.getFunctionParam(), functionDetail);
        functionDetail.setCreateTime(LocalDateTime.now());
        functionDetail.setCreator(loginUser.getId());
        functionDetail.setCreatorName(loginUser.getName());
        functionDetail.setYearId(param.getYearId());
        functionDetailService.save(functionDetail);
        //新增年份报表流程信息
        HospitalYearProcess process = new HospitalYearProcess()
                .setCreateTime(LocalDateTime.now())
                .setCreator(loginUser.getId())
                .setCreatorName(loginUser.getName())
                .setYearId(hospitalYear.getId())
                .setState(ReportProcessEnum.WAIT.getValue())
                .setUserId(loginUser.getId())
                .setUserName(loginUser.getName())
                .setTime(LocalDateTime.now());
        hospitalYearProcessService.save(process);
        return R.success();
    }


    /**
     * 根据id插叙医院年份报表信息
     *
     * @param param 入参对象
     * @return 返回年份报表信息
     */
    @Override
    public R<HospitalYearOneVo> selectHospitalYearById(IdParam param) {
        //判断年份报表是否存在
        HospitalYear hospitalYear = hospitalYearService.getById(param.getId());
        if (hospitalYear == null) {
            return R.failed(YEAR_NOT_EXISTS);
        }
        HospitalYearOneVo yearOneVo = new HospitalYearOneVo();
        BeanUtils.copyProperties(hospitalYear, yearOneVo);
        yearOneVo.setYearId(hospitalYear.getId());
        if (!hospitalYear.getState().equals(ReportStateEnum.NO.getValue())) {
            //查询年份基础信息
            YearBaseVo baseVo = new YearBaseVo();
            HospitalYearBaseDetail baseDetail = baseDetailService.getOne(new QueryWrapper<HospitalYearBaseDetail>().eq(HospitalYearBaseDetail.YEAR_ID, param.getId()));
            if (baseDetail != null) {
                BeanUtils.copyProperties(baseDetail, baseVo);
            }
            yearOneVo.setBaseVo(baseVo);
            //查询年份用户信息
            YearUserVo userVo = new YearUserVo();
            HospitalYearUserDetail userDetail = userDetailService.getOne(new QueryWrapper<HospitalYearUserDetail>().eq(HospitalYearUserDetail.YEAR_ID, param.getId()));
            if (userDetail != null) {
                BeanUtils.copyProperties(userDetail, userVo);
            }
            yearOneVo.setUserVo(userVo);
            //查询年份功能信息
            YearFunctionVo functionVo = new YearFunctionVo();
            HospitalYearFunctionDetail functionDetail = functionDetailService.getOne(new QueryWrapper<HospitalYearFunctionDetail>().eq(HospitalYearFunctionDetail.YEAR_ID, param.getId()));
            if (functionDetail != null) {
                BeanUtils.copyProperties(functionDetail, functionVo);
            }
            yearOneVo.setFunctionVo(functionVo);
        }
        return R.ok(yearOneVo);
    }


    /**
     * 修改年份报表信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> updateHospitalYear(HospitalYearAddParam param) {
        LoginUser loginUser = getLoginUser();
        //判断年份报表是否存在
        HospitalYear hospitalYear = hospitalYearService.getById(param.getYearId());
        if (hospitalYear == null) {
            return R.failed(YEAR_NOT_EXISTS);
        }
        /**
         * 1.报表已上传，不允许修改
         * 2.报表已驳回，修改报表状态
         */
        if (hospitalYear.getState().equals(ReportStateEnum.FINISH.getValue())) {
            return R.failed(MONTH_FINISH);
        } else if (hospitalYear.getState().equals(ReportStateEnum.REJECT.getValue())) {
            hospitalYear.setState(ReportStateEnum.WAIT.getValue());
        }
        //修改年份报表信息
        BeanUtils.copyProperties(param, hospitalYear);
        hospitalYear.setUpdateTime(LocalDateTime.now());
        hospitalYear.setUpdater(loginUser.getId());
        hospitalYear.setUpdaterName(loginUser.getName());
        hospitalYearService.updateById(hospitalYear);
        //修改年份基础信息
        HospitalYearBaseDetail baseDetail = baseDetailService.getOne(new QueryWrapper<HospitalYearBaseDetail>().eq(HospitalYearBaseDetail.YEAR_ID, param.getYearId()));
        baseDetail = baseDetail == null ? new HospitalYearBaseDetail() : baseDetail;
        if (param.getBaseParam() != null) {
            BeanUtils.copyProperties(param.getBaseParam(), baseDetail);
            baseDetail.setUpdater(loginUser.getId());
            baseDetail.setUpdaterName(loginUser.getName());
            baseDetail.setUpdateTime(LocalDateTime.now());
            baseDetailService.updateById(baseDetail);
        }
        //修改年份用户信息
        HospitalYearUserDetail userDetail = userDetailService.getOne(new QueryWrapper<HospitalYearUserDetail>().eq(HospitalYearUserDetail.YEAR_ID, param.getYearId()));
        userDetail = userDetail == null ? new HospitalYearUserDetail() : userDetail;
        if (param.getUserParam() != null) {
            BeanUtils.copyProperties(param.getUserParam(), userDetail);
            userDetail.setUpdater(loginUser.getId());
            userDetail.setUpdaterName(loginUser.getName());
            userDetail.setUpdateTime(LocalDateTime.now());
            userDetailService.updateById(userDetail);
        }
        //修改年份功能信息
        HospitalYearFunctionDetail functionDetail = functionDetailService.getOne(new QueryWrapper<HospitalYearFunctionDetail>().eq(HospitalYearFunctionDetail.YEAR_ID, param.getYearId()));
        functionDetail = functionDetail == null ? new HospitalYearFunctionDetail() : functionDetail;
        if (param.getFunctionParam() != null) {
            BeanUtils.copyProperties(param.getFunctionParam(), functionDetail);
            functionDetail.setUpdater(loginUser.getId());
            functionDetail.setUpdaterName(loginUser.getName());
            functionDetail.setUpdateTime(LocalDateTime.now());
            functionDetailService.updateById(functionDetail);
        }
        //新增年份报表流程
        HospitalYearProcess process = new HospitalYearProcess()
                .setYearId(param.getYearId())
                .setState(ReportProcessEnum.UPDATE.getValue())
                .setCreateTime(LocalDateTime.now())
                .setCreator(loginUser.getId())
                .setCreatorName(loginUser.getName())
                .setUserId(loginUser.getId())
                .setUserName(loginUser.getName())
                .setTime(LocalDateTime.now());
        hospitalYearProcessService.save(process);
        return R.success();
    }


    /**
     * 审核医院年度报表
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> verifyHospitalYear(VerifyYearParam param) {
        LoginUser loginUser = getLoginUser();
        //判断备注是否为空
        if (param.getState().equals(WhetherEnum.NO.getValue())) {
            if (StringUtils.isEmpty(param.getRemark())) {
                return R.failed(REMARK_NOT_EXISTS);
            }
        }
        //判断年份报表是否存在
        HospitalYear hospitalYear = hospitalYearService.getById(param.getYearId());
        if (hospitalYear == null) {
            return R.failed(YEAR_NOT_EXISTS);
        }
        //新增报表流程信息
        HospitalYearProcess process = new HospitalYearProcess()
                .setUserName(loginUser.getName())
                .setYearId(hospitalYear.getId())
                .setCreateTime(LocalDateTime.now())
                .setCreator(loginUser.getId())
                .setCreatorName(loginUser.getName())
                .setTime(LocalDateTime.now())
                .setUserId(loginUser.getId());
        if (param.getState().equals(WhetherEnum.NO.getValue())) {
            hospitalYear.setState(ReportStateEnum.REJECT.getValue());
            process.setState(ReportProcessEnum.REJECT.getValue());
            process.setSuggest(param.getRemark());
        } else {
            hospitalYear.setState(ReportStateEnum.FINISH.getValue());
            hospitalYear.setTime(LocalDateTime.now());
            process.setState(ReportProcessEnum.FINISH.getValue());
        }
        hospitalYear.setUpdateTime(LocalDateTime.now());
        hospitalYear.setUpdaterName(loginUser.getName());
        hospitalYear.setUpdater(loginUser.getId());
        hospitalYearService.updateById(hospitalYear);
        hospitalYearProcessService.save(process);
        return R.success();
    }

    /**
     * 查询年度报表驳回信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<MonthRejectVo> selectYearRejectById(IdParam param) {
        //判断年份报表是否存在
        HospitalYear hospitalYear = hospitalYearService.getById(param.getId());
        if (hospitalYear == null) {
            return R.failed(YEAR_NOT_EXISTS);
        }
        //获取驳回备注信息
        HospitalYearProcess yearProcess = hospitalYearProcessService.getOne(new QueryWrapper<HospitalYearProcess>().eq(HospitalYearProcess.YEAR_ID, param.getId()).eq(HospitalYearProcess.STATE, ReportProcessEnum.REJECT.getValue()).orderByDesc(HospitalMonthProcess.CREATE_TIME));
        MonthRejectVo rejectVo = new MonthRejectVo();
        rejectVo.setSuggest(yearProcess.getSuggest());
        return R.ok(rejectVo);
    }


    /**
     * 新增医院月度报表
     *
     * @param param 年度报表入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public synchronized R<Object> addHospitalYear(YearParam param) {
        LoginUser loginUser = getLoginUser();
        //判断当前年份该医院是否有记录
        HospitalYear hospitalYear = hospitalYearService.getOne(new QueryWrapper<HospitalYear>().eq(HospitalYear.HOSPITAL_ID, param.getHospitalId()).eq(HospitalYear.YEAR, param.getYear()));
        if (hospitalYear != null) {
            return R.failed(YEAR_IS_EXISTS);
        } else {
            //新增医院月度报表信息
            hospitalYear = new HospitalYear()
                    .setState(ReportStateEnum.NO.getValue())
                    .setCreateTime(LocalDateTime.now())
                    .setHospitalId(param.getHospitalId())
                    .setHospitalName(hospitalService.getById(param.getHospitalId()).getName())
                    .setYear(param.getYear())
                    .setCreator(loginUser.getId())
                    .setCreatorName(loginUser.getName());
            hospitalYearService.save(hospitalYear);
            //新增医院年度报表流程信息
            HospitalMonthProcess process = new HospitalMonthProcess()
                    .setUserId(loginUser.getId())
                    .setName(loginUser.getName())
                    .setTime(LocalDateTime.now())
                    .setCreatorName(loginUser.getName())
                    .setCreator(loginUser.getId())
                    .setMonthId(hospitalYear.getId())
                    .setCreateTime(LocalDateTime.now())
                    .setState(ReportProcessEnum.MANUAL.getValue());
            processService.save(process);
        }
        return R.success();
    }


    /**
     * 删除医院年度报表信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> deleteHospitalYear(IdParam param) {
        //判断年度报表是否存在
        HospitalYear hospitalYear = hospitalYearService.getById(param.getId());
        if (hospitalYear == null) {
            return R.failed(MONTH_NOT_EXISTS);
        }
        //报表已上传，不允许删除
        if (hospitalYear.getState().equals(ReportStateEnum.FINISH.getValue())) {
            return R.failed(MONTH_IS_FINISH);
        }
        hospitalYearService.removeById(param.getId());
        return R.success();
    }

    /**
     * 导出月度报表信息
     *
     * @param param 入参对象
     * @return 返回数据流
     */
    @Override
    public void importHospitalMonth(HospitalMonthParam param, HttpServletRequest request, HttpServletResponse response) {
        //获取当前用户信息
        LoginUser loginUser = getLoginUser();
        param.setQualityId(loginUser.getQualityId());
        List<HospitalMonthVo> list = new ArrayList<>();
        if (StringUtils.isNotNull(loginUser.getPermissionType())) {
            //根据质控中心查询医院信息
            if (loginUser.getPermissionType().equals(PermissionEnum.ALL.getValue())) {
                param.setType(HospitalMonthParam.TYPE_ALL);
            } else {
                if (StringUtils.isNotEmpty(loginUser.getHospitalIds())) {
                    param.setType(HospitalMonthParam.TYPE_ONLY);
                    param.setList(Arrays.asList(Convert.toLongArray(loginUser.getHospitalIds())));
                }
            }
            list = reportMapper.selectHospitalMonthList(param);
        }
        if (list.size() > 0) {
            ExcelExportUtil.exportXssfExcel("医院月度报表导出结果", "医院月度报表导出结果", HospitalMonthVo.class, list, request, response);
        } else {
            PrintWriter printWriter = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            try {
                printWriter = response.getWriter();
                printWriter.print(JSON.toJSONString(R.failed(DATA_EMPTY.getCode(), DATA_EMPTY.getMsg())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 导出年度报表信息
     *
     * @param param 入参对象
     * @return 返回数据流
     */
    @Override
    public void importHospitalYear(HospitalYearParam param, HttpServletRequest request, HttpServletResponse response) {
        //获取当前用户信息
        LoginUser loginUser = getLoginUser();
        param.setQualityId(loginUser.getQualityId());
        List<HospitalYearVo> list = new ArrayList<>();
        if (StringUtils.isNotNull(loginUser.getPermissionType())) {
            //根据质控中心查询医院信息
            if (loginUser.getPermissionType().equals(PermissionEnum.ALL.getValue())) {
                param.setType(HospitalMonthParam.TYPE_ALL);
            } else {
                if (StringUtils.isNotEmpty(loginUser.getHospitalIds())) {
                    param.setType(HospitalMonthParam.TYPE_ONLY);
                    param.setList(Arrays.asList(Convert.toLongArray(loginUser.getHospitalIds())));
                }
            }
            list = reportMapper.selectHospitalYearList(param);
        }
        if (list.size() > 0) {
            ExcelExportUtil.exportXssfExcel("医院年度报表导出结果", "医院年度报表导出结果", HospitalYearVo.class, list, request, response);
        } else {
            PrintWriter printWriter = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            try {
                printWriter = response.getWriter();
                printWriter.print(JSON.toJSONString(R.failed(DATA_EMPTY.getCode(), DATA_EMPTY.getMsg())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
