package com.ddd.common.infrastructure.exception;

import com.ddd.common.infrastructure.constant.ErrorCodeEnum;
import com.ddd.common.infrastructure.entity.ErrorInfo;

/**
 * 业务定义异常,使用运行时异常
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/09/27
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码信息
     */
    private ErrorInfo errorCode;


    public BusinessException(ErrorInfo errorInfo) {
        this.errorCode = errorInfo;
    }

    public BusinessException(String message) {
        super(message);
        this.errorCode = ErrorCodeEnum.ERROR.getErrorInfo();
    }

    public BusinessException(Throwable cause) {
        super(cause);
        this.errorCode = ErrorCodeEnum.ERROR.getErrorInfo();
    }

    public BusinessException(String message, ErrorInfo errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(Throwable cause, ErrorInfo errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ErrorInfo getErrorInfo() {
        return errorCode;
    }
}
