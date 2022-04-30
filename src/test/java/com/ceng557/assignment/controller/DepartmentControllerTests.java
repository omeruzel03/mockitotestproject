package com.ceng557.assignment.controller;

import com.ceng557.assignment.modules.controller.DepartmentController;
import com.ceng557.assignment.modules.entity.Department;
import com.ceng557.assignment.modules.service.DepartmentService;
import com.ceng557.assignment.rest.GenericException;
import com.ceng557.assignment.rest.enums.MessageCodeEnum;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebClient
@WebMvcTest(DepartmentController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private static Department department;

    @BeforeAll
    public static void setUp(){
        department = new Department(100L, "Hukuk");
    }

    @Test
    @Order(1)
    public void testDepartmentList() throws Exception {
        Mockito.when(departmentService.getDepartmentList()).thenReturn(Collections.singletonList(department));

        mockMvc.perform(post("/api/department/list")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.equalTo("OK")))
                .andExpect(jsonPath("$.messageCode", Matchers.equalTo("INFO")))
                .andExpect(jsonPath("$.message", Matchers.equalTo("Departmanlar başarıyla listelendi.")))
                .andExpect(jsonPath("$.data", Matchers.notNullValue()))
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", Matchers.is(100)))
                .andExpect(jsonPath("$.data[0].name", Matchers.equalTo("Hukuk")));
    }

    @Test
    @Order(2)
    public void testWarningDepartmentList() throws Exception {
        Mockito.when(departmentService.getDepartmentList())
                .thenThrow(new GenericException.GenericExceptionBuilder(MessageCodeEnum.WARNING).message("Uyarı!").build());

        mockMvc.perform(post("/api/department/list")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.equalTo("OK")))
                .andExpect(jsonPath("$.messageCode", Matchers.equalTo(MessageCodeEnum.WARNING.getValue())))
                .andExpect(jsonPath("$.message", Matchers.equalTo("Uyarı!")))
                .andExpect(jsonPath("$.data", Matchers.nullValue()));
    }

}