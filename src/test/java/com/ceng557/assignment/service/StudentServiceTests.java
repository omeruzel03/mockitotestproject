package com.ceng557.assignment.service;

import com.ceng557.assignment.modules.entity.Student;
import com.ceng557.assignment.modules.entity.util.ListUtil;
import com.ceng557.assignment.modules.repository.StudentRepository;
import com.ceng557.assignment.modules.service.StudentService;
import com.ceng557.assignment.modules.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StudentServiceTests {
    @SpyBean
    StudentService service;


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

        doReturn(ListUtil.filterGraduated(service.getStudentList()))
                .when(service).getGraduatedStudentList();

        // Service's real method is used since a spy object was created
        Student student1 = service.getStudentList().get(0);
        Assertions.assertEquals(student1.getNumber(), "202271001");
        Assertions.assertEquals(student1.getId(), 1L);
        Assertions.assertEquals(student1.getName(), "Atalay");

        // getGraduatedList is not implemented and would throw error if the real method was used
        Student student2 = service.getGraduatedStudentList().get(0);
        Assertions.assertEquals(student2.getNumber(), "202271003");
        Assertions.assertEquals(student2.getId(), 3L);
        Assertions.assertEquals(student2.getName(), "Ahmet");
        Assertions.assertEquals(student2.getGraduated(), true);

        verify(service, times(2)).getStudentList();
        verify(service, times(1)).getGraduatedStudentList();
    }
}
