package com.hpower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Joiner;
import com.hpower.dto.LoginUser;
import com.hpower.entity.QualityMenu;
import com.hpower.entity.QualityRole;
import com.hpower.entity.QualityRoleMenu;
import com.hpower.entity.QualityUserRole;
import com.hpower.errorcode.ApiErrorCode;
import com.hpower.mapper.SMenuMapper;
import com.hpower.mapper.SRoleMapper;
import com.hpower.param.*;
import com.hpower.service.*;
import com.hpower.support.BaseSupport;
import com.hpower.util.Convert;
import com.hpower.util.TreeUtil;
import com.hpower.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname 角色接口服务实现类
 * @Description TODO
 * @Date 2020/3/29 12:13 上午
 * @Created yangyang.jiang
 */
@Service
@Slf4j
public class SRoleServiceImpl extends BaseSupport implements SRoleService {

    @Autowired
    private SRoleMapper roleMapper;

    @Autowired
    private QualityRoleService roleService;

    @Autowired
    private QualityUserRoleService userRoleService;

    @Autowired
    private QualityRoleMenuService roleMenuService;

    @Autowired
    private QualityMenuService qualityMenuService;

    @Autowired
    private SMenuMapper menuMapper;

    /**
     * 分页查询角色信息
     *
     * @param param 查询入参对象
     * @return 返回结果信息
     */
    @Override
    public R<Page<RoleVo>> selectRoleListByPage(RoleParam param) {
        Page<RoleVo> page = new Page<>(param.getCurrent(), param.getSize());
        List<RoleVo> list = roleMapper.selectRoleListByPage(page, param);
        page.setRecords(list);
        return R.ok(page);
    }

    /**
     * 新增角色信息
     *
     * @param param 新增用户入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> addRole(RoleAddParam param) {
        LoginUser loginUser = getLoginUser();
        //判断角色编码是否存在
        QualityRole qualityRole = roleService.getOne(new QueryWrapper<QualityRole>().eq(QualityRole.ROLE_CODE, param.getRoleCode()).eq(QualityRole.QUALITY_ID, loginUser.getQualityId()));
        if (qualityRole != null) {
            return R.failed(ApiErrorCode.ROLE_USER);
        }
        QualityRole role = new QualityRole()
                .setRoleCode(param.getRoleCode())
                .setRoleName(param.getRoleName())
                .setCreateTime(LocalDateTime.now())
                .setCreator(loginUser.getId())
                .setCreatorName(loginUser.getName())
                .setEnabled(param.getEnabled())
                .setQualityId(loginUser.getQualityId())
                .setRemark(param.getRemark());
        roleService.save(role);
        return R.success();
    }


    /**
     * 根据id查询角色信息
     *
     * @param idParam id入参对象
     * @return 返回角色对象信息
     */
    @Override
    public R<RoleOneVo> selectRoleById(IdParam idParam) {
        //根据id查询角色信息，判断角色是否存在
        QualityRole role = roleService.getById(idParam.getId());
        if (role == null) {
            return R.failed(ApiErrorCode.ROLE_NOT_EXISTS);
        }
        RoleOneVo roleOneVo = RoleOneVo.builder()
                .id(role.getId())
                .roleCode(role.getRoleCode())
                .roleName(role.getRoleName())
                .remark(role.getRemark())
                .build();
        return R.ok(roleOneVo);
    }


    /**
     * 修改角色信息
     *
     * @param param 修改角色入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> updateRole(RoleAddParam param) {
        LoginUser loginUser = getLoginUser();
        //根据id查询角色信息，判断角色是否存在
        QualityRole role = roleService.getById(param.getId());
        if (role == null) {
            return R.failed(ApiErrorCode.ROLE_NOT_EXISTS);
        }
        role.setUpdater(loginUser.getId());
        role.setUpdaterName(loginUser.getName());
        role.setUpdateTime(LocalDateTime.now());
        role.setRoleCode(param.getRoleCode());
        role.setRoleName(param.getRoleName());
        role.setRemark(param.getRemark());
        roleService.updateById(role);
        return R.success();
    }


    /**
     * 删除角色信息
     *
     * @param param 删除角色信息
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> deleteRole(IdParam param) {
        //根据id查询角色信息，判断角色是否存在
        QualityRole role = roleService.getById(param.getId());
        if (role == null) {
            return R.failed(ApiErrorCode.ROLE_NOT_EXISTS);
        }
        //判断该角色是否被用户使用
        List<QualityUserRole> list = userRoleService.list(new QueryWrapper<QualityUserRole>().eq(QualityUserRole.ROLE_ID, param.getId()));
        if (list.size() > 0) {
            return R.failed(ApiErrorCode.ROLE_USER);
        }
        //删除角色对应的菜单信息
        roleMenuService.remove(new QueryWrapper<QualityRoleMenu>().eq(QualityRoleMenu.ROLE_ID, param.getId()));
        //删除角色信息
        roleService.remove(new QueryWrapper<QualityRole>().eq(QualityRole.ID, param.getId()));
        return R.success();
    }


    /**
     * 启用禁用角色信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> enabled(EnabledParam param) {
        LoginUser loginUser = getLoginUser();
        //根据id查询角色信息，判断角色是否存在
        QualityRole role = roleService.getById(param.getId());
        if (role == null) {
            return R.failed(ApiErrorCode.ROLE_NOT_EXISTS);
        }
        role.setEnabled(param.getEnabled());
        role.setUpdater(loginUser.getId());
        role.setUpdateTime(LocalDateTime.now());
        role.setUpdaterName(loginUser.getName());
        roleService.updateById(role);
        return R.success();
    }


    /**
     * 角色分配菜单
     *
     * @param param 角色菜单入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> authorizeMenu(RoleMenuParam param) {
        LoginUser loginUser = getLoginUser();
        //根据id查询角色信息，判断角色是否存在
        QualityRole role = roleService.getById(param.getRoleId());
        if (role == null) {
            return R.failed(ApiErrorCode.ROLE_NOT_EXISTS);
        }
        //先删除角色对应的菜单信息
        roleMenuService.remove(new QueryWrapper<QualityRoleMenu>().eq(QualityRoleMenu.ROLE_ID, param.getRoleId()));
        Long[] longs = Convert.toLongArray(param.getMenuIds());
        for (Long menuId : longs) {
            QualityRoleMenu roleMenu = new QualityRoleMenu()
                    .setMenuId(menuId)
                    .setRoleId(param.getRoleId())
                    .setCreateTime(LocalDateTime.now())
                    .setCreator(loginUser.getId())
                    .setCreatorName(loginUser.getName());
            roleMenuService.save(roleMenu);
        }
        return R.success();
    }

    /**
     * 用于分配角色信息上
     *
     * @return 返回所有权限的菜单
     */
    @Override
    public R<QualityRoleMenuVo> selectAllMenu(IdParam idParam) {
        LoginUser loginUser = getLoginUser();
        QualityRoleMenuVo qualityRoleMenuVo = new QualityRoleMenuVo();
        List<RoleMenuVo> userMenuVos = new ArrayList<>();
        //获取所有权限菜单信息
        List<QualityMenu> qualityMenus = qualityMenuService.list(new QueryWrapper<QualityMenu>().eq(QualityMenu.QUALITY_ID, loginUser.getQualityId()));
        //根据角色id查询菜单信息
        List<QualityRoleMenu> list = roleMenuService.list(new QueryWrapper<QualityRoleMenu>().eq(QualityRoleMenu.ROLE_ID, idParam.getId()));
        List<Long> collect = list.stream().map(QualityRoleMenu::getMenuId).collect(Collectors.toList());
        for (QualityMenu qualityMenu : qualityMenus) {
            RoleMenuVo roleMenuVo = new RoleMenuVo();
            roleMenuVo.setId(qualityMenu.getId());
            roleMenuVo.setName(qualityMenu.getName());
            roleMenuVo.setParentId(qualityMenu.getParentId());
            userMenuVos.add(roleMenuVo);
        }
        qualityRoleMenuVo.setRoleMenuVos(TreeUtil.toTree(userMenuVos));
        qualityRoleMenuVo.setIds(Joiner.on(",").join(collect));
        return R.ok(qualityRoleMenuVo);
    }

}
