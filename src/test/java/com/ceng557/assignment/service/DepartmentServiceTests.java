package com.ceng557.assignment.service;

import com.ceng557.assignment.modules.entity.Department;
import com.ceng557.assignment.modules.repository.DepartmentRepository;
import com.ceng557.assignment.modules.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentServiceTests {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private static Department department;

    @BeforeAll
    public static void setUp(){
        department = new Department(100L, "Hukuk");
    }

    @Test
    @Order(1)
    public void testDepartmentList(){
        Mockito.when(departmentRepository.findAll()).thenReturn(Collections.singletonList(department));

        List<Department> list = departmentService.getDepartmentList();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(100L, list.get(0).getId());
        Assertions.assertEquals("Hukuk", list.get(0).getName());
    }

    @Test
    @Order(2)
    public void testExceptionDepartmentList(){
        Mockito.when(departmentRepository.findAll()).thenThrow(new RuntimeException());

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            departmentService.getDepartmentList();
        });
        Assertions.assertTrue(exception instanceof RuntimeException);
    }

}
