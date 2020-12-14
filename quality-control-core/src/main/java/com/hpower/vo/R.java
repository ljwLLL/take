package com.hpower.vo;

import com.hpower.errorcode.ApiErrorCode;
import com.hpower.errorcode.IErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 处理结果类
 *
 * @param <T> 返回的数据类型
 * @author yangyang.jiang
 * @date 2020/03/23
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("处理结果类")
@Accessors
public class R<T> implements Serializable {
    @NonNull
    @ApiModelProperty(value = "结果码", example = "0")
    private int code = IErrorCode.CODE_SUCCESSFUL;
    @ApiModelProperty(value = "结果说明", example = "成功")
    private String msg;
    @ApiModelProperty(value = "业务数据")
    private T data;

    public R<T> code(int code) {
        this.code = code;
        return this;
    }

    public R<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

    public boolean ok() {
        return code == IErrorCode.CODE_SUCCESSFUL;
    }

    public static <T> R<T> failed(String msg) {
        return failed(ApiErrorCode.FAILED.getCode(), msg);
    }

    public static <T> R<T> failed(int code, String msg) {
        return failed(code, msg, null);
    }

    public static <T> R<T> failed(int code, String msg, T data) {
        return r(code == ApiErrorCode.SUCCESSFUL.getCode() ? ApiErrorCode.FAILED.getCode() : code, msg, data);
    }

    public static <T> R<T> failed(IErrorCode errorCode) {
        return r(errorCode, null);
    }

    public static <T> R<T> r(IErrorCode errorCode, T data) {
        return r(errorCode.getCode(), errorCode.getMsg(), data);
    }

    public static <T> R<T> r(int code, String msg, T data) {
        return new R<T>().code(code).msg(msg).data(data);
    }

    public static <T> R<T> unauthorized() {
        return failed(ApiErrorCode.UNAUTHORIZED);
    }

    public static <T> R<T> forbidden() {
        return failed(ApiErrorCode.FORBIDDEN);
    }

    public static <T> R<T> ok(T data) {
        return new R<T>().data(data);
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R<T>().msg(msg).data(data);
    }

    public static <T> R<T> success(){
        return new R<T>().msg(ApiErrorCode.SUCCESSFUL.getMsg()).code(ApiErrorCode.SUCCESSFUL.getCode());
    }
}
