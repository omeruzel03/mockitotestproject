package com.ceng557.assignment.modules.service;

import com.ceng557.assignment.modules.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartmentList();
    Department getFirstDepartmentByNameContains(String name);

}
