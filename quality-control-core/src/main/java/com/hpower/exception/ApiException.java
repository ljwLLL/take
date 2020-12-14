package com.hpower.exception;


import com.hpower.errorcode.IErrorCode;

/**
 * API异常
 *
 * @author yangyang.jiang
 * @date 2020/03/23
 * @since 1.0
 */
public class ApiException extends RuntimeException {

    /**
     * 错误码
     */
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
