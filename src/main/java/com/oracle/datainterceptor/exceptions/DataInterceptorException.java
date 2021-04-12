package com.oracle.datainterceptor.exceptions;

/**
 * 
 * @author velisela
 * This is a super class of all the exceptions in the scope of this application
 *
 */
public class DataInterceptorException extends Exception {
    private static final long serialVersionUID = 1L;
    protected String message;

    public DataInterceptorException(String message) {
        super(message);
    }

    public DataInterceptorException(Throwable cause) {
        super(cause == null ? null : cause.toString(), cause);
    }    

    public DataInterceptorException(String str,Throwable cause) {
        super(str,cause);
    }
}
