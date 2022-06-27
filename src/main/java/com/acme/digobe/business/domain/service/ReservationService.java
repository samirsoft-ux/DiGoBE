package com.acme.digobe.business.domain.service;

import com.acme.digobe.business.domain.model.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAll();

    List<Reservation> getAllBySportFieldId(Long sportFieldId);

    Page<Reservation> getAllBySportFieldId(Long sportFieldId, Pageable pageable);

    //crud
    Reservation create(Long sportFieldId, Reservation reservation);

    Reservation update(Long sportFieldId, Long reservationId, Reservation request);

    ResponseEntity<?> delete(Long reservationId, Long sportFieldId);
}
