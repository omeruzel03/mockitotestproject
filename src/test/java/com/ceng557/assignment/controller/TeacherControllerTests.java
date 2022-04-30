package com.ceng557.assignment.controller;

import com.ceng557.assignment.modules.controller.TeacherController;
import com.ceng557.assignment.modules.entity.Teacher;
import com.ceng557.assignment.modules.service.TeacherService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebClient
@WebMvcTest(TeacherController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    private static Teacher teacher;

    @BeforeAll
    public static void setUp(){
        teacher = new Teacher(100L, "800100001", "Ahmet", "Cansoy", Boolean.TRUE);
    }

    @Test
    @Order(1)
    public void testLectureByCode() throws Exception {
        Mockito.when(teacherService.getTeacherByNumber(any())).thenReturn(teacher);

        mockMvc.perform(post("/api/teacher/1111")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.equalTo("OK")))
                .andExpect(jsonPath("$.messageCode", Matchers.equalTo("INFO")))
                .andExpect(jsonPath("$.message", Matchers.equalTo("Öğretmen başarıyla listelendi.")))
                .andExpect(jsonPath("$.data", Matchers.notNullValue()))
                .andExpect(jsonPath("$.data.id", Matchers.is(100)))
                .andExpect(jsonPath("$.data.number", Matchers.equalTo("800100001")))
                .andExpect(jsonPath("$.data.name", Matchers.equalTo("Ahmet")))
                .andExpect(jsonPath("$.data.surname", Matchers.equalTo("Cansoy")))
                .andExpect(jsonPath("$.data.active", Matchers.is(Boolean.TRUE)));
    }

    @Test
    @Order(2)
    public void testErrorLectureByCode() throws Exception {
        Mockito.when(teacherService.getTeacherByNumber(any()))
                .thenThrow(new GenericException.GenericExceptionBuilder(MessageCodeEnum.ERROR).message("Error!").build());

        mockMvc.perform(post("/api/teacher/2222")).andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", Matchers.equalTo("INTERNAL_SERVER_ERROR")))
                .andExpect(jsonPath("$.messageCode", Matchers.equalTo(MessageCodeEnum.ERROR.getValue())))
                .andExpect(jsonPath("$.message", Matchers.equalTo("Error!")))
                .andExpect(jsonPath("$.data", Matchers.nullValue()));
    }

}