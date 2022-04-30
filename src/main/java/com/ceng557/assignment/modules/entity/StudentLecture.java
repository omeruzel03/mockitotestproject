package com.ceng557.assignment.modules.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student_lecture", schema = "assignment")
public class StudentLecture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_lecture_seq")
    @SequenceGenerator(sequenceName = "student_lecture_seq", allocationSize = 1, name = "student_lecture_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name = "grade")
    private int grade;

    public StudentLecture(Student student, Lecture lecture) {
        this.student = student;
        this.lecture = lecture;
        this.grade = -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentLecture that = (StudentLecture) o;
        return student.equals(that.student) && lecture.equals(that.lecture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, lecture);
    }
}
