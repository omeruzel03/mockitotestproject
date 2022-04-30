package com.ceng557.assignment.modules.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "student_lecture", schema = "assignment")
public class StudentLecture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_lecture_seq")
    @SequenceGenerator(sequenceName = "student_lecture_seq", allocationSize = 1, name = "student_lecture_seq")
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "lecture_id")
    private Long lectureId;

    @Column(name = "grade")
    private int grade;

}
