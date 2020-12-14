package com.hpower.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.dto.LoginUser;
import com.hpower.entity.QualityNotice;
import com.hpower.errorcode.ApiErrorCode;
import com.hpower.mapper.SNoticeMapper;
import com.hpower.param.IdParam;
import com.hpower.param.NoticeAddParam;
import com.hpower.param.NoticeParam;
import com.hpower.service.QualityNoticeService;
import com.hpower.service.SNoticeService;
import com.hpower.support.BaseSupport;
import com.hpower.vo.NoticeVo;
import com.hpower.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname 新闻公告接口服务实现层
 * @Description TODO
 * @Date 2020/3/27 3:25 下午
 * @Created yangyang.jiang
 */
@Service
@Slf4j
public class SNoticeServiceImpl extends BaseSupport implements SNoticeService {

    @Autowired
    private SNoticeMapper noticeMapper;

    @Autowired
    private QualityNoticeService noticeService;

    /**
     * 分页查询新闻公告信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<Page<NoticeVo>> selectNoticeByPage(NoticeParam param) {
        Page<NoticeVo> page = new Page<>(param.getCurrent(), param.getSize());
        List<NoticeVo> list = noticeMapper.selectNoticeByPage(page, param);
        page.setRecords(list);
        return R.ok(page);
    }


    /**
     * 新增新闻公告
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> addNotice(NoticeAddParam param) {
        LoginUser loginUser = getLoginUser();
        QualityNotice notice = new QualityNotice();
        BeanUtils.copyProperties(param, notice);
        notice.setFileId(param.getFileId());
        notice.setCreateTime(LocalDateTime.now());
        notice.setCreator(loginUser.getId());
        notice.setCreatorName(loginUser.getName());
        notice.setQualityId(loginUser.getQualityId());
        noticeService.save(notice);
        return R.success();
    }


    /**
     * 根据id查询新闻公告信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<QualityNotice> selectNoticeById(IdParam param) {
        QualityNotice notice = noticeService.getById(param.getId());
        if (notice == null) {
            return R.failed(ApiErrorCode.NOTICE_NOT_EXISTS);
        }
        return R.ok(notice);
    }


    /**
     * 修改新闻公告
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> updateNotice(NoticeAddParam param) {
        LoginUser loginUser = getLoginUser();
        QualityNotice notice = noticeService.getById(param.getId());
        if (notice == null) {
            return R.failed(ApiErrorCode.NOTICE_NOT_EXISTS);
        }
        BeanUtils.copyProperties(param, notice);
        notice.setUpdater(loginUser.getId());
        notice.setUpdaterName(loginUser.getName());
        notice.setUpdateTime(LocalDateTime.now());
        noticeService.updateById(notice);
        return R.success();
    }


    /**
     * 删除新闻公告
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> deleteNotice(IdParam param) {
        QualityNotice notice = noticeService.getById(param.getId());
        if (notice == null) {
            return R.failed(ApiErrorCode.NOTICE_NOT_EXISTS);
        }
        noticeService.removeById(param.getId());
        return R.success();
    }
}
