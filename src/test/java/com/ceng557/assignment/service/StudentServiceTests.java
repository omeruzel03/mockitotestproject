package com.ceng557.assignment.service;

import com.ceng557.assignment.modules.entity.Student;
import com.ceng557.assignment.modules.repository.StudentRepository;
import com.ceng557.assignment.modules.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {
    @Spy
    @InjectMocks
    public static StudentServiceImpl studentService;

    @Mock
    public static StudentRepository repository;

    @Test
    public void testSaveStudent() {
        // Data setup
        Student student = new Student();
        student.setNumber("202271001");
        student.setName("Atalay");
        student.setSurname("Aytekin");

        // Mock setup
        given(studentService.save("202271001", "Atalay", "Aytekin")).willReturn(student);

        Student savedStudent = studentService.save("202271001", "Atalay", "Aytekin");
        Assertions.assertEquals(savedStudent.getNumber(), "202271001");
        Assertions.assertEquals(savedStudent.getName(), "Atalay");
        Assertions.assertEquals(savedStudent.getSurname(), "Aytekin");

        verify(studentService).save("202271001", "Atalay", "Aytekin");
    }

    @Test
    public void testSaveStudent_WithUnformattedNumber_ThrowsException() {
        // Data setup
        Student student = new Student(1L, "000000", "Filiz", "Olcay", Boolean.FALSE);
        // Unsupported number format is set for the student
        student.setNumber("00000");

        // Mock setup
        doThrow(new IllegalArgumentException(
                String.format("%s is not a correct number format", student.getNumber())
        )).when(studentService).save(student);

        try {
            studentService.save(student);

            // Should not proceed after this and should jump to catch block
            Assertions.fail("Must throw exception");
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof IllegalArgumentException);
            Assertions.assertEquals(String.format("%s is not a correct number format", student.getNumber()), e.getMessage());
        }

        verify(studentService).save(student);
    }

    @Test
    public void testGetAllStudents_AsSpy() {
        // Data setup
        List<Student> students = Collections.singletonList(new Student(3L, "202271003", "Egemen", "Olcay", true));

        // Mock setup
        doReturn(students).when(repository).findAll();

        Student student = studentService.getStudentList().get(0);
        Assertions.assertEquals(student.getNumber(), "202271003");
        Assertions.assertEquals(student.getId(), 3L);
        Assertions.assertEquals(student.getName(), "Egemen");

        verify(repository, times(1)).findAll();

        // This method is run on real studentService object
        verify(studentService, times(1)).getStudentList();
    }
}
