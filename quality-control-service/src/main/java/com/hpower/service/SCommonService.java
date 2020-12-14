package com.hpower.service;

import com.hpower.entity.SysFile;
import com.hpower.param.*;
import com.hpower.vo.DictTypeVo;
import com.hpower.vo.R;
import com.hpower.vo.ResultSelectVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname 公共服务接口类
 * @Description TODO
 * @Date 2020/3/26 5:26 下午
 * @Created yangyang.jiang
 */
public interface SCommonService {


    /**
     * 导入报表,一级菜单中报表分析下月度的的表格
     *
     * @param param 查询的条件
     * @return
     */
    R<Object> ExportMonth(HospitalMonthParam param);

    /**
     * 下载,一级菜单中报表分析下年度的的表格
     *
     * @param param 查询的条件
     * @return
     */
    R<Object> ExportYear(HospitalYearParam param);

    /**
     * 文件上传接口
     *
     * @param fileParam 文件类型
     * @return 返回上传结果
     */
    R<List<SysFile>> uploadFile(String type, HttpServletRequest request);

    /**
     * 根据父级id查询省市区信息
     *
     * @param regionParam 父级id
     * @return 返回结果信息
     */
    R<List<ResultSelectVo>> selectNextList(RegionParam regionParam);

    /**
     * 获取所有字典信息
     *
     * @return 返回字典数据集合
     */
    R<List<DictTypeVo>> selectDictDataList();

    /**
     * 根据类型和附件id下载文件信息
     *
     * @param downLoadFile 文件下载入参对象
     * @return 返回结果信息
     */
    R<Object> downLoadFile(DownLoadFileParam downLoadFile);

    /**
     * 根据质控中心id查询用户信息
     *
     * @return 返回医院下拉列表集合信息
     */
    R<List<ResultSelectVo>> selectHospitalList(HospitalListParam param);

    /**
     * 每月一号生成医院报表
     */
    void createHospitalMonth();


    /**
     * 每年一号生成医院报表
     */
    void createHospitalYear();

    /**
     * 根据质控中心id查询角色信息
     *
     * @return
     */
    R<List<ResultSelectVo>> selectRoleList();
}
