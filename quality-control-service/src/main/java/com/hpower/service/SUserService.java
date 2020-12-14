package com.hpower.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hpower.param.*;
import com.hpower.vo.*;

import java.util.List;


/**
 * 用户service接口类
 *
 * @author yangyang.jiang
 * @date 2020-03-23
 * @since 1.0
 */
public interface SUserService {


    /**
     * 用户登录接口
     *
     * @param loginParam 用户入参对象
     * @return token作为返回值
     */
    R<TokenVo> login(LoginParam loginParam);

    /**
     * 获取当前用户信息
     *
     * @return 返回当前用户对象包含菜单信息
     */
    R<LoginUserVo> getLoginUserInfo();


    /**
     * 退出登录接口
     *
     * @return 返回响应信息
     */
    R<Object> loginOut();

    /**
     * 分页查询用户集合信息
     *
     * @param param 用户查询入参对象
     * @return 返回用户视图对象，用page对象封装
     */
    R<Page<UserVo>> selectUserListByPage(UserQueryParam param);

    /**
     * 新增用户信息
     *
     * @param param 用户入参对象
     * @return 返回结果信息
     */
    R<Object> addUser(UserAddParam param);

    /**
     * 修改用户信息
     *
     * @param param 用户入参对象
     * @return 返回结果信息
     */
    R<Object> updateUser(UserAddParam param);


    /**
     * 根据id查询用户信息
     *
     * @param param 主键id
     * @return 返回查询用户信息
     */
    R<UserOneVo> getUserById(IdParam param);


    /**
     * 删除用户信息
     *
     * @param param 主键id
     * @return 返回结果信息
     */
    R<Object> deleteUser(IdParam param);

    /**
     * 启用禁用用户信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> enabled(EnabledParam param);


    /**
     * 修改用户密码
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> updatePassword(ResetPasswordParam param);

    /**
     * 用于管理员定义用户密码
     * @param param
     * @return
     */
    R<Object> resetPassword(PasswordRestParam param);
    /**
     * 给用户分配角色
     *
     * @param param 用户角色入参对象
     * @return 返回结果信息
     */
    R<Object> addUserRole(UserRoleParam param);

    /**
     * 分配用户数据权限
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<Object> addAuthorization(AuthorizationParam param);

    /**
     * 根据用户id查询用户数据权限
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<AuthorizationVo> selectAuthorization(IdParam param);

    /**
     * 根据用户id查询用户角色信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    R<UserRoleVo> selectRoleById(IdParam param);

    /**
     * 根据用户id查询这个用户的所有菜单信息
     * @param idParam
     * @return
     */
    R<List<UserMenuVo>> selectOneMenu(IdParam idParam);
}
