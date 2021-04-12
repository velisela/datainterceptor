package com.oracle.datainterceptor.exceptions;

/**
 * 
 * @author velisela
 * This is a exception class for InvalidDataformat
 *
 */
public class IncorrectDataFormatException extends DataInterceptorException {

    private static final long serialVersionUID = 123456789L;

    public IncorrectDataFormatException() {
        super("The parsed data is of incorrect format, verify the input data");
    }
    public IncorrectDataFormatException(String message) {
        super(message);
    }

    public IncorrectDataFormatException(Throwable cause) {
        super(cause == null ? null : cause.toString(), cause);
    }    

    public IncorrectDataFormatException(String str,Throwable cause) {
        super(str,cause);
    }

}
