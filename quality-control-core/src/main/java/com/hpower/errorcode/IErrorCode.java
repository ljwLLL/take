package com.hpower.errorcode;


/**
 * api错误码定义
 *
 * @author yangyang.jiang
 * @date 2020/03/23
 * @since 1.0
 */
public interface IErrorCode {
    /**
     * 成功
     */
    int CODE_SUCCESSFUL = 0;
    /**
     * 失败
     */
    int CODE_FAILED = 1;
    /**
     * 无效的请求参数
     */
    int CODE_INVALID_PARAMETER = 2;
    /**
     * 数据未授权
     */
    int CODE_FORBIDDEN_DATA = 9;
    /**
     * 用户已存在
     */
    int CODE_USER_EXISTENT = 10;
    /**
     * 用户不存在
     */
    int CODE_USER_NON_EXISTENT = 11;
    /**
     * 用户未登录
     */
    int CODE_UNAUTHORIZED = 401;
    /**
     * 用户未授权
     */
    int CODE_FORBIDDEN = 403;

    /**
     * 错误编码：0、成功 否则失败
     *
     * @return 错误码：0、成功 否则失败
     */
    int getCode();

    /**
     * 错误描述
     *
     * @return 错误描述
     */
    String getMsg();
}
