package com.acme.digobe.business.resource;

import com.acme.digobe.security.resource.UserResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class SportFieldResource {

    private Long id;

    private String name;

    private String img;

    private String address;

    private String description;

    private int price;

    private String sports;

    private UserResource userResource;
}
