package com.tpo.world.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PASSWORDS")
@Getter
@Setter
public class PasswordEntity extends IdEntity {

    @Column
    private Long userId;

    @Column
    private String password;
}
