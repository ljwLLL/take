package com.hpower.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Joiner;
import com.hpower.constant.MessageQueueConstant;
import com.hpower.dto.LoginLogDto;
import com.hpower.dto.LoginUser;
import com.hpower.entity.*;
import com.hpower.enums.DataTypeEnum;
import com.hpower.enums.EnabledEnum;
import com.hpower.enums.PermissionEnum;
import com.hpower.enums.SexEnum;
import com.hpower.errorcode.ApiErrorCode;
import com.hpower.mapper.SMenuMapper;
import com.hpower.mapper.SUserMapper;
import com.hpower.param.*;
import com.hpower.service.*;
import com.hpower.support.BaseSupport;
import com.hpower.util.Convert;
import com.hpower.util.EncryptUtil;
import com.hpower.util.StringUtils;
import com.hpower.util.TreeUtil;
import com.hpower.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 用户服务实现类
 *
 * @author yangyang.jiang
 * @date 2020-03-23
 * @since 1.0
 */
@Service
@Slf4j
public class SUserServiceImpl extends BaseSupport implements SUserService {


    @Autowired
    private SUserMapper userMapper;

    @Autowired
    private SMenuMapper menuMapper;

    @Autowired
    private QualityRoleDataPermissionService roleDataPermissionService;

    @Autowired
    private QualityUserRoleService userRoleService;

    @Autowired
    private QualityUserService userService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private QualityControlService qualityControlService;


    /**
     * 用户登录接口
     *
     * @param loginParam 用户入参对象
     * @return token作为返回值
     */
    @Override
    public R<TokenVo> login(LoginParam loginParam) {
        //根据电话号码查询用户是否存在
        QualityUser user = userMapper.getUserByMobile(loginParam);
        //判断用户是否存在
        if (user == null) {
            return R.failed(ApiErrorCode.USER_NON_EXISTENT.getCode(), ApiErrorCode.USER_NON_EXISTENT.getMsg());
        }
        //判断用户是否被禁用
        if (user.getEnabled().equals(EnabledEnum.FORBIDDEN.getValue())) {
            return R.failed(ApiErrorCode.USER_DISABLED.getCode(), ApiErrorCode.USER_DISABLED.getMsg());
        }
        //判断密码是否正确
        String encode = EncryptUtil.encodeByMD5(loginParam.getPassword());
        if (!user.getPassword().equals(encode)) {
            return R.failed(ApiErrorCode.USER_NON_EXISTENT_OR_INVALID_PASSWORD.getCode(), ApiErrorCode.USER_NON_EXISTENT_OR_INVALID_PASSWORD.getMsg());
        }
        //判断用户是否有权限
        List<QualityUserRole> userRoleList = userRoleService.list(
                new QueryWrapper<QualityUserRole>()
                        .eq(QualityUserRole.USER_ID, user.getId())
        );
        if (userRoleList.size() == 0) {
            return R.failed(ApiErrorCode.FORBIDDEN.getCode(), ApiErrorCode.FORBIDDEN.getMsg());
        }
        //查询该用户的数据权限
        QualityRoleDataPermission roleDataPermission = roleDataPermissionService.getOne(
                new QueryWrapper<QualityRoleDataPermission>()
                        .eq(QualityRoleDataPermission.USER_ID, user.getId())
        );
        String hospitalIds = "";
        Integer permissionType = null;
        if (roleDataPermission != null) {
            if (roleDataPermission.getDataType().equals(DataTypeEnum.ALL.getValue())) {
                permissionType = PermissionEnum.ALL.getValue();
            } else if (roleDataPermission.getDataType().equals(DataTypeEnum.ONLY.getValue())) {
                hospitalIds = user.getHospitalId().toString();
                permissionType = PermissionEnum.PART.getValue();
            } else {
                hospitalIds = roleDataPermission.getHospitals();
                permissionType = PermissionEnum.PART.getValue();
            }
        }
        QualityControl qualityControl = qualityControlService.getById(user.getQualityId());
        //构建用户信息存储在redis中，每次可以直接获取用户信息
        LoginUser loginUser = LoginUser.builder()
                .id(user.getId())
                .age(user.getAge())
                .mobile(user.getMobile())
                .hospitalId(user.getHospitalId())
                .hospitalIds(hospitalIds)
                .sex(user.getSex().equals(SexEnum.FEMALE.getValue()) ? SexEnum.FEMALE.getLabel() : SexEnum.MALE.getLabel())
                .qualityId(user.getQualityId())
                .name(user.getName())
                .provinceId(qualityControl.getProvinceId())
                .provinceName(qualityControl.getProvinceName())
                .permissionType(permissionType).build();
        HttpSession httpSession = getHttpServletRequest().getSession();
        httpSession.setAttribute(httpSession.getId(), JSON.toJSONString(loginUser));
        //保存登录日志信息
        LoginLogDto loginLogDto = LoginLogDto.builder()
                .sessionId(httpSession.getId())
                .userId(loginUser.getId())
                .hospitalId(loginUser.getHospitalId())
                .idAddress(HttpUtil.getClientIP(getHttpServletRequest()))
                .userAgent(getHttpServletRequest().getHeader(HttpHeaders.USER_AGENT))
                .loginTime(LocalDateTime.now())
                .qualityId(loginUser.getQualityId())
                .type(LoginLogDto.LOGIN)
                .userName(loginUser.getName())
                .build();
        amqpTemplate.convertAndSend(MessageQueueConstant.LOGIN_LOG, loginLogDto);
        //返回sessionId作为token
        return R.ok(TokenVo.builder().token(httpSession.getId()).build());
    }


    /**
     * 获取当前用户信息
     *
     * @return 返回当前用户对象包含菜单信息
     */
    @Override
    public R<LoginUserVo> getLoginUserInfo() {
        LoginUser loginUser = getLoginUser();
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(loginUser, loginUserVo);
        //获取当前用户权限菜单信息
        List<UserMenuVo> userMenuVos = menuMapper.listByUser(loginUser.getId(), loginUser.getQualityId());
        loginUserVo.setUserMenuVoList(TreeUtil.toTree(userMenuVos));
        return R.ok(loginUserVo);
    }


    /**
     * 退出登录接口
     *
     * @return 返回响应信息
     */
    @Override
    public R<Object> loginOut() {
        HttpServletRequest request = getHttpServletRequest();
        //获取当前session对象
        HttpSession session = request.getSession();
        LoginUser loginUser = getLoginUser();
        //session注销
        session.invalidate();
        //记录退出日志信息
        return R.success();
    }

    /**
     * 分页查询用户集合信息
     *
     * @param param 用户查询入参对象
     * @return 返回用户视图对象，用page对象封装
     */
    @Override
    public R<Page<UserVo>> selectUserListByPage(UserQueryParam param) {
        LoginUser loginUser = getLoginUser();
        //page封装当前页和每页显示的条数
        Page<UserVo> page = new Page<>(param.getCurrent(), param.getSize());
        param.setQualityId(loginUser.getQualityId());

        List<UserVo> list = new ArrayList<>();

        //获取全部账号数量
        List<QualityUser> listAll = new ArrayList<>();


        if (StringUtils.isNotNull(loginUser.getPermissionType())) {
            //根据质控中心查询医院信息
            if (loginUser.getPermissionType().equals(PermissionEnum.ALL.getValue())) {
                param.setType(HospitalParam.TYPE_ALL);
                //全部数据权限的账户
                listAll = userService.list(new QueryWrapper<QualityUser>().eq(QualityUser.QUALITY_ID, loginUser.getQualityId()));
            } else {
                if (StringUtils.isNotEmpty(loginUser.getHospitalIds())) {
                    param.setType(HospitalParam.TYPE_ONLY);
                    param.setList(Arrays.asList(Convert.toLongArray(loginUser.getHospitalIds())));
                    //部分数据权限的账户
                    listAll = userService.list(
                            new QueryWrapper<QualityUser>().eq(QualityUser.QUALITY_ID, loginUser.getQualityId())
                            .in(QualityUser.HOSPITAL_ID, param.getList())
                    );
                }else{
                    //自己所在医院的所有账户
                    listAll = userService.list(
                        new QueryWrapper<QualityUser>().eq(QualityUser.QUALITY_ID, loginUser.getQualityId())
                            .eq(QualityUser.HOSPITAL_ID,loginUser.getHospitalId())
                    );
                }
            }
            list = userMapper.selectUserListByPage(page, param);
        }

        //获取账号已启用数量
        Integer enabledCount = (int) listAll.stream().filter(userInfo -> userInfo.getEnabled().equals(EnabledEnum.ENABLED.getValue())).count();
        //获取账号未启用数量
        Integer disabledCount = listAll.size() - enabledCount;

        PageVo<UserVo> pageVo = new PageVo<>();
        pageVo.setTotal(page.getTotal());
        pageVo.setSize(page.getSize());
        pageVo.setCurrent(page.getCurrent());
        pageVo.setCount(list.size());
        pageVo.setEnabledCount(enabledCount);
        pageVo.setDisabledCount(disabledCount);
        pageVo.setRecords(list);
        return R.ok(pageVo);
    }


    /**
     * 新增用户信息
     *
     * @param param 用户入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> addUser(UserAddParam param) {
        LoginUser loginUser = getLoginUser();
        //根据电话号码查询是否存在用户信息
        List<QualityUser> list = userService.list(new QueryWrapper<QualityUser>().eq(QualityUser.MOBILE, param.getMobile()));
        if (list.size() > 0) {
            //用户已存在返回错误信息
            return R.failed(ApiErrorCode.USER_EXISTENT);
        }
        //新增用户入库
        QualityUser user = new QualityUser();
        BeanUtils.copyProperties(param, user);
        //对密码进行加密处理
        String password = EncryptUtil.encodeByMD5(param.getPassword());
        user.setPassword(password);
        user.setCreateTime(LocalDateTime.now());
        user.setCreator(loginUser.getId());
        user.setCreatorName(loginUser.getName());
        user.setQualityId(loginUser.getQualityId());
        userService.save(user);
        QualityRoleDataPermission permission = new QualityRoleDataPermission();
        permission.setDataType(DataTypeEnum.ONLY.getValue());
        permission.setCreatorName(loginUser.getName());
        permission.setCreateTime(LocalDateTime.now());
        permission.setUserId(user.getId());
        permission.setCreator(loginUser.getId());
        roleDataPermissionService.save(permission);
        return R.success();
    }


    /**
     * 修改用户信息
     *
     * @param param 用户入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> updateUser(UserAddParam param) {
        LoginUser loginUser = getLoginUser();
        //根据电话号码查询是否存在用户信息
        List<QualityUser> list = userService.list(new QueryWrapper<QualityUser>().eq(QualityUser.MOBILE, param.getMobile()).notIn(QualityUser.ID, param.getId()));
        if (list.size() > 0) {
            //用户已存在返回错误信息
            return R.failed(ApiErrorCode.USER_EXISTENT);
        }
        //新增用户入库
        QualityUser user = new QualityUser()
                .setId(param.getId())
                .setHospitalId(param.getHospitalId())
                .setSex(param.getSex())
                .setMobile(param.getMobile())
                .setDepartment(param.getDepartment())
                .setPost(param.getPost())
                .setName(param.getName())
                .setUpdater(getLoginUser().getId())
                .setUpdaterName(loginUser.getName())
                .setUpdateTime(LocalDateTime.now());
        userService.updateById(user);
        return R.success();
    }

    /**
     * 根据id查询用户信息
     *
     * @param param 主键id
     * @return 返回查询用户信息
     */
    @Override
    public R<UserOneVo> getUserById(IdParam param) {
        QualityUser user = userService.getById(param.getId());
        if (user == null) {
            return R.failed(ApiErrorCode.USERISEMPTY);
        }
        UserOneVo userOneVo = new UserOneVo();
        BeanUtils.copyProperties(user, userOneVo);
        return R.ok(userOneVo);
    }


    /**
     * 删除用户信息
     *
     * @param param 主键id
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> deleteUser(IdParam param) {
        QualityUser user = userService.getById(param.getId());
        if (user == null) {
            return R.failed(ApiErrorCode.USERISEMPTY);
        }
        //删除用户对应的角色信息
        userRoleService.remove(new QueryWrapper<QualityUserRole>().eq(QualityUserRole.USER_ID, param.getId()));
        //删除用户
        userService.removeById(param.getId());
        return R.success();
    }

    /**
     * 启用禁用用户信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> enabled(EnabledParam param) {
        LoginUser loginUser = getLoginUser();
        QualityUser user = userService.getById(param.getId());
        if (user == null) {
            return R.failed(ApiErrorCode.USERISEMPTY);
        }
        user.setEnabled(param.getEnabled());
        user.setUpdater(loginUser.getId());
        user.setUpdaterName(loginUser.getName());
        user.setUpdateTime(LocalDateTime.now());
        userService.updateById(user);
        return R.success();
    }


    /**
     * 修改用户密码
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> updatePassword(ResetPasswordParam param) {
        LoginUser loginUser = getLoginUser();
        QualityUser user = userService.getById(param.getId());
        if (user == null) {
            return R.failed(ApiErrorCode.USERISEMPTY);
        }
        String oldPassword = EncryptUtil.encodeByMD5(param.getOldPassword());
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(EncryptUtil.encodeByMD5(param.getNewPassword()));
            user.setUpdater(loginUser.getId());
            user.setUpdateTime(LocalDateTime.now());
            user.setUpdaterName(loginUser.getName());
            userService.updateById(user);
            return R.success();
        }
        return R.failed(ApiErrorCode.INVALID_PASSWORD);
    }

    @Override
    @Transactional
    public R<Object> resetPassword(PasswordRestParam param) {
        LoginUser loginUser = getLoginUser();
        QualityUser user = userService.getById(param.getId());
        if (user == null){
            return R.failed((ApiErrorCode.USERISEMPTY));
        }
        user.setPassword(EncryptUtil.encodeByMD5(param.getNewPassword()));
        user.setUpdater(loginUser.getId());
        user.setUpdaterName(loginUser.getName());
        user.setUpdateTime(LocalDateTime.now());
        userService.updateById(user);
        return R.success();
    }


    /**
     * 给用户分配角色
     *
     * @param param 用户角色入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> addUserRole(UserRoleParam param) {
        //获取当前用户信息
        LoginUser loginUser = getLoginUser();
        QualityUser user = userService.getById(param.getUserId());
        if (user == null) {
            return R.failed(ApiErrorCode.USERISEMPTY);
        }
        //先删除用户对应的角色信息
        userRoleService.remove(new QueryWrapper<QualityUserRole>().eq(QualityUserRole.USER_ID, param.getUserId()));
        //将角色id串转换为long类型数组，循环插入用户角色信息表
        Long[] longs = Convert.toLongArray(param.getRoleIds());
        for (Long id : longs) {
            QualityUserRole userRole = new QualityUserRole()
                    .setRoleId(id)
                    .setUserId(param.getUserId())
                    .setCreateTime(LocalDateTime.now())
                    .setCreator(loginUser.getId())
                    .setCreatorName(loginUser.getName());
            userRoleService.save(userRole);
        }
        return R.success();
    }

    /**
     * 分配用户数据权限
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    @Transactional
    public R<Object> addAuthorization(AuthorizationParam param) {
        LoginUser loginUser = getLoginUser();
        QualityRoleDataPermission permission = new QualityRoleDataPermission();
        //先删除该用户之前的数据权限，再次新增用户数据权限
        roleDataPermissionService.remove(new QueryWrapper<QualityRoleDataPermission>().eq(QualityRoleDataPermission.USER_ID, param.getUserId()));
        permission.setHospitals(param.getHospitalIds());
        //修改:增加用户id
        permission.setUserId(param.getUserId());
        permission.setDataType(param.getType());
        permission.setCreateTime(LocalDateTime.now());
        permission.setCreator(loginUser.getId());
        permission.setCreatorName(loginUser.getName());
        roleDataPermissionService.save(permission);
        return R.success();
    }

    /**
     * 根据用户id查询用户数据权限
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<AuthorizationVo> selectAuthorization(IdParam param) {
        QualityRoleDataPermission serviceOne = roleDataPermissionService.getOne(new QueryWrapper<QualityRoleDataPermission>().eq(QualityRoleDataPermission.USER_ID, param.getId()));
        //修改原因:可能会遇上没有权限,就获取为空,会空指针
        if (serviceOne == null){
            return  R.ok(null);
        }
        AuthorizationVo authorizationVo = new AuthorizationVo();
        if (serviceOne != null) {
            authorizationVo.setType(serviceOne.getDataType());
            authorizationVo.setHospitalIds(serviceOne.getHospitals());
        }
        return R.ok(authorizationVo);
    }


    /**
     * 根据用户id查询用户角色信息
     *
     * @param param 入参对象
     * @return 返回结果信息
     */
    @Override
    public R<UserRoleVo> selectRoleById(IdParam param) {
        List<QualityUserRole> list = userRoleService.list(new QueryWrapper<QualityUserRole>().eq(QualityUserRole.USER_ID, param.getId()));
        UserRoleVo userRoleVo = new UserRoleVo();
        List<Long> longs = new ArrayList<>();
        if (list.size() > 0) {
            list.forEach(role -> {
                longs.add(role.getRoleId());
            });
        }
        userRoleVo.setRoleIds(longs.size() > 0 ? Joiner.on(",").join(longs) : "");
        return R.ok(userRoleVo);
    }

    /**
     * 根据角色id查询这个角色的所有菜单信息
     *
     * @param idParam 角色的id
     * @return
     */
    @Override
    public R<List<UserMenuVo>> selectOneMenu(IdParam idParam) {
        LoginUser loginUser = getLoginUser();
        //获取当前用户权限菜单信息
        List<UserMenuVo> userMenuVos = menuMapper.listByRole(idParam.getId(), loginUser.getQualityId());
        System.out.println(TreeUtil.toTree(userMenuVos));
        return R.ok(TreeUtil.toTree(userMenuVos));
    }
}
