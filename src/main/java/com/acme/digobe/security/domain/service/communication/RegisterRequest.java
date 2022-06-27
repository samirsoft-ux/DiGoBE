package com.acme.digobe.security.domain.service.communication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class RegisterRequest {
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String password2;

    @NotNull
    @NotBlank
    private String location;

    @NotNull
    @NotBlank
    private String cellPhone;

    @NotNull
    @NotBlank
    private String profile;


    private Set<String> roles;

}