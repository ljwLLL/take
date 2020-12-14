package com.hpower.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.RoleParam;
import com.hpower.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname 角色mapper接口类
 * @Description TODO
 * @Date 2020/3/29 12:30 上午
 * @Created yangyang.jiang
 */
@Component
public interface SRoleMapper {

    /**
     * 分页查询角色信息
     *
     * @param page  分页对象
     * @param param 查询对象
     * @return 角色集合
     */
    List<RoleVo> selectRoleListByPage(Page<RoleVo> page, @Param("param") RoleParam param);
}
