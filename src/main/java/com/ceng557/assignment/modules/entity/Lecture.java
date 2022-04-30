package com.ceng557.assignment.modules.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

}
