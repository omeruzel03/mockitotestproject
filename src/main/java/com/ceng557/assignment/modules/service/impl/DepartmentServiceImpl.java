package com.ceng557.assignment.modules.service.impl;

import com.ceng557.assignment.modules.entity.Department;
import com.ceng557.assignment.modules.repository.DepartmentRepository;
import com.ceng557.assignment.modules.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getDepartmentList() {
        return (List<Department>) departmentRepository.findAll();
    }

}
