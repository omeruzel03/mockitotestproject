package com.ceng557.assignment.controller;

import com.ceng557.assignment.constant.ApiConstant;
import com.ceng557.assignment.modules.controller.StudentController;
import com.ceng557.assignment.modules.entity.Student;
import com.ceng557.assignment.modules.service.StudentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebClient
@WebMvcTest(StudentController.class)
public class StudentControllerTests {
    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Test
    public void testStudent() throws Exception {
        // Data setup
        String STUDENT_NUMBER = "202271001";
        Student s = new Student(1L, STUDENT_NUMBER, "Atalay", "Aytekin", Boolean.FALSE);

        // Mock setup
        given(service.getByNumber(STUDENT_NUMBER)).willReturn(s);

        // Test
        mockMvc.perform(get(ApiConstant.API_URL + ApiConstant.STUDENT_URL + "/{number}", STUDENT_NUMBER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.equalTo("OK")))
                .andExpect(jsonPath("$.messageCode", Matchers.equalTo("INFO")))
                .andExpect(jsonPath("$.message", Matchers.equalTo("")))
                .andExpect(jsonPath("$.data", Matchers.notNullValue()))
                .andExpect(jsonPath("$.data.id", Matchers.is(1)))
                .andExpect(jsonPath("$.data.number", Matchers.equalTo(STUDENT_NUMBER)))
                .andExpect(jsonPath("$.data.name", Matchers.equalTo("Atalay")))
                .andExpect(jsonPath("$.data.surname", Matchers.equalTo("Aytekin")))
                .andExpect(jsonPath("$.data.graduated", Matchers.equalTo(Boolean.FALSE)));

        // Verify
        verify(service).getByNumber(STUDENT_NUMBER);
    }

    @Test
    public void testStudentNotExistsException() throws Exception {
        // Data setup
        String STUDENT_NUMBER = "202271999";

        // Mock setup
        given(service.getByNumber(STUDENT_NUMBER)).willReturn(null);

        // Test
        mockMvc.perform(get(ApiConstant.API_URL + ApiConstant.STUDENT_URL + "/{number}", STUDENT_NUMBER))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", Matchers.equalTo("Student not found")));


        // Verify
        verify(service).getByNumber(STUDENT_NUMBER);
    }

    @Test
    public void testStudentGetList() throws Exception {
        // Mock setup
        given(service.getStudentList()).willReturn(Arrays.asList(
                new Student(1L, "202271001", "Atalay", "Aytekin", Boolean.FALSE),
                new Student(2L, "202271002", "Filiz", "Olcay", Boolean.TRUE)
        ));

        // Test
        mockMvc.perform(get(ApiConstant.API_URL + ApiConstant.STUDENT_URL + "/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()", Matchers.equalTo(2)))
                .andExpect(jsonPath("$.data[0].name", Matchers.equalTo("Atalay")))
                .andExpect(jsonPath("$.data[0].surname", Matchers.equalTo("Aytekin")))
                .andExpect(jsonPath("$.data[1].name", Matchers.equalTo("Filiz")))
                .andExpect(jsonPath("$.data[1].surname", Matchers.equalTo("Olcay")));

        // Verify
        verify(service).getStudentList();
    }

    @Test
    public void testStudentGetListException() throws Exception {
        // Mock setup
        given(service.getStudentList()).willReturn(null);

        // Test
        mockMvc.perform(get(ApiConstant.API_URL + ApiConstant.STUDENT_URL + "/list"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", Matchers.equalTo("Student list does not exist")));

        // Verify
        verify(service).getStudentList();
    }
}
