package com.ceng557.assignment.service;

import com.ceng557.assignment.modules.entity.Teacher;
import com.ceng557.assignment.modules.repository.TeacherRepository;
import com.ceng557.assignment.modules.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherServiceTests {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    private static Teacher teacher;

    @BeforeAll
    public static void setUp(){
        teacher = new Teacher(100L, "800100001", "Ahmet", "Cansoy", Boolean.TRUE);
    }

    @Test
    @Order(1)
    public void testTeacherByNumber(){
        Mockito.when(teacherRepository.findByNumber(any())).thenReturn(teacher);

        Teacher teacher = teacherService.getTeacherByNumber("1111");
        Assertions.assertNotNull(teacher);
        Assertions.assertEquals(100L, teacher.getId());
        Assertions.assertEquals("800100001", teacher.getNumber());
        Assertions.assertEquals("Ahmet", teacher.getName());
        Assertions.assertEquals("Cansoy", teacher.getSurname());
        Assertions.assertTrue(Boolean.TRUE);
    }

    @Test
    @Order(2)
    public void testExceptionTeacherByNumber(){
        Mockito.when(teacherRepository.findByNumber(any())).thenThrow(new RuntimeException());

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            teacherService.getTeacherByNumber("1111");
        });
        Assertions.assertTrue(exception instanceof RuntimeException);
    }

}
