package com.hpower.mapper;

import com.hpower.vo.UserMenuVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname MenuMapper
 * @Description TODO
 * @Date 2020/3/26 4:23 下午
 * @Created yangyang.jiang
 */
@Component
public interface SMenuMapper {

    /**
     * 根据用户id查询用户菜单信息
     *
     * @param userId 用户id
     * @return 返回用户对应菜单信息
     */
    List<UserMenuVo> listByUser(@Param("userId") Long userId, @Param("qualityId") Long qualityId);

    /**
     * 查询所有角色菜单，用于分配菜单
     * @param qualityId 质控中心的id
     * @return
     */
    List<UserMenuVo> listAll(@Param("qualityId") Long qualityId);

    /**
     * 根据角色id查询这个角色的权限菜单
     * @param roleId
     * @param qualityId
     * @return
     */
    List<UserMenuVo> listByRole(@Param("roleId") Long roleId, @Param("qualityId") Long qualityId);
}
