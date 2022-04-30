package com.ceng557.assignment.modules.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department", schema = "assignment")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(sequenceName = "department_seq", allocationSize = 1, name = "department_seq")
    private Long id;

    @Column(name = "name")
    private String name;

}
