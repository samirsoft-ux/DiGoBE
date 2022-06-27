package com.acme.digobe.business.domain.service;

import com.acme.digobe.business.domain.model.entity.SportField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SportFieldService {

    List<SportField> getAll();

    List<SportField> getAllByUserId(Long userId);

    Page<SportField> getAllByUserId(Long userId, Pageable pageable);

    //crud
    SportField create(Long userId, SportField sportField);

    SportField update(Long userId, Long sportFieldId, SportField request);

    ResponseEntity<?> delete(Long sportFieldId, Long userId);
}
