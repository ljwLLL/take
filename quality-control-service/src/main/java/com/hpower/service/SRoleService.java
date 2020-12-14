package com.hpower.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.*;
import com.hpower.vo.*;

import java.util.List;

/**
 * @Classname 角色服务接口类
 * @Description TODO
 * @Date 2020/3/29 12:13 上午
 * @Created yangyang.jiang
 */
public interface SRoleService {

    /**
     * 分页查询角色信息
     *
     * @param param 查询入参对象
     * @return 返回结果信息
     */
    R<Page<RoleVo>> selectRoleListByPage(RoleParam param);


    /**
     * 新增角色信息
     *
     * @param param 新增角色入参对象
     * @return 返回结果信息
     */
    R<Object> addRole(RoleAddParam param);


    /**
     * 根据id查询角色信息
     *
     * @param idParam id入参对象
     * @return 返回角色对象信息
     */
    R<RoleOneVo> selectRoleById(IdParam idParam);

    /**
     * 修改角色信息
     *
     * @param param 修改角色入参对象
     * @return 返回结果信息
     */
    R<Object> updateRole(RoleAddParam param);

    /**
     * 删除角色信息
     *
     * @param param 删除角色信息
     * @return 返回结果信息
     */
    R<Object> deleteRole(IdParam param);


    /**
     * 启用禁用角色信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> enabled(EnabledParam param);

    /**
     * 角色分配菜单
     *
     * @param param 角色菜单入参对象
     * @return 返回结果信息
     */
    R<Object> authorizeMenu(RoleMenuParam param);

    /**
     * 用于分配角色信息上
     *
     * @return 返回所有权限的菜单
     */
    R<QualityRoleMenuVo> selectAllMenu(IdParam idParam);

}
