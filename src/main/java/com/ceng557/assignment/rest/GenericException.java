package com.ceng557.assignment.rest;

import com.ceng557.assignment.rest.enums.MessageCodeEnum;
import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

    private MessageCodeEnum messageCodeEnum;
    private String message;

    public GenericException(GenericExceptionBuilder genericExceptionBuilder){
        super();
        this.messageCodeEnum = genericExceptionBuilder.messageCodeEnum;
        this.message = genericExceptionBuilder.message;
    }

    public static class GenericExceptionBuilder {

        private MessageCodeEnum messageCodeEnum;
        private String message;

        public GenericExceptionBuilder(MessageCodeEnum messageCodeEnum){
            this.messageCodeEnum = messageCodeEnum;
        }

        public GenericExceptionBuilder messageCodeEnum(MessageCodeEnum messageCodeEnum){
            this.messageCodeEnum = messageCodeEnum;
            return this;
        }

        public GenericExceptionBuilder message(String message){
            this.message = message;
            return this;
        }

        public GenericException build(){
            GenericException genericException = new GenericException(this);
            return genericException;
        }

    }

}