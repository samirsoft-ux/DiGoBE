package com.acme.digobe.business.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date start;

    private Date end;

    //relacion con sportField

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sportField_id", nullable = false)
    @JsonIgnore
    private SportField sportField;
}
