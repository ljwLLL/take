package com.hpower.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.entity.QualityNotice;
import com.hpower.param.IdParam;
import com.hpower.param.NoticeAddParam;
import com.hpower.param.NoticeParam;
import com.hpower.service.SNoticeService;
import com.hpower.validator.groups.AddGroup;
import com.hpower.validator.groups.PageGroup;
import com.hpower.validator.groups.UpdateGroup;
import com.hpower.vo.NoticeVo;
import com.hpower.vo.R;
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

/**
 * @Classname 新闻公告控制层
 * @Description TODO
 * @Date 2020/3/27 3:18 下午
 * @Created yangyang.jiang
 */

@RestController
@RequestMapping("/api/notice")
@Api(value = "新闻公告控制层", description = "新闻公告控制层")
public class NoticeController {

    @Autowired
    private SNoticeService noticeService;

    @PostMapping("/selectNoticeByPage")
    @ApiOperation(value = "分页查询新闻公告信息", notes = "分页查询新闻公告信息")
    public R<Page<NoticeVo>> selectNoticeByPage(@Validated({PageGroup.class, Default.class}) @RequestBody NoticeParam param) {
        return noticeService.selectNoticeByPage(param);
    }

    @PostMapping("/addNotice")
    @ApiOperation(value = "新增新闻公告", notes = "新增新闻公告")
    public R<Object> addNotice(@Validated({AddGroup.class, Default.class}) @RequestBody NoticeAddParam param) {
        return noticeService.addNotice(param);
    }

    @PostMapping("/selectNoticeById")
    @ApiOperation(value = "根据id查询新闻公告信息", notes = "根据id查询新闻公告信息")
    public R<QualityNotice> selectNoticeById(@Valid @RequestBody IdParam param) {
        return noticeService.selectNoticeById(param);
    }

    @PostMapping("/updateNotice")
    @ApiOperation(value = "修改新闻公告", notes = "修改新闻公告")
    public R<Object> updateNotice(@Validated({UpdateGroup.class, Default.class}) @RequestBody NoticeAddParam param) {
        return noticeService.updateNotice(param);
    }

    @PostMapping("/deleteNotice")
    @ApiOperation(value = "删除新闻公告", notes = "删除新闻公告")
    public R<Object> deleteNotice(@Valid @RequestBody IdParam param) {
        return noticeService.deleteNotice(param);
    }
}
