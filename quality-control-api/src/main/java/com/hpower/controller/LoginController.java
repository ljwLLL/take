package com.hpower.controller;

import com.hpower.param.LoginParam;
import com.hpower.service.SUserService;
import com.hpower.vo.LoginUserVo;
import com.hpower.vo.R;
import com.hpower.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Classname 登录控制器
 * @Description TODO
 * @Date 2020/3/27 3:41 下午
 * @Created yangyang.jiang
 */
@RestController
@RequestMapping("/api")
@Api(value = "登录控制器", description = "登录控制器")
public class LoginController {

    @Autowired
    private SUserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录信息")
    public R<TokenVo> login(@Valid @RequestBody LoginParam loginParam) {
        return userService.login(loginParam);
    }


    @PostMapping("/getLoginUser")
    @ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息")
    public R<LoginUserVo> getLoginUserInfo() {
        return userService.getLoginUserInfo();
    }

    @PostMapping("/loginOut")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public R<Object> loginOut() {
        return userService.loginOut();
    }
}
