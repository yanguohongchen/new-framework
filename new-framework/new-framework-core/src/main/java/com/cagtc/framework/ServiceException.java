package com.cagtc.framework;

import java.io.Serializable;

/**
 * 业务异常
 * 
 * @author sea
 *
 */
public class ServiceException extends BaseServiceException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ServiceException() {

    }

    public ServiceException(EnumVal errorCode) {
        super(errorCode);
    }


    public ServiceException(EnumVal errorCode, Throwable cause) {
        super(errorCode, cause);
    }



    public ServiceException(int code, String msg) {
        super(code, msg);
    }


    public ServiceException(int code, String msg, Throwable cause) {
        super(code, msg, cause);
    }



}
