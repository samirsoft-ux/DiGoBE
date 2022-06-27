package com.acme.digobe.business.domain.persistence;

import com.acme.digobe.business.domain.model.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findBySportFieldId(Long sportFieldId);

    Page<Reservation> findBySportFieldId(Long sportFieldId, Pageable pageable);

    Optional<Reservation> findByIdAndSportFieldId(Long id, Long sportFieldId);

}
