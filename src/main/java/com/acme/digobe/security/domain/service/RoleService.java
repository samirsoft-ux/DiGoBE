package com.acme.digobe.security.domain.service;

import com.acme.digobe.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {

    void seed();

    List<Role> getAll();
}