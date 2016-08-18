package com.tpo.world.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PASSWORDS")
public class PasswordEntity extends IdEntity {

    @Column
    private Long userId;

    @Column
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long aUserId) {
        this.userId = aUserId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String aPassword) {
        this.password = aPassword;
    }
}
