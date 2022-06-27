package com.acme.digobe.security.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserResource {
    private Long id;
    private String username;
    private String email;
    private String type;
    private String location;
    private String cellPhone;
    private String profile;
    private List<RoleResource> roles;
}