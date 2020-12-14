package com.hpower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.dto.DataPanelDto;
import com.hpower.dto.LoginUser;
import com.hpower.entity.*;
import com.hpower.enums.ReportStateEnum;
import com.hpower.enums.WhetherEnum;
import com.hpower.mapper.SDataPanelMapper;
import com.hpower.mapper.SReportMapper;
import com.hpower.param.DataYearParam;
import com.hpower.param.PatientDetailParam;
import com.hpower.param.PatientParam;
import com.hpower.param.ProvinceHospitalParam;
import com.hpower.service.*;
import com.hpower.support.BaseSupport;
import com.hpower.util.DateUtil;
import com.hpower.util.LocalDateTimeUtils;
import com.hpower.util.StringUtils;
import com.hpower.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname 数据总览服务实现接口层
 * @Description TODO
 * @Date 2020/3/27 3:25 下午
 * @Created yangyang.jiang
 */
@Service
@Slf4j
public class SDataPanelServiceImpl extends BaseSupport implements SDataPanelService {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @Autowired
    private SysDictDataService sysDictDataService;

    @Autowired
    private HospitalYearService hospitalYearService;

    @Autowired
    private SDataPanelMapper dataPanelMapper;

    @Autowired
    private SReportMapper sReportMapper;

    /**
     * 查询全省医院分布
     *
     * @param param 查询入参对象
     * @return 返回全省分布对象集合
     */
    @Override
    public R<List<ProvinceHospitalVo>> selectHospital(ProvinceHospitalParam param) {
        LoginUser loginUser = getLoginUser();
        List<ProvinceHospitalVo> list = new ArrayList<>();
        final int[] count = {0};
        BigDecimal sum = new BigDecimal(100);
        //查询医院集合
        List<Hospital> hospitalList = hospitalService.list(
                new QueryWrapper<Hospital>()
                        .eq(Hospital.QUALITY_ID, loginUser.getQualityId()).eq(Hospital.ENABLED, WhetherEnum.YES.getValue())
                        .eq(StringUtils.isNotNull(param.getCityId()), Hospital.CITY_CODE, param.getCityId())
                        .eq(StringUtils.isNotNull(param.getAreaId()), Hospital.AREA_CODE, param.getAreaId())

        );
        if (hospitalList.size() > 0) {
            //获取医院去重级别信息
            List<Hospital> levelList = hospitalList.stream().collect(
                    Collectors.collectingAndThen(
                            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Hospital::getLevel))), ArrayList::new));
            levelList.forEach(hospital -> {
                ProvinceHospitalVo hospitalVo = new ProvinceHospitalVo();
                List<Hospital> hospitals = hospitalList.stream().filter(a -> a.getLevel().equals(hospital.getLevel())).collect(Collectors.toList());
                SysDictType sysDictType = sysDictTypeService.getOne(new QueryWrapper<SysDictType>().eq(SysDictType.CODE, "hospital_level"));
                SysDictData sysDictData = sysDictDataService.getOne(new QueryWrapper<SysDictData>().eq(SysDictData.DICT_TYPE_ID, sysDictType.getId()).eq(SysDictData.CODE, hospital.getLevel()));
                hospitalVo.setHospitalCount(hospitals.size());
                hospitalVo.setType(sysDictData.getName());
                count[0] = count[0] + hospitals.size();
                list.add(hospitalVo);
            });
            NumberFormat nt = NumberFormat.getPercentInstance();
            nt.setMinimumFractionDigits(1);
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    list.get(i).setPercentage(sum.floatValue() + "%");
                } else {
                    String format = nt.format(Float.parseFloat(list.get(i).getHospitalCount().toString()) / count[0]);
                    list.get(i).setPercentage(format);
                    sum = sum.subtract(new BigDecimal(format.replace("%", "")));
                }
            }
        }
        return R.ok(list);
    }


    /**
     * 全省上报情况
     *
     * @return 返回结果信息
     */
    @Override
    public R<ReportListVo> selectReportList() {
        LoginUser loginUser = getLoginUser();
        ReportListVo listVo = new ReportListVo();
        //获取当前月份
        String nowMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now(), "yyyy-MM");
        String nowYear = LocalDateTimeUtils.formatTime(LocalDateTime.now(), "yyyy");
        String queryMonth = DateUtil.monthDown(nowMonth, -2);
        String queryYear = DateUtil.yearDown(nowYear, -1);
        listVo.setMonth(Objects.requireNonNull(queryMonth).replace("-", "年") + "月");
        listVo.setYear(queryYear + "年");

        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(1);
        List<Hospital> hospitalList = hospitalService.list(new QueryWrapper<Hospital>().eq(Hospital.ENABLED, 1).eq(Hospital.QUALITY_ID, loginUser.getQualityId()));
        //查询医院集合中月度报表信息
        List<HospitalMonth> hospitalMonthList = sReportMapper.queryHospitalMonthByDate(queryMonth, loginUser.getQualityId());
        /**
         * 抽取已上传的报表信息
         * 未上传通过全部报表数量-已上传报表数量
         */
        List<ReportListVo.ReportVo> monthReport = new ArrayList<>();
        ReportListVo.ReportVo yesReport = new ReportListVo.ReportVo();
        yesReport.setType(ReportStateEnum.FINISH.getLabel());
        yesReport.setReportCount(hospitalMonthList.size());
        String formatMonth = nt.format((float) hospitalMonthList.size() / hospitalList.size());
        yesReport.setPercentage(formatMonth);
        monthReport.add(yesReport);
        ReportListVo.ReportVo noReport = new ReportListVo.ReportVo();
        noReport.setType(ReportStateEnum.NO.getLabel());
        noReport.setReportCount(hospitalList.size() - hospitalMonthList.size());
        noReport.setPercentage(BigDecimal.valueOf(100).subtract(new BigDecimal(formatMonth.replace("%", ""))) + "%");
        monthReport.add(noReport);
        //查询医院集合中年度报表信息
        List<HospitalYear> hospitalYearList = sReportMapper.queryHospitalYearByDate(queryYear, loginUser.getQualityId());
        /**
         * 抽取已上传的报表信息
         * 未上传通过全部报表数量-已上传报表数量
         */
        List<ReportListVo.ReportVo> yearReport = new ArrayList<>();
        ReportListVo.ReportVo yesYearReport = new ReportListVo.ReportVo();
        yesYearReport.setType(ReportStateEnum.FINISH.getLabel());
        yesYearReport.setReportCount(hospitalYearList.size());
        String formatYear = nt.format((float) hospitalYearList.size() / hospitalList.size());
        yesYearReport.setPercentage(formatYear);
        yearReport.add(yesYearReport);
        ReportListVo.ReportVo noYearReport = new ReportListVo.ReportVo();
        noYearReport.setType(ReportStateEnum.NO.getLabel());
        noYearReport.setReportCount(hospitalList.size() - hospitalYearList.size());
        noYearReport.setPercentage(BigDecimal.valueOf(100).subtract(new BigDecimal(formatYear.replace("%", ""))) + "%");
        yearReport.add(noYearReport);
        //封装返回对象
        listVo.setMonthReport(monthReport);
        listVo.setYearReport(yearReport);
        return R.ok(listVo);
    }


    /**
     * 住院患者营养风险筛查率
     *
     * @param param 查询入参对象
     * @return 返回结果信息
     */
    @Override
    public R<List<DataMonthVo>> selectPatient(PatientParam param) {
        LoginUser loginUser = getLoginUser();
        List<DataMonthVo> list = new ArrayList<>();
        DataPanelDto dataPanelDto = new DataPanelDto();
        dataPanelDto.setQualityId(loginUser.getQualityId());
        //判断查询时间格式,将其转换为开始月份和结束月份带入查询
        String endMonth = "";
        String startMonth = "";
        switch (param.getType()) {
            case 0:
                endMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(1), "yyyy-MM");
                startMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(1), "yyyy-MM");
                break;
            case 1:
                endMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(1), "yyyy-MM");
                startMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(6), "yyyy-MM");
                break;
            case 2:
                endMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(1), "yyyy-MM");
                startMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(12), "yyyy-MM");
                break;
            case 3:
                startMonth = param.getStartMonth();
                endMonth = param.getEndMonth();
                break;
        }
        dataPanelDto.setStartMonth(startMonth);
        dataPanelDto.setEndMonth(endMonth);
        param.getList().forEach(tag -> {
            DataMonthVo monthVo = new DataMonthVo();
            monthVo.setTagName(tag.getTagName());
            monthVo.setTag(tag.getTag());
            dataPanelDto.setTag(tag.getTag());

            List<DataMonthDetailVo> detailVos = dataPanelMapper.selectPatient(dataPanelDto);
            if (detailVos.size() > 0) {
                /**
                 * 1.判断医院月度报表信息是否存在
                 *    存在：将其相加求平均值
                 *    不存在：设置为空
                 */
                DataMonthDetailVo province = new DataMonthDetailVo();
                if (detailVos.size() > 0) {
                    BigDecimal count = new BigDecimal(0);
                    for (DataMonthDetailVo detailVo : detailVos) {
                        count = count.add(detailVo.getValue());
                    }
                    province.setValue(count.divide(new BigDecimal(detailVos.size()), 2));
                }
                province.setCityName(loginUser.getProvinceName());
                detailVos.add(0, province);
                monthVo.setList(detailVos);
            }
            list.add(monthVo);
        });
        return R.ok(list);
    }


    /**
     * 根据医院查询月平均明细信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<Page<DataMonthElseVo>> selectPatientDetail(PatientDetailParam param) {
        //获取当前用户信息
        LoginUser loginUser = getLoginUser();
        DataPanelDto dataPanelDto = new DataPanelDto();
        dataPanelDto.setQualityId(loginUser.getQualityId());
        dataPanelDto.setTag(param.getTag());
        //判断查询时间格式,将其转换为开始月份和结束月份带入查询
        LocalDateTime today = LocalDateTime.now();
        String endMonth = "";
        String startMonth = "";
        switch (param.getType()) {
            case 0:
                endMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(1), "yyyy-MM");
                startMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(1), "yyyy-MM");
                break;
            case 1:
                endMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(1), "yyyy-MM");
                startMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(6), "yyyy-MM");
                break;
            case 2:
                endMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(1), "yyyy-MM");
                startMonth = LocalDateTimeUtils.formatTime(LocalDateTime.now().minusMonths(12), "yyyy-MM");
                break;
            case 3:
                startMonth = param.getStartMonth();
                endMonth = param.getEndMonth();
                break;
        }
        dataPanelDto.setStartMonth(startMonth);
        dataPanelDto.setEndMonth(endMonth);
        dataPanelDto.setCityId(param.getCityId());
        Page<DataMonthElseVo> page = new Page<>(param.getCurrent(), param.getSize());
        List<DataMonthElseVo> dataMonthElseVoList = dataPanelMapper.selectPatientDetail(page, dataPanelDto);
        page.setRecords(dataMonthElseVoList);
        return R.ok(page);
    }

    /**
     * 查询年度信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<DataYearVo> selectYearList(DataYearParam param) {
        LoginUser loginUser = getLoginUser();
        DataYearVo dataYearVo = new DataYearVo();
        DataPanelDto dataPanelDto = new DataPanelDto();
        dataPanelDto.setQualityId(loginUser.getQualityId());
        switch (param.getType()) {
            case 0:
                dataPanelDto.setStartYear(LocalDateTimeUtils.formatTime(LocalDateTime.now().minusYears(1), "yyyy"));
                dataPanelDto.setEndYear(LocalDateTimeUtils.formatTime(LocalDateTime.now().minusYears(1), "yyyy"));
                break;
            case 1:
                dataPanelDto.setStartYear(param.getStartYear());
                dataPanelDto.setEndYear(param.getEndYear());
                break;
        }
        //营养科专业人员和床位比分布
        List<DataYearCommonVo> bedList = new ArrayList<>();
        //不同等级医院中住院病人营养风险筛查率的分布
        List<DataYearCommonVo> patientList = new ArrayList<>();
        //不同等级医院中有肠内营养配制室的比例
        List<DataYearCommonVo> entericList = new ArrayList<>();
        //不同等级医院营养科是否有独立收费项目分布
        List<DataYearCommonVo> ownList = new ArrayList<>();
        //按照条件查询所有医院的级别信息
        List<Integer> hospitalYearList = dataPanelMapper.selectHospitalYearList(dataPanelDto);
        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(1);
        hospitalYearList.forEach(level -> {
            SysDictType sysDictType = sysDictTypeService.getOne(new QueryWrapper<SysDictType>().eq(SysDictType.CODE, "hospital_level"));
            SysDictData sysDictData = sysDictDataService.getOne(new QueryWrapper<SysDictData>().eq(SysDictData.DICT_TYPE_ID, sysDictType.getId()).eq(SysDictData.CODE, level));
            dataPanelDto.setLevel(level);
            //查询营养科专业人员和床位比分布统计
            DataYearCommonVo bedCommonVo = new DataYearCommonVo();
            bedCommonVo.setTagName(sysDictData.getName());
            List<DataYearCommonDetailVo> bedDetail = dataPanelMapper.selectUserBedList(dataPanelDto);
            common(bedDetail, nt);
            bedCommonVo.setList(bedDetail);
            bedList.add(bedCommonVo);
            //不同等级医院中住院病人营养风险筛查率的分布
            DataYearCommonVo patientCommonVo = new DataYearCommonVo();
            patientCommonVo.setTagName(sysDictData.getName());
            List<DataYearCommonDetailVo> patientDetail = dataPanelMapper.selectPatientList(dataPanelDto);
            common(patientDetail, nt);
            patientCommonVo.setList(patientDetail);
            patientList.add(patientCommonVo);
            //不同等级医院中有肠内营养配制室的比例
            DataYearCommonVo enteric = new DataYearCommonVo();
            enteric.setTagName(sysDictData.getName());
            List<DataYearCommonDetailVo> entericDetail = dataPanelMapper.selectEntericList(dataPanelDto);
            List<DataYearCommonDetailVo> collect = entericDetail.stream().collect(
                    Collectors.collectingAndThen(
                            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DataYearCommonDetailVo::getType))), ArrayList::new));
            if (collect.size() == 1) {
                DataYearCommonDetailVo detailVo = new DataYearCommonDetailVo();
                detailVo.setCount(0);
                Integer type = collect.get(0).getType();
                if (type == 1) {
                    detailVo.setType(0);
                } else {
                    detailVo.setType(1);
                }
                entericDetail.add(detailVo);
            }
            common(entericDetail, nt);
            enteric.setList(entericDetail);
            entericList.add(enteric);
            //不同等级医院营养科是否有独立收费项目分布
            DataYearCommonVo own = new DataYearCommonVo();
            own.setTagName(sysDictData.getName());
            List<DataYearCommonDetailVo> ownDetail = dataPanelMapper.selectOwnList(dataPanelDto);
            List<DataYearCommonDetailVo> ownCollect = ownDetail.stream().collect(
                    Collectors.collectingAndThen(
                            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DataYearCommonDetailVo::getType))), ArrayList::new));
            if (ownCollect.size() == 1) {
                DataYearCommonDetailVo detailVo = new DataYearCommonDetailVo();
                detailVo.setCount(0);
                Integer type = ownCollect.get(0).getType();
                if (type == 1) {
                    detailVo.setType(0);
                } else {
                    detailVo.setType(1);
                }
                ownDetail.add(detailVo);
            }
            common(ownDetail, nt);
            own.setList(ownDetail);
            ownList.add(own);
        });
        dataYearVo.setBedList(bedList);
        dataYearVo.setEntericList(entericList);
        dataYearVo.setOwnList(ownList);
        dataYearVo.setPatientList(patientList);
        return R.ok(dataYearVo);
    }

    public void common(List<DataYearCommonDetailVo> bedDetail, NumberFormat nt) {
        if (bedDetail.size() > 0) {
            int count = bedDetail.stream().mapToInt(DataYearCommonDetailVo::getCount).sum();
            BigDecimal sum = BigDecimal.valueOf(100);
            for (int i = 0; i < bedDetail.size(); i++) {
                if (i == bedDetail.size() - 1) {
                    bedDetail.get(i).setPercentage(sum + "%");
                } else {
                    String format = nt.format((float) bedDetail.get(i).getCount() / count);
                    bedDetail.get(i).setPercentage(format);
                    sum = sum.subtract(new BigDecimal(format.replace("%", "")));
                }
            }
        }
    }
}
