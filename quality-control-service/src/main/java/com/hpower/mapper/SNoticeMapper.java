package com.hpower.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.NoticeParam;
import com.hpower.vo.NoticeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname 新闻公告mapper类
 * @Description TODO
 * @Date 2020/4/3 4:53 下午
 * @Created yangyang.jiang
 */
@Component
public interface SNoticeMapper {

    /**
     * 分页查询新闻公告
     *
     * @param page  分页对象
     * @param param 查询参数对象
     * @return 返回结果信息
     */
    List<NoticeVo> selectNoticeByPage(Page<NoticeVo> page, @Param("param") NoticeParam param);
}
