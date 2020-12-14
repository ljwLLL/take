package com.hpower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.dto.LoginUser;
import com.hpower.entity.*;
import com.hpower.enums.PermissionEnum;
import com.hpower.enums.ReportStateEnum;
import com.hpower.errorcode.ApiErrorCode;
import com.hpower.mapper.SAnalyseMapper;
import com.hpower.param.*;
import com.hpower.service.*;
import com.hpower.support.BaseSupport;
import com.hpower.util.Convert;
import com.hpower.util.LocalDateTimeUtils;
import com.hpower.util.StringUtils;
import com.hpower.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname 统计分析接口服务实现层
 * @Description TODO
 * @Date 2020/3/27 3:24 下午
 * @Created yangyang.jiang
 */
@Service
@Slf4j
public class SAnalyseServiceImpl extends BaseSupport implements SAnalyseService {

    @Autowired
    private SAnalyseMapper analyseMapper;

    @Autowired
    private HospitalMonthService hospitalMonthService;


    @Autowired
    HospitalYearService hospitalYearService;


    @Autowired
    HospitalYearBaseDetailService hospitalYearBaseDetailService;

    @Autowired
    HospitalYearFunctionDetailService hospitalYearFunctionDetailService;

    @Autowired
    HospitalYearUserDetailService hospitalYearUserDetailService;


    /**
     * 分页查询月度报表统计
     *
     * @param param 入参对象
     * @return 返回结果集合对象
     */
    @Override
    public R<Page<AnalyseMonthVo>> selectAnalyseMonthByPage(AnalyseMonthParam param) {
        //获取用户信息
        LoginUser loginUser = getLoginUser();
        Page<AnalyseMonthVo> page = new Page<>(param.getCurrent(), param.getSize());
        param.setQualityId(loginUser.getQualityId());
        int state = 0;
        /**
         * 1.判断当前用户是不是获取所有医院信息
         * 2.判断当前页是否是首页
         */
        if (StringUtils.isNotNull(loginUser.getPermissionType())) {
            //根据质控中心查询医院信息
            if (loginUser.getPermissionType().equals(PermissionEnum.ALL.getValue())) {
                param.setType(HospitalMonthParam.TYPE_ALL);
                //如何该用户数据权限是查看所有的医院，则将当前条数减一为了让插入该省份的记录信息
                if (param.getCurrent().equals(1)) {
                    page.setCurrent(param.getCurrent());
                    page.setSize(param.getSize() - 1);
                }
                state = 1;
            } else {
                if (StringUtils.isNotEmpty(loginUser.getHospitalIds())) {
                    param.setType(HospitalMonthParam.TYPE_ONLY);
                    param.setList(Arrays.asList(Convert.toLongArray(loginUser.getHospitalIds())));
                }
            }
        }
        List<AnalyseMonthVo> list = analyseMapper.selectAnalyseMonthByPage(page, param);
        //判断该集合是否有值
        if (list.size() > 0) {
            //过滤掉没有已上报的信息，将最近的一条记录填充进集合当中。返回给前端
            list.stream().filter(vo -> vo.getYesReport() > 0).collect(Collectors.toList()).forEach(vo -> {
                HospitalMonth one = hospitalMonthService.getOne(new QueryWrapper<HospitalMonth>().eq(HospitalMonth.HOSPITAL_ID, vo.getHospitalId()).eq(HospitalMonth.STATE, ReportStateEnum.FINISH.getValue()).orderByDesc(HospitalMonth.CREATE_TIME));
                BeanUtils.copyProperties(one, vo);
            });
            if (state == 1) {
                Integer findState = analyseMapper.selectCount(param);
                if (findState > 0) {
                    AnalyseMonthVo monthVo = new AnalyseMonthVo();
                    monthVo.setHospitalName(loginUser.getProvinceName());
                    list.add(0, monthVo);
                }
            }
        }
        page.setRecords(list);
        return R.ok(page);
    }


    /**
     * 根据医院id分页查询月度报表信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<Page<AnalyseMonthDetailVo>> selectAnalyseMonthDetailByPage(AnalyseMonthDetailParam param) {
        LoginUser loginUser = getLoginUser();
        Page<AnalyseMonthDetailVo> page = new Page<>(param.getCurrent(), param.getSize());
        param.setQualityId(loginUser.getQualityId());
        //查询医院id查询月度报表信息
        List<AnalyseMonthDetailVo> list = analyseMapper.selectAnalyseMonthDetailByPage(page, param);
        page.setRecords(list);
        return R.ok(page);
    }


    /**
     * 根据月度id查询月度报表明细
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<AnalyseMonthOneVo> selectAnalyseMonth(AnalyseOneParam param) {
        LoginUser loginUser = getLoginUser();
        param.setQualityId(loginUser.getQualityId());
        AnalyseMonthOneVo analyseMonthOneVo;
        if (StringUtils.isNotNull(param.getMonthId())) {
            analyseMonthOneVo = analyseMapper.selectAnalyseMonth(param);
        } else {
            analyseMonthOneVo = analyseMapper.selectAllAnalyseMonth(param);
        }
        return R.ok(analyseMonthOneVo);
    }

    /**
     * 查找浙江省的月度报表历史记录
     *
     * @param param 分页
     * @return
     */
    @Override
    public R<Page<AnalyseMonthHistoryVO>> selectAllMonthByPage(AnalyseMonthAllParam param) {
        Page<AnalyseMonthHistoryVO> page = new Page<>(param.getCurrent(), param.getSize());
        List<AnalyseMonthHistoryVO> list = analyseMapper.selectAllMonthHistory(page);
        page.setRecords(list);
        return R.ok(page);
    }


    /**************************************************年度数据*************************************************************************8*/

    /**
     * 返回年度页面的分页数据
     *
     * @param analyseParam 记载可能有的医院名称, 参数可为空
     * @return 返回年度页面的数据, 多一个累计上报 / 医院名称 / 最近上报时间 / 上报人 / 手机号 / 所属科室 / 职称
     */
    @Override
    public R<Page<AnalyseYearVO>> selectYearAnalyse(AnalyseParam analyseParam) {
        //获取用户信息
        LoginUser loginUser = getLoginUser();
        List<AnalyseYearVO> list;
        Page<AnalyseYearVO> page = new Page<>(analyseParam.getCurrent(), analyseParam.getSize());
        analyseParam.setQualityId(loginUser.getQualityId());
        /**
         * 1.判断当前用户是不是获取所有医院信息
         * 2.判断当前页是否是首页
         */
        if (StringUtils.isNotNull(loginUser.getPermissionType())) {
            //根据质控中心查询医院信息
            if (loginUser.getPermissionType().equals(PermissionEnum.ALL.getValue())) {
                analyseParam.setType(HospitalMonthParam.TYPE_ALL);
            } else {
                if (StringUtils.isNotEmpty(loginUser.getHospitalIds())) {
                    analyseParam.setType(HospitalMonthParam.TYPE_ONLY);
                    analyseParam.setList(Arrays.asList(Convert.toLongArray(loginUser.getHospitalIds())));
                }
            }
        }
        list = analyseMapper.selectAnalyseYear(page, analyseParam);
        //判断该集合是否有值
        if (list.size() > 0) {
            //过滤掉没有已上报的信息，将最近的一条记录填充进集合当中。返回给前端
            list.stream().filter(vo -> vo.getYesReport() > 0).collect(Collectors.toList()).forEach(vo -> {
                HospitalYear one = hospitalYearService.getOne(new QueryWrapper<HospitalYear>().eq(HospitalYear.HOSPITAL_ID, vo.getHospitalId()).eq(HospitalYear.STATE, ReportStateEnum.FINISH.getValue()).orderByDesc(HospitalYear.CREATE_TIME));
                BeanUtils.copyProperties(one, vo);
            });
        }
        page.setRecords(list);
        return R.ok(page);
    }

    /**
     * 常用信息
     * 通过id获取,这个医院那一年的所有信息   信息填入
     * 根据年度中每个医院的id,查询信息,查询的信息包括 年度总结的信息+历史记录
     *
     * @param idParam id信息
     * @return 返回历史记录加最近的一条年度总结
     */
    @Override
    public R<AnalyseYearLookOneVO> lookYearAnalyseHospital(IdParam idParam) {
        AnalyseYearLookOneVO analyseYearLookOneVO = new AnalyseYearLookOneVO();//输出的结果
        //状态是2（表示已经填写） 的
        List<HospitalYear> yearList = hospitalYearService.list(new QueryWrapper<HospitalYear>().eq(HospitalYear.HOSPITAL_ID, idParam.getId()).eq(HospitalYear.STATE, 2));
        if (yearList.size() == 0) {
            return R.ok(null);//如果没有记录，那就是返回空
        }
        BeanUtils.copyProperties(yearList.get(Math.toIntExact(0)), analyseYearLookOneVO);//添加操作人/部门/职位等信息
        HospitalYearBaseParam hospitalYearBaseParam = new HospitalYearBaseParam();
        HospitalYearFunctionParam hospitalYearFunctionParam = new HospitalYearFunctionParam();
        HospitalYearUserParam hospitalYearUserParam = new HospitalYearUserParam();
        BeanUtils.copyProperties(hospitalYearBaseDetailService.getOne(new QueryWrapper<HospitalYearBaseDetail>().eq(HospitalYearBaseDetail.YEAR_ID, yearList.get(0).getId())), hospitalYearBaseParam);
        BeanUtils.copyProperties(hospitalYearFunctionDetailService.getOne(new QueryWrapper<HospitalYearFunctionDetail>().eq(HospitalYearFunctionDetail.YEAR_ID, yearList.get(0).getId())), hospitalYearFunctionParam);
        BeanUtils.copyProperties(hospitalYearUserDetailService.getOne(new QueryWrapper<HospitalYearUserDetail>().eq(HospitalYearUserDetail.YEAR_ID, yearList.get(0).getId())), hospitalYearUserParam);
        analyseYearLookOneVO.setHospitalYearBaseParam(hospitalYearBaseParam);//设置基础
        analyseYearLookOneVO.setHospitalYearFunctionParam(hospitalYearFunctionParam);//设置功能
        analyseYearLookOneVO.setHospitalYearUserParam(hospitalYearUserParam);//设置使用者
        return R.ok(analyseYearLookOneVO);
    }

    /**
     * 根据id查询某一条记录，年度的数据
     *
     * @param idParam 年度数据的id t_hospital_year表的id
     * @return 某一年的记录
     */
    @Override
    public R<AnalyseYearLookOneVO> lookHistory(IdParam idParam) {
        HospitalYear hospitalYear = hospitalYearService.getById(idParam.getId());
        if (hospitalYear == null) {
            return R.failed(ApiErrorCode.HOSPITAL_REPORT_NOT_EXISTS);
        }
        HospitalYearBaseParam hospitalYearBaseParam = new HospitalYearBaseParam();
        HospitalYearFunctionParam hospitalYearFunctionParam = new HospitalYearFunctionParam();
        HospitalYearUserParam hospitalYearUserParam = new HospitalYearUserParam();
        BeanUtils.copyProperties(hospitalYearBaseDetailService.getOne(new QueryWrapper<HospitalYearBaseDetail>().eq(HospitalYearBaseDetail.YEAR_ID, idParam.getId())), hospitalYearBaseParam);
        BeanUtils.copyProperties(hospitalYearFunctionDetailService.getOne(new QueryWrapper<HospitalYearFunctionDetail>().eq(HospitalYearFunctionDetail.YEAR_ID, idParam.getId())), hospitalYearFunctionParam);
        BeanUtils.copyProperties(hospitalYearUserDetailService.getOne(new QueryWrapper<HospitalYearUserDetail>().eq(HospitalYearUserDetail.YEAR_ID, idParam.getId())), hospitalYearUserParam);

        AnalyseYearLookOneVO analyseYearLookOneVO = new AnalyseYearLookOneVO();//创建VO返回对象
        analyseYearLookOneVO.setHospitalYearBaseParam(hospitalYearBaseParam);//设置基础模块
        analyseYearLookOneVO.setHospitalYearFunctionParam(hospitalYearFunctionParam);//设置功能模块
        analyseYearLookOneVO.setHospitalYearUserParam(hospitalYearUserParam);//设置使用者模块
        BeanUtils.copyProperties(hospitalYear, analyseYearLookOneVO);//添加操作人/部门/职位等信息

        return R.ok(analyseYearLookOneVO);
    }

    /**
     * 根据医院id查询这个医院的上报历史记录
     *
     * @param analyseParam 医院id
     * @return 返回医院年度报表历史记录
     */
    public R<Page<AnalyseYearHistoryVO>> yearHistory(AnalyseParam analyseParam) {
        //根据医院id获取这个医院每年的报表
        Page<AnalyseYearHistoryVO> page = new Page<>(analyseParam.getCurrent(), analyseParam.getSize());
        LoginUser loginUser = getLoginUser();
        analyseParam.setQualityId(loginUser.getQualityId());
        List<AnalyseYearHistoryVO> analyseYearHistoryVOList = analyseMapper.selectAnalyseYearHistory(page, analyseParam);

        page.setRecords(analyseYearHistoryVOList);
        return R.ok(page);
    }

}
