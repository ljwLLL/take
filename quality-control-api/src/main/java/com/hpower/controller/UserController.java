package com.hpower.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.*;
import com.hpower.service.SUserService;
import com.hpower.support.BaseSupport;
import com.hpower.validator.groups.AddGroup;
import com.hpower.validator.groups.PageGroup;
import com.hpower.validator.groups.UpdateGroup;
import com.hpower.vo.*;
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
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname 用户信息控制层
 * @Description TODO
 * @Date 2020/3/26 5:21 下午
 * @Created yangyang.jiang
 */
@RestController
@RequestMapping("/api/user")
@Api(value = "用户信息控制层", description = "用户信息控制层")
public class UserController extends BaseSupport {

    @Autowired
    private SUserService userService;

    @PostMapping("/selectUserListByPage")
    @ApiOperation(value = "分页查询用户集合信息", notes = "分页查询用户集合信息")
    public R<Page<UserVo>> selectUserListByPage(@Validated({PageGroup.class, Default.class}) @RequestBody UserQueryParam param) {
        return userService.selectUserListByPage(param);
    }


    @PostMapping("/addUser")
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息")
    public R<Object> addUser(@Validated({AddGroup.class, Default.class}) @RequestBody UserAddParam param) {
        return userService.addUser(param);
    }

    @PostMapping("/getUserById")
    @ApiOperation(value = "根据id查询用户信息", notes = "根据id查询用户信息")
    public R<UserOneVo> getUserById(@Valid @RequestBody IdParam param) {
        return userService.getUserById(param);
    }

    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public R<Object> updateUser(@Validated({UpdateGroup.class, Default.class}) @RequestBody UserAddParam param) {
        return userService.updateUser(param);
    }

    @PostMapping("/deleteUser")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息")
    public R<Object> deleteUser(@Valid @RequestBody IdParam param) {
        return userService.deleteUser(param);
    }

    @PostMapping("/enabled")
    @ApiOperation(value = "启用禁用用户", notes = "启用禁用用户")
    public R<Object> enabled(@Valid @RequestBody EnabledParam param) {
        return userService.enabled(param);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public R<Object> updatePassword(@Valid @RequestBody ResetPasswordParam param) {
        return userService.updatePassword(param);
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "重置密码" , notes = "重置密码")
    public R<Object> resetPassword(@Valid @RequestBody PasswordRestParam param){
        return userService.resetPassword(param);
    }

    @PostMapping("/selectRoleById")
    @ApiOperation(value = "根据用户id查询用户角色信息", notes = "根据用户id查询用户角色信息")
    public R<UserRoleVo> selectRoleById(@Valid @RequestBody IdParam param) {
        return userService.selectRoleById(param);
    }

    @PostMapping("/addUserRole")
    @ApiOperation(value = "分配用户角色", notes = "分配用户角色")
    public R<Object> addUserRole(@Valid @RequestBody UserRoleParam param) {
        return userService.addUserRole(param);
    }

    @PostMapping("/selectAuthorization")
    @ApiOperation(value = "根据用户id查询用户数据权限", notes = "根据用户id查询用户数据权限")
    public R<AuthorizationVo> selectAuthorization(@Valid @RequestBody IdParam param) {
        return userService.selectAuthorization(param);
    }

    @PostMapping("/addAuthorization")
    @ApiOperation(value = "分配用户数据权限", notes = "分配用户数据权限")
    public R<Object> addAuthorization(@Valid @RequestBody AuthorizationParam param) {
        return userService.addAuthorization(param);
    }

    @PostMapping(value = "/selectOneMenu")
    @ApiOperation(value = "根据id查询这个角色所拥有的菜单",notes = "根据id查询这个角色所拥有的菜单")
    public R<List<UserMenuVo>> selectAllMenu(@Valid @RequestBody IdParam idParam){
        return userService.selectOneMenu(idParam);
    }
}
