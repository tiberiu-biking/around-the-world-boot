package com.tpo.world.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MARKERS")
@Getter
@Setter
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

    @Column(name = "userid")
    private Long userId;

    @Column
    private String externalId;

    @Column
    private Integer rating;
}
