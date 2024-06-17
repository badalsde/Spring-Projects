package com.wecare.exception;

import java.io.Serial;

public class WeCareException extends Exception {

    @Serial
    private static final long serialVersionUID=1L;
    public WeCareException(){}

    public WeCareException(String message){
        super(message);
    }
}
