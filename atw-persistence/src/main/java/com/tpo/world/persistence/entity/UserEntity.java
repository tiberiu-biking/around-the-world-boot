package com.tpo.world.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class UserEntity extends IdEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String country;

    @Column
    private String dropboxToken;

    @Column
    private String foursquareToken;

    @Column
    private String foursquareId;
}
