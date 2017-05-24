package com.plalab.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity //
@Table  //DDL
public class Member {
    @Id @Column
    private Integer id;
    @Column
    private String name;
}