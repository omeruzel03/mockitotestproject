package com.ceng557.assignment.rest.util;

import com.ceng557.assignment.rest.GenericException;
import com.ceng557.assignment.rest.enums.MessageCodeEnum;
import org.springframework.util.ObjectUtils;

public class ExceptionUtil {

    protected ExceptionUtil(){
        throw new UnsupportedOperationException();
    }

    public static void throwIfAny(String message, MessageCodeEnum messageCodeEnum){
        if(!ObjectUtils.isEmpty(message)){
            throw new GenericException.GenericExceptionBuilder(messageCodeEnum).message(message).build();
        }
    }

}
