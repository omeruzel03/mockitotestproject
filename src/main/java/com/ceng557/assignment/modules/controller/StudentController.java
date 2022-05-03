package com.ceng557.assignment.modules.controller;

import com.ceng557.assignment.constant.ApiConstant;
import com.ceng557.assignment.modules.entity.Student;
import com.ceng557.assignment.modules.service.StudentService;
import com.ceng557.assignment.rest.GenericException;
import com.ceng557.assignment.rest.GenericResponse;
import com.ceng557.assignment.rest.enums.MessageCodeEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ApiConstant.API_URL + ApiConstant.STUDENT_URL)
@AllArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping(value = "/{number}")
    public GenericResponse<Student> getStudent(@PathVariable String number) {
        Student student = service.getByNumber(number);
        if (student == null) {
            throw new GenericException
                    .GenericExceptionBuilder(MessageCodeEnum.ERROR)
                    .message("Student not found")
                    .build();
        }
        return new GenericResponse<>(HttpStatus.OK, MessageCodeEnum.INFO, "", student);
    }

    @GetMapping(value = "/list")
    public GenericResponse<List<Student>> getStudents() {
        List<Student> students = service.getStudentList();
        if (students == null) {
            throw new GenericException
                    .GenericExceptionBuilder(MessageCodeEnum.ERROR)
                    .message("Student list does not exist")
                    .build();
        }
        return new GenericResponse<>(HttpStatus.OK, MessageCodeEnum.INFO, "", students);
    }
}
