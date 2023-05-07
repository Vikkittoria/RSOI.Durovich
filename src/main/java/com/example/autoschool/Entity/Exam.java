package com.example.autoschool.Entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
@Table(name="exam")
@Getter
@Setter
public class Exam {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String category;

    @Column
    private String exam;

    @Column
    private String date;

}
