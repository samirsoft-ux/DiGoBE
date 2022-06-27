package com.acme.digobe.business.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSportFieldResource {

    private String name;

    private String img;

    private String address;

    private String description;

    private int price;

    private String sports;

    private Long user_id;
}
