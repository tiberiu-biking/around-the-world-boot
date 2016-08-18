package com.tpo.world.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MARKERS")
public class MarkerEntity extends IdEntity {

    @Column
    private BigDecimal latitude;

    @Column
    private BigDecimal longitude;

    @Column
    private String name;

    @Column
    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Column
    private String note;

    @Column
    private Long userId;

    @Column
    private String externalId;

    @Column
    private Integer rating;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long aUserId) {
        this.userId = aUserId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal aLongitude) {
        this.longitude = aLongitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal aLatitude) {
        this.latitude = aLatitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String aExternalId) {
        externalId = aExternalId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer aRating) {
        rating = aRating;
    }
}
