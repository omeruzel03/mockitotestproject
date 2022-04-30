package com.ceng557.assignment.modules.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teacher_lecture", schema = "assignment")
public class TeacherLecture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_lecture_seq")
    @SequenceGenerator(sequenceName = "teacher_lecture_seq", allocationSize = 1, name = "teacher_lecture_seq")
    private Long id;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "lecture_id")
    private Long lectureId;

}
