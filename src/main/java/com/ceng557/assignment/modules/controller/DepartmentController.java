package com.ceng557.assignment.modules.controller;

import com.ceng557.assignment.constant.ApiConstant;
import com.ceng557.assignment.modules.entity.Department;
import com.ceng557.assignment.modules.service.DepartmentService;
import com.ceng557.assignment.rest.GenericResponse;
import com.ceng557.assignment.rest.enums.MessageCodeEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = ApiConstant.API_URL + ApiConstant.DEPARTMENT_URL)
public class DepartmentController {

    private final DepartmentService departmentService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public GenericResponse<List<Department>> getDepartmentList() {
        List<Department> list = departmentService.getDepartmentList();
        return new GenericResponse<>(HttpStatus.OK, MessageCodeEnum.INFO, "Departmanlar başarıyla listelendi.", list);
    }

}
