package com.ceng557.assignment.modules.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teacher", schema = "assignment")
public class Teacher {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    @SequenceGenerator(sequenceName = "teacher_seq", allocationSize = 1, name = "teacher_seq")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "lecture")
    private List<TeacherLecture> teacherLectures;

    public Teacher(String number, String name, String surname) {
        this.number = number;
        this.name = name;
        this.surname = surname;
        this.active = Boolean.TRUE;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", active=" + active + "}";
    }
}
