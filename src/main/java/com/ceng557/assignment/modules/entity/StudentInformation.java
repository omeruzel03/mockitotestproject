package com.ceng557.assignment.modules.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "student_information", schema = "assignment")
public class StudentInformation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_information_seq")
    @SequenceGenerator(sequenceName = "student_information_seq", allocationSize = 1, name = "student_information_seq")
    private Long id;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "record_date")
    private Date recordDate;

}
