package com.ceng557.assignment.service;

import com.ceng557.assignment.modules.entity.Student;
import com.ceng557.assignment.modules.repository.StudentRepository;
import com.ceng557.assignment.modules.service.StudentService;
import com.ceng557.assignment.modules.service.impl.StudentServiceImpl;
import com.ceng557.assignment.rest.util.ListUtil;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {
    @Test
    public void testSaveStudent() {
        // Data setup
        Student student = new Student();
        student.setNumber("202271001");
        student.setName("Atalay");
        student.setSurname("Aytekin");

        // Mock setup
        StudentRepository repository = mock(StudentRepository.class);
        StudentService studentService = new StudentServiceImpl(repository);
        given(repository.save(student)).willReturn(student);

        Student savedStudent = studentService.save(student);
        Assertions.assertEquals(savedStudent.getNumber(), "202271001");
        Assertions.assertEquals(savedStudent.getName(), "Atalay");
        Assertions.assertEquals(savedStudent.getSurname(), "Aytekin");

        verify(repository).save(student);
    }

    @Test
    public void testSaveStudent_WithUnformattedNumber_ThrowsException() {
        // Data setup
        Student student = new Student(1L, "000000", "Filiz", "Olcay", Boolean.FALSE);
        // Unsupported number format is set for the student
        student.setNumber("00000");

        // Mock setup
        StudentService studentService = mock(StudentService.class);
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
    public void testGetStudentByNumber() {
        String STUDENT_NUMBER = "202271003";
        Student student = new Student(1L, STUDENT_NUMBER, "Egemen", "Aytekin", true);

        StudentRepository repository = mock(StudentRepository.class);
        doReturn(student).when(repository).getStudentByNumber(STUDENT_NUMBER);

        StudentService studentService = new StudentServiceImpl(repository);
        Student studentResp = studentService.getByNumber(STUDENT_NUMBER);

        Assertions.assertEquals(studentResp.getId(), student.getId());
        Assertions.assertEquals(studentResp.getNumber(), student.getNumber());
        Assertions.assertEquals(studentResp.getName(), student.getName());
        Assertions.assertEquals(studentResp.getSurname(), student.getSurname());

        verify(repository, times(1)).getStudentByNumber(STUDENT_NUMBER);
    }

    @Test
    public void testGetAllStudents_AsSpy() {
        // Data setup
        List<Student> students = List.of(
                new Student(3L, "202271003", "Egemen", "Olcay", false),
                new Student(4L, "202271004", "Filiz", "Olcay", true)
        );

        // Mock setup
        StudentRepository repository = mock(StudentRepository.class);
        StudentService spyService = spy(new StudentServiceImpl(repository));

        doReturn(students).when(repository).findAll();
        doReturn(ListUtil.filterGraduated(repository.findAll()))
                .when(spyService).getGraduatedStudentList();

        // Service's real method is used since a spy object was created
        Student student1 = spyService.getStudentList().get(0);
        Assertions.assertEquals(student1.getNumber(), "202271003");
        Assertions.assertEquals(student1.getId(), 3L);
        Assertions.assertEquals(student1.getName(), "Egemen");

        // getGraduatedList is not implemented and would throw error if the real method was used
        Student student2 = spyService.getGraduatedStudentList().get(0);
        Assertions.assertEquals(student2.getNumber(), "202271004");
        Assertions.assertEquals(student2.getId(), 4L);
        Assertions.assertEquals(student2.getName(), "Filiz");
        Assertions.assertEquals(student2.getGraduated(), true);


        verify(repository, times(2)).findAll();
        verify(spyService, times(1)).getStudentList();
        verify(spyService, times(1)).getGraduatedStudentList();
    }
}
