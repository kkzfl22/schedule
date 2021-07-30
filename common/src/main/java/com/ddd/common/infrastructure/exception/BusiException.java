package com.ddd.common.infrastructure.exception;

import com.ddd.common.infrastructure.constant.ErrorCodeEnum;

/**
 * 业务定义异常,使用运行时异常
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/09/27
 */
public class BusiException extends RuntimeException {

  /** 错误码信息 */
  private ErrorCodeEnum errorCode;

  public BusiException(ErrorCodeEnum errorCode) {
    this.errorCode = errorCode;
  }

  public BusiException(String message) {
    super(message);
    this.errorCode = ErrorCodeEnum.ERROR;
  }

  public BusiException(Throwable cause) {
    super(cause);
    this.errorCode = ErrorCodeEnum.ERROR;
  }

  public BusiException(String message, ErrorCodeEnum errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public BusiException(Throwable cause, ErrorCodeEnum errorCode) {
    super(cause);
    this.errorCode = errorCode;
  }

  public ErrorCodeEnum getErrorCode() {
    return errorCode;
  }
}
