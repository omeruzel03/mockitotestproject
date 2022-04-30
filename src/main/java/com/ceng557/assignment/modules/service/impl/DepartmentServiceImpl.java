package com.ceng557.assignment.modules.service.impl;

import com.ceng557.assignment.modules.entity.Department;
import com.ceng557.assignment.modules.repository.DepartmentRepository;
import com.ceng557.assignment.modules.service.DepartmentService;
import com.ceng557.assignment.rest.enums.MessageCodeEnum;
import com.ceng557.assignment.rest.util.ExceptionUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getDepartmentList() {
        return (List<Department>) departmentRepository.findAll();
    }

    @Override
    public Department getFirstDepartmentByNameContains(String name) {
        if(ObjectUtils.isEmpty(name)){
            ExceptionUtil.throwIfAny("Departman adı boş olamaz!", MessageCodeEnum.WARNING);
        }
        return departmentRepository.findFirstByNameContains(name);
    }

}
