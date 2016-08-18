package com.tpo.world.model.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String aLastName) {
        lastName = aLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String aCountry) {
        country = aCountry;
    }

    public String getDropboxToken() {
        return dropboxToken;
    }

    public void setDropboxToken(String dropboxToken) {
        this.dropboxToken = dropboxToken;
    }

    public String getFoursquareToken() {
        return foursquareToken;
    }

    public void setFoursquareToken(String foursquareToken) {
        this.foursquareToken = foursquareToken;
    }

    public String getFoursquareId() {
        return foursquareId;
    }

    public void setFoursquareId(String aFoursquareId) {
        foursquareId = aFoursquareId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", getId())
                .append("firstName", firstName)
                .append("lastName", lastName)
                .toString();
    }
}
