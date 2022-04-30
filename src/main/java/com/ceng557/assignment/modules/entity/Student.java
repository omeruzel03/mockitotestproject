package com.ceng557.assignment.modules.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "student", schema = "assignment")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(sequenceName = "student_seq", allocationSize = 1, name = "student_seq")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "graduated")
    private Boolean graduated;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<StudentLecture> studentLecture;

    public Student() {
    }

    public Student(String number, String name, String surname, Boolean graduated) {
        this.number = number;
        this.name = name;
        this.surname = surname;
        this.graduated = graduated;
    }

    public boolean isLectureRegistered(Lecture lecture) {
        return studentLecture != null && lecture.isStudentRegistered(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(number, student.number) && Objects.equals(name, student.name) && Objects.equals(surname, student.surname) && Objects.equals(graduated, student.graduated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, name, surname, graduated);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", graduated=" + graduated +
                '}';
    }
}
