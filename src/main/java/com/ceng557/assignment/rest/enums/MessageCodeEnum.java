package com.ceng557.assignment.rest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageCodeEnum {

    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR");

    private String value;

}
