package com.ceng557.assignment.rest;

import com.ceng557.assignment.rest.enums.MessageCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GenericResponse<T> {

    private final HttpStatus status;
    private final MessageCodeEnum messageCode;
    private final String message;
    private final T data;

}
