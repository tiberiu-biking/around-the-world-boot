package com.tpo.world.domain.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
}
