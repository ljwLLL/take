package com.hpower.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.constant.RedisConstant;
import com.hpower.dto.LoginUser;
import com.hpower.entity.*;
import com.hpower.enums.EnabledEnum;
import com.hpower.enums.PermissionEnum;
import com.hpower.enums.ReportStateEnum;
import com.hpower.enums.WhetherEnum;
import com.hpower.errorcode.ApiErrorCode;
import com.hpower.mapper.SReportMapper;
import com.hpower.param.*;
import com.hpower.service.*;
import com.hpower.support.BaseSupport;
import com.hpower.util.Convert;
import com.hpower.util.FileUtil;
import com.hpower.util.LocalDateTimeUtils;
import com.hpower.util.StringUtils;
import com.hpower.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname 公共服务实现类
 * @Description TODO
 * @Date 2020/3/26 5:28 下午
 * @Created yangyang.jiang
 */
@Service
@Slf4j
public class SCommonServiceImpl extends BaseSupport implements SCommonService {

    @Value("${file.filePath}")
    String uploadFile;

    @Autowired
    private SysFileService sysFileService;

    @Autowired
    private SysRegionService sysRegionService;

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @Autowired
    private SysDictDataService sysDictDataService;

    @Autowired
    private QualityRoleService roleService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalMonthService hospitalMonthService;

    @Autowired
    private HospitalYearService hospitalYearService;

    @Autowired
    private HospitalMonthProcessService hospitalMonthProcessService;

    @Autowired
    private HospitalYearProcessService hospitalYearProcessService;

    @Autowired
    private SReportMapper reportMapper;


    /**
     * 文件上传接口
     *
     * @param fileParam 文件类型
     * @return 返回上传结果
     */
    @Override
    @Transactional
    public R<List<SysFile>> uploadFile(String type, HttpServletRequest request) {
        LoginUser loginUser = getLoginUser();
        List<SysFile> fileList = new ArrayList<>();
        //获取上传文件路径
        String uploadPath = uploadFile + type + "/";
        //多文件上传
        CommonsMultipartResolver crm = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (crm.isMultipart(request)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
            Iterator<String> files = mRequest.getFileNames();
            while (files.hasNext()) {
                MultipartFile mFile = mRequest.getFile(files.next());
                // 文件名
                String fileName = mFile.getOriginalFilename();
                //新文件名
                String newFileName = StringUtils.getRandom18Str();
                // 文件扩展名
                String fileExtensionName = FileUtil.getExtensionName(fileName);
                File file = new File(uploadPath);
                //判断文件是否存在，不存在则创建
                if (!file.exists()) {
                    file.mkdirs();
                }
                //上传文件的真实路径
                String filePath = uploadPath + newFileName + "." + fileExtensionName;
                File localPath = new File(filePath);
                try {
                    mFile.transferTo(localPath);
                    //保存文件信息
                    SysFile sysFile = new SysFile()
                            .setUrl(filePath)
                            .setName(newFileName)
                            .setCreateTime(LocalDateTime.now())
                            .setCreator(loginUser.getId())
                            .setCreatorName(loginUser.getName())
                            .setType(type);
                    sysFileService.save(sysFile);
                    fileList.add(sysFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("文件上传失败：" + e.getMessage());
                    return R.failed(ApiErrorCode.FILE_FAILED);
                }
            }
        }
        return R.ok(fileList);
    }

    /**
     * 导出,一级菜单中的表格
     *
     * @param param 查询的条件
     * @return
     */
    @Override
    public R<Object> ExportMonth(HospitalMonthParam param) {

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
            Page<HospitalMonthVo> page = new Page<>(param.getCurrent(), param.getSize());
            list = reportMapper.selectHospitalMonthByPage(page, param);
        }
        ExportParams exportParams = new ExportParams();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, HospitalMonthVo.class, list);

        try {

            String path = ResourceUtils.getURL("classpath:").getPath() + uploadFile + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + ".xlsx";
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
            //关闭文件流,清除outstream的缓存
            fileOutputStream.flush();
            fileOutputStream.close();

            //文件名称,时间命名,年月日时分秒
            String fileName = param.getHospitalId() + ":" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + ".xlsx";

            HttpServletResponse response = getHttpServletResponse();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName).getBytes("GBK"), "ISO8859_1"));

            //File picFile = new File(path);
            File picFile = new File(path);
            if (!picFile.exists()) {
                //判断有没有文件,没有就创建
                picFile.mkdirs();
            }
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(picFile);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed("导出失败,无信息");
        }

    }

    /**
     * 年度查询信息,导出成表格,导出
     *
     * @param param 查询的条件
     * @return
     */
    @Override
    public R<Object> ExportYear(HospitalYearParam param) {
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

        ExportParams exportParams = new ExportParams();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, HospitalYearVo.class, list);

        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + uploadFile + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + ".xlsx";
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();

            //文件名称,时间命名,年月日时分秒
            String fileName = param.getHospitalId() + ":" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + ".xlsx";
            HttpServletResponse response = getHttpServletResponse();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName).getBytes("GBK"), "ISO8859_1"));

            File picFile = new File(path);
            if (!picFile.exists()) {
                //判断有没有文件,没有就创建
                picFile.mkdirs();
            }
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(picFile);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed("未知错误");
        }
    }


    /**
     * 根据父级id查询省市区信息
     *
     * @param regionParam 父级id
     * @return 返回结果信息
     */
    @Override
    public R<List<ResultSelectVo>> selectNextList(RegionParam regionParam) {
        //根据父级id查询省市区信息
        List<SysRegion> list = sysRegionService.list(
                new QueryWrapper<SysRegion>()
                        .eq(SysRegion.PARENT_ID, regionParam.getId())
                        .orderByAsc(SysRegion.ID)
        );
        //对象封装结果返回对象
        List<ResultSelectVo> resultSelectVos = new ArrayList<>();
        list.forEach(sysRegion -> {
            ResultSelectVo selectVo = new ResultSelectVo();
            selectVo.setId(sysRegion.getId().toString());
            selectVo.setName(sysRegion.getRegionName());
            resultSelectVos.add(selectVo);
        });
        return R.ok(resultSelectVos);
    }


    /**
     * 获取所有字典信息
     *
     * @return 返回字典数据集合
     */
    @Override
    public R<List<DictTypeVo>> selectDictDataList() {
        List<DictTypeVo> dictTypeVoList = new ArrayList<>();
        List<SysDictType> list = sysDictTypeService.list();
        for (SysDictType sysDictType : list) {
            DictTypeVo dictTypeVo = new DictTypeVo();
            dictTypeVo.setDictType(sysDictType.getCode());
            List<SysDictData> dictDataList = sysDictDataService.list(new QueryWrapper<SysDictData>().eq(SysDictData.DICT_TYPE_ID, sysDictType.getId()));
            List<DictDataVo> dictDataVoList = new ArrayList<>();
            for (SysDictData sysDictData : dictDataList) {
                DictDataVo dictDataVo = new DictDataVo();
                dictDataVo.setCode(sysDictData.getCode());
                dictDataVo.setName(sysDictData.getName());
                dictDataVoList.add(dictDataVo);
            }
            dictTypeVo.setDictDataList(dictDataVoList);
            dictTypeVoList.add(dictTypeVo);
        }
        return R.ok(dictTypeVoList);
    }


    /**
     * 根据类型和附件id下载文件信息
     *
     * @param downLoadFile 文件下载入参对象
     * @return 返回结果信息
     */
    @Override
    public R<Object> downLoadFile(DownLoadFileParam downLoadFile) {
        HttpServletResponse response = getHttpServletResponse();
        //根据id获取附件信息
        SysFile sysFile = sysFileService.getById(downLoadFile.getAttachId());
        if (sysFile == null) {
            return R.failed(ApiErrorCode.FILE_EMPTY);
        }
        // 文件扩展名
        String path = sysFile.getUrl();
        File picFile = new File(path);
        String fileName = picFile.getName();
        // 文件扩展名
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
            //判断下载方式是下载本地还是预览文件
            if (downLoadFile.getType().equals("1")) {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" + new String((StringUtils.isEmpty(downLoadFile.getFileName()) ? sysFile.getName() + "." + suffix : downLoadFile.getFileName() + "." + suffix).getBytes("GBK"), "ISO8859_1"));
            } else {
                URL u = new URL("file:///" + path);
                String contentType = u.openConnection().getContentType();
                response.setContentType(contentType);
                response.setHeader("Content-Disposition", "inline;filename=" + new String((StringUtils.isEmpty(downLoadFile.getFileName()) ? sysFile.getName() + "." + suffix : downLoadFile.getFileName() + "." + suffix).getBytes("GBK"), "ISO8859_1"));
            }
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(picFile);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("下载文件失败：" + e.getMessage());
            return R.failed(ApiErrorCode.FILE_DOWN_FAILED);
        }
    }


    /**
     * 根据质控中心id查询用户信息
     *
     * @return 返回医院下拉列表集合信息
     */
    @Override
    public R<List<ResultSelectVo>> selectHospitalList(HospitalListParam param) {
        //获取当前用户信息
        LoginUser loginUser = getLoginUser();
        List<Hospital> hospitalList = new ArrayList<>();
        List<ResultSelectVo> resultSelectVoList = new ArrayList<>();
        if (param.getType().equals(EnabledEnum.ENABLED.getValue())) {
            hospitalList = hospitalService.list(new QueryWrapper<Hospital>().eq(Hospital.QUALITY_ID, loginUser.getQualityId()).eq(Hospital.ENABLED, EnabledEnum.ENABLED.getValue()));
        } else {
            if (StringUtils.isNotNull(loginUser.getPermissionType())) {
                //根据质控中心查询医院信息
                if (loginUser.getPermissionType().equals(PermissionEnum.ALL.getValue())) {
                    hospitalList = hospitalService.list(new QueryWrapper<Hospital>().eq(Hospital.QUALITY_ID, loginUser.getQualityId()).eq(Hospital.ENABLED, WhetherEnum.YES.getValue()).orderByDesc(Hospital.CREATE_TIME));
                } else {
                    if (StringUtils.isNotEmpty(loginUser.getHospitalIds())) {
                        hospitalList = hospitalService.list(new QueryWrapper<Hospital>().in(Hospital.ID, Arrays.asList(loginUser.getHospitalIds().split(","))).eq(Hospital.ENABLED, WhetherEnum.YES.getValue()));
                    }
                }
            }
        }
        if (hospitalList.size() > 0) {
            //封装返回对象
            hospitalList.forEach(hospital -> {
                ResultSelectVo selectVo = new ResultSelectVo();
                selectVo.setId(hospital.getId().toString());
                selectVo.setName(hospital.getName());
                resultSelectVoList.add(selectVo);
            });
        }
        return R.ok(resultSelectVoList);
    }


    /**
     * 每月一号生成医院报表
     */
    @Override
    @Transactional
    public void createHospitalMonth() {
        //获取当前月份
        String month = LocalDateTimeUtils.formatTime(LocalDateTime.now(), "yyyyMM");
        List<Hospital> hospitalList = hospitalService.list();
        hospitalList.forEach(hospital -> {
            HospitalMonth hospitalMonth = new HospitalMonth()
                    .setMonth(month)
                    .setHospitalId(hospital.getId())
                    .setHospitalName(hospital.getName())
                    .setCreateTime(LocalDateTime.now())
                    .setState(ReportStateEnum.NO.getValue());
            hospitalMonthService.save(hospitalMonth);
            HospitalMonthProcess process = new HospitalMonthProcess()
                    .setCreateTime(LocalDateTime.now())
                    .setMonthId(hospitalMonth.getId())
                    .setState(ReportStateEnum.NO.getValue());
            hospitalMonthProcessService.save(process);

        });
    }


    /**
     * 每年一号生成医院报表
     */
    @Override
    @Transactional
    public void createHospitalYear() {
        //获取当前年份
        String year = LocalDateTimeUtils.formatTime(LocalDateTime.now(), "yyyy");
        List<Hospital> hospitalList = hospitalService.list();
        hospitalList.forEach(hospital -> {
            HospitalYear hospitalYear = new HospitalYear()
                    .setYear(year)
                    .setHospitalId(hospital.getId())
                    .setHospitalName(hospital.getName())
                    .setCreateTime(LocalDateTime.now())
                    .setState(ReportStateEnum.NO.getValue());
            hospitalYearService.save(hospitalYear);
            HospitalYearProcess process = new HospitalYearProcess()
                    .setCreateTime(LocalDateTime.now())
                    .setState(ReportStateEnum.NO.getValue())
                    .setYearId(hospitalYear.getId());
            hospitalYearProcessService.save(process);
        });
    }


    /**
     * 根据质控中心id查询角色信息
     *
     * @return
     */
    @Override
    public R<List<ResultSelectVo>> selectRoleList() {
        LoginUser loginUser = getLoginUser();
        List<ResultSelectVo> selectVos = new ArrayList<>();
        //查询所有角色信息
        List<QualityRole> list = roleService.list(new QueryWrapper<QualityRole>().eq(QualityRole.QUALITY_ID, loginUser.getQualityId()).eq(QualityRole.ENABLED, EnabledEnum.ENABLED.getValue()));
        if (list.size() > 0) {
            list.forEach(role -> {
                ResultSelectVo selectVo = new ResultSelectVo();
                selectVo.setId(role.getId().toString());
                selectVo.setName(role.getRoleName());
                selectVos.add(selectVo);
            });
        }
        return R.ok(selectVos);
    }
}
