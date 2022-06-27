package com.acme.digobe.business.domain.persistence;

import com.acme.digobe.business.domain.model.entity.SportField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SportFieldRepository extends JpaRepository<SportField, Long> {

    List<SportField> findByUserId(Long userId);

    Page<SportField> findByUserId(Long userId, Pageable pageable);

    Optional<SportField> findByIdAndUserId(Long id, Long userId);

}
