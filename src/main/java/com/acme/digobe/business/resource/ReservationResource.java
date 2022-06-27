package com.acme.digobe.business.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResource {

    private Long id;

    private String title;

    private Date start;

    private Date end;

    private SportFieldResource sportFieldResource;
}
