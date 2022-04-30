package com.ceng557.assignment.modules.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lecture", schema = "assignment")
public class Lecture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecture_seq")
    @SequenceGenerator(sequenceName = "lecture_seq", allocationSize = 1, name = "lecture_seq")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "quota")
    private Integer quota;

    @Column(name = "pass_grade")
    private Integer passGrade;

    @OneToMany(mappedBy = "lecture")
    private Set<StudentLecture> studentLectures;

    @OneToMany(mappedBy = "teacher")
    private List<TeacherLecture> teacherLectures;

    public Lecture(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public boolean isStudentRegistered(Student student) {
        return studentLectures != null && studentLectures.contains(new StudentLecture(student, this));
    }


    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", quota=" + quota +
                ", passGrade=" + passGrade +
                '}';
    }
}
