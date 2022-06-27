package com.acme.digobe.business.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateReservationResource {

    private String title;

    private Date start;

    private Date end;

    private Long sportField_id;

}
