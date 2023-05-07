package com.example.autoschool.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
@Table(name="ProgramDescription")
@Getter
@Setter
public class ProgramDescription {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String program;

    @Column
    private int programTerm;
    @Column
    private int price;
}
