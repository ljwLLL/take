package com.hpower.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.*;
import com.hpower.service.SRoleService;
import com.hpower.validator.groups.AddGroup;
import com.hpower.validator.groups.PageGroup;
import com.hpower.validator.groups.UpdateGroup;
import com.hpower.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.List;

/**
 * @Classname 角色控制器
 * @Description TODO
 * @Date 2020/3/29 12:12 上午
 * @Created yangyang.jiang
 */
@RestController
@RequestMapping("/api/role")
@Api(value = "角色控制器", description = "角色控制器")
public class RoleController {

    @Autowired
    private SRoleService roleService;

    @PostMapping("/selectRoleListByPage")
    @ApiOperation(value = "分页查询角色集合信息", notes = "分页查询角色集合信息")
    public R<Page<RoleVo>> selectRoleListByPage(@Validated({PageGroup.class, Default.class}) @RequestBody RoleParam param) {
        return roleService.selectRoleListByPage(param);
    }


    @PostMapping("/addRole")
    @ApiOperation(value = "新增角色信息", notes = "新增角色信息")
    public R<Object> addRole(@Validated({AddGroup.class, Default.class}) @RequestBody RoleAddParam param) {
        return roleService.addRole(param);
    }


    @PostMapping("/selectRoleById")
    @ApiOperation(value = "根据id查询角色信息", notes = "根据id查询角色信息")
    public R<RoleOneVo> selectRoleById(@Valid @RequestBody IdParam idParam) {
        return roleService.selectRoleById(idParam);
    }

    @PostMapping("/updateRole")
    @ApiOperation(value = "修改角色信息", notes = "修改角色信息")
    public R<Object> updateRole(@Validated({UpdateGroup.class, Default.class}) @RequestBody RoleAddParam param) {
        return roleService.updateRole(param);
    }

    @PostMapping("/deleteRole")
    @ApiOperation(value = "删除角色信息", notes = "删除角色信息")
    public R<Object> deleteRole(@Valid @RequestBody IdParam param) {
        return roleService.deleteRole(param);
    }

    @PostMapping("/enabled")
    @ApiOperation(value = "启用禁用角色", notes = "启用禁用角色")
    public R<Object> enabled(@Valid @RequestBody EnabledParam param) {
        return roleService.enabled(param);
    }


    @PostMapping("/authorizeMenu")
    @ApiOperation(value = "角色分配菜单", notes = "角色分配菜单")
    public R<Object> authorizeMenu(@Valid @RequestBody RoleMenuParam param) {
        return roleService.authorizeMenu(param);
    }


    @PostMapping(value = "/selectAllMenu")
    @ApiOperation(value = "根据质控中心的id去获取所有的菜单，用于分配菜单", notes = "根据质控中心的id去获取所有的菜单，用于分配菜单")
    public R<QualityRoleMenuVo> selectAllMenu(@Valid @RequestBody IdParam idParam) {
        return roleService.selectAllMenu(idParam);
    }
}
