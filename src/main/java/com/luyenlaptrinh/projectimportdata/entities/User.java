package com.luyenlaptrinh.projectimportdata.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROJECT_USER")
public class User {
    @Id
    @Column
    private String id;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "NAME")
    private String name;
}
