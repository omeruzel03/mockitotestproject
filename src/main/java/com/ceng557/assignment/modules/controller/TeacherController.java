package com.ceng557.assignment.modules.controller;

import com.ceng557.assignment.constant.ApiConstant;
import com.ceng557.assignment.modules.entity.Teacher;
import com.ceng557.assignment.modules.service.TeacherService;
import com.ceng557.assignment.rest.GenericResponse;
import com.ceng557.assignment.rest.enums.MessageCodeEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = ApiConstant.API_URL + ApiConstant.TEACHER_URL)
public class TeacherController {

    private final TeacherService teacherService;

    @RequestMapping(value = "/{number}", method = RequestMethod.POST)
    public GenericResponse<Teacher> getTeacherByNumber(@PathVariable String number){
        Teacher result = teacherService.getTeacherByNumber(number);
        return new GenericResponse<>(HttpStatus.OK, MessageCodeEnum.INFO, "Öğretmen başarıyla listelendi.", result);
    }

}
