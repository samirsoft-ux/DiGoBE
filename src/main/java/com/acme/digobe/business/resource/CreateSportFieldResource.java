package com.acme.digobe.business.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateSportFieldResource {

    private String name;

    private String img;

    private String address;

    private String description;

    private int price;

    private String sports;

}
