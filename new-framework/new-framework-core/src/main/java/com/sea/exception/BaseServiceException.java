package com.sea.exception;

import java.io.Serializable;

/**
 * 业务异常
 * 
 * @author sea
 *
 */
public class BaseServiceException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BaseServiceException() {

    }

    public BaseServiceException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BaseServiceException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }


    public BaseServiceException(EnumVal errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    public BaseServiceException(EnumVal errorCode, Throwable cause) {
        super(errorCode.getMsg(), cause);
        this.code = errorCode.getCode();
    }

}
