package com.ceng557.assignment.rest;

import com.ceng557.assignment.rest.enums.MessageCodeEnum;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleExceptions(RuntimeException exception, WebRequest request) {
        if(exception instanceof GenericException){
            GenericException genericException = (GenericException) exception;
            if(genericException.getMessageCodeEnum().equals(MessageCodeEnum.ERROR.getValue())){
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, genericException.getMessageCodeEnum(), genericException.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK, genericException.getMessageCodeEnum(), genericException.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, MessageCodeEnum.ERROR, exception.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
