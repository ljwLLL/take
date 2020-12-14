package com.hpower.errorcode;


/**
 * REST API 错误码
 *
 * @author yangyang.jiang
 * @date 2020/03/23
 * @since 1.0
 */
public enum ApiErrorCode implements IErrorCode {

    /**
     * 成功
     */
    SUCCESSFUL(CODE_SUCCESSFUL, "成功"),
    /**
     * 失败
     */
    FAILED(CODE_FAILED, "失败"),
    /**
     * 无效的请求参数
     */
    INVALID_PARAMETER(CODE_INVALID_PARAMETER, "无效的请求参数"),
    /**
     * 数据未授权
     */
    FORBIDDEN_DATA(CODE_FORBIDDEN_DATA, "数据未授权"),
    /**
     * 用户已存在
     */
    USER_EXISTENT(CODE_USER_EXISTENT, "用户已存在"),
    /**
     * 用户不存在
     */
    USER_NON_EXISTENT(CODE_USER_NON_EXISTENT, "用户不存在"),
    /**
     * 用户已停用
     */
    USER_DISABLED(CODE_FAILED, "用户已停用"),
    /**
     * 用户不存在或密码错误
     */
    USER_NON_EXISTENT_OR_INVALID_PASSWORD(CODE_FAILED, "用户不存在或密码错误"),
    /**
     * 用户不存在
     */
    USERISEMPTY(CODE_FAILED, "用户不存在"),
    /**
     * 用户未登录
     */
    UNAUTHORIZED(CODE_UNAUTHORIZED, "用户未登录"),
    /**
     * 用户未授权
     */
    FORBIDDEN(CODE_FORBIDDEN, "用户未授权"),

    /**
     * 文件上传失败
     */
    FILE_FAILED(CODE_FAILED, "文件上传失败"),

    /**
     * 暂无文件信息
     */
    FILE_EMPTY(CODE_FAILED, "暂无文件信息"),

    /**
     * 暂无数据信息
     */
    DATA_EMPTY(CODE_FAILED, "暂无数据信息"),

    /**
     * 文件下载失败
     */
    FILE_DOWN_FAILED(CODE_FAILED, "文件下载失败"),

    /**
     * 密码错误
     */
    INVALID_PASSWORD(CODE_FAILED, "密码错误"),

    /**
     * 角色不存在
     */
    ROLE_NOT_EXISTS(CODE_FAILED, "角色不存在"),

    /**
     * 角色已使用
     */
    ROLE_USER(CODE_FAILED, "角色已使用"),

    /**
     * 医院不存在
     */
    HOSPITAL_NOT_EXISTS(CODE_FAILED, "医院不存在"),

    /**
     * 存在业务数据，允许删除
     */
    HOSPITAL_DATA_EXISTS(CODE_FAILED, "存在业务数据，不允许删除"),

    /**
     * 月度报表不存在
     */
    MONTH_NOT_EXISTS(CODE_FAILED, "月度报表不存在"),

    /**
     * 月度报表存在
     */
    MONTH_IS_COMMIT(CODE_FAILED, "请勿重复提交"),

    /**
     * 驳回备注不能为空
     */
    REMARK_NOT_EXISTS(CODE_FAILED, "驳回备注不能为空"),

    /**
     * 报表已上传，不允许删除
     */
    MONTH_IS_FINISH(CODE_FAILED, "报表已上传，不允许删除"),

    /**
     * 报表已上传，不允许修改
     */
    MONTH_FINISH(CODE_FAILED, "报表已上传，不允许修改"),

    /**
     * 该医院当前月份报表已存在
     */
    MONTH_IS_EXISTS(CODE_FAILED, "该医院当前月份报表已存在"),

    /**
     * 该医院当前年份的报表已存在
     */
    YEAR_IS_EXISTS(CODE_FAILED,"该医院当前年份的报表已存在"),

    /**
     * 该医院当前年份报表已存在
     */
    YEAR_NOT_EXISTS(CODE_FAILED, "该医院当前年份报表已存在"),

    /**
     * 年度报表已存在
     */
    YEAR_IS_COMMIT(CODE_FAILED, "请勿重复提交"),

    /**
     * 新闻公告不存在
     */
    NOTICE_NOT_EXISTS(CODE_FAILED, "新闻公告不存在"),

    /**
     * 新闻公告不存在
     */
    HOSPITAL_REPORT_NOT_EXISTS(CODE_FAILED, "年度报表不存在"),
    ;

    private final int code;
    private final String msg;

    ApiErrorCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiErrorCode fromCode(int code) {
        ApiErrorCode[] apiErrorCodes = ApiErrorCode.values();
        for (ApiErrorCode apiErrorCode : apiErrorCodes) {
            if (apiErrorCode.getCode() == code) {
                return apiErrorCode;
            }
        }
        return SUCCESSFUL;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }
}
