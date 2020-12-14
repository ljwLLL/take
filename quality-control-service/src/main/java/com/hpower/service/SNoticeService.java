package com.hpower.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.entity.QualityNotice;
import com.hpower.param.IdParam;
import com.hpower.param.NoticeAddParam;
import com.hpower.param.NoticeParam;
import com.hpower.vo.NoticeVo;
import com.hpower.vo.R;

/**
 * @Classname 新闻公告接口服务层
 * @Description TODO
 * @Date 2020/3/27 3:22 下午
 * @Created yangyang.jiang
 */
public interface SNoticeService {

    /**
     * 分页查询新闻公告信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Page<NoticeVo>> selectNoticeByPage(NoticeParam param);

    /**
     * 新增新闻公告
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> addNotice(NoticeAddParam param);

    /**
     * 根据id查询新闻公告信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<QualityNotice> selectNoticeById(IdParam param);

    /**
     * 修改新闻公告
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> updateNotice(NoticeAddParam param);

    /**
     * 删除新闻公告
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> deleteNotice(IdParam param);
}
