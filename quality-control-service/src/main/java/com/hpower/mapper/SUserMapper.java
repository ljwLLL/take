package com.hpower.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.entity.QualityUser;
import com.hpower.param.LoginParam;
import com.hpower.param.UserQueryParam;
import com.hpower.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 用户mapper接口类
 *
 * @author yangyang.jiang
 * @date 2020-03-23
 * @since 1.0
 */
@Component
public interface SUserMapper {

    /**
     * 用户登录接口
     *
     * @param loginParam 用户登录入参对象
     * @return 返回用户信息
     */
    QualityUser getUserByMobile(@Param("user") LoginParam loginParam);

    /**
     * 分页查询用户信息
     *
     * @param page
     * @param param
     * @return
     */
    List<UserVo> selectUserListByPage(Page<UserVo> page, @Param("param") UserQueryParam param);
}
